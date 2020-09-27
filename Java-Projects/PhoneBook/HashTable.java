/** Project 6 of Data Structures and Algorithms - HashTable
 *
 * This program contains the HashTable class which contains a HashNode
 * class which holds data for both a String and a double. The class is constructed
 * from an ArrayList which holds LinkedLists which contain HashNodes which contain
 * the data stored. When data is hashed to the structure, a Bernstein hash algorithm
 * is used to determine an index position of the ArrayList to add data to. Chaining
 * is implemented through the use of a LinkedList. When data are hashed to the same
 * index in the ArrayList, the data is added to the end of the LinkedList at that
 * index.
 *
 * This program compiles with no errors and does what it should.
 * 
 * @author David Boudreau
 */
package PhoneBook;

import java.util.ArrayList;
import java.text.DecimalFormat;

/*
Class: HashTable
Author: David Boudreau
Description: This class constructs HashTable objects
 */
public class HashTable {

    /*
    Class: HashNode
    Author: David Boudreau
    Description: This class constructs HashNode objects,
    which contain the data held in the overall structure.
     */
    public class HashNode {

        String key;
        double value;

        //Constructor initializing the fields to the parameters
        public HashNode(String key, double val) {
            this.key = key;
            this.value = val;
        }

        /*
        Method: equals
        Author: David Boudreau
        Description: Compares two HashNodes by String field only, since
        data is hashed by name in the application
        Input: An Object to be converted to a HashNode
        Output: True if equal, false if not
         */
        @Override
        public boolean equals(Object o) {

            if (o == this) {
                return true;
            }
            if (!(o instanceof HashNode)) {
                return false;
            }

            HashNode h = (HashNode) o;

            return this.key.equals(h.key);
        }

        /*
        Method: halfEquals
        Author: David Boudreau
        Description: Compares two HashNodes by double field only. This was
        mainly needed for the search method, which counts the occurences of
        a double value in the table.
        Input: A HashNode to compare against
        Output: True if equal, false if not
         */
        public boolean halfEquals(HashNode h) {
            return this.value == h.value;
        }

        /*
        Method: toString
        Author: David Boudreau
        Description: Converts the HashNode to a String representation
        for printing. It converts the double to decimal format.
        Output: The String representation
         */
        @Override
        public String toString() {
            DecimalFormat format = new DecimalFormat("#");
            format.setMaximumFractionDigits(1);
            return this.key + " " + format.format(this.value);
        }

    }

    //The structure which holds all the data
    private ArrayList<LinkedList<HashNode>> list = new ArrayList<>();

    /*
    Constructor: default
    Author: David Boudreau
    Description: Default constructs a HashTable with the ArrayList set
    to size 50. The ArrayList is filled with LinkedLists containing nothing.
     */
    public HashTable() {

        for (int i = 0; i < 50; ++i) {
            list.add(new LinkedList<>());
        }
    }

    /*
    Constructor: user-defined
    Author: David Boudreau
    Description: Constructs a HashTable with the ArrayList set
    to whatever size is passed in. The ArrayList is filled with
    LinkedLists containing nothing.
     */
    public HashTable(int tableSize) {

        for (int i = 0; i < tableSize; ++i) {
            list.add(new LinkedList<>());
        }
    }

    /*
    Method: hash
    Author: David Boudreau
    Description: Computes a hash value using a Bernstein hash
    algorithm, then uses that value as an index for the LinkedList
    located at that index in the ArrayList. A new HashNode is created
    with the input value as the double field and is added to the LinkedList.
    Input: The double to be hashed
     */
    public void hash(double value) {

        long hashVal = 5381;
        String num = String.valueOf(value);

        for (char c : num.toCharArray()) {

            hashVal *= 32;

            hashVal += (int) c;

        }

        hashVal %= this.list.size();
        int index = (int) hashVal;

        LinkedList<HashNode> chain = list.get(index);

        if (chain.isEmpty()) {
            chain.addFront(new HashNode("", value));
        } else {
            chain.addRear(new HashNode("", value));
        }

    }

    /*
    Method: hash
    Author: David Boudreau
    Description: Computes a hash value using a Bernstein hash
    algorithm, then uses that value as an index for the LinkedList
    located at that index in the ArrayList. A new HashNode is created
    with the input values as the String field and double field
    and is added to the LinkedList. If the hash value ended up negative,
    the size of the ArrayList was added to it.
    Input: The String to be hashed and the double to be used later in
    the HashNode construction and addition
     */
    public void hash(String key, double value) {

        long hashVal = 5381;

        for (char c : key.toCharArray()) {

            hashVal *= 32;

            hashVal += (int) c;

        }

        hashVal %= this.list.size();
        int index = (int) hashVal;

        //This was needed because the input file in the HashApp
        //would generate negative values for some Strings
        if (index < 0) {
            index += this.list.size();
        }

        LinkedList<HashNode> chain = list.get(index);

        if (chain.isEmpty()) {
            chain.addFront(new HashNode(key, value));
        } else {
            chain.addRear(new HashNode(key, value));
        }

    }

