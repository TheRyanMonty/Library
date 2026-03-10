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

    public MyHeap(E[] objList){
        max = true;
        for (int i = 0; i < objList.length; i++)
            add(objList[i]);
    } 

    public MyHeap(E[] objList, boolean b){
        max = b;
        for (int i = 0; i < objList.length; i++)
            add(objList[i]);
    } 


    //TASK 1:  ADD MIN LOGIC FOR ADD
    //PRE:   Accepts the item
    //POST:  Adds item to the end of MyHeap
    //       As long as the parent node is larger (if max), swap
    public void add(E element){

        //add to end of the heap
        myHeap.add(element);
        int currPos = myHeap.size() - 1;

        //reheap 'up'
        //if compareTo > 0 then currPos > parPos (swap)
        //TASK 1:  ADD MIN LOGIC FOR ADD
        while (currPos > 0){
            int parPos = (currPos - 1)/2;
            if (max && myHeap.get(currPos).compareTo(myHeap.get(parPos)) > 0){
                E temp = myHeap.get(currPos);
                myHeap.set(currPos, myHeap.get(parPos));
                myHeap.set(parPos, temp);                
            }
            else
                break;
            currPos = parPos;
        }
    } 

    //TASK 2:  ADD MIN LOGIC FOR REMOVE
    //PRE:  none
    //POST: if the heap is empty - return null
    //      swap first & last element
    //      set currpos to 0th position
    //      reheap down
    //      return deleted element

    public E remove(){
        //remove 'top' element - swap with last position 
        if (myHeap.size() == 0)
            return null;
        
        E delItem = myHeap.get(0);
        myHeap.set(0, myHeap.get(myHeap.size() - 1));
        myHeap.remove(myHeap.size() - 1); 

        int currPos = 0;

        //reheap 'down' - get largest (or smallest) child
        //swap if needed 
        //TASK 2:  ADD MIN LOGIC FOR REMOVE
        while (currPos < myHeap.size()){
            int left  = currPos * 2 + 1;
            int right = currPos * 2 + 2;
            if (left >= myHeap.size())
                break; 
            if (max){
                int maxPos = left;
                if (right < myHeap.size() &&
                    myHeap.get(maxPos).compareTo(myHeap.get(right)) < 0)
                    maxPos = right;
                if (myHeap.get(currPos).compareTo(myHeap.get(maxPos)) < 0){
                    E temp = myHeap.get(maxPos);
                    myHeap.set(maxPos, myHeap.get(currPos));
                    myHeap.set(currPos, temp); 
                    currPos = maxPos;
                }
                else
                    break;
            }
        }
        return delItem;
    } 

    //PRE:  none
    //POST: if the heap is empty - return null
    //      otherwise return top element
 
    public E top(){
 
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
