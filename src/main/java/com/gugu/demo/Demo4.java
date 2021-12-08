package com.gugu.demo;

/**
 * @author Administrator
 * @Classname Demo4
 * @Description TODO
 * @Date 2021/12/1 23:28
 */
public class Demo4 {
    public static void main(String[] args) {
        // 假设全是降序
        int [][] source = new int[1000][];
        System.out.println(getTop10(source));
    }

    private static int[] getTop10(int[][] source) {
        // 拆分
        return getTop10(getTop10(source, 0,(source.length+1)/2) , getTop10(source, (source.length+1)/2,source.length));
    }

    private static int[] getTop10(int[][] source, int start, int end) {
        if (end - start > 2){
            return getTop10(getTop10(source, start,(end+1-start)/2) , getTop10(source, (end+1-start)/2,end));
        }
        return getTop10(source[start], source[end]);

    }
    /**
     * @Description 获取两个素组的top10
     */
    
    private static int[] getTop10(int[] a, int[] b){
        int[] res = new int[10];
        int i = 0;
        int j = 0;
        while (i<a.length && j<b.length && i+j < 10){
            if (a[i] >= b[j]){
                res[i+j] = a[i--];
            }else if (a[i] < b[j]){
                res[i+j] = b[j--];
            }
        }
        return res;
    }

}
