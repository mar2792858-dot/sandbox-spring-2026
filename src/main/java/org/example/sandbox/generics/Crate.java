package org.example.sandbox.generics;

import java.util.ArrayList;
import java.util.List;

public class Crate<T extends Box<?>> {

    List<T> containers;

    public Crate() {
        this.containers = new ArrayList<>();
    }

    public void addBox(T box) {
        this.containers.add(box);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Crate{");
        sb.append(containers);
        sb.append('}');
        return sb.toString();
    }
}