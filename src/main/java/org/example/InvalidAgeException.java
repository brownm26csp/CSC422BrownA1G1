package org.example;

/*
Melissa Brown
5/21/2023
CSC 422
This is an exception which is thrown when a pet has an age outside of the range 1-20, inclusive
 */

class InvalidAgeException extends Exception {
    private int age;

    public InvalidAgeException(int age){
        super("Age " + age + " is outside of the acceptable range 1-20.");
        this.age = age;
    }
}
