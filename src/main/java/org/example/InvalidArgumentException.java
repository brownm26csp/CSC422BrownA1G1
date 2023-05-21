package org.example;

class InvalidArgumentException extends Exception{
    private String badArguments;

    public InvalidArgumentException(String badArguments){
        super(badArguments + " is not valid input.");
        this.badArguments = badArguments;
    }


}
