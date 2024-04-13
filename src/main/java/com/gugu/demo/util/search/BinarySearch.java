package com.gugu.demo.util.search;

/**
 * @author Administrator
 * @Classname BinarySerachTest
 * @Description TODO
 * @Date 2021/5/24 22:07
 */
public class BinarySearch {

    public static int binarySerach(int[] array, int key) {
        if (array.length == 0 || array[0] > key || array[array.length - 1] < key) {
            return -1;
        }

        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            if (array[(left + right) / 2] > key) {
                right = (left + right) / 2 - 1;
            } else if (array[(left + right) / 2] < key) {
                left = (left + right) / 2 + 1;
            } else {
                return (left + right) / 2;
            }
        }
        return -1;
    }

    public static int findFirstEqual(int[] array, int key) {

        if (array.length == 0 || array[0] > key || array[array.length - 1] < key) {
            return -1;
        }

        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = array[(left + right) / 2];

            if (mid > key) {
                right = (left + right) / 2 - 1;
            } else if (mid < key) {
                left = (left + right) / 2 + 1;
            } else {
                if((left + right) / 2 == 0 || array[(left + right) / 2 -1 ] != key){
                    return (left + right) / 2;
                }
                right = (left + right) / 2 - 1;
            }
        }
        return -1;
    }

    public static int findLastEqual(int[] array, int key) {

        if (array.length == 0 || array[0] > key || array[array.length - 1] < key) {
            return -1;
        }

        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = array[(left + right) / 2];

            if (mid > key) {
                right = (left + right) / 2 - 1;
            } else if (mid < key) {
                left = (left + right) / 2 + 1;
            } else {
                if((left + right) / 2 == array.length - 1 || array[(left + right) / 2 + 1 ] != key){
                    return (left + right) / 2;
                }
                right = (left + right) / 2 - 1;
            }
        }
        return -1;
    }

}
