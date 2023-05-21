package org.example;

/*
Melissa Brown
5/21/2023
CSC 422
This class is an exception which is thrown when someone tries to add a pet when the database already includes 5 pets.
 */
class FullDatabaseException extends Exception{
    public FullDatabaseException(){
        super("The database is overridden with pets. Any additional pets would compromise the integrity "
                + "of the database. \nIt might also lead to animal negligence. 5 pets is already quite a few.");
    }
}
