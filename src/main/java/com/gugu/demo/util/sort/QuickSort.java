package com.gugu.demo.util.sort;

/**
 * @author Administrator
 * @Classname QuickSort
 * @Description TODO
 * @Date 2021/10/17 16:30
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] strDate = new int[]{11, 66, 22, 0, 55, 22, 0, 32};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(strDate, 0, strDate.length - 1);
        for (int i = 0; i < strDate.length; i++) {
            System.out.print(strDate[i] + "\t");
        }
    }

    public void quickSort(int[] strDate, int left, int right) {
        int middle, tempDate;
        int i = left, j = right;
        middle = strDate[(i + j) / 2];

        do {
            while (strDate[i] > middle && i < right) {
                i++;//找出左边比中间值大的数
            }
            while (strDate[j] < middle && j > left) {
                j--;//找出右边比中间值小的数
            }
            if (i <= j) { //将左边大的数和右边小的数进行替换
                tempDate = strDate[i];
                strDate[i] = strDate[j];
                strDate[j] = tempDate;
                i++;
                j--;
            }
        } while (i <= j); //当两者碰撞时停止
        if (i < right) {
            quickSort(strDate, i, right);
        }
        if (j > left) {
            quickSort(strDate, left, j);
        }
    }
}
