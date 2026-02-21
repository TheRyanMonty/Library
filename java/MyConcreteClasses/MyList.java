import java.util.Collection;

public interface MyList<E> extends java.util.Collection<E> {
  ///////////////////////////
  //ABSTRACT METHODS
  ///////////////////////////

  //Abstract Method: Create a new larger array, double the current size + 1   
  public void add(int index, E e);

  //Abstract Method: Clear the list
  public void clear();

  //Abstract Method: Retrieve the element at the index position
  public E get(int index); 

  //Abstract Method: Return the index of the object provided or -1 if not found
  public int indexOf(Object e);    
  public int lastIndexOf(E e);

  //Abstract Method: Remove the element at the specified position
  // in this list. Shift any subsequent elements to the left.
  // Return the element that was removed from the list.  
  public E remove (int index);

  //Abstract Method: Replace the element at the specified position with the new value
  public E set(int index, E e);

  //Abstract Method: return the size of the list
  public int size();

  ///////////////////////////
  //DEFAULT METHODS
  ///////////////////////////
  @Override 
  //Default Method: Return true if this list contains no elements  
  //PRE: none
  //POST:return true if the array is empty, false if not 
  public default boolean isEmpty() {
    return size() == 0;
  }

  @Override 
  //Default Method: remove the given element if found    
  //PRE: Accepts the element to remove
  //POST: Return true if the list is updated, false if not  
  public default boolean remove(Object e) {
    //If e is null, identify position within the indexOf concrete method
    int index = indexOf(e);
    if (index >= 0) {
      remove(index);
      return true;
    }
    else
      return false;
  }

  @Override
  //Default Method: add the given element to the end of the list   
  //PRE: accepts the element to remove
  //POST:adds element to the end of the list  
  public default boolean add(E e){
      add(size(), e);
      return true;
  }

  @Override
  //PRE: Pull in a list
  //POST: Returns the resulting array
  public default Object[] toArray() {
    //Create a new array called result that is the same size as provided array
    Object[] result = new Object[this.size()];
    //Copy elements from internal storage to new array
    for (int i = 0; i < size(); i++) {
      result[i] = this.get(i); 
    }
    //Return the populated array
    return result;
  }

  //PRE: Pull in a list of elements to add to an array
  //POST: Returns true/false
  @Override
  public default boolean addAll(Collection<? extends E> c) {
    //Ensure we are catching null values appropriately by throwing exception
    if (c == null) throw new NullPointerException();
    //Return false if the values passed in are empty
    if (c.isEmpty()) return false;
    //For each element in the passed in collection, add each element
    for (E element : c) {
        add(element); 
    }
    //Return true if list changes
    return true;
  }

  //PRE: Pull in collection of objects
  //POST: Return true/false based on whether the elements are contained in the list
  public default boolean containsAll(Collection<?> c){
    //Loop through each element in the provided list
    for (Object element : c) {
      //If the element is not contained within the list, return false
      if (!this.contains(element)) { 
          return false;
      }
    }
    //Otherwise, return true
    return true;
  } 

  //PRE: Pull in object
  //POST: Return position of object within list
  public default boolean contains(Object o) {
    return indexOf(o) >= 0;
  }

  //PRE: Pull in list of objects to remove from collection
  //POST: True for successful removal, false for failure
  public default boolean removeAll(Collection<?> c) {
    //Check for null values being passed in
    if (c == null) throw new NullPointerException("The specified collection is null");
    //Track whether the collection is modified
    boolean modified = false;
    //Use an iterator to loop through list
    var it = this.iterator(); 
    //While the iterator has another value
    while (it.hasNext()) {
        //if the collection contains the next iterator value
        if (c.contains(it.next())) {
            //Remove the iterator value
            it.remove();
            //Ensure modified is updated for when the loop completes
            modified = true;
        }
    }
    return modified;
  }
}

