public class Node {
   public int data;
    public  Node left,right;

    public Node(int data) {
        this.data = data;
        this.left=null;
        this.right=null;

    }

    public void setData(int data) {
        this.data = data;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
