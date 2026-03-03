import java.util.Collection;

public interface MyDeque<E> extends MyQueue<E> {

    // Insert at front
    void addFirst(E e);
    boolean offerFirst(E e);

    // Insert at back
    void addLast(E e);
    boolean offerLast(E e);

    // Remove from front
    E removeFirst();
    E pollFirst();

    // Remove from back
    E removeLast();
    E pollLast();

    // Peek front
    E getFirst();
    E peekFirst();

    // Peek back
    E getLast();
    E peekLast();

    // Stack-style aliases (LIFO)
    void push(E e);   // addFirst
    E pop();          // removeFirst
}
