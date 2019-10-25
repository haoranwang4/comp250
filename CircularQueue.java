public class CircularQueue {
    private int[] arr;
    private int head ;
    private int size ;


    public CircularQueue() {
        this.size=0;
        this.head=0;
        this.arr = new int[5];
    }

    public int[] getArr() {
        return arr;
    }

    //1 2 3 4
    public void enquene (int num){
        //一个元素进队，先判断有没有他的位置
        if(size == arr.length){
            //full
            int[]newArr = new int[arr.length*2];
            for(int i = 0;i < arr.length;i++ ){
                newArr[i]=arr[(head+i)%arr.length];
            }
            arr=newArr;
        }
        //有位置给他了就size++然后把它放在尾巴后一位 尾巴在head+size-1%size位

        arr[(head+size)%arr.length] = num;
        size++;
    }

    public int dequeue(){
        int element = arr[head];
        arr[head]=0;
        size--;
        head = (head+1)%arr.length;


        return element;
    }


}
