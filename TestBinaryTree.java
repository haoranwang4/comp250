public class TestBinaryTree {
    public static void main(String[] args) {
        BinaryTree tree1 = new BinaryTree();
        TreeNode root = new TreeNode(1);
        tree1.setRoot(root);
        TreeNode rootLeftnode = new TreeNode(2);
        TreeNode rootRightnode = new TreeNode(3);
        root.setLeftNode(rootLeftnode);
        root.setRightNode(rootRightnode);
        rootLeftnode.setLeftNode(new TreeNode(4));
        rootLeftnode.setRightNode(new TreeNode(5));
        rootRightnode.setLeftNode(new TreeNode(6));
        rootRightnode.setRightNode(new TreeNode(7));
        TreeNode target = tree1.midSearcch(2);
        System.out.println(target==rootLeftnode);



    }
}
