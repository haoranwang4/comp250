package 二叉排序树;

public class Node {
    int value;
    Node right;
    Node left;

    public Node(int value) {
        this.value = value;
    }


    //向二叉排序树添加节点
    public void add(Node node){
        //空树
        if(node == null) return;
        //判断大小 小放左 大放右
        if(node.value<this.value) {
            if(this.left ==null){
                this.left = node;
            }else{
                this.left.add(node);
            }
            //放右边
        } else {
            if(node.value>this.value){
                if(this.right==null){
                    this.right = node;
                }else{
                    this.right.add(node);
                }
            }

        }
    }
    public void midShow(){
       if(this.left!=null)
       this.left.midShow();
        System.out.println(this.value);
        if(this.right!=null)
        this.right.midShow();
    }

    public Node search(int value){
        if(this.value == value) {
            return this;
        } else  if (value < this.value){
            if(this.left ==null)return null;
           return this.left.search(value);
        }else if (value>this.value){
            if(this.right==null)return null;
            return this.right.search(value);
        }else{
        //没找到
       return null;}
    }

    public Node searchParent(int value){
     if((this.right!=null&&this.right.value==value)||(this.left!=null&&this.left.value==value)){
         return this;
     }else if(value<this.value&&this.left!=null){
         return this.left.searchParent(value);
     }else if(value>this.value&&this.right!=null) {
         return this.right.searchParent(value);
     }
     return null;
    }







    }


