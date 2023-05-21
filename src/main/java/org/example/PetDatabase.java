package org.example;

/*
Melissa Brown
CSC 422
5/14/2023
This program uses the Pet class to create an array of Pets and display the information for the user to see in the
database.
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class PetDatabase {

    //maximum number of pets which can be in the database
    static final int CAPACITY = 5;//added 5.21

    //array to hold all of the pet objects created
    static Pet[] pets = new Pet[CAPACITY];//added 5.21

    //total number of pets in the database
    public static int petCount = 0;

    //name of the file which will be read from and written to
    static String filename = "petData.txt";//added 5.21

    /*array with maximum 100 pets possible
    public static Pet[] pets = new Pet[100]; commented out 5.21*/

    //Scanner which takes in integers
    public static Scanner s = new Scanner(System.in);

    //Scanner which takes in String values
    public static Scanner st = new Scanner(System.in);


    public static void main(String[] args){

        //Lines 37-41 reads from the pets.txt file and puts the information into the Pet[] array
        try {
            loadDatabase();
        }
        catch(InvalidArgumentException e){}
        catch(InvalidAgeException e){
            System.out.println(e);
        }
        catch(FullDatabaseException e){
            System.out.println(e);
            System.out.println("Pets beyond the count of 100 have been eliminated.");
        }

        System.out.println("Pet Database Program");

        //gets the users initial choice before the loop starts
        int choice = getUserChoice();
        System.out.println("Your choice: " + choice);

        //7 means the user wishes to quit the database
        while(choice !=4){
            boolean tryAgain = false;
            switch(choice){
                //1 = user wishes to see all pets in the database
                case 1:
                    showAllPets();
                    break;

                //2 = user wants to add a pet to the database
                case 2:
                    try{
                        addPets();
                    }
                    catch(InvalidArgumentException e){
                        System.out.println(e);
                        tryAgain = true;
                    }
                    catch(InvalidAgeException e){
                        System.out.println(e);
                        tryAgain = true;
                    }
                    catch(FullDatabaseException e){
                        System.out.println(e);
                    }
                    break;
                    /*
                    addPets();
                    System.out.println("hello");
                    break;*/

                //3 = user wishes to search pets by a certain name and see a list of only the pets with that name
               /* case 3:
                    searchPetsByName();
                    break;

                //4 = user wishes to search pets by a certain age and see a list of only the pets with that name
                case 4:
                    searchPetsByAge();
                    break;

                //5 user wishes to update a pet name and/or age in the database
                case 5:
                    updatePet();
                    break;*/

                //3 = user wishes to remove a pet from the database
                case 3:
                    showAllPets();
                    try{
                        removePet();
                    }
                    catch(InvalidIdException e){
                        System.out.println(e);
                    }
                    /*
                    System.out.println("Enter the pet ID to remove: ");
                    int remove = s.nextInt();
                    System.out.println(pets[remove].getName() + " " + pets[remove].getAge() + " is removed.");
                    for(int i = remove; i < petCount; i++){
                        pets[i] = pets[i+1];
                    }
                    petCount--;*/
                    break;


            }
            choice = getUserChoice();
            System.out.println("Your choice: " + choice);
        }

        saveDatabase();


        //user has chosen 7, which quits the program
        System.out.println("Good-bye!");
    }

    //Displays the options for what the user can do with the Pet database
    public static int getUserChoice(){
        System.out.println("\nWhat would you like to do?");
        System.out.println("1) View all pets \n2) Add more pets \n3) Remove a pet \n4) Exit program");
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
    the information as "name age", for example "kitty 8." If the format is incorrect, for example, there is no
    space, or only an age, then InvalidArgumentException is thrown. If the user tries to enter a pet that is too
    young or too old, InvalidAgeException is thrown. If the database is full, FullDatabaseException is thrown.
     */
    public static void addPets() throws InvalidAgeException, InvalidArgumentException, FullDatabaseException {
        System.out.println("add pet (name, age) or type 'done' to finish:");
        String nextPet = st.nextLine();
        while (!nextPet.equalsIgnoreCase("done")) {
            //throws InvalidArgumentException if not parsed correctly
            String[] nextPetInfo = parseArgument(nextPet);

            //checks the age if it is correctly parsed, throws InvalidAgeException if too old or young
            if (Integer.parseInt(nextPetInfo[1]) < 1 || Integer.parseInt(nextPetInfo[1]) > 50) {
                throw new InvalidAgeException(Integer.parseInt(nextPetInfo[1]));
            } else {
                //throws FullDatabaseException if the database is full, and invalid age exception
                addPet(nextPetInfo[0],Integer.parseInt(nextPetInfo[1]));
                System.out.println("add pet (name, age):");
                nextPet = st.nextLine();
            }
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

    public static void addPet(String name, int age) throws FullDatabaseException,InvalidAgeException {
        //Pet constructor throws InvalidAgeException
        Pet petAdd = new Pet(name, age);
        if(petCount >= 100){
            throw new FullDatabaseException();
        }
        else {
            pets[petCount] = petAdd;
            petCount++;
        }
    }

    /*
    This method removes a pet from the database. The user must choose an ID to remove. If the user chooses
    an ID which is not in the database, an InvalidIdException is thrown.
     */
    public static void removePet() throws InvalidIdException{
        showAllPets();
        System.out.println("Enter the petID to remove: ");
        int remove = s.nextInt();
        if(remove < 0 || remove >= petCount){
            throw new InvalidIdException(remove);
        }
        else{
            System.out.println(pets[remove].getName() + " " + pets[remove].getAge() + " is removed.");

            //this for loop shuffles each from right after the removed pet one index lower than it originally
            //was.
            for(int i = remove; i < petCount; i++){
                //if the pet is the last pet and the database is full, then the shuffling doesn't occur since
                //that would cause an index out of bounds error
                if(i!=99) {
                    pets[i] = pets[i + 1];
                }
            }
            petCount--;
        }
    }

    /*
    This method loads the database from the pets.txt file. It reads each line, uses parseArgument to make sure
    the lines are formatted correctly, creates a Pet object from the line, and adds it to the Pet[] array. If
    there are already too many pets in the file from the start, a message is given to the user so that they know
    data had to be compromised, since they were too ambitious with their pet database goals.
     */
    public static void loadDatabase() throws InvalidArgumentException,InvalidAgeException,FullDatabaseException{
        File petData = new File("petData.txt");
        try (Scanner input = new Scanner(petData)){
            while(input.hasNext()){
                String nextPet = input.nextLine();
                String[] nextPetInfo = parseArgument(nextPet);
                addPet(nextPetInfo[0], Integer.parseInt(nextPetInfo[1]));
            }
        } catch (IOException e) {
        }

    }

    /*
    This is the method which saves the database to the text file.
     */
    public static void saveDatabase(){

        //try with resources to create a PrintWriter object and have it be automatically closed
        try (PrintWriter output = new PrintWriter(new File("petData.txt"))){

            //loops through each index in the array and writes it line by line onto the file in the
            //correct format
            for(int i = 0; i < petCount; i++){
                output.println(pets[i].getName() + " " + pets[i].getAge());
            }
        }
        catch(IOException e){}

    }

    /*
    Makes sure the argument is parsed correctly. The String[] array should have only two values.
     */
    public static String[] parseArgument(String line) throws InvalidArgumentException {
        String[] result = line.split(" ");
        if (result.length != 2) {
            throw new InvalidArgumentException(line);
        }
        else
            return result;
    }



}
