package org.example;

/*
Melissa Brown
5/14/2023
CSC 422
This program creates a pet object containing the pet's name and age.
 */

public class Pet {
    private String name;
    private int age;

    public Pet(String name,int age){
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }
}
