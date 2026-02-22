import java.util.*;

@SuppressWarnings("unchecked")

public class MyArrayList<E> implements MyList<E> {
    ///////////////////////////
    //Variable Declaration
    ///////////////////////////
    public static final int INITIAL_CAPACITY = 5;
    private E[] data = (E[]) new Object[INITIAL_CAPACITY];
    private int size = 0;

    ///////////////////////////
    //Constructors
    ///////////////////////////

    //Constructor:  Create a default list 
    public MyArrayList() { }

    //Constructor: Create a list from an array of objects  
    public MyArrayList(E[] objects) {
       for (int i = 0; i < objects.length; i++)
           add(objects[i]); // Warning: don't use super(objects)!
    }

    ///////////////////////////
    //Methods
    ///////////////////////////
    @Override 
    public void add(int index, E e) {
        //Add a new element at the specified index   
        //PRE: Accepts the index & value to add
        //POST:Verify the index is valid, if not, throw exception
        //     use ensureCapacity to add additional space if there is not enough capacity in the array
        //     shift elements in array to the right following the location
        //     add data item at the index position
        //     increase size    

        // Check index
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + " < size: " + size);

        // Check capacity
        ensureCapacity();

        // Move the elements to the right after the specified index
        for (int i = size - 1; i >= index; i--)
            data[i + 1] = data[i];

