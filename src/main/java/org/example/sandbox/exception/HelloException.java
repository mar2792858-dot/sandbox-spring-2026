package org.example.sandbox.exception;

import java.util.Arrays;

public class HelloException {

    public static void main(String[] args) {

        //try-catch block to handle the exception
        try {
            //do something that may throw an exception
        } catch(Exception e) {
            System.out.println("An exception occurred: " + e.getMessage());
        }
    }
}
