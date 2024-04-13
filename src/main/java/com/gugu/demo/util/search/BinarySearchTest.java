package com.gugu.demo.util.search;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * BinarySerachTest Tester.
 *
 * @author <Authors name>
 * @version 1.0
 */
public class BinarySearchTest {

    @BeforeEach
    public void before() throws Exception {
    }

    @AfterEach
    public void after() throws Exception {
    }

    /**
     * Method: binarySerach(int[] array, int key)
     */
    @Test
    public void testBinarySerach() throws Exception {
        int[] array = new int[]{1, 3, 5, 7, 9};
        System.out.println(BinarySearch.binarySerach(array, 1));
        System.out.println(BinarySearch.binarySerach(array, 2));
        System.out.println(BinarySearch.binarySerach(array, 8));
        System.out.println(BinarySearch.binarySerach(array, 5));
    }

    /**
     * Method: findFirstEqual(int[] array, int key)
     */
    @Test
    public void testFindFirstEqual() throws Exception {
        int[] array = new int[]{1, 3, 5, 5, 7, 7, 9};
        System.out.println(BinarySearch.findFirstEqual(array, 1));
        System.out.println(BinarySearch.findFirstEqual(array, 2));
        System.out.println(BinarySearch.findFirstEqual(array, 8));
        System.out.println(BinarySearch.findFirstEqual(array, 5));
        System.out.println(BinarySearch.findFirstEqual(array, 7));
        System.out.println(BinarySearch.findFirstEqual(array, 9));
    }

    @Test
    public void testFindLastEquall() throws Exception {
        int[] array = new int[]{1, 3, 5, 5, 7, 7, 9};
        System.out.println(BinarySearch.findLastEqual(array, 1));
        System.out.println(BinarySearch.findLastEqual(array, 2));
        System.out.println(BinarySearch.findLastEqual(array, 8));
        System.out.println(BinarySearch.findLastEqual(array, 5));
        System.out.println(BinarySearch.findLastEqual(array, 7));
        System.out.println(BinarySearch.findLastEqual(array, 9));
    }
} 
