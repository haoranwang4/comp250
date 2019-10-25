import java.util.Arrays;

public class TestMain {
    public static void main(String[] args) {
        CircularQueue cq1 = new CircularQueue();
        cq1.enquene(1);
        cq1.enquene(2);
        cq1.enquene(3);
        cq1.enquene(4);
        cq1.enquene(5);
        cq1.enquene(6);
        cq1.enquene(7);
        cq1.enquene(8);
        System.out.println(Arrays.toString(cq1.getArr()));
        System.out.println(cq1.dequeue());
        System.out.println(Arrays.toString(cq1.getArr()));
        System.out.println(cq1.dequeue());
        System.out.println(Arrays.toString(cq1.getArr())); System.out.println(cq1.dequeue());
        System.out.println(Arrays.toString(cq1.getArr())); System.out.println(cq1.dequeue());
        System.out.println(Arrays.toString(cq1.getArr()));
        cq1.enquene(1);
        cq1.enquene(2);
        cq1.enquene(3);
        cq1.enquene(4);
        cq1.enquene(5);
        cq1.enquene(6);
        System.out.println(Arrays.toString(cq1.getArr()));


    }
}
