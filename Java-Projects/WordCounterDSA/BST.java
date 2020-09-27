/** Project 5 of Data Structures and Algorithms - BST Class
 *
 * This program contains the generic Binary Search Tree (BST) class which is a tree
 * structure with a root node at the first level and with every node being able to have
 * both a left leaf and right leaf node.
 *
 * This program contains no errors and does what it is supposed to do.
 * 
 * @author David Boudreau
 */
package WordCounterDSA;

/*
Class: BST
Author: David Boudreau
Parameter: An E object which extends the Comparable interface
Description: This class constructs BST objects containing
a generic type. It contains nodes that point to both left and right
children per node and a root node at the base.
 */
public class BST<E extends Comparable<E>> {

    /*
    Class: Node
    Author: David Boudreau
    Description: This class stores the object the node references,
    and the two children belonging to the node.
     */
    public class Node {

        E data;

        Node left;
        Node right;

        Node(E data) {
            this.data = data;
        }

    }

    //The root node of the tree
    Node root;

    //Constructor for the BST setting the tree's node to null initially
    public <E> BST() {
        this.root = null;
    }

    /*
    Method: insert
    Author: David Boudreau
    Description: Private method to add
    data to the tree. Uses recursion until
    the correct position to insert is found.
    Input: The new data to add, the reference
    node to place the new data to the left or right of it
     */
    private void insert(E data, Node n) {

        if (this.root == null) {
            this.root = new Node(data);
        } else if (data.compareTo(n.data) < 0) {
            if (n.left != null) {
                insert(data, n.left);
            } else {
                n.left = new Node(data);
            }
        } else if (data.compareTo(n.data) > 0) {
            if (n.right != null) {
                insert(data, n.right);
            } else {
                n.right = new Node(data);
            }
        }

    }

    /*
    Method: insert
    Author: David Boudreau
    Description: Public method to add data to
    the tree. It calls the private method to add
    the data using the root node as a reference.
    Input: The new data to add
     */
    public void insert(E data) {
        this.insert(data, this.root);
    }

    /*
    Method: remove
    Author: David Boudreau
    Description: Private method to remove
    a piece of data from the tree. It uses
    recursion until the data being searched for
    is found.
    Input: The data to be removed and a reference node
    to start searching from
    Output: The node being removed
     */
    private Node remove(E data, Node n) {

        if (this.root == null) {
            return n;
        }
        if (data.compareTo(n.data) < 0) {
            n.left = this.remove(data, n.left);
        } else if (data.compareTo(n.data) > 0) {
            n.right = this.remove(data, n.right);
        } else {

            if (n.left == null && n.right == null) {
                return null;
            } else if (n.right == null) {
                return n.left;
            } else if (n.left == null) {
                return n.right;
            } else {

                n.data = findMin(n.right);
                n.right = remove(n.data, n.right);
            }
        }

        return n;
    }

    /*
    Method: remove
    Author: David Boudreau
    Description: Public method to remove
    data from the tree. It calls the private method
    to search for the data starting at the root.
    Input: The data to be removed
     */
    public void remove(E data) {
        this.root = this.remove(data, this.root);
    }

    /*
    Method: contains
    Author: David Boudreau
    Description: Private method to determine
    if a piece of data is contained in the tree.
    It uses recursion until the search is over.
    Input: The data to be searched for and a
    reference node
    Output: True if found, false if not
     */
    private boolean contains(E data, Node n) {

        if (n == null) {
            return false;
        } else if (data.compareTo(n.data) < 0) {
            return contains(data, n.left);
        } else if (data.compareTo(n.data) > 0) {
            return contains(data, n.right);
        } else {
            return true;
        }

    }

    /*
    Method: contains
    Author: David Boudreau
    Description: Public method to determine
    if a piece of data is contained in the tree.
    It calls the private method using the root
    as the reference node.
    Input: The data to be searched for
    Output: True if found, false if not
     */
    public boolean contains(E data) {
        return this.contains(data, this.root);
    }

    /*
    Method: findMin
    Author: David Boudreau
    Description: Private method to find the smallest
    data value in the tree. It uses recursion until the
    search is over.
    Input: The reference node to start the search at
    Output: The smallest data value in the tree
     */
    private E findMin(Node n) {

        if (n.left == null) {
            return n.data;
        } else {
            return findMin(n.left);
        }
    }

    /*
    Method: findMin
    Author: David Boudreau
    Description: Public method to find
    the smallest value in the tree.
    It calls the private method using the root
    as the reference node.
    Output: The smallest data value in the tree
     */
    public E findMin() {
        return this.findMin(this.root);
    }

    /*
    Method: findMax
    Author: David Boudreau
    Description: Private method to find the biggest
    data value in the tree. It uses recursion until the
    search is over.
    Input: The reference node to start the search at
    Output: The largest data value in the tree
     */
    private E findMax(Node n) {
        if (n.right == null) {
            return n.data;
        } else {
            return findMax(n.right);
        }
    }

    /*
    Method: findMax
    Author: David Boudreau
    Description: Public method to find
    the biggest value in the tree.
    It calls the private method using the root
    as the reference node.
    Output: The biggest data value in the tree
     */
    public E findMax() {
        return this.findMax(this.root);
    }

    /*
    Method: isEmpty
    Author: David Boudreau
    Description: Determines if the tree
    is empty by checking if the root is null
    Output: True if empty, false if not
     */
    public boolean isEmpty() {
        return this.root == null;
    }

    /*
    Method: printTree
    Author: David Boudreau
    Description: Private method to print the tree.
    It uses recursion to print the reference node's
    data first, then its left subtree, then its right
    subtree.
    Input: The node to start the printing from
     */
    private void printTree(Node n) {

        if (n != null) {
            System.out.println(n.data);
            this.printTree(n.left);
            this.printTree(n.right);
        }

    }

    /*
    Method: printTree
    Author: David Boudreau
    Description: Public method to print the tree.
    It uses the private method to start printing from
    the root. The root is printed first, then everything
    to its left, then everything to its right.
     */
    public void printTree() {
        this.printTree(this.root);
    }

    //FOR TESTING: this class runs with no errors and it does what it should
    /*public static void main(String[] args) {

        BST<Integer> tree = new BST<>();

        tree.insert(1);
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(2);

        if (tree.contains(10)) {
            System.out.println("Found");
        } else {
            System.out.println("Not found");
        }
        tree.printTree();
        System.out.println("Remove node\n");
        tree.remove(15);
        tree.printTree();

        if (tree.contains(15)) {
            System.out.println("Found");
        } else {
            System.out.println("Not found");
        }
    }*/
}
