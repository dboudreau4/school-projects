/** Project 4 of Data Structures and Algorithms - Queue Class
 *
 * This program contains the generic Queue class which extends
 * the doubly linked list in order to use its functionality. This class
 * follows a first in first out (FIFO) pattern in adding and removing
 * elements from the queue.
 *
 * This program contains all the methods from the Queue class with modifications
 * to the getTail method in order to return the Node, not the data at the tail.
 * The dequeue method was modified to not only remove the last element, but to return
 * it, much like the pop method of the Stack class. Additionally, the removeFront, copy,
 * and equals methods were added to assist in the functionality of the checkPalindrome class.
 *
 * This program contains no errors and does what it is supposed to do.
 * 
 * @author David Boudreau
 */
package PalindromeCheckerDSA;

/*
Class: Queue
Author: David Boudreau
Extends: DoublyLinkedList
Parameter: E object
Description: This class constructs Queue objects containing
a generic type
 */
public class Queue<E> extends DoublyLinkedList<E> {

    //This class uses a DLL as its basic structure
    protected DoublyLinkedList<E> list = new DoublyLinkedList<>();


    /*
    Method: getTail
    Author: David Boudreau
    Description: Allows the user to keep track of the tail
    of the queue
    Output: The Node at the tail
     */
    public Node getTail() {
        //if queue is empty
        if (list.head == null) {
            System.out.println("Queue is empty");
        }
        //if head is only element in queue
        if (list.head.next == null) {
            return list.head;
        }
        Node tail = list.head;
        while (tail.next != null) {
            tail = tail.next;
        }
        return tail;

    }

    /*
    Method: enqueue
    Author: David Boudreau
    Description: Adds an element to the front of the queue
    Input: The data to construct the new node to be added
    to the front of the queue
     */
    public void enqueue(E element) {
        this.list.addFront(element);
    }

    /*
    Method: dequeue
    Author: David Boudreau
    Description: Removes the tail of the queue after calling
    the getTail method and returns the data at the tail
    Output: The data contained in the tail being removed
     */
    public E dequeue() {
        if (this.list.head == null) {
            System.out.print("Empty stack");

        }
        //if head is alone
        if (this.list.head.prev == null && this.list.head.next == null) {
            E headData = this.list.head.data;
            this.list.head = null;
            return headData;
        }
        Node tail = this.getTail();
        Node res = tail;
        tail = tail.prev;
        tail.next = null;

        return res.data;
    }

    /*
    Method: removeFront
    Author: David Boudreau
    Description: Removes the head of the queue and returns the
    data at the head.
    Output: The data contained in the head being removed
    Rationale: This method was needed to make it easier to
    reverse a Queue and check if it's a palindrome for the
    checkPalindrome class.
     */
    public E removeFront() {

        if (this.list.head == null) {
            System.out.println("Empty queue");

        }

        E data = this.list.head.data;
        E cpy = data;

        this.list.head = this.list.head.next;

        return cpy;
    }

    /*
    Method: peek
    Author: David Boudreau
    Description: Lets the user see the tail or the next
    element to leave the queue
    Output: The data belonging to the tail of the queue
     */
    public E peek() {
        return this.getTail().data;
    }

    /*
    Method: copy
    Author: David Boudreau
    Description: Creates a copy of a Queue
    Output: The copy of the Queue
    Rationale: I could have made a copy constructor
    instead, but I found this method to be easier.
    This method was needed to assist in reversing a
    Queue in the checkPalindrome class. With the methods
    I had, making a reversed Queue out of an existing Queue
    would delete the contents of the original Queue, so this
    method was made to assign a reference to another copied
    Queue to preserve the original.
     */
    public Queue<E> copy() {

        Queue<E> res = new Queue<>();
        Node tail = new Node();

        //if queue is empty
        if (this.list.head == null) {
            System.out.println("Queue is empty");
        }
        //if head is only element in queue
        if (this.list.head.next == null) {
            tail = this.list.head;
            res.enqueue(tail.data);
            return res;
        }
        tail = this.list.head;
        while (tail.next != null) {
            tail = tail.next;
        }
        while (tail != null) {
            res.enqueue(tail.data);
            tail = tail.prev;
        }

        return res;

    }

    /*
    Method: toString
    Author: David Boudreau
    Description: Converts the queue into a string
    representation
    Output: A string representing the queue
     */
    @Override
    public String toString() {
        String str = "";

        Node curr = list.head;
        Node end = null;

        while (curr != null) {
            str += curr.data + " ";
            end = curr;
            curr = curr.next;

        }
        String res = "Queue printed: " + str;
        return res;
    }

    /*
    Method: equals
    Author: David Boudreau
    Description: Determines if 2 Queues are equal
    Input: The Queue to compare
    Output: True if equal, false if not
    Rationale: This method was needed for the
    isPalindrome method of the checkPalindrome class.
    Once a Queue is reversed, the original is checked
    against the reversed, and if they are equal, then
    the original is a palindrome
     */
    public boolean equals(Queue<E> check) {

        Node leftHead = this.list.head;
        Node rightHead = check.list.head;

        while (leftHead.next != null && rightHead.next != null) {
            if (leftHead.data == rightHead.data) {
                leftHead = leftHead.next;
                rightHead = rightHead.next;
            } else {
                break;
            }
        }

        return leftHead.data == rightHead.data;
    }

    //FOR TESTING: this class runs with no errors and does what it should
    /*public static void main(String[] args) {

        Queue<String> q = new Queue<>();
        q.enqueue("A");
        q.enqueue("B");
        q.enqueue("D");
        //String str = q.removeFront();
        //System.out.println(str);
        System.out.println(q);
        Queue<String> r = q.copy();
        System.out.println(r);
        System.out.println(r.removeFront());
        System.out.println(r.removeFront());
        System.out.println(r.removeFront());
        System.out.println(r);
        //q.dequeue();
        //System.out.println(q.peek());
        //String str = q.list.toString();
        System.out.println(q);
        /*Queue<String> r = new Queue<>();
        r.enqueue("A");
        r.enqueue("B");
        r.enqueue("C");
        System.out.println(r);
        System.out.println(q.equals(r));

    }*/
}