    /*
    Method: search
    Author: David Boudreau
    Description: Traverses the entire ArrayList, checking each
    LinkedList and each HashNode in each LinkedList for the input
    value. If the value is found, the counter is incremented. Once the
    end of the ArrayList is reached, the number of times the double value
    showed up in the HashTable is returned.
    Input: The double to be searched for
    Output: The number of times the value occurred
     */
    public int search(double value) {

        HashNode h = new HashNode("", value);
        int count = 0;

        for (LinkedList<HashNode> index : this.list) {
            for (HashNode node : index) {
                if (node.halfEquals(h)) {
                    count++;
                }
            }
        }
        return count;
    }

    /*
    Method: find
    Author: David Boudreau
    Description: This is a helper function used to retrieve a HashNode
    containing the String asked for. A double enhanced for loop was used
    to traverse the table.
    Input: The String to look for in the table
    Output: The HashNode containing the String asked for
     */
    public HashNode find(String search) {

        HashNode h = new HashNode(search, 0);
        HashNode res = new HashNode("", 0);

        for (LinkedList<HashNode> index : this.list) {
            for (HashNode node : index) {
                if (node.equals(h)) {
                    HashNode n = new HashNode(node.key, node.value);
                    res = n;
                    break;

                }

            }
        }
        return res;
    }

    /*
    Method: hashLoc
    Author: David Boudreau
    Description: Uses the find method to retrieve the HashNode
    containing the String being searched for. It then traverses
    the table to find both the index in the ArrayList (the LinkedList)
    where the HashNode is located and the index in the LinkedList where
    the HashNode is.
    Input: The String to find its location
    Output: A String represenation of the location. The index of the ArrayList
    will always be output if found, but if the node isn't located at the head
    of the LinkedList, the index of the LinkedList (offset) will be output after
    the first index and a dash.
     */
    public String hashLoc(String search) {

        HashNode h = this.find(search);
        int loc = -1;
        int offset = this.hashOffset(search);
        String message = "Key not found";

        for (LinkedList<HashNode> index : this.list) {
            for (HashNode node : index) {
                if (node.equals(h)) {
                    loc = list.indexOf(index);
                }
            }
        }

        //if loc was never updated, data not found
        if (loc == -1) {
            return message;
        } //if the index of the data in the LL isn't the first one
        else if (offset == 0) {
            return "(" + loc + ")";
        }
        //if index is not the first, return the index in the AL and index in LL
        return "(" + loc + "-" + offset + ")";
    }

    /*
    Method: hashOffset
    Author: David Boudreau
    Description: Uses the find method to retrieve the HashNode
    containing the String being searched for. It then traverses
    the table to find the index in the LinkedList where the HashNode is
    located. This value is used in the hashLoc method.
    Input: The String to find its offset location
    Output: A String represenation of the index/offset
     */
    public int hashOffset(String search) {

        HashNode h = this.find(search);
        int offset = 0;

        for (LinkedList<HashNode> lst : this.list) {
            if (lst.find(h) == true) {
                offset += lst.indexOf(h);
                break;
            }
        }

        return offset;
    }

    /*
    Method: lookUp
    Author: David Boudreau
    Description: Finds the data in the table based on the input, then
    converts it to a String along with its hash location.
    Input: The String to look up
    Output: A String represenation of data information and location.
     */
    public String lookUp(String input) {

        HashNode info = this.find(input);
        String name = info.key;
        double number = info.value;

        //Without this conversion, the double would be in
        //scientific notation when diplayed
        DecimalFormat format = new DecimalFormat("#");
        format.setMaximumFractionDigits(1);
        String str = format.format(number);

        String num = "";
        //conversion back to phone number format
        for (int i = 0; i <= str.length() - 1; ++i) {
            switch (i) {

                case 0:
                    num += "(" + str.charAt(i);
                    break;
                case 2:
                    num += str.charAt(i) + ")" + " ";
                    break;
                case 5:
                    num += str.charAt(i) + "-";
                    break;
                default:
                    num += str.charAt(i);
                    break;

            }
        }

        return name + " " + this.hashLoc(input) + " " + num;

    }

    /*
    Method: printTable
    Author: David Boudreau
    Description: Prints the table by printing the LinkedLists at each
    index whose HashNodes are printed head to tail.
     */
    public void printTable() {

        for (LinkedList<HashNode> index : this.list) {

            System.out.println(index);

        }

    }

    //FOR TESTING: This code compiles with no errors and does what it's supposed to
    /*public static void main(String[] args) {

        HashTable h = new HashTable(53);
        /*h.hash(1);
        h.hash(2);
        h.hash(15);
        h.hash(9);
        h.hash(25);
        h.hash(45);
        h.hash(6);
        h.hash("Jackson", 25);
        h.hash("Gavin", 25);
        h.printTable();
        System.out.println(h.search(25));
        System.out.println(h.find("Jackson"));
        System.out.println(h.hashLoc("Gavin"));
        System.out.println(h.hashLoc("Jackson"));
        //System.out.println(h.hashOffset("hello"));
    }*/
}
