package org.example;

/*
Melissa Brown
5/21/2023
CSC 422
This class is an exception which is thrown when someone tries to remove an index which is not currently filled with a pet
 */
class InvalidIdException extends Exception {
    private int id;

    public InvalidIdException(int id){
        super(id + " is out of range. Try again.");
        this.id = id;
    }
}
