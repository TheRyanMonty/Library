import java.util.*;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

//MyPriorityQueue HAS-A MyHeap of elements
//POST: removes the top element in the heap
public class MyPriorityQueue <E extends Comparable<E>>{

    private MyHeap<E> heap;

    //PRE: constructor for myPriorityQueue
    //POST: if not value sent - this is a min heap
    public MyPriorityQueue(){
        heap = new MyHeap<>(false);
    }

    //PRE: constructor for PQ
    //POST: set to min heap(if setType is false) 
    //      or max(if setTYpe is true)
    public MyPriorityQueue(boolean setType){
        heap = new MyHeap<>(setType);
    }

    //PRE: accepts an element to add to the PQ
    //POST: uses add from MyHeap to add element 
    public void enqueue(E e){
        heap.add(e);
    }

    //PRE:  none
    //POST: removes the top element in the heap
    public E dequeue(){
        return heap.remove();
    }

    //PRE:  none
    //POST: returns top element in the heap
    public E top(){
        return heap.top();
    }

    //PRE:  none
    //POST: returns size of priority queue(heap)
    public int getSize(){
        return heap.getSize();
    }

    //UPDATE PRIORITY - OPTION 3
    //PRE:  Uses values set from the following call:
    //        pQueue.update(
    //        p -> p.getId() == 4,     
    //        p -> { p.setPriority(100); return p; } );    
    //POST:  will apply the update for the first element found
    //       that matches the test given (id == 4)
    //       enqueues all elements from the buffer
    //       returns status
    public boolean update(Predicate<E> matcher, 
                          UnaryOperator<E> updater){
        if (matcher == null || updater == null) return false;

        int n = getSize();
        boolean updated = false;
        List<E> buffer = new ArrayList<>(n);

        //decrement n while n > 0
        while (n-- > 0){
            E e = dequeue();
            if (!updated && matcher.test(e)){
                e = updater.apply(e); // mutate or replace
                updated = true;
            }
            buffer.add(e);
        }
        for (E x : buffer) enqueue(x);
        return updated;
    }



}
