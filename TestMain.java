package 二叉排序树;

public class TestMain {
    public static void main(String[] args) {
        int [] arr = {7,3,10,12,5,1,9};
        //创建二叉排序树
        BinarySortTree bst = new BinarySortTree();
        //循环添加
        for(int i :arr){
            bst.add(new Node(i));
        }
        //查看树中的值
        bst.midShow();
        System.out.println("-----------------");
        Node node= bst.search(3);
        System.out.println(node.value);
        Node node1 = bst.search(20);
        System.out.println(node1);
        //测试查找父节点
        System.out.println("父节点");
        Node dad=bst.searchParent(1);
        System.out.println(dad.value);
        System.out.println("______________删除后");
        bst.delete(7);
        bst.delete(10);
       // bst.delete(12);
        bst.midShow();

    }

}
