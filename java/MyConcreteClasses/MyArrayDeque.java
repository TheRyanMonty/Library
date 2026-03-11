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

    public void addFirst(E item) {
        //PRE:  accepts an item to add 
        //POST: if list is full, resize
        //      calculate head index (using circular logic)
        //      add item to location of head
        //      update size

        //SIMILAR:  boolean offerFirst(E e);
        //SIMILAR:  void push(E e);  
        if (size == data.length) {
            resize();
        }

        //Move the head backward by one
        head = (head - 1 + data.length) % data.length;

        //Add the item at the new head location
        data[head] = item;

        //Increment the size
        size++;
    }

    public void addLast(E item) {
        //PRE:  accepts new element
        //POST: if list is full, resize
        //      set tail location to new eleemnt
        //      update tail & size
        //SIMILAR:  boolean offerLast(E e);
        if (size == data.length) {
            resize();
        }

        data[tail] = item;
        tail = (tail + 1) % data.length;
        size++;
    }
    
    public E removeFirst() {
        //PRE:  none
        //POST: if list is empty - throw exception
        //      else save the first position (to return)
        //      update head & size, return saved item
        //SIMILAR: E pollFirst();
        //SIMILAR: E pop(); 
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }

        E item = (E) data[head];
        data[head] = null; // avoid memory leak
        head = (head + 1) % data.length;
        size--;

        return item;
    }
    
    public E removeLast(){
        //PRE: none
        //POST: if list is not empty, reset the tail 
        //      (using circular queue logic)
        //      save item to return, decrement size
        //      return saved item

        //SIMILAR: E pollLast();
        //tail points 1 past the last element
        if (isEmpty()) throw new IllegalStateException("Deque is empty");
        tail = (tail - 1 + data.length) % data.length;
        E item = (E) data[tail];
        data[tail] = null;size--;
        return item;
    }

    public E getFirst() {
        // Peek front
        //E peekFirst();
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return (E)data[head];
    }
        
    public E getLast() {
        // Peek back
        //SIMILAR: E peekLast();
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return (E) data[(tail - 1 + data.length) % data.length];
    }
  
    @SuppressWarnings("unchecked")
    private void resize() {
        //PRE:  existing array is full
        //POST: allocate new array with 2*the current capacity
        //      for items up to size (current capacity)      
        //         set location [0] to the value at head
        //         until all items are copied
        //      reset head & rear positions
        //      reset data to new array
        //Create new array twice the size of the original
        E[] newData = (E[]) new Object[data.length * 2];

        //Iterate through the list and copy data in order to the new list
        for ( int i = 0; i < data.length; i++) {
            newData[i] = data[(head + i)% data.length];
        }
        //Reset global values for the new array
        head = 0;
        tail = size;
        //Assign new ordered array to old array
        data = newData;
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
 
    public void removeItem(E e) {
        //PRE:  accepts an item to remove
        //POST: removes item from queue, leaving
        //      remaining items in order
        //If the deque is empty, return
        if (isEmpty()) return;
        //Initialize index counter
        int i = 0;
        //Iterate through searching for element starting at the head
        while (i < size) {
            int currentIndex = (head + i) % data.length;
            
            //If the value of current position equals elemeent, shift the elements after
            if (Objects.equals(data[currentIndex], e)) {
                //Shift all elements after 'i' one position to the left
                for (int j = i; j < size - 1; j++) {
                    int current = (head + j) % data.length;
                    int next = (head + j + 1) % data.length;
                    data[current] = data[next];
                }
                
                //Clear out the last element
                int lastIdx = (head + size - 1) % data.length;
                data[lastIdx] = null;
                //Decrement size
                size--;
            } else {
                i++;
            }
        }
        // Synchronize the tail pointer with the new size
        tail = (head + size) % data.length;
    }

    @SuppressWarnings("unchecked")
    public void addPriority(E e){
        //PRE:  accepts new item to add
        //      assumes all previous elements were 
        //      added as priority
        //POST: adds new item & places based on
        //      highest value
        //If the container is full, resize it
        if (size == data.length) 
            resize();
        //Initialize variables
        int pos = 0, newPos = 0, i = 0;
        //Loop through data to spot of insertion
        for (i = 0; i < size; i++){
            pos = (head + i) % data.length;
            E posValue = (E) data[pos];

            if (posValue instanceof Comparable &&
                 e instanceof Comparable)
                if (((Comparable<Object>)posValue).compareTo(e) < 0)
                    break;
        }
        //Move items from index to the right
        for (int j = size; j > i; j--){
            pos = (head + j - 1) % data.length;
            newPos = (head + j) % data.length;
            data[newPos] = data[pos];
        }
        //Increment size due to addition
        size ++;
        //Assign new value at correct position
        data[(head + i )% data.length] = e;
        //Reassign tail to correct position
        tail = (head + size) % data.length;
    }
}
