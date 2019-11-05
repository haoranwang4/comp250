import  java.util.Stack;

public class BTree {
    public Node root;

    public void inorder(){
        if(root==null)return;
        //使用栈来实现不用recursive的中序遍历
        Stack<Node> s = new Stack<Node>();
        Node curr = root;
        //如果curr为空并且stack为空就代表做完了
        while (curr!=null || s.size() >0){
            //一直push直到左下角
            while(curr!=null) {
                s.push(curr);
                curr = curr.left;
            }
            //左下角的先打印
            curr=s.pop();
            System.out.println(curr.data);
            curr=curr.right;

            }
    }







    }

