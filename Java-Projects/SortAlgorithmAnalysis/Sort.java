/** Project 7 of Data Structures and Algorithms - Sort
 *
 * This is the main program for the project.
 *
 * This program contains the Sort class which contains 4 sorting algorithms:
 * selection sort, shell sort, quick sort, and merge sort. The purpose of this
 * class is to test each of these sorting algorithms on an array of doubles
 * and provide functionality to retrieve information on each one including the
 * time taken to complete a sort and the number of iterations it took to finish
 * the sort. A nested timer class is used to keep track of the time taken.
 *
 * The report on algorithm efficiency is located right above the main method.
 *
 * This program compiles with no errors and does what it should.
 * 
 * @author David Boudreau
 */
package SortAlgorithmAnalysis;

import java.util.Arrays;
import java.util.Random;

/*
Class: Sort
Author: David Boudreau
Description: This class constructs Sort objects
and implements the 4 sorting algorithm methods.
 */
public class Sort {

    /*
    Class: Timer
    Author: David Boudreau
    Description: This class provides functionality
    to time execution time for methods in this project.
    This code can be used to time any method, but it is
    used for each of the sort methods to time their execution.
     */
    public class Timer {

        //keeps track of time
        double count = 0;

        /*
        Method: startTimer
        Author: David Boudreau
        Description: Initializes the count field to current
        system time.
         */
        public void startTimer() {
            count = System.nanoTime();
        }

        /*
        Method: stopTimer
        Author: David Boudreau
        Description: Retrieves the current time and
        subtracts the previous value of the count field
        from it to reinitialize it to the time between
        stop and start.
         */
        public void stopTimer() {
            long stopCount = System.nanoTime();
            count = stopCount - count;
        }

        /*
        Method: getMilli
        Author: David Boudreau
        Description: Converts the count field to
        milliseconds by dividing my 1 million
        Output: The count field in milliseconds
         */
        public double getMilli() {
            return count / 1000000.0;
        }

        /*
        Method: getMicro
        Author: David Boudreau
        Description: Converts the count field to
        microseconds by dividing my 1 thousand
        Output: The count field in microseconds
         */
        public double getMicro() {
            return count / 1000.0;
        }

        /*
        Method: getSecond
        Author: David Boudreau
        Description: Converts the count field to
        seconds by dividing my 1 billion
        Output: The count field in seconds
         */
        public double getSecond() {
            return count / 1000000000.0;
        }
    }

    double[] dbl;
    int[] intg;
    char[] chr;
    float[] flt;
    int itr;    //keeps track of iterations for each sort
    double timer;   //keeps track of time to complete sort

    /*
    Constructor: getMilli
    Author: David Boudreau
    Description: Initializes the array field to the input array
    Input: The array to intialize the field to
     */
    public Sort(double[] arr) {
        this.dbl = arr;
    }

    public Sort(int[] arr) {
        this.intg = arr;
    }

    public Sort(char[] arr) {
        this.chr = arr;
    }

    public Sort(float[] arr) {
        this.flt = arr;
    }

    /*
    Method: getItr
    Author: David Boudreau
    Description: After an array has been sorted, this method can be called
    to return the number of iterations it took to sort based on the last sorting
    method used. The itr field is reset to 0 at the beginning of each sorting
    method and is incremented within each method.
    Output: The number of iterations taken for a sort
     */
    public int getItr() {
        return this.itr;
    }

    /*
    Method: getTime
    Author: David Boudreau
    Description: After an array has been sorted, this method can be called
    to return the time taken to complete the sort in seconds. Each sorting
    method contains their own Timer class objects which keep track of the time,
    and once the methods complete, the timer field is intialized to the time
    recorded by the timer object.
    Output: The time in seconds taken for a sort
     */
    public double getTime() {
        return this.timer;
    }