        // Insert new element to data[index]
        data[index] = e;
        // Increase size by 1
        size++;
    }

    private void ensureCapacity() {
        /** Create a new larger array, double the current size + 1 */
        //PRE: none (this is a helper method)
        //POST: checks if the size of the array is at capacity
        //      if so, it doubles the size of the array & copies data to new array   
        //if the current size exceeds the data size
        if (size >= data.length) {
            E[] newData = (E[])(new Object[size * 2 + 1]);
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }

    @Override 
    public void clear() {
        //Clear the list
        //PRE: none
        //POST:allocate an empty array of objects
        //     sets size to 0
        data = (E[])new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override 
    public boolean contains(Object e) {
        //Return true if this list contains the element 
        //PRE: accepts the object
        //POST:checks data elements if found, returns true
        //     else returns false
        //Use null safe indexOf logic to profide boolean return
        return indexOf(e) >= 0;
    }

    @Override  
    public E get(int index) {
        //Retrieve the element at the index position
        //PRE: accepts the index
        //POST:verify the index & throw out of bounds if invalid
        //     return the element 
        checkIndex(index);
        return data[index];
    }

    private void checkIndex(int index) {
        //verify the index (helper method)
        //PRE: none
        //POST:throw out of bounds if index is invalid 
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index " + index + " out of bounds");
    }

    @Override
    public int size(){
        //Get the size of the array
        //PRE: none
        //POST:return the size of the array
        return size;
    }

    @Override 
    public int indexOf(Object e) {
        //Abstract Method: Return the index of the first matching object or -1 if not found
        //PRE: accepts an object
        //POST:returns the index if found or -1 if not 
        //Check for null value of the passed in element
        if (e == null) {
            //If e object is null, iterate through the dataset to find the index value of a null entry 
            for (int i = 0; i < size; i++) 
                //Once idenfitied, return the index value
                if (data[i] == null) return i;
        } else {
            //Loop through each element within the dataset to find e
            for (int i = 0; i < size; i++)
                //return index value of element e within the dataset data
                if (e.equals(data[i])) return i;
        }
        //If not found, return -1
        return -1;
    }

    @Override  
    public int lastIndexOf(E e) {
        //Returns the last index of the matching object or -1 if not found
        //PRE: accepts an object
        //POST:returns the last index if found or -1 if not 
        //Check for null value of the passed in element
        if (e == null) {
            //Reverse search for a null value in data
            for (int i = size - 1; i >= 0; i--) 
                //Once idenfitied, return the index value
                if (data[i] == null) return i;
        } else {
            //Reverse search for the element in data
            for (int i = size - 1; i >= 0; i--)
                if (e.equals(data[i])) return i;
        }

        return -1;
    }

    @Override 
    public E remove(int index) {
        //Remove the element at the specified position in this list
        //PRE: accepts the index value
        //POST:verifies the value (will throw an exception if invalid)
        //     shift all elements to the right of the index one position to the left
        //     Return the element that was removed from the list.  
        //     decrement size
        //     return element
        //Verify the value
        checkIndex(index);
        E temp = (E) data[index];

        // Shift data to the left
        for (int j = index; j < size - 1; j++)
            data[j] = data[j + 1];

        data[size - 1] = null; // This element is now null

        // Decrement size
        size--;
        return temp;
    }

    @Override 

    public E set(int index, E e) {
        //Replace the element at the specified position with new element
        //PRE: accepts the index value & new element
        //POST:verifies the value (will throw an exception if invalid)
        //     saves old value at the index
        //     sets index value to new element  
        //     returns the old element which was replaced
        //Verify the Value
        checkIndex(index);
        //Save old value at the index
        E temp = (E) data[index];
        //Set index value to new element
        data[index]=e;
        //Return element
        return temp;
    }
    
    @Override
    public String toString() {
        //Create a string that holds values in the array
        //PRE: none
        //POST:creates a string with array values & returns string
        StringBuilder result = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            result.append(data[i]);
            if (i < size - 1) result.append(", ");
        }

        return result.toString() + "]";
    }

    public void trimToSize() {
        //Trims the capacity to current size 
        //PRE: none
        //POST:cuts the array to the number of elements in the array (size)
        if (size != data.length) {
            E[] newData = (E[])(new Object[size]);
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        } // If size == capacity, no need to trim
    }

    @Override
    public Object[] toArray() {
        //Returns an array of Object for the elements in this collection.
        //PRE: Initialized list in data
        //POST: creates an array of objects, 
        //      copies elements from array to new array & returns new array
        //System.out.println("Need to write: toArray");
        E[] newArray = (E[])(new Object[size]);
        System.arraycopy(data, 0, newArray, 0, size);
        return newArray;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        //PRE: Accepts a list of items 'c'
        //POST Adds all the elements in the collection c 
        //     returns true if data was updated 
        //Create object a to evaluate
        Object[] a = c.toArray();
        //Determine length of a array
        int numNew = a.length;
        //If it's empty, return false
        if (numNew == 0) return false;

        //Ensure there's enough capacity to add to the array
        while (size + numNew > data.length) {
            ensureCapacity();
        }

        // Copy array info to the existing array starting at the last filled position
        System.arraycopy(a, 0, data, size, numNew);
        size += numNew;
        return true;
    }
    
    @Override
    public boolean containsAll(Collection<?> c) {
        //Returns true if the collection contains all the elements in c.
        //PRE: accepts a generic collection of objects
        //POST:if these are the same, return true
        //     else returm false  
        //System.out.println("Need to write: containsAll");
        //Check each object in the collection
        for (Object o : c) {
            if (!contains(o))
                    return false;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        //Removes all the elements in c from this collection.
        //PRE: Accepts a list of items 'c'
        //POST Adds all the elements in the collection c 
        //     returns true if data was updated 
        int writeIndex = 0;
        boolean modified = false;
        //Loop through collection, check if the process will modify or note
        for (int readIndex = 0; readIndex < size; readIndex++) {
            //If the current item is NOT in the collection 'c', keep it
            if (!c.contains(data[readIndex])) {
                data[writeIndex++] = data[readIndex];
            } else {
                modified = true;
            }
        }
        // Null out the remaining slots for Garbage Collection
        for (int i = writeIndex; i < size; i++) {
            data[i] = null;
        }
        //Assign new size value based on non-removed itemes
        size = writeIndex;
        return modified;

    }

    // TODO: Not implemented at this time
    @Override
    public <T> T[] toArray(T[] array) {
        // not implementing at this time
        return null;
    }

    // TODO: Not implemented at this time
    @Override
    public boolean retainAll(Collection<?> c) {
        //Retains the elements that are both in c and in this collection
        // not implementing at this time
        return false;
    }
    
    @Override 
    public java.util.Iterator<E> iterator() {
        //Override iterator() defined in Iterable 
        //PRE: none
        //POST return a new array list iterator
        return new ArrayListIterator();
    }

  ///////////////////////////
  //Classes
  ///////////////////////////
    private class ArrayListIterator implements java.util.Iterator<E> {
        //Current index
        private int current = 0; 
        //Can we safely remove
        private boolean canRemove = false; 

        @Override
        public boolean hasNext() {
            return (current < size);
        }

        @Override
        public E next() {
            //If there is no next element, throw an exception
            if (!hasNext()) throw new NoSuchElementException();
            //Otherwise, set the canRemove flag to true and return data
            canRemove = true;
            return data[current++];
        }

        @Override
        public void remove() {
            //Ensure canRemove is true, next() must be called first so we know value can be removed
            if (!canRemove) {
                throw new IllegalStateException("next() must be called before remove().");
            }
            //Remove the target element
            MyArrayList.this.remove(--current);
            //Reset canRemove to false, next must be called to validate
            canRemove = false;
        }
    }

}
