package com.example.demo;

public class AdNotFoundException extends RuntimeException {
    AdNotFoundException(int index) {
        super("Can't find ad with index " + index);
    }
}