    /*
    Method: selectionSort
    Author: David Boudreau
    Description: Sorts a double array by succesively moving the next
    smallest element to the beginning of the array. The insertion point
    is updated and moved to the right of the last element inserted.
    Input: A double array to be sorted, and the last index of the array (or the
    maximum position in the array the user wants to sort to).
    Output: The sorted double array
     */
    private double[] selectionSort(double[] list, int last) {

        int smallest = 0;
        double holdData = 0;
        this.itr = 0;
        this.timer = 0;
        Timer t = new Timer();

        //The input array is copied
        double[] copy = new double[last + 1];
        for (int i = 0; i < last + 1; ++i) {
            copy[i] = list[i];
        }

        t.startTimer(); //timer is started

        for (int current = 0; current < last; current++) {
            smallest = current;
            for (int index = current + 1; index <= last; index++) {
                if (copy[index] < copy[smallest]) {
                    smallest = index;
                    this.itr++;
                }

            }
            holdData = copy[current];
            copy[current] = copy[smallest];
            copy[smallest] = holdData;
        }

        t.stopTimer();      //timer is ended
        this.timer = t.getSecond(); //timer field is set to the time taken

        return copy;
    }

    /*
    Method: selectionSort
    Author: David Boudreau
    Description: Public method which calls the private selectionSort method.
    It has the private method take the array field of the class and the last
    index of the array.
    Output: The sorted double array
     */
    public double[] selectionSort() {
        return this.selectionSort(this.dbl, this.dbl.length - 1);
    }

    /*
    Method: shellSort
    Author: David Boudreau
    Description: Sorts a double array by dividing it into segments
    and sorting those individuals segments
    Input: A double array to be sorted
    Output: The sorted double array
     */
    private double[] shellSort(double[] list) {
        double hold;
        int incre;
        int index;
        int last = list.length - 1;
        this.itr = 0;   //iterations and timer reset to 0
        this.timer = 0;

        Timer t = new Timer();

        //Input array is copied
        double[] copy = new double[last + 1];
        for (int i = 0; i < last + 1; ++i) {
            copy[i] = list[i];
        }

        t.startTimer(); //timer is started

        incre = last / 2;
        while (incre != 0) {
            for (int curr = incre; curr <= last; curr++) {
                hold = copy[curr];
                index = curr - incre;
                while (index >= 0 && hold < copy[index]) {
                    //move larger element up in list
                    copy[index + incre] = copy[index];
                    //Fall back one partition
                    index = (index - incre);
                }

                //Insert hold in proper position
                copy[index + incre] = hold;
                this.itr++;
            }

            //Calculate next increment.
            incre = incre / 2;

        }

        t.stopTimer();  //timer is ended
        this.timer = t.getSecond(); //timer field is set to time taken

        return copy;
    }

    /*
    Method: shellSort
    Author: David Boudreau
    Description: Public method which calls the private shellSort using the
    array field as the input
    Output: The sorted double array
     */
    public double[] shellSort() {
        return this.shellSort(this.dbl);
    }

    /*
    Method: selectionSort
    Author: David Boudreau
    Description: Sorts a double array by dividing it succesively until
    only the individual elements are left. The elements are then merged back
    into a sorted list.
    Input: A double array to be sorted, the first index of the array (or the
    lowest index to sort from), and the last index of the array (or the
    maximum position in the array the user wants to sort to).
    Output: The sorted double array
     */
    private double[] mergeSort(double[] arr, int low, int high) {

        int mid;

        if (low < high) {
            mid = (low + high) / 2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            merge(arr, low, high, mid);
        }
        this.itr++;

        return arr;
    }

