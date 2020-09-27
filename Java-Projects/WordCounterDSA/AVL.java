/** Project 5 of Data Structures and Algorithms - AVL Class
 *
 * This program contains the generic AVL class which is a tree
 * structure that is derived from the BST class, but with extra
 * functionality to ensure the rules of the AVL tree. AVL trees must
 * be balanced such that the left subtree from the root isn't more
 * than one level higher than the right subtree and vice versa.
 *
 * This program contains no errors and does what it is supposed to do.
 * 
 * @author David Boudreau
 */
package WordCounterDSA;

/*
Class: AVL
Author: David Boudreau
Parameter: An E object which extends the Comparable interface
Extends: BST
Description: This class constructs AVL objects containing
a generic type. It contains nodes that point to both left and right
children per node and a root node at the base. The nodes contain height
parameters to keep track of the subtree heights.
 */
public class AVL<E extends Comparable<E>> extends BST<E> {

    /*
    Class: AVLNode
    Author: David Boudreau
    Extends: Node from BST to inherit the Node functionality
    Description: This class stores the object the node references,
    the two children belonging to the node, the height, and the counter
    to keep track of the number of attempts at adding the node. There
    are no duplicates in the BST or AVL trees.
     */
    public class AVLNode extends Node {

        AVLNode lt;
        AVLNode rt;
        int height;
        int counter;

        AVLNode(E data, AVLNode r, AVLNode l) {
            super(data);
            this.lt = l;
            this.rt = r;
            this.counter = 0;       //the counter is set to 0 upon construction
        }
    }

    //Same as the root from BST, but named orig to avoid hiding
    protected AVLNode orig;

