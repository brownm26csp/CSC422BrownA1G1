package org.example;

/*
Melissa Brown
CSC 222
This program uses the Pet class to create an array of Pets and display the information for the user to see in the
database.
 */

import java.util.Scanner;
import java.util.ArrayList;

public class PetDatabase {

    //total number of pets in the database
    public static int petCount = 0;

    //array with maximum 100 pets possible
    public static Pet[] pets = new Pet[100];

    //Scanner which takes in integers
    public static Scanner s = new Scanner(System.in);

    //Scanner which takes in String values
    public static Scanner st = new Scanner(System.in);


    public static void main(String[] args){

        System.out.println("Pet Database Program");

        //gets the users initial choice before the loop starts
        int choice = getUserChoice();
        System.out.println("Your choice: " + choice);

        //7 means the user wishes to quit the database
        while(choice !=3){
            switch(choice){
                //1 = user wishes to see all pets in the database
                case 1:
                    showAllPets();
                    break;

                //2 = user wants to add a pet to the database
                case 2:
                    addPets();
                    System.out.println("hello");
                    break;


            }
            choice = getUserChoice();
            System.out.println("Your choice: " + choice);
        }

        //user has chosen 7, which quits the program
        System.out.println("Good-bye!");
    }

    //Displays the options for what the user can do with the Pet database
    public static int getUserChoice(){
        System.out.println("\nWhat would you like to do?");
        System.out.println("1) View all pets \n2) Add more pets \n3) Exit program");
        int result = s.nextInt();
        return result;
    }

    //Prints the top of the database table
    public static void printTableHeader(){
        System.out.println("+-------------------+");
        System.out.printf("|%-3s|%-10s|%-4s|","ID","NAME","AGE");
        System.out.print("\n+-------------------+");
    }

    //prints each row of the database table. Includes the pets spot in the array, their name, and age
    public static void printTableRow(int id, String name, int age){
        System.out.printf("\n|%-3s|%-10s|%-4s|",id,name,age);
    }

    //Prints the bottom of the display table. Tells how many pets are currently in the database.
    public static void printTableFooter(int rowCount){
        System.out.println("\n+-------------------+");
        System.out.println(rowCount + " rows in set.");
    }

    //Displays the header, rows, and footer of the database
    public static void showAllPets(){
        printTableHeader();
        for(int i = 0; i < petCount; i++){
            printTableRow(i,pets[i].getName(),pets[i].getAge());
        }
        printTableFooter(petCount);
    }

    /*When the user calls this method, the method does not stop until the user types in "done." The user must enter
    the information as "name age", for example "kitty 8." Any other format is not acceptable, as the method depends on
    the space to process the different aspects of the input String.
     */
    public static void addPets(){
        System.out.println("add pet (name, age) Type 'done' to exit:");
        String nextPet = st.nextLine();
        while(!nextPet.equalsIgnoreCase("done")) {
            String[] nextPetInfo = nextPet.split(" ");
            Pet petAdd = new Pet(nextPetInfo[0], Integer.parseInt(nextPetInfo[1]));
            pets[petCount] = petAdd;
            petCount++;
            System.out.println("add pet (name, age):");
            nextPet = st.nextLine();
        }
    }



}
