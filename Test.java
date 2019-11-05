public class Test {
    public static void main(String[] args) {
        BTree tree1 = new BTree();
        tree1.root = new Node(1);
        tree1.root.left =  new Node(2);
        tree1.root.right =  new Node(3);
        tree1.root.left.left=new Node(4);
        tree1.root.left.right=new Node(5);
        tree1.root.left.left.right=new Node(6);
        tree1.inorder();





    }
}
