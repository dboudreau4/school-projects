/** Project 5 of Data Structures and Algorithms - Main method/WordCount
 *
 * This is the main program of the project.
 *
 * This program contains the WordCount class which uses an AVL tree
 * structure to store words contained in a string. As the string is read,
 * the frequency of occurrence of each word is incremented. The AVL tree is
 * then printed showing each word contained in the string along with the
 * number of times the word appeared in the string.
 *
 * The test file "WordCountTest.text" was used.
 * The words from the file are read in and stored in the AVL tree.
 * For this program to run as written in the main method, the file path must be
 * correct. If a different test file is used, make sure the path is set properly
 * on line 120 in the main method.
 *
 * This program contains no errors and does what it is supposed to do.
 * 
 * @author David Boudreau
 */
package WordCounterDSA;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/*
Class: WordCount
Author: David Boudreau
Extends: AVL
Description: This class sets up functionality to allow the AVL tree
to be used as a word counter.
 */
public class WordCount extends AVL {

    //The AVL tree the words are added into
    private AVL<String> avl = new AVL<>();


    /*
    Method: addData
    Author: David Boudreau
    Description: Takes a string as input, parses
    for words in the string, storing them in an array
    of strings, then individually adds the words to the AVL
    tree. If a word already exists in the tree, the node containing
    the word is accessed and its counter is incremented.
    Input: The string to be word counted
     */
    public void addData(String input) {

        String[] checked = input.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

        for (int i = 0; i < checked.length; ++i) {

            if (!avl.contains(checked[i])) {
                avl.insert(checked[i]);
                avl.updateCounter(avl.findNode(checked[i]));

            } else {
                avl.updateCounter(avl.findNode(checked[i]));
            }

        }

    }

    /*
    Method: printResult
    Author: David Boudreau
    Description: Private method print both the
    data and the frequency counts for the data
    in the tree. This shows both the word and the number
    of times the word showed up in the string. The words
    are printed in alphabetical order.
    It uses recursion to start at the leftmost part of the tree
    and print to the right.
    Input: The node used as a reference point
     */
    private void printResult(AVLNode n) {

        if (n != null) {
            printResult(n.lt);
            System.out.println(n.data + " - " + n.counter);
            printResult(n.rt);
        }

    }

    /*
    Method: printResult
    Author: David Boudreau
    Description: Public method to print the words
    and their frequencies. It uses the private method
    to start at the orig or root as the reference.
     */
    public void printResult() {

        if (this.avl.isEmpty()) {
            System.out.println("Nothing to print");
        } else {
            this.printResult(this.avl.orig);
        }

    }

    /*
    Method: main
    Author: David Boudreau
    Description: This main method tests the code against the provided
    text file. The file is read in, the words from the file are put into
    an array of strings, and the words are added to an AVL tree. The words
    along with the number of times the words showed up in the file are printed
    out.
     */
    public static void main(String[] args) {

        WordCount wd = new WordCount();

        try ( BufferedReader b = new BufferedReader(new FileReader("WordCountTest.txt"))) {

            String line;
            String contents = "";
            while ((line = b.readLine()) != null) {
                contents += line + " ";
            }

            wd.addData(contents);
            wd.printResult();

        } catch (IOException e) {
            System.out.println("Unable to read file");
        }

    }
}
