package org.example;

class FullDatabaseException extends Exception{
    public FullDatabaseException(){
        super("The database is overridden with pets. Any additional pets would compromise the integrity "
                + "of the database. \nIt might also lead to animal negligence. 100 pets is already quite a few.");
    }
}
