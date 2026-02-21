import java.util.*;

//public class MyArrayDeque<E> implements MyDeque<E> {
public class MyArrayDeque<E> {

    private E[] data;
    private int head; // index of first element
    private int tail; // index one past last element
    private int size;

    @SuppressWarnings("unchecked")
    public MyArrayDeque() {
        data = (E[]) new Object[4]; // power of two
        head = 0;
        tail = 0;
        size = 0;
    }

    //TASK 2: ADDFIRST
    //PRE:  accepts an item to add 
    //POST: if list is full, resize
    //      calculate head index (using circular logic)
    //      add item to location of head
    //      update size

    //SIMILAR:  boolean offerFirst(E e);
    //SIMILAR:  void push(E e);    
    public void addFirst(E item) {
        System.out.println("TASK 2: ADD FIRST NEEDS TO BE CODED");
    }

    //PRE:  accepts new element
    //POST: if list is full, resize
    //      set tail location to new eleemnt
    //      update tail & size
    //SIMILAR:  boolean offerLast(E e);
    public void addLast(E item) {
        if (size == data.length) {
            resize();
        }

        data[tail] = item;
        tail = (tail + 1) % data.length;
        size++;
    }
    
    //PRE:  none
    //POST: if list is empty - throw exception
    //      else save the first position (to return)
    //      update head & size, return saved item
    //SIMILAR: E pollFirst();
    //SIMILAR: E pop(); 
    public E removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }

        E item = (E) data[head];
        data[head] = null; // avoid memory leak
        head = (head + 1) % data.length;
        size--;

        return item;
    }
    
    //TASK 3: REMOVELAST
    //PRE: none
    //POST: if list is not empty, reset the tail 
    //      (using circular queue logic)
    //      save item to return, decrement size
    //      return saved item

    //SIMILAR: E pollLast();
    //tail points 1 past the last element
    public E removeLast(){
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }

        System.out.println("TASK 3: REMOVE LAST NEEDS TO BE CODED");
        return null;
    }


    // Peek front
    //E peekFirst();
    public E getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return (E)data[head];
    }
        

    // Peek back
    //SIMILAR: E peekLast();
    public E getLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return (E)data[tail];
    }
  

    @SuppressWarnings("unchecked")
    //TASK 1
    //PRE:  existing array is full
    //POST: allocate new array with 2*the current capacity
    //      for items up to size (current capacity)      
    //         set location [0] to the value at head
    //         until all items are copied
    //      reset head & rear positions
    //      reset data to new array

    private void resize() {
        System.out.println("TASK 1: RESIZE NEEDS TO BE CODED");
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < size; i++) {
            int index = (head + i) % data.length;
            sb.append(data[index]);

            if (i < size - 1) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    
    //TASK 4: REMOVEITEM
    //PRE:  accepts an item to remove
    //POST: removes item from queue, leaving
    //      remaining items in order
    public void removeItem(E e){

        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }

        System.out.println("TASK 4: REMOVE ITEM NEEDS TO BE CODED");
    }

    //TASK 5: ADDPRIORITY
    //PRE:  accepts new item to add
    //      assumes all previous elements were 
    //      added as priority
    //POST: adds new item & places based on
    //      highest value
    @SuppressWarnings("unchecked")
    public void addPriority(E e){

        System.out.println("TASK 5: ADD PRIORITY NEEDS TO BE CODED");

    }
}
