public class BinaryTree {
    TreeNode root;
    public BinaryTree() {
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
    public void frontShow(){
         this.root.frontShow();
    }
    public void midShow(){
        this.root.midShow();
    }
    public void afterShow(){
        this.root.afterShow();
    }
    public TreeNode frontSearcch(int i){
        return root.frontSearch(i);
    }
    public TreeNode midSearcch(int i){
        return root.midSearch(i);
    }
}
