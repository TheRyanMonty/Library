import java.util.EmptyStackException;

public class MyStack<E extends Comparable<? super E>> {

    private final MyDblLinkedList<E> list = new MyDblLinkedList<E>();

    public void push(E item) {
        list.addFirst(item);
    }

    public E pop() {
        //Just in case, throw exception if is empty
        if (isEmpty()) throw new EmptyStackException();
        return list.removeFirst();
    }

    public E peek() {
        //Just in case, throw exception if is empty
        if (isEmpty()) throw new EmptyStackException();
        return list.getFirst();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear() {
        list.clear();
    }
}