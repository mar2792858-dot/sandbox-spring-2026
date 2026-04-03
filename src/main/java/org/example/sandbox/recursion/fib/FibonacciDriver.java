package org.example.sandbox.recursion.fib;

import java.math.BigInteger;

public class FibonacciDriver {

    public static void main(String[] args) {

        FibonacciNumbers fn = new FibonacciNumbers();

        for (int x = 0; x <= 50; x++) {

            BigInteger fib = fn.fib(x);
            System.out.println("fib(" + x + ") = " + fib);
        }
    }
}
