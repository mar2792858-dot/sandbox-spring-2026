package org.example.sandbox.streamapi;

public class FluentApi {

    public static void main(String[] args) {

        Widget widget = new Widget.Builder()
                .withLength(10)
                .withWidth(5)
                .withhHeight(2)
                .build();
        System.out.println(widget);



    }
}