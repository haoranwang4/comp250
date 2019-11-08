package 二叉排序树;

public class BinarySortTree {
    Node root;




  public void add(Node node){
      if(root == null) {
          root = node;
      } else{
          root.add(node);
      }
   }

   //中序遍历二叉排序树的结果是从小到大排序好的
   public void midShow(){
      if(root!= null)
          root.midShow();
    }

    /* 节点的查找
     *
     */
    public Node search(int value){
      if(root==null){
          return null;
      }else{
            return root.search(value);
            }
    }
    /*删除节点
     *
     */
    public void delete (int value){
        //找到这个节点
        Node target = search(value);
        //如果没有这个节点
        if(target == null) return;
        //找到他的父节点
        Node parent = searchParent(value);
        //如果要删除的节点是叶子节点
        if(target.left==null&&target.right==null){
            if(parent.right ==target){
                parent.right = null;
            }else {
                parent.left = null;
            }
            //要删除的节点有两个子节点
        }else if(target.left!=null&&target.right!=null){
            //删除右子树中最小的节点然后获取节点的值
            int min = deleteMin(target.right);
            //替换目标中的值
            target.value = min;

            //要删除的节点有一个子节点
        } else {
            //目标只有左子节点
            if(target.left!=null){
                //如果目标是其父节点的左节点
                if(target == parent.left){
                    parent.left =target.left;
                }else {
                    //如果目标是其父节点的右节点
                    parent.right = target.left;
                }
            }
            //有右子节点
            else {
                if(target == parent.left){
                    parent.left = target.right;
                }else {
                    parent.right = target.right;
                }
            }

        }



    }
    //找到父节点
    public Node searchParent(int value){
        if(root==null) {return null;}
        else{
          return root.searchParent(value);
        }
    }
    //删除一棵树最小节点
    public int deleteMin(Node right){
        Node target = right;
        if(target.left ==null){
            delete(target.value);
            return target.value;
        }else {
            return deleteMin(target.left);
        }
    }



}
