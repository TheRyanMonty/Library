package MyConcreteClasses;
import java.util.Collection;

public interface MyQueue<E> extends Collection<E> {

    //ABSTRACT METHODS
    // Inserts element, throws exception if it fails
    boolean add(E e);

    // Inserts element, returns false if it fails
    boolean offer(E e);

    // Removes and returns head, throws exception if empty
    E remove();

    // Removes and returns head, returns null if empty
    E poll();

    // Returns head without removing, throws exception if empty
    E element();

    // Returns head without removing, returns null if empty
    E peek();
}
