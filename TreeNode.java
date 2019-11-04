public class TreeNode {
    int value;
    TreeNode leftNode;
    TreeNode rightNode;

    public TreeNode(int value) {
        this.value = value;
    }

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }
    //前序遍历
    public void frontShow(){
        System.out.println(this.value);
        if(this.leftNode!=null){
            this.leftNode.frontShow();
        }
        if (this.rightNode!=null){
            this.rightNode.frontShow();
        }
    }
    //中序遍历
    public void midShow(){
        if(this.leftNode!=null){
            this.leftNode.midShow();
        }
        System.out.println(this.value);
        if (this.rightNode!=null){
            this.rightNode.midShow();
        }
    }
    //后序遍历
    public void afterShow(){
        if(this.leftNode!=null){
            this.leftNode.afterShow();
        }
        if (this.rightNode!=null){
            this.rightNode.afterShow();
        }
        System.out.println(this.value);
    }

    public TreeNode frontSearch (int i){
        if (this.value==i)return this;

        TreeNode target = null;
        if (this.leftNode!=null){
            target=this.leftNode.frontSearch(i);
        }
        if(target!=null){
            return target;
        }
        if(this.rightNode!=null){
            target=this.rightNode.frontSearch(i);
        }
        return target;
    }
    public TreeNode midSearch (int i){
        TreeNode target = null;
        if(this.leftNode!=null){
            target = leftNode.midSearch(i);
        }
        if(target!=null)return target;
        if(this.value==i)return this;
        if(this.rightNode!=null){
            target= rightNode.midSearch(i);
        }
        return target;
    }


}
