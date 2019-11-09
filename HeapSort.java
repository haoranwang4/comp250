package 堆排序;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[]arr = {9,6,8,7,0,1,10,4,2};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));

    }






    public static  void heapSort(int[] arr){
        //开始位置为最后一个非叶子节点
        int start = (arr.length-2)/2;
        //把输入数组变成一个大顶堆
        for(int i =start;i>=0;i--){
            maxHeap(arr,arr.length,i);
        }
        //
        for(int j =arr.length-1;j>0;j--){
            int temp = arr[0];
            arr[0]=arr[j];
            arr[j] = temp;
            maxHeap(arr,j,0);





        }




    }

    public static void maxHeap(int[] arr,int size,int index){
        //左子节点
        int leftNode = 2*index+1;
        //右子节点
        int rightNode = 2*index+2;
        //和两个子节点分别对比找出最大的节点
        int max = index;
        if(leftNode<size&&arr[leftNode]>arr[max]) {max = leftNode;}
        if(rightNode<size&&arr[rightNode]>arr[max]) {max = rightNode;}
        //交换位置
        if(max != index){
            int temp = arr[index];
            arr[index] = arr[max];
            arr[max]=temp;
            //交换位置后可能会破坏之前排好的堆，所以之前的排好的堆需要重新调整
            maxHeap(arr,size,max);
        }
    }




}
