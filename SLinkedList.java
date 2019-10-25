import java.util.Iterator;

public class SLinkedList <E> implements Iterable<E> {
    private SNode<E> head;
    
    public MyIterator iterator(){
        return new MyIerator(this);
    }
    private class MyIterator implements Iterator<E>{
        SNode<E>curr;

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }
    }









}
