/** Project 6 of Data Structures and Algorithms - LinkedList
 *
 * This program contains the generic LinkedList class which implements
 * the Iterable interface to allow for enhanced for loops. This is the same file
 * created for Project 2 in which the need for enhanced for loops wasn't needed,
 * but I'm glad I had implemented a custom iterator then because it made a huge
 * difference when used in the HashTable. Minor improvements were made to the
 * iterator class and an additional method was added to allow for index
 * retrieval of an object in the list.
 *
 * This program compiles with no errors and does what it should.
 *
 * @author David Boudreau
 */
package PhoneBook;

import java.util.Iterator;

/*
Class: LinkedList
Author: David Boudreau
Implements: Iterable<T>
Parameter: T object
Description: This class constructs LinkedList objects containing
a generic type
 */
public class LinkedList<T> implements Iterable<T> {

    public <T> LinkedList() {
        this.head = null;
    }

    //Node to the first object in the list
    protected Node head;

    /*
    Class: Node
    Author: David Boudreau
    Description: This class stores the object the node points
    to and the next node in the list
     */
    public class Node {

        T data;
        Node next;

    }

    public boolean isEmpty() {
        return this.head == null;
    }

    /*
    Class: MyIterator
    Author: David Boudreau
    Implements: Iterator<T>
    Description: Creates an iterator unique to this class
    to help other methods in the class move through the list
     */
    private class MyIterator implements Iterator<T> {

        private Node currentNode;   //making the Node be the index makes most sense in class' implementation

        //The constructor sets the head of the list as the starting point
        public MyIterator(LinkedList<T> list) {
            currentNode = list.head;
        }

        /*
        Method: hasNext
        Author: David Boudreau
        Description: Checks if the next node isn't null
        Output: true if there is a next node, false if not
        Override: The iterator hasNext method
         */
        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        /*
        Method: next
        Author: David Boudreau
        Description: Returns the data from the next node in
        the list
        Output: The data section of the next node
         */
        @Override
        public T next() {
            T stored = currentNode.data;
            currentNode = currentNode.next;
            return stored;
        }

    }

    /*
    Method: iterator
    Author: David Boudreau
    Description: Constructs the custom iterator for the class
    Output: The custom iterator object
     */
    @Override
    public Iterator<T> iterator() {
        return new MyIterator(this);
    }

    /*
    Method: addFront
    Author: David Boudreau
    Description: Adds a new node and object
    to the front of the list
    Input: The new data to add
     */
    public void addFront(T newData) {

        Node newNode = new Node();
        newNode.data = newData;

        newNode.next = head;

        head = newNode;

    }

    /*
    Method: addRear
    Author: David Boudreau
    Description: Adds a new node and object
    to the end of the list
    Input: The new data to add
     */
    public void addRear(T newData) {
        if (head == null) {
            head = new Node();
            head.data = newData;
            head.next = null;
        } else {
            Node p = this.head;

            while (p.next != null) {
                p = p.next;
            }

            Node newNode = new Node();
            newNode.data = newData;
            newNode.next = null;

            p.next = newNode;
        }

    }

    /*
    Method: insert
    Author: David Boudreau
    Description: Adds a new node and object
    after a certain object
    Input: The object which the new data will be added after,
    and the new data to add to the new node
     */
    public void insert(T search, T newValue) {
        Node p = head;

        while (!p.data.equals(search)) {
            p = p.next;
        }
        Node newNode = new Node();
        newNode.data = newValue;

        newNode.next = p.next;
        p.next = newNode;
    }

    /*
    Method: deleteNode
    Author: David Boudreau
    Description: Deletes a node containing
    a specific data value
    Input: The data to search for whose node will be
    removed
     */
    public void deleteNode(T search) {
        Node p = this.head;

        if (p.data.equals(search)) {
            this.head = p.next;
            return;
        }

        if (p.next == null) {
            System.out.println("Object to delete not found");
            return;
        }

        while (!p.next.data.equals(search)) {
            p = p.next;
        }

        p.next = p.next.next;
    }

    /*
    Method: find
    Author: David Boudreau
    Description: Searches for a specific
    data value in the list
    Input: The data value to be found
    Output: True if found, false if not
     */
    public boolean find(T search) {

        if (head == null) {
            return false;
        }

        if (head.data.equals(search)) {
            return true;
        }

        Node p = head;
        boolean flag = false;

        while (p.next != null) {
            p = p.next;
            if (p.data.equals(search)) {
                flag = true;
            }
        }

        return flag;
    }

    /*
    Method: indexOf
    Author: David Boudreau
    Description: Retrieves the 0-based
    index position of a given object in the list
    Input: The object whose index needs to be found
    Output: The index of the object
     */
    public int indexOf(T obj) {

        int index = 0;

        if (this.find(obj) == false) {
            return -1;
        }

        //Enhanced for loop used for easy access
        for (T t : this) {

            if (!t.equals(obj)) {
                index++;
            } else {
                break;
            }

        }

        return index;
    }

    /*
    Method: toString
    Author: David Boudreau
    Description: Converts the list into a string
    representation
    Output: A string representing the list
     */
    @Override
    public String toString() {
        String str = "";

        Node curr = head;

        while (curr != null) {
            str += curr.data + " ";
            curr = curr.next;

        }

        return str;
    }

    //FOR TESTING: This code compiles with no errors and does what it's supposed to
    /*public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();

        System.out.println(list.isEmpty());

        list.addFront("hello");
        list.addFront("bye");
        list.addRear("hola");
        list.addRear("bonjour");
        list.addRear("adios");
        list.insert("hola", "farewell");
        list.addRear("hola");
        list.addRear("hola");
        System.out.println(list.isEmpty());

        System.out.println(list);
        list.deleteNode("hello");
        list.deleteNode("adios");
        list.deleteNode("bye");
        list.deleteNode("bonjour");
        System.out.println(list);
        System.out.println(list.find("adios"));

        System.out.println(list.indexOf("farewell"));
        System.out.println(list.indexOf("adios"));
    }*/
}
