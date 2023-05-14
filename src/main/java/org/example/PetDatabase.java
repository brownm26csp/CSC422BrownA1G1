package org.example;

/*
Melissa Brown
CSC 422
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
        while(choice !=7){
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

                //3 = user wishes to search pets by a certain name and see a list of only the pets with that name
                case 3:
                    searchPetsByName();
                    break;

                //4 = user wishes to search pets by a certain age and see a list of only the pets with that name
                case 4:
                    searchPetsByAge();
                    break;

                //5 user wishes to update a pet name and/or age in the database
                case 5:
                    updatePet();
                    break;

                //6 = user wishes to remove a pet from the database
                case 6:
                    showAllPets();
                    System.out.println("Enter the pet ID to remove: ");
                    int remove = s.nextInt();
                    System.out.println(pets[remove].getName() + " " + pets[remove].getAge() + " is removed.");
                    for(int i = remove; i < petCount; i++){
                        pets[i] = pets[i+1];
                    }
                    petCount--;
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
        System.out.println("1) View all pets \n2) Add more pets \n3) Search pets by name \n4) Search pets by age" +
                "\n5) Update pet \n6)Remove pet \n7) Exit program");
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
            System.out.println("add pet (name, age) type 'done' to exit:");
            nextPet = st.nextLine();
        }
    }

    /*
    The user inputs a pet name to search. An ArrayList is created to temporarily store the matches. The pets[] array is
    searched index by index. If a name in the pets[] array matches the search name (regardless of case), then it is
    added to the ArrayList of pet objects. At the end, a table is printed with a header, the number of rows in the
    ArrayList of pets, and a footer with the tally of pets matching the specified name.
     */
    public static void searchPetsByName(){
        System.out.println("Enter a name to search: ");
        String name = st.nextLine();
        ArrayList<Pet> nameMatches = new ArrayList<Pet>();
        for(int i = 0; i < petCount; i++){
            if(name.equalsIgnoreCase(pets[i].getName())){
                nameMatches.add(pets[i]);
            }
        }
        printTableHeader();
        for(int j = 0; j < nameMatches.size(); j++){
            printTableRow(j,(nameMatches.get(j)).getName(),(nameMatches.get(j)).getAge());

        }
        printTableFooter(nameMatches.size());
    }


    /*
        The user inputs a pet age to search. An ArrayList is created to temporarily store the matches. The pets[] array is
        searched index by index. If an age in the pets[] array matches the search age, then it is
        added to the ArrayList of pet objects. At the end, a table is printed with a header, the number of rows in the
        ArrayList of pets, and a footer with the tally of pets matching the specified age.
         */
    public static void searchPetsByAge(){
        System.out.println("Enter an age to search: ");
        int age = s.nextInt();
        ArrayList<Pet> ageMatches = new ArrayList<>();
        for(int i = 0; i < petCount; i++){
            if(age == pets[i].getAge()){
                ageMatches.add(pets[i]);
            }
        }
        printTableHeader();
        for(int j = 0; j < ageMatches.size(); j++){
            printTableRow(j,(ageMatches.get(j)).getName(),(ageMatches.get(j)).getAge());
        }
        printTableFooter(ageMatches.size());
    }

    /*Allows user to update a pet's information. The table displays, and the user puts in the ID they wish to change.
    This ID is put into the pets[] array, and the information that the user types in is put into that spot in the
    array. The old name and age are saved in a temporary String to show the user at the end what the old name was changed from,
    and also displays the new name.
     */
    public static void updatePet(){
        showAllPets();
        System.out.println("Enter the petID you want to update");
        int idUpdate = s.nextInt();
        System.out.println("Enter new name and new age: ");
        String newInfo = st.nextLine();
        String[] nextPetInfo = newInfo.split(" ");
        String tempName = pets[idUpdate].getName();
        int tempAge = pets[idUpdate].getAge();
        pets[idUpdate].setName(nextPetInfo[0]);
        pets[idUpdate].setAge(Integer.parseInt(nextPetInfo[1]));
        System.out.println(tempName + " " + tempAge + " changed to " + pets[idUpdate].getName() + " " + pets[idUpdate].getAge());
    }



}
