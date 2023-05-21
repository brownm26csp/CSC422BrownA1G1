package org.example;

/*
Melissa Brown
5/21/2023
CSC 422
This class is an exception which is thrown when someone tries to enter in arguments which are not amenable to adding
a new pet to the database. Examples include:
1) any string which has more than 1 space (e.g. "melissa brown 43" or "johnny bravo 2)
2) any string which only has one field (e.g. "john," "44")
3) Any string which has the appropriate amount of fields, but the second one is not a number (e.g. "melissa brown" or
"sam eleven")
 */

class InvalidArgumentException extends Exception{
    private String badArguments;

    public InvalidArgumentException(String badArguments){
        super(badArguments + " is not valid input.");
        this.badArguments = badArguments;
    }


}
