public class BinarySearch {
    public static void main(String[] args) {
        int[]arr = {1,2,3,4,5};
        int result = binarySearch(arr,1,0,arr.length-1);
        System.out.println(result);
    }

//返回值为int的时候 recursive call 也需要return
    public static int binarySearch(int[]arr,int target,int start,int end) {
        if (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid]>target){
                return binarySearch(arr,target,start,mid-1);
            }else {
                return binarySearch(arr,target,mid+1,end);
            }

        }
        return -1;

    }


}
