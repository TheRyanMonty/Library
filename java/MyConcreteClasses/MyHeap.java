//This implementation is a "HAS-A" relationship
//if max = 0; this is a minimum heap
//if max = 1; this is a maximum heap

public class MyHeap <E extends Comparable<E>>  {

    private MyArrayList<E> myHeap = new MyArrayList<E> ();
    private boolean max;

    //default to max heap 
    public MyHeap() {
        max = true;
    }
    
    //Set value of max to 'b' - 
    public MyHeap(boolean b) {
        max = b;
    }

    public MyHeap(E[] objList) {
        this(objList, true);
    }


    public MyHeap(E[] objList, boolean b) {
        max = b;
        for (E item : objList) myHeap.add(item); 
        
        // You MUST heapify after adding all elements
        for (int i = (myHeap.size() / 2) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void siftDown(int index) {
        int currentPosition = index;
        while (true) {
            int left = currentPosition * 2 + 1;
            int right = currentPosition * 2 + 2;
            if (left >= myHeap.size()) break;

            int targetChild = left;
            if (right < myHeap.size()) {
                int childCmp = myHeap.get(right).compareTo(myHeap.get(left));
                if ((max && childCmp > 0) || (!max && childCmp < 0)) {
                    targetChild = right;
                }
            }

            int rootCmp = myHeap.get(targetChild).compareTo(myHeap.get(currentPosition));
            if ((max && rootCmp > 0) || (!max && rootCmp < 0)) {
                E temp = myHeap.get(currentPosition);
                myHeap.set(currentPosition, myHeap.get(targetChild));
                myHeap.set(targetChild, temp);
                currentPosition = targetChild;
            } else {
                break;
            }
        }
    }

    private void siftUp(int index) {
        int currentPosition = index;

        while (currentPosition > 0) {
            int parentPosition = (currentPosition - 1) / 2;
            int cmp = myHeap.get(currentPosition).compareTo(myHeap.get(parentPosition));

            // Swap logic for Max-Heap vs Min-Heap
            if ((max && cmp > 0) || (!max && cmp < 0)) {
                E temp = myHeap.get(currentPosition);
                myHeap.set(currentPosition, myHeap.get(parentPosition));
                myHeap.set(parentPosition, temp);
                currentPosition = parentPosition;
            } else {
                break;
            }
        }
    }

    public void add(E element) {
        //Add to the end of the list
        myHeap.add(element);
        
        //Sift up from the last position to restore heap property
        siftUp(myHeap.size() - 1);
    }

    public E remove() {
        if (myHeap.size() == 0) return null;

        E delItem = myHeap.get(0);
        
        // Move last element to root
        myHeap.set(0, myHeap.get(myHeap.size() - 1));
        myHeap.remove(myHeap.size() - 1);

        // Restore property by sifting down from root
        if (myHeap.size() > 0) {
            siftDown(0);
        }
        
        return delItem;
    }

    public E top(){
        //PRE:  none
        //POST: if the heap is empty - return null otherwise return top element
        if (myHeap.size() == 0)
            return null;
        else
            return myHeap.get(0);
    }

    public int getSize(){
        return myHeap.size();
    }

    @Override
    public String toString(){
        return myHeap.toString();
    }

}
