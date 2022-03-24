/**
 * Name: Anh Bach
 * ID: A17133630
 * Email: tbach@ucsd.edu
 * Sources used: None
 * 
 * This file contains all the custom tests for 
 * MyMinHeap
 */

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class contains custom test cases for MyMinHeap 
 * 
 * IMPORTANT: Do not change the method names and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class CustomTester {
    
    Integer[] arr = {2,3,4,5,10,15};
    Collection<Integer> col = new ArrayList<>();
    MyMinHeap<Integer> minHeap = new MyMinHeap<>();
    MyMinHeap<Integer> minHeap2 = new MyMinHeap<>();


    /**
     * Test the constructor when min-heap using collection
     */
    @Test
    public void testMyMinHeapConstructor() {
        col.add(1);
        col.add(2);
        col.add(4);
        col.add(3);
        col.add(7);

        MyMinHeap<Integer> minHeap = new MyMinHeap<>(col);
        Integer[] expected = {1,2,4,3,7};
        for (int i = 0; i < 5; i++) {
            assertEquals(expected[i], minHeap.data.get(i));
        } 
    }

    /**
     * Test the getMinChildIdx method when node is a leaf
     */
    @Test
    public void testGetMinChildIdx() {
        // Add element to min heap
        for (int i = 0; i < 6; i++) {
            minHeap.insert(arr[i]);
        }

        assertEquals(-1, minHeap.getMinChildIdx(3));
    }

    /**
     * Test the percolateUp method when two nodes are equal
     */
    @Test
    public void testPercolateUp() {
        Integer[] intArr = {2,3,6,3,8};
        for (int i = 0; i < 5; i++) {
            minHeap.insert(intArr[i]);
        }

        Integer[] expected = {2,3,6,3,8};
        minHeap.percolateUp(3);
        for (int i = 0; i < 5; i++) {
            assertEquals("Should not change since child is equal to parent",
                expected[i], minHeap.data.get(i));
        }
        
    }

    /**
     * Test the percolateDown method when node is a leaf
     */
    @Test
    public void testPercolateDown() {
        Integer[] intArr = {2,3,6,3,8};
        for (int i = 0; i < 5; i++) {
            minHeap.insert(intArr[i]);
        }

        Integer[] expected = {2,3,6,3,8};
        minHeap.percolateDown(4);
        for (int i = 0; i < 5; i++) {
            assertEquals("Should not change since node is a leaf",
                expected[i], minHeap.data.get(i));
        }
    }

    /**
     * Test the deleteIndex method when node is at middle of the heap
     */
    @Test
    public void testDeleteIndex() {
        // Example 1: percolate down
        Integer[] intArr = {2,3,6,3,8};
        for (int i = 0; i < 5; i++) {
            minHeap.insert(intArr[i]);
        }
        minHeap.deleteIndex(1);
        Integer[] expected = {2,3,6,8};
        for (int i = 0; i < 4; i++) {
            assertEquals(expected[i], minHeap.data.get(i));
        }
        // Check size
        assertEquals(4, minHeap.data.size());

        // Example 2: percolate up
        Integer[] intArr1 = {1,20,10,30,50,11,15,31,32,51,52,12,13,16,17};
        for (int i = 0; i < 15; i++) {
            minHeap2.insert(intArr1[i]);
        }
        minHeap2.deleteIndex(4);
        Integer[] expected1 = {1,17,10,30,20,11,15,31,32,51,52,12,13,16};
        for (int i = 0; i < 14; i++) {
            assertEquals(expected1[i], minHeap2.data.get(i));
        }
        // Check size
        assertEquals(14, minHeap2.data.size());
    }

    /**
     * Test the deleteIndex method when node is at the end of the heap
     */
    @Test
    public void testDeleteIndex2() {

        Integer[] intArr = {2,3,6,3,8};
        for (int i = 0; i < 5; i++) {
            minHeap.insert(intArr[i]);
        }
        minHeap.deleteIndex(4);
        Integer[] expected = {2,3,6,3};
        for (int i = 0; i < 4; i++) {
            assertEquals(expected[i], minHeap.data.get(i));
        }
        // Check size
        assertEquals(4, minHeap.data.size());
    }

    /**
     * Test the insert method when element is null
     */
    @Test
    public void testInsert(){
        try {
            minHeap.insert(null);
            fail();
        }
        catch (NullPointerException e) {
            System.out.print("Exception!");
        }
    }

    /**
     * Test the insert method when add many new element to the null heap
     */
    @Test
    public void testInsert2(){
        minHeap2.insert(3);
        minHeap2.insert(6);
        minHeap2.insert(5);
        minHeap2.insert(8);
        minHeap2.insert(9);
        minHeap2.insert(1);

        Integer[] expected = {1,6,3,8,9,5};
        for (int i = 0; i < 6; i++) {
            assertEquals(expected[i], minHeap2.data.get(i));
        }
        // Check size
        assertEquals(6, minHeap2.data.size());
    }

   
    /**
     * Test remove when heap is empty
     */
    @Test
    public void testRemove(){
        // Heap is empty
        assertNull("Should return null", minHeap.remove());
    }

  
    /**
     * Test getMin when heap is empty
     */
    @Test
    public void testGetMin(){
        assertNull("Should return null", minHeap.getMin());
    }
}