    //Like the BST, the orig or root node is set to null upon construction
    public <E> AVL() {
        this.orig = null;
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
    Output: A method call to balance which returns
    an AVLNode once the data is removed and the tree
    is balanced
     */
    private AVLNode remove(E data, AVLNode n) {

        if (n == null) {
            return n;
        }
        if (data.compareTo(n.data) < 0) {
            n.lt = remove(data, n.lt);
        } else if (data.compareTo(n.data) > 0) {
            n.rt = remove(data, n.rt);
        } else {

            if (n.lt == null && n.rt == null) {
                return null;
            } else if (n.rt == null) {
                return n.lt;
            } else if (n.lt == null) {
                return n.rt;
            } else {

                n.data = findMin(n.rt).data;
                n.rt = remove(n.data, n.rt);
            }

        }

        return balance(n);

    }

    /*
    Method: remove
    Author: David Boudreau
    Description: Public method to remove
    data from the tree. It calls the private method
    to search for the data starting at the orig.
    Input: The data to be removed
    Override: The BST remove method
     */
    @Override
    public void remove(E data) {
        this.orig = this.remove(data, this.orig);
    }

    /*
    Method: findNode
    Author: David Boudreau
    Description: Private method to search for
    a piece of data in the tree and return the
    node holding the data. It uses recursion
    until the search is over. This method does the
    same thing as the contains method, but it returns
    a node instead of a boolean.
    Input: The data to be searched for and a reference node
    Output: The AVLNode node containing the data
    Rationale: This method made things much easier in retrieving
    nodes and updating their counters for the WordCount class.
     */
    private AVLNode findNode(E data, AVLNode n) {

        if (n == null) {
            return n;
        } else if (data.compareTo(n.data) < 0) {
            return findNode(data, n.lt);
        } else if (data.compareTo(n.data) > 0) {
            return findNode(data, n.rt);
        } else {
            return n;
        }

    }

    /*
    Method: findNode
    Author: David Boudreau
    Description: Public method to find data
    in the tree. It calls the private method
    to search for the data starting at the root.
    Input: The data to be searched
    Output: The node containing the data being searched
     */
    public AVLNode findNode(E data) {
        return this.findNode(data, this.orig);
    }

    /*
    Method: updateCounter
    Author: David Boudreau
    Description: Public method increment the counter
    field of an AVLNode.
    Input: The node whose counter is to be incremented
    Rationale: This was needed to increment the counter
    effectively in the WordCount class
     */
    public void updateCounter(AVLNode n) {
        n.counter = n.counter + 1;
    }

    /*
    Method: insert
    Author: David Boudreau
    Description: Private method to add
    data to the tree. Uses recursion until
    the correct position to insert is found.
    Input: The new data to add, the reference
    node to place the new data to the left or right of it
    Output: A method call to balance which returns
    an AVLNode once the data is added and the tree
    is balanced
     */
    private AVLNode insert(E data, AVLNode n) {

        if (n == null) {
            return new AVLNode(data, null, null);
        }
        if (data.compareTo(n.data) < 0) {
            n.lt = insert(data, n.lt);
        } else if (data.compareTo(n.data) > 0) {
            n.rt = insert(data, n.rt);
        }

        return balance(n);

    }

    /*
    Method: insert
    Author: David Boudreau
    Description: Public method to add
    data to the tree. It calls the private method
    to add the data in the correct position
    starting at the orig.
    Input: The data to add
    Override: The BST remove method
     */
    @Override
    public void insert(E data) {
        this.orig = this.insert(data, this.orig);
    }

    /*
    Method: findMin
    Author: David Boudreau
    Description: Private method to find the smallest
    data value in the tree. It uses recursion until the
    search is over.
    Input: The reference node to start the search at
    Output: The node containing the smallest data value
    in the tree
     */
    private AVLNode findMin(AVLNode n) {

        if (n == null) {
            return n;
        }
        while (n.lt != null) {
            n = n.lt;
        }

        return n;
    }

    /*
    Method: findMin
    Author: David Boudreau
    Description: Public method to find
    the smallest value in the tree.
    It calls the private method using the root
    as the reference node.
    Output: The smallest data value in the tree
    Override: The BST findMin method
     */
    @Override
    public E findMin() {
        return this.findMin(this.orig).data;
    }

    /*
    Method: findMax
    Author: David Boudreau
    Description: Private method to find the biggest
    data value in the tree. It uses recursion until the
    search is over.
    Input: The reference node to start the search at
    Output: The node containing the biggest data value
    in the tree
     */
    private AVLNode findMax(AVLNode n) {
        if (n == null) {
            return n;
        }
        while (n.rt != null) {
            n = n.rt;
        }
        return n;
    }

    /*
    Method: findMax
    Author: David Boudreau
    Description: Public method to find
    the biggest value in the tree.
    It calls the private method using the root
    as the reference node.
    Output: The biggest data value in the tree
    Override: The BST findMax method
     */
    @Override
    public E findMax() {
        return this.findMax(this.orig).data;
    }

    /*
    Method: isEmpty
    Author: David Boudreau
    Description: Determines if the tree
    is empty by checking if the orig is null
    Output: True if empty, false if not
    Override: BST isEmpty method
     */
    @Override
    public boolean isEmpty() {
        return this.orig == null;
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
    private boolean contains(E data, AVLNode n) {

        if (n == null) {
            return false;
        } else if (data.compareTo(n.data) < 0) {
            return contains(data, n.lt);
        } else if (data.compareTo(n.data) > 0) {
            return contains(data, n.rt);
        } else {
            return true;
        }

    }

    /*
    Method: contains
    Author: David Boudreau
    Description: Public method to determine
    if a piece of data is contained in the tree.
    It calls the private method using the orig
    as the reference node.
    Input: The data to be searched for
    Output: True if found, false if not
    Override: BST contains method
     */
    @Override
    public boolean contains(E data) {
        return this.contains(data, this.orig);
    }

    /*
    Method: printTree
    Author: David Boudreau
    Description: Private method to print the tree
    from least to greatest data values
    Input: The node to start printing from
     */
    private void printTree(AVLNode n) {

        if (n != null) {
            printTree(n.lt);
            System.out.println(n.data);
            printTree(n.rt);
        }

    }

    /*
    Method: printTree
    Author: David Boudreau
    Description: Public method to print the tree.
    It calls the private method using the root
    as the input.
    Override: The BST printTree method
     */
    @Override
    public void printTree() {

        if (this.isEmpty()) {
            System.out.println("Empty tree");
        } else {
            this.printTree(this.orig);
        }

    }

    /*
    Method: makeEmpty
    Author: David Boudreau
    Description: Public method to empty
    the tree by setting the root/orig to null.
     */
    public void makeEmpty() {
        this.orig = null;
    }

    /*
    Method: height
    Author: David Boudreau
    Description: Private method to determine
    a node's height in the tree.
    Input: The node to height check
    Output: The height of the node as an int
     */
    private int height(AVLNode n) {
        return n == null ? -1 : n.height;
    }

    /*
    Method: rotateWithLeftChild
    Author: David Boudreau
    Description: Rotates a node with a left child,
    updates the height
    Input: The node to rotate
    Output: A new node
     */
    private AVLNode rotateWithLeftChild(AVLNode k2) {

        AVLNode k1 = k2.lt;
        k2.lt = k1.rt;
        k1.rt = k2;
        k2.height = Math.max(height(k2.lt), height(k2.rt)) + 1;
        k1.height = Math.max(height(k1.lt), k2.height) + 1;
        return k1;
    }

    /*
    Method: rotateWithRightChild
    Author: David Boudreau
    Description: Rotates a node with a right child,
    updates the height
    Input: The node to rotate
    Output: A new node
     */
    private AVLNode rotateWithRightChild(AVLNode k1) {

        AVLNode k2 = k1.rt;
        k1.rt = k2.lt;
        k2.lt = k1;
        k1.height = Math.max(height(k1.lt), height(k1.rt)) + 1;
        k2.height = Math.max(height(k2.rt), k1.height) + 1;
        return k2;
    }

    /*
    Method: doubleWithLeftChild
    Author: David Boudreau
    Description: Rotates a node with left child twice
    Input: The node to rotate
    Output: A new node
     */
    private AVLNode doubleWithLeftChild(AVLNode k3) {
        k3.lt = this.rotateWithRightChild(k3.lt);
        return this.rotateWithLeftChild(k3);
    }

    /*
    Method: doubleWithRightChild
    Author: David Boudreau
    Description: Rotates a node with right child twice
    Input: The node to rotate
    Output: A new node
     */
    private AVLNode doubleWithRightChild(AVLNode k1) {

        k1.rt = this.rotateWithLeftChild(k1.rt);
        return this.rotateWithRightChild(k1);

    }

    /*
    Method: checkBalance
    Author: David Boudreau
    Description: checks the balance given a certain node
    Input: The node to be checked
    Output: A method call to height to get the height of the node
    as an int
     */
    private int checkBalance(AVLNode n) {

        if (n == null) {
            return -1;
        } else {
            int hl = this.checkBalance(n.lt);
            int hr = this.checkBalance(n.rt);
            if (Math.abs(height(n.lt) - height(n.rt)) > 1
                    || height(n.lt) != hl || height(n.rt) != hr) {
                System.out.println("Unbalanced");
            }
        }

        return this.height(n);

    }

    /*
    Method: checkBalance
    Author: David Boudreau
    Description: checks the balance of the whole tree
    by calling the private method with orig as input
    Input: The node to rotate
    Output: A new node
     */
    public void checkBalance() {
        this.checkBalance(this.orig);
    }

    //sets the allowed imbalance of the AVL tree to be 1
    private static final int ALLOWED_IMBALANCE = 1;

    /*
    Method: balance
    Author: David Boudreau
    Description: Makes the AVL tree adhere to the
    allowed imbalance of 1 by doing any rotations necessary
    Input: The node to start as a reference
    Output: A new node
     */
    private AVLNode balance(AVLNode n) {

        if (n == null) {
            return n;
        }

        if (height(n.lt) - height(n.rt) > ALLOWED_IMBALANCE) {
            if (height(n.lt.lt) >= height(n.lt.rt)) {
                n = this.rotateWithLeftChild(n);
            } else {
                n = this.doubleWithLeftChild(n);
            }
        } else {
            if (height(n.rt) - height(n.lt) > ALLOWED_IMBALANCE) {
                if (height(n.rt.rt) >= height(n.rt.lt)) {
                    n = this.rotateWithRightChild(n);
                } else {
                    n = this.doubleWithRightChild(n);
                }
            }
        }

        n.height = Math.max(height(n.lt), height(n.rt)) + 1;
        return n;

    }

    //FOR TESTING: this class runs with no errors and it does what it should
    /*public static void main(String[] args) {
        AVL<Integer> avl = new AVL<>();
        avl.insert(23);
        avl.insert(18);
        avl.insert(44);
        avl.insert(6);
        avl.insert(12);
        avl.insert(52);
        avl.insert(14);
        avl.insert(8);

        avl.printTree();
        System.out.println("After Remove");

        avl.remove(52);
        avl.printTree();
    }*/
}
