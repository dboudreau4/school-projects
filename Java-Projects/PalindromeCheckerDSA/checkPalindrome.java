/** Project 4 of Data Structures and Algorithms - Main file/checkPalindrome
 *
 * This program contains the checkPalindrome class and main method for the project,
 * which uses a Queue to check if a file contains palindromes. A file ("palFile.txt", included
 * in the folder) is read line by line with each character from a line being added to a 
 * Queue object, which is then reversed. If the original and the reversed are equal, the 
 * line of the file is a palindrome, so it is written to a file called "pal.txt", which 
 * will be generated in the same folder.
 *
 * The file read is the test text file "palFile.txt" located in the project folder. 
 * If anyone running this program wants to use a different test file, make sure it
 * exists in the same path or use the proper path on line 108 in the main method.
 *
 * This program contains no errors and does what it is supposed to do.
 * 
 * @author David Boudreau
 */
package PalindromeCheckerDSA;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;

/*
Class: checkPalindrome
Author: David Boudreau
Description: This class contains methods to determine if
a given string is a palindrome by using Queues.
 */
public class checkPalindrome {

    /*
    Method: convert
    Author: David Boudreau
    Description: Converts a string into a Queue by removing
    each character of the string, putting them in lower case,
    and placing them into a Queue.
    Input: The string to be converted
    Output: A Queue containing characters from the string
     */
    public Queue<Character> convert(String input) {
        String str = input.replaceAll("\\s", "").toLowerCase();
        Queue<Character> res = new Queue<>();
        for (int i = str.length() - 1; i >= 0; --i) {
            res.enqueue(str.charAt(i));
        }
        return res;

    }

    /*
    Method: reversed
    Author: David Boudreau
    Description: Takes a Queue and puts its contents in
    reverse order. A copy of the input Queue is made so
    it won't be altered.
    Input: The Queue to be reversed
    Output: The Queue in reverse order
     */
    public Queue<Character> reversed(Queue<Character> input) {

        Queue<Character> res = new Queue<>();
        Queue<Character> cpy = input.copy();

        while (cpy.list.head != null) {
            res.enqueue(cpy.removeFront());

        }
        return res;

    }

    /*
    Method: isPalindrome
    Author: David Boudreau
    Description: Takes a string, converts it to a Queue,
    reverses the converted Queue, and determines if the
    two Queues are equal, which indicates the string is a
    palindrome. It uses the equals method to compare the
    two Queues
    Input: The string that is checked to see if it's a palindrome
    Output: true if it is a palindrome, false if not
     */
    public boolean isPalindrome(String input) {

        Queue<Character> orig = this.convert(input);
        Queue<Character> rev = this.reversed(orig);

        return orig.equals(rev);
    }

    /*
    Method: main
    Author: David Boudreau
    Description: It reads a file containing a mix of palindromes and non-palindromes (palFile.txt),
    and checks if each line of the file is a palindrome. If a line is one, the line
    is added to an ArrayList of strings. Once the file is done being read, the palindromes
    from the ArrayList are written line by line to a file called "pal.txt".
     */
    public static void main(String[] args) {
        checkPalindrome a = new checkPalindrome();

        //The ArrayList was used to store the palindromes for easy transfer to the written file
        ArrayList<String> arr = new ArrayList<>();

        try ( BufferedReader b = new BufferedReader(new FileReader("palFile.txt"))) {
            String fileLine;

            while ((fileLine = b.readLine()) != null) {
                if (a.isPalindrome(fileLine) == true) {
                    arr.add(fileLine);

                }

            }

        } catch (IOException e) {
            System.out.println("Unable to open file");
        }

        try {
            FileWriter palindrome = new FileWriter("pal.txt");
            for (String str : arr) {
                palindrome.write(str);
                palindrome.write(System.getProperty("line.separator"));
            }
            palindrome.close();
        } catch (IOException e) {
            System.out.println("Unable to write file");
        }

    }

}
