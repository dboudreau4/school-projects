/** Project 6 of Data Structures and Algorithms - Main method/HashApp
 *
 * This is the main program for the project.
 *
 * This program contains the HashApp class which loads names and phone numbers
 * from a text file into a HashTable and includes a user interface which allows
 * the user to type a name located in the file to look up the phone number for
 * the person with that name. The person's location in the table is also output.
 *
 * This program compiles with no errors and does what it should.
 * 
 * @author David Boudreau
 */
package PhoneBook;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
Class: HashApp
Author: David Boudreau
Description: This class constructs HashApp objects
and implements the methods necessary for the user
interface for phone number lookup
 */
public class HashApp {

    //The HashTable used constructed to size 53
    private HashTable table = new HashTable(53);

    /*
    Method: load
    Author: David Boudreau
    Description: Takes the String input, removes all
    non-digit characters from the phone number portion,
    converts it to a double, and adds the name and
    phone number to the table.
    Input: The String to convert and add
     */
    public void load(String input) {

        String raw = input.replaceAll("[()\\s-]+", "").trim();
        String[] arr = raw.split(";");
        String name = "";
        double number = 0;

        for (String str : arr) {
            try {
                number += Double.parseDouble(str);
            } catch (NumberFormatException e) {
                name += str;
            }
        }
        table.hash(name, number);

    }

    /*
    Method: userInterface
    Author: David Boudreau
    Description: Sets up the user interface with
    prompts. The interface stays open and the program
    runs until the user says they don't want to look up
    another number. The lookUp method from HashTable is
    used to retrieve the information being searched for.
     */
    public void userInterface() {

        boolean proceed = true;

        while (proceed == true) {

            System.out.println("Enter a name to find phone number: ");
            Scanner user = new Scanner(System.in);
            String input = table.lookUp(user.nextLine());
            System.out.println(input);

            System.out.println("Look up another number? <Y/N>");
            Scanner answer = new Scanner(System.in);
            String ans = answer.nextLine();

            if (ans.equals("y") || ans.equals("Y")) {
                proceed = true;
            } else {
                proceed = false;
            }
        }
    }

    /*
    Method: readTable
    Author: David Boudreau
    Description: Reads in the text file "Hashdata.txt". Each line
    in the file is read and loaded into the HashTable. Once the whole
    file is read, the table is printed, and the user interface is opened.
     */
    public void readTable() {

        try ( BufferedReader b = new BufferedReader(new FileReader("Hashdata.txt"))) {

            String line;
            ArrayList<String> arr = new ArrayList<>();
            while ((line = b.readLine()) != null) {

                arr.add(line);
            }

            for (String str : arr) {
                this.load(str);
            }
            this.print();

        } catch (IOException e) {
            System.out.println("Unable to read file");
        }

        this.userInterface();

    }

    /*
    Method: print
    Author: David Boudreau
    Description: This wasn't necessary, but I added it
    anyway. This just prints the HashTable.
     */
    public void print() {
        this.table.printTable();
    }

    /*
    Method: main
    Author: David Boudreau
    Description: This executes the program by just making a call to readTable,
    which contains all the functionality.
     */
    public static void main(String[] args) {

        HashApp app = new HashApp();
        app.readTable();
    }

}
