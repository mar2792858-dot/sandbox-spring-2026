package org.example.sandbox.trees.binarysearchtrees;

import org.example.sandbox.trees.ElementNotFoundException;
import org.example.sandbox.trees.Tree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    private BinarySearchTree<Integer> bst;

    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree<>();
    }

    // ─────────────────────────────────────────────
    // Helper: collect non-null values from an iterator
    // ─────────────────────────────────────────────
    private List<Integer> collectNonNull(Iterator<Integer> it) {
        List<Integer> result = new ArrayList<>();
        while (it.hasNext()) {
            Integer val = it.next();
            if (val != null) result.add(val);
        }
        return result;
    }

    // ─────────────────────────────────────────────
    // Empty tree
    // ─────────────────────────────────────────────

    @Test
    @DisplayName("New tree: isEmpty() returns true")
    void testNewTreeIsEmpty() {
        assertTrue(bst.isEmpty(), "A newly created BST should be empty");
    }

    @Test
    @DisplayName("New tree: size() returns 0")
    void testNewTreeSizeIsZero() {
        assertEquals(0, bst.size(), "A newly created BST should have size 0");
    }

    @Test
    @DisplayName("New tree: contains() returns false for any element")
    void testEmptyTreeContainsFalse() {
        assertFalse(bst.contains(42));
    }

    @Test
    @DisplayName("New tree: search() throws ElementNotFoundException")
    void testSearchOnEmptyTreeThrows() {
        assertThrows(ElementNotFoundException.class, () -> bst.search(42));
    }

    @Test
    @DisplayName("New tree: in-order traversal yields no non-null elements")
    void testEmptyTreeInOrderIsEmpty() {
        List<Integer> result = collectNonNull(bst.iteratorInOrder());
        assertTrue(result.isEmpty(), "In-order traversal of empty tree should yield no elements");
    }

    // ─────────────────────────────────────────────
    // Insert
    // ─────────────────────────────────────────────

    @Test
    @DisplayName("insert() returns the inserted element")
    void testInsertReturnsElement() {
        Integer returned = bst.insert(10);
        assertEquals(10, returned, "insert() should return the element that was inserted");
    }

    @Test
    @DisplayName("insert() single element: contains() returns true")
    void testInsertSingleElement() {
        bst.insert(10);
        assertTrue(bst.contains(10));
    }

    @Test
    @DisplayName("insert() multiple elements: all are present")
    void testInsertMultipleElements() {
        int[] values = {10, 8, 11, 3, 6, 1, 7, 4, 12, 2, 5, 9};
        for (int v : values) bst.insert(v);

        for (int v : values) {
            assertTrue(bst.contains(v), "BST should contain " + v + " after insertion");
        }
    }

    @Test
    @DisplayName("insert() duplicate value: duplicate is still reachable")
    void testInsertDuplicate() {
        bst.insert(10);
        bst.insert(10); // duplicate goes to right subtree (compareTo >= 0 branch)
        assertTrue(bst.contains(10), "BST should still contain 10 after inserting a duplicate");
    }

    // ─────────────────────────────────────────────
    // Search
    // ─────────────────────────────────────────────

    // Note: when first creating this code, test failed, after updating the
    // BinarySearchTree findNode, test passed - not performance test though
    @Test
    @DisplayName("search() returns the correct element when found")
    void testSearchExistingElement() throws ElementNotFoundException {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);

        assertEquals(5, bst.search(5));
        assertEquals(10, bst.search(10));
        assertEquals(15, bst.search(15));
    }

    @Test
    @DisplayName("search() throws ElementNotFoundException when element is missing")
    void testSearchMissingElementThrows() {
        bst.insert(10);
        bst.insert(5);
        assertThrows(ElementNotFoundException.class, () -> bst.search(99));
    }

    // ─────────────────────────────────────────────
    // Contains
    // ─────────────────────────────────────────────

    @Test
    @DisplayName("contains() returns true for an existing element")
    void testContainsExistingElement() {
        bst.insert(7);
        assertTrue(bst.contains(7));
    }

    @Test
    @DisplayName("contains() returns false for a missing element")
    void testContainsMissingElement() {
        bst.insert(7);
        assertFalse(bst.contains(99));
    }

    // ─────────────────────────────────────────────
    // Delete
    // ─────────────────────────────────────────────

    @Test
    @DisplayName("delete() leaf node: element is no longer present")
    void testDeleteLeafNode() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        // 5 and 15 are leaves
        bst.delete(5);
        assertFalse(bst.contains(5), "Deleted leaf node 5 should not be in the tree");
        assertTrue(bst.contains(10), "Root 10 should still be present after deleting leaf 5");
        assertTrue(bst.contains(15), "Sibling 15 should still be present after deleting leaf 5");
    }

    @Test
    @DisplayName("delete() node with two children: in-order successor takes its place")
    void testDeleteNodeWithTwoChildren() {
        // Tree: 10→left=8, right=12; 8→left=5, right=9
        bst.insert(10);
        bst.insert(8);
        bst.insert(12);
        bst.insert(5);
        bst.insert(9);

        bst.delete(8); // has two children (5 and 9); in-order successor = 9
        assertFalse(bst.contains(8), "Deleted node 8 should not be in the tree");
        assertTrue(bst.contains(5),  "Left child 5 should still be present");
        assertTrue(bst.contains(9),  "In-order successor 9 should still be present");
        assertTrue(bst.contains(10), "Root 10 should still be present");
        assertTrue(bst.contains(12), "Node 12 should still be present");
    }

    @Test
    @DisplayName("delete() root node: remaining elements still accessible")
    void testDeleteRootNode() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);

        bst.delete(10);
        assertFalse(bst.contains(10), "Deleted root 10 should not be present");
        assertTrue(bst.contains(5),   "Child 5 should still be present after root deletion");
        assertTrue(bst.contains(15),  "Child 15 should still be present after root deletion");
    }

    @Test
    @DisplayName("delete() non-existing element: tree remains unchanged")
    void testDeleteNonExistingElement() {
        bst.insert(10);
        bst.insert(5);
        // deleting something that isn't there should not throw or corrupt the tree
        assertDoesNotThrow(() -> bst.delete(99));
        assertTrue(bst.contains(10));
        assertTrue(bst.contains(5));
    }

    // ─────────────────────────────────────────────
    // Traversals
    // ─────────────────────────────────────────────

    /**
     * Standard BST populated with: 10, 8, 11, 3, 6, 1, 7, 4, 12, 2, 5, 9
     */
    private void populateStandardTree() {
        int[] values = {10, 8, 11, 3, 6, 1, 7, 4, 12, 2, 5, 9};
        for (int v : values) bst.insert(v);
    }

    @Test
    @DisplayName("in-order traversal produces elements in sorted (ascending) order")
    void testInOrderTraversalIsSorted() {
        populateStandardTree();
        List<Integer> result = collectNonNull(bst.iteratorInOrder());

        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12), result,
                "In-order traversal should yield elements in ascending order");
    }

    @Test
    @DisplayName("pre-order traversal: root is first element")
    void testPreOrderRootIsFirst() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        List<Integer> result = collectNonNull(bst.iteratorPreOrder());
        assertEquals(10, result.get(0), "Pre-order traversal must start with the root");
    }

    @Test
    @DisplayName("pre-order traversal produces correct sequence")
    void testPreOrderTraversal() {
        populateStandardTree();
        List<Integer> result = collectNonNull(bst.iteratorPreOrder());
        // Root → Left → Right  →  10, 8, 3, 1, 2, 6, 4, 5, 7, 11, 9, 12
        assertEquals(List.of(10, 8, 3, 1, 2, 6, 4, 5, 7, 11, 9, 12), result);
    }

    @Test
    @DisplayName("post-order traversal: root is last element")
    void testPostOrderRootIsLast() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        List<Integer> result = collectNonNull(bst.iteratorPostOrder());
        assertEquals(10, result.get(result.size() - 1), "Post-order traversal must end with the root");
    }

    @Test
    @DisplayName("post-order traversal produces correct sequence")
    void testPostOrderTraversal() {
        populateStandardTree();
        List<Integer> result = collectNonNull(bst.iteratorPostOrder());
        // Left → Right → Root  →  2, 1, 5, 4, 7, 6, 3, 8, 9, 12, 11, 10
        assertEquals(List.of(2, 1, 5, 4, 7, 6, 3, 8, 9, 12, 11, 10), result);
    }

    @Test
    @DisplayName("level-order traversal: root is first, breadth-first order")
    void testLevelOrderRootIsFirst() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        List<Integer> result = collectNonNull(bst.iteratorLevelOrder());
        assertEquals(10, result.get(0), "Level-order traversal must start with the root");
    }

    @Test
    @DisplayName("level-order traversal produces breadth-first order")
    void testLevelOrderTraversal() {
        populateStandardTree();
        List<Integer> result = collectNonNull(bst.iteratorLevelOrder());
        // BFS:  10, 8, 11, 3, 9, 12, 1, 6, 2, 4, 7, 5
        assertEquals(List.of(10, 8, 11, 3, 9, 12, 1, 6, 2, 4, 7, 5), result);
    }

    // ─────────────────────────────────────────────
    // Known implementation notes (documented as tests)
    // ─────────────────────────────────────────────

    /**
     * NOTE: The current BinarySearchTree implementation never increments the
     * internal {@code count} field inside {@code insert()}, so {@code isEmpty()}
     * always returns {@code true} and {@code size()} always returns {@code 0}
     * regardless of how many elements have been inserted.
     *
     * The two tests below document this known bug. They are expected to FAIL
     * until the bug is fixed by adding {@code this.count++} inside insert().
     */
    @Test
    @DisplayName("[BUG] isEmpty() should return false after insertions")
    void testIsEmptyReturnsFalseAfterInsert() {
        bst.insert(10);
        assertFalse(bst.isEmpty(),
                "isEmpty() should return false after inserting an element. " +
                "Bug: count is never incremented in insert().");
    }

    @Test
    @DisplayName("[BUG] size() should reflect the number of inserted elements")
    void testSizeReflectsInsertions() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        assertEquals(3, bst.size(),
                "size() should return 3 after three insertions. " +
                "Bug: count is never incremented in insert().");
    }
}

