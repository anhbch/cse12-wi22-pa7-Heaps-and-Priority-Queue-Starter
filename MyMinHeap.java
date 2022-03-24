
/**
 * Name: Anh Bach
 * ID: A17133630
 * Email: tbach@ucsd.edu
 * Sources used: Zybooks, Oracle, Slides
 * This file contains MyMinHeap class that implements 
 * MinHeapInterface and has all methods in the interface.
 */

// Your import statements
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class contains and implements all the methods in
 * MinHeapInterface.
 */
public class MyMinHeap<E extends Comparable<E>> implements MinHeapInterface <E>{

    public ArrayList<E> data;

    // ===================== Constructor =====================
    /**
     * Constructor to initialize data to an empty ArrayList
     */
    public MyMinHeap() {
        this.data = new ArrayList<>();
    }
    /**
     * Constructor to initialize a min-heap using 
     * the elements in collection
     * @param collection - the collection of elements 
     */
    public MyMinHeap(Collection<? extends E> collection) {
        // Exception
        if (collection == null || collection.contains(null)) {
            throw new NullPointerException();
        }
        
        this.data = new ArrayList<>(collection);
        // Iterate through data backward
        for (int i = this.data.size() - 1; i >= 0; i--) {
            percolateDown(i);
        }
    }

    // ===================== Helper Methods =====================
    /**
     * Swap the elements at from and to indices in data
     * @param from - index of element to be swapped
     * @param to - index of element to be swapped
     */
    protected void swap(int from, int to) {
        // Create a temporary variable to hold data of from
        E temp = data.get(from);
        
        // Swap elements
        data.set(from, data.get(to));
        data.set(to, temp);
    }

    /**
     * Find the parent index of the parameter index.
     * @param index - index of the parameter
     * @return the parent index of the parameter index
     */
    protected int getParentIdx(int index) {
        return (index - 1) / 2;
    }

    /**
     * Find the left child index of the parameter index
     * @param index - index of parameter
     * @return the left child index of the parameter index
     */
    protected int getLeftChildIdx(int index) {
        return (2 * index) + 1;
    }

    /**
     * Find the right child index of the parameter index
     * @param index - index of parameter
     * @return the right child index of the parameter index
     */
    protected int getRightChildIdx(int index) {
        return (2 * index) + 2;
    }

    /**
     * Find index of the smaller child of the element at index
     * @param index - index of element need to find smaller child
     * @return index of the smaller child of the element at index
     */
    protected int getMinChildIdx(int index) {
        // isLeaf
        if ((getRightChildIdx(index) >= data.size())
            && (getLeftChildIdx(index) >= data.size())) {
            return -1;
        }

        // Children indecies
        int left = getLeftChildIdx(index);
        int right = getRightChildIdx(index);
        // Index of a smaller child 
        int minChildIdx;
        // Compare children data
        // If node has only 1 child
        if (left == data.size() - 1) {
            minChildIdx = left;
        }
        // If node has 2 children
        else if (data.get(left).compareTo(data.get(right)) <= 0) {
            minChildIdx = left;
        }
        else {
            minChildIdx = right;
        }
        return minChildIdx;
    }

    /**
     * Percolate the element at index up until no heap properties 
     * are violated by this element
     * @param index - index of element need to percolate up
     */
    protected void percolateUp(int index) {
        int parent = getParentIdx(index);
        // Element's parent is greater
        while (index != 0 && 
            data.get(parent).compareTo(data.get(index)) > 0) {
                swap(index, parent);
                index = parent;
                parent = getParentIdx(index);
        }
    }

    /**
     * Percolate the element at index down 
     * until no heap properties are violated by this element 
     * @param index - index of element need to percolate down
     */
    protected void percolateDown(int index) {
        // Find the child with smallest value
        int minIndex = getMinChildIdx(index);
        while (minIndex != -1 && 
            data.get(index).compareTo(data.get(minIndex)) > 0) {
            swap(index, minIndex);
            index = minIndex;
            minIndex = getMinChildIdx(index);
        }  
    }

    /**
     * Remove the element at index from data and return it
     * @param index - index of the element need to be removed
     * @return element at index from data
     */
    protected E deleteIndex(int index) {
        E removedElement = data.get(index);
        if (index != data.size() - 1) {
            data.set(index, data.get(data.size() - 1));
            data.remove(data.size()-1);
            // Percolate up and down when needed
            percolateUp(index);
            percolateDown(index);
            
        }
        else {
            data.remove(data.size()-1);
        }
        return removedElement;
    }

    // ===================== Core Methods =====================
    /**
     * Add element to the end of the heap
     * @param element - inserted element
     */
    public void insert(E element) {
        // Exception
        if (element == null) {
            throw new NullPointerException();
        }

        // Add element to the end
        data.add(element);
        percolateUp(data.size() - 1);
    }

    /**
     * Get and return root 
     * @return root element of the heap.
     */
    public E getMin() {
        if (data == null || data.size() == 0) {
            return null;
        }
        return data.get(0);
    }

    /**
     * Remove and return root
     * @return return the root 
     */
    public E remove() {
        if (data == null || data.size() == 0) {
            return null;
        }
        E root = data.get(0);
        deleteIndex(0);
        return root;
    }

    /**
     * Find size of min-heap
     * @return number of elements in this min-heap
     */
    public int size() {
        return data.size();
    }

    /**
     * Clear out the entire heap
     */
    public void clear() {
        data.clear();
    }
}

