/** Project 4 of Data Structures and Algorithms - DoublyLinkedList Class
 *
 * This program contains the generic DoublyLinkedList class which acts the same
 * as a singly linked list except the nodes point to both previous and next nodes,
 * allowing for easier traversal through the list in both directions.
 *
 * This is the exact same program as the one submitted in project 3. It was used
 * to support the Queue class for this project.
 *
 * This program contains no errors and does what it is supposed to do.
 * 
 * @author David Boudreau
 */
package PalindromeCheckerDSA;

/*
Class: DoublyLinkedList
Author: David Boudreau
Parameter: E object
Description: This class constructs DoublyLinkedList objects containing
a generic type. It contains nodes that point both before and after each node.
 */
public class DoublyLinkedList<E> {

    //The node for the first element in the list
    protected Node head;

    /*
    Class: Node
    Author: David Boudreau
    Description: This class stores the object the node points
    to, the node before it, and the node after it
     */
    class Node {

        E data;

        Node next;
        Node prev;

    }

    /*
    Method: addFront
    Author: David Boudreau
    Description: Adds a new node and object
    to the front of the list
    Input: The new data to add
     */
    public void addFront(E newData) {

        Node newNode = new Node();
        newNode.data = newData;

        newNode.next = head;
        newNode.prev = null;

        if (head != null) {
            head.prev = newNode;
        }

        head = newNode;

    }

    /*
    Method: addRear
    Author: David Boudreau
    Description: Adds a new node and object
    to the end of the list
    Input: The new data to add
     */
    public void addRear(E newData) {

        Node newNode = new Node();
        newNode.data = newData;

        Node end = head;

        newNode.next = null;

        if (head == null) {
            newNode.prev = null;
            head = newNode;
            return;
        }

        while (end.next != null) {
            end = end.next;
        }

        end.next = newNode;

        newNode.prev = end;

    }

    /*
    Method: insert
    Author: David Boudreau
    Description: Adds a new node and object
    after a certain object
    Input: The object which the new data will be added after,
    and the new data to add to the new node
     */
    public void insert(E search, E newVal) {

        Node newNode = new Node();
        newNode.data = newVal;

        Node p = head;

        while (!p.data.equals(search)) {
            p = p.next;
        }

        newNode.next = p.next;
        p.next = newNode;

        newNode.prev = p;

        if (newNode.next != null) {
            newNode.next.prev = newNode;
        }

    }

    /*
    Method: delete
    Author: David Boudreau
    Description: Deletes a node containing
    a specific data value
    Input: The data to search for whose node will be
    removed
     */
    public void delete(E search) {

        Node p = head;

        //if deleting the head
        if (p.data.equals(search)) {
            head = p.next;
            head.prev = null;
        }

        if (p.next == null) {
            System.out.print("Object to delete not found.");
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
    public boolean find(E search) {

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
    Method: toString
    Author: David Boudreau
    Description: Converts the list into a string
    representation
    Output: A string representing the list
     */
    @Override
    public String toString() {
        String str1 = "";
        String str2 = "";

        Node curr = head;
        Node end = null;

        while (curr != null) {
            str1 += curr.data + " ";
            end = curr;
            curr = curr.next;

        }

        while (end != null) {
            str2 += end.data + " ";
            end = end.prev;
        }

        String str = "List printed forward: " + str1 + "\n" + "List printed in reverse: " + str2;

        return str;
    }

    //FOR TESTING: this class runs with no errors and it does what it should
    /*
    public static void main(String args[]) {

        DoublyLinkedList<String> lst = new DoublyLinkedList<>();

        lst.addFront("Hello");
        lst.addRear("Hi");
        lst.addRear("Hola");
        lst.addFront("Bueno");
        lst.insert("Hello", "Bonjour");
        lst.delete("Hola");

        System.out.print(lst);
        System.out.print(lst.find("Hello"));
    }*/
}