    /*
    Method: merge
    Author: David Boudreau
    Description: Merges 2 sorted lists into 1 sorted list.
    Input: A double array to be sorted, the first index of the array (or the
    lowest index to sort from), the last index of the array (or the
    maximum position in the array the user wants to sort to), and the midpoint
    index of the 2
     */
    private void merge(double[] arr, int low, int high, int mid) {

        int s1 = mid - low + 1; //1st sublist size
        double[] sub1 = new double[s1]; //1st sublist
        int s2 = high - mid;    //2nd sublist size
        double[] sub2 = new double[s2]; //2nd sublist

        for (int i = 0; i < s1; ++i) {
            sub1[i] = arr[low + i];
        }

        for (int j = 0; j < s2; ++j) {
            sub2[j] = arr[mid + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = low;

        while ((i < s1) && (j < s2)) {
            if (sub1[i] <= sub2[j]) {
                arr[k] = sub1[i];
                k++;
                i++;
            } else {
                arr[k] = sub2[j];
                k++;
                j++;
            }
        }
        while (i < s1) {
            arr[k] = sub1[i];
            k++;
            i++;
        }
        while (j < s2) {
            arr[k] = sub2[j];
            k++;
            j++;
        }
    }

    /*
    Method: mergeSort
    Author: David Boudreau
    Description: Public method which calls the private mergeSort to sort
    the array field of the class. Due to recursion, the iterations are started
    here and the timer is started and ended here.
    Output: The sorted array
     */
    public double[] mergeSort() {
        this.itr = 0;
        this.timer = 0;

        Timer t = new Timer();
        t.startTimer();

        double[] res = this.mergeSort(this.dbl, 0, this.dbl.length - 1);

        t.stopTimer();
        this.timer = t.getSecond();

        return res;
    }

    /*
    Method: quickSort
    Author: David Boudreau
    Description: Determines a pivot location to divide an array into
    3 sublists to sort. The left is sorted first, then the right.
    Input: The array to be sorted, the starting index to sort from (mainly 0),
    and the ending index (mainly the last index of the array)
    Output: The sorted array
     */
    private double[] quickSort(double[] list, int low, int high) {
        double key;
        int i, j;

        if (low < high) {

            key = list[choosePivot(low, high)];
            i = low;
            j = high;

            while (i <= j) {
                while ((i <= high) && (list[i] < key)) {
                    i++;
                }
                while ((j >= low) && (list[j] > key)) {
                    j--;
                }
                if (i <= j) {
                    swap(list, i, j);
                    i++;
                    j--;
                }
            }

            //recursively sort the lesser list
            if (j > low) {
                quickSort(list, low, j);
            }

            //recursively sort the greater list
            if (high > i) {
                quickSort(list, i, high);
            }
        }

        this.itr++;

        return list;
    }

    /*
    Method: quickSort
    Author: David Boudreau
    Description: Public method which calls the private quickSort to sort the
    array field of the class. The time to execute is measured here due to
    recursion in the private method, and the iterations are intialized to 0 here.
    Output: The sorted array
     */
    public double[] quickSort() {

        double[] copy = new double[this.dbl.length];
        for (int i = 0; i < this.dbl.length; ++i) {
            copy[i] = this.dbl[i];
        }
        this.itr = 0;
        this.timer = 0;

        Timer t = new Timer();
        t.startTimer();

        double[] res = this.quickSort(copy, 0, copy.length - 1);

        t.stopTimer();
        this.timer = t.getSecond();

        return res;
    }

    /*
    Method: choosePivot
    Author: David Boudreau
    Description: Calculates the pivot for quickSort based on 2 input
    indices
    Input: The low and high end indices to determine the pivot
    Output: The pivot or middle between the inputs
     */
    private int choosePivot(int i, int j) {

        return ((i + j) / 2);
    }

    /*
    Method: swap
    Author: David Boudreau
    Description: Moves the contents of 2 indices in an array over
    Input: The array to move contents around, the first index and second
     */
    private void swap(double[] list, int m, int j) {

        double tmp = list[m];
        list[m] = list[j];
        list[j] = tmp;
    }

    /*
    Method: main
    Author: David Boudreau
    Description: This tests each of the 4 sorting algorithms on arrays of
    random doubles of size 1,000, 10,000, and 100,000. Once each method is called,
    the iterations and times for each sort are printed. The lines to print the
    sorted arrays are commented out since there's no reason for them to be there
    for these massive arrays. However, they can be uncommented if needed.

    Report:

    With an array size of 1,000, the fastest algorithm is the shell sort,
    followed by quick sort, then merge sort, and finally selection is the slowest.
    Although quick sort is slightly slower than shell sort, shell sort takes 10 times
    more iterations than quick sort. As a result, I believe quick sort is more
    efficient for this array size.

    With an array of size 10,000, the fastest algorithm is quick sort, followed
    closely by merge sort, which is followed even closer by shell sort, and finally
    selection is considerably slower than all the others. Quick sort is roughly
    50% faster than merge sort, and takes less than half the iterations to complete,
    so quick sort appears to be the most efficient for this array size.

    With an array of size 100,000, the fastest algorithm is quick sort, which generally
    takes about half the time of both shell and merge sort, which are nearly identical in
    time. Again, selection sort is significantly slower than the others by nearly a factor
    of 500. Since the pivot of the merge sort is always the mid point, it appears
    to behave at the same algorithm complexity (O(n^2)) as shell sort. Quick sort again
    is the most efficient because it not only takes the least time to execute, but
    it also takes a significantly smaller number of iterations to execute.


     */
    public static void main(String[] args) {

        //Test arry of size 1,000
        double[] arr = new double[1000];

        Random r = new Random();

        for (int i = 0; i < arr.length; ++i) {
            arr[i] = 1000 * r.nextDouble();
        }

        Sort srt = new Sort(arr);

        System.out.println("TEST OF SIZE 1,000: " + "\n");

        double[] sel = srt.selectionSort();
        //System.out.println(Arrays.toString(sel));
        System.out.println("Iterations for selection sort: " + srt.getItr());
        System.out.println("Time for selection sort: " + srt.getTime());

        double[] shell = srt.shellSort();
        //System.out.println(Arrays.toString(shell));
        System.out.println("Iterations for shell sort: " + srt.getItr());
        System.out.println("Time for shell sort: " + srt.getTime());

        double[] quick = srt.quickSort();
        //System.out.println(Arrays.toString(quick));
        System.out.println("Iterations for quick sort: " + srt.getItr());
        System.out.println("Time for quick sort: " + srt.getTime());

        double[] merge = srt.mergeSort();
        //System.out.println(Arrays.toString(merge));
        System.out.println("Iterations for merge sort: " + srt.getItr());
        System.out.println("Time for merge sort: " + srt.getTime() + "\n");

        //Test arry of size 10,000
        double[] arr2 = new double[10000];

        Random r2 = new Random();

        for (int i = 0; i < arr2.length; ++i) {
            arr2[i] = 10000 * r2.nextDouble();
        }

        Sort srt2 = new Sort(arr2);

        System.out.println("TEST OF SIZE 10,000: " + "\n");

        double[] sel2 = srt2.selectionSort();
        //System.out.println(Arrays.toString(sel2));
        System.out.println("Iterations for selection sort: " + srt2.getItr());
        System.out.println("Time for selection sort: " + srt2.getTime());

        double[] shell2 = srt2.shellSort();
        //System.out.println(Arrays.toString(shell2));
        System.out.println("Iterations for shell sort: " + srt2.getItr());
        System.out.println("Time for shell sort: " + srt2.getTime());

        double[] quick2 = srt2.quickSort();
        //System.out.println(Arrays.toString(quick2));
        System.out.println("Iterations for quick sort: " + srt2.getItr());
        System.out.println("Time for quick sort: " + srt2.getTime());

        double[] merge2 = srt2.mergeSort();
        //System.out.println(Arrays.toString(merge2));
        System.out.println("Iterations for merge sort: " + srt2.getItr());
        System.out.println("Time for merge sort: " + srt2.getTime() + "\n");

        //Test arry of size 100,000
        double[] arr3 = new double[100000];

        Random r3 = new Random();

        for (int i = 0; i < arr3.length; ++i) {
            arr3[i] = 100000 * r3.nextDouble();
        }

        Sort srt3 = new Sort(arr3);

        System.out.println("TEST OF SIZE 100,000: " + "\n");

        double[] sel3 = srt3.selectionSort();
        //System.out.println(Arrays.toString(sel3));
        System.out.println("Iterations for selection sort: " + srt3.getItr());
        System.out.println("Time for selection sort: " + srt3.getTime());

        double[] shell3 = srt3.shellSort();
        //System.out.println(Arrays.toString(shell3));
        System.out.println("Iterations for shell sort: " + srt3.getItr());
        System.out.println("Time for shell sort: " + srt3.getTime());

        double[] quick3 = srt3.quickSort();
        //System.out.println(Arrays.toString(quick3));
        System.out.println("Iterations for quick sort: " + srt3.getItr());
        System.out.println("Time for quick sort: " + srt3.getTime());

        double[] merge3 = srt3.mergeSort();
        //System.out.println(Arrays.toString(merge3));
        System.out.println("Iterations for merge sort: " + srt3.getItr());
        System.out.println("Time for merge sort: " + srt3.getTime() + "\n");

    }
}
