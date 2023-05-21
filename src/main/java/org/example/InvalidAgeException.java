package org.example;

class InvalidAgeException extends Exception {
    private int age;

    public InvalidAgeException(int age){
        super("Age " + age + " is outside of the acceptable range 1-50.");
        this.age = age;
    }
}
