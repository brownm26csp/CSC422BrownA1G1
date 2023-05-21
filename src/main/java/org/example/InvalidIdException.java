package org.example;

class InvalidIdException extends Exception {
    private int id;

    public InvalidIdException(int id){
        super(id + " is out of range. Try again.");
        this.id = id;
    }
}
