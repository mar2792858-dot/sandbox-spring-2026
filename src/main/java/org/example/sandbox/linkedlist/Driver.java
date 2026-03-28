package org.example.sandbox.linkedlist;

public class Driver {

    public static void main(String[] args) {

        LinkedList<String> linkedList = new SinglyLinkedList<>();

        linkedList.addFirst("One");
        linkedList.addFirst("Two");
        linkedList.addFirst("Three");
        linkedList.addFirst("Four");
        linkedList.addFirst("Five");

        linkedList.addLast("Six");
        linkedList.addLast("Seven");
        linkedList.addLast("Eight");
        linkedList.addLast("Nine");
        linkedList.addLast("Ten");

        System.out.println(linkedList);

    }
}