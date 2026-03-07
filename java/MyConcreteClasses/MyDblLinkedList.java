import java.util.*;

public class MyDblLinkedList<E extends Comparable<? super E>>implements MyList<E> {

  private Node<E> head, tail;
  private int size = 0; // Number of elements in the list
  
  /** Create an empty list */
  public MyDblLinkedList() {
  }


  public MyDblLinkedList(E[] objects) {
    /** Create a list from an array of objects */
    for (int i = 0; i < objects.length; i++)
      add(objects[i]); 
  }

  public E getFirst() {
    //Retrieve the element at the head
    //PRE: none
    //POST:verify the list is not empty
    //     return the head element 
    if (size == 0) {
      return null;
    }
    else {
      return head.element;
    }
  }

  public E getLast() {
    //Retrieve the element at the head
    //PRE: none
    //POST:verify the list is not empty
    //     return the tail element 
    if (size == 0) {
      return null;
    }
    else {
      return tail.element;
    }
  }

  public void addFirst(E e) {
    //Add the element to the head of the list
    //PRE: accepts the element to add
    //POST:creates the new node
    //     adds the element as the new 'head' element
    //     adjusts tail if the list was empty
    //     increases the size of the list
    //New node to contain the data
    Node<E> newNode = new Node<>(e);
    //Connect newnode.next to head
    newNode.next = head;
    //If the head is not null, connect head previous to newnode
    if (head != null) head.prev = newNode; 
    //Now that linkages are done, assign newnode as head
    head = newNode;
    size++;
    if (tail == null) tail = head;
  }

  public void addLast(E e) {
    //Add an element to the end of the list
    //PRE: accepts the element to add
    //POST:creates the new node
    //     adds the element as the tail element
    //     adjusts head if the list was empty 
    //     increases the size of the list

    //Create the new node
    Node<E> newNode = new Node<>(e); // Create a new for element e

    //If the tail is null, assign newnode to both head and tail
    if (tail == null) {
      head = tail = newNode; 
    }
    else {
      //Otherwise, connect newNode to soon-to-be previous tail
      newNode.prev = tail;
      //Connect the new with the tail node
      tail.next = newNode; 
      //Tail is now the new node
      tail = newNode;
    }
    //Increment size
    size++; 
  }

  @Override 
  public void add(int index, E e) {
    //Add a new element at the specified index in this list
    //PRE: accepts the element to add & index location
    //POST:if index is 0 - addfirst
    //     if index is at or after size - addlast
    //     create node, get to index position
    //     adjust pointers, increase size

    //If the index provided is 0, call add first
    if (index == 0) {
      addFirst(e);
    }
    //If the index provided is larger than size, then slap it to the end
    else if (index >= size) {
      addLast(e);
    }
    else {
      //Create the current node starting at the head
      Node<E> current = head;
      //Iterate through the list until right before index
      for (int i = 1; i < index; i++) {
        current = current.next;
      }
      //Create the new node with passed in element
      Node<E> newNode = new Node<>(e);
      //Link newnode with present index value node
      newNode.next = current.next;
      //Link new node just before current target index, to make it the target index value
      (current.next).prev = newNode ;
      //Link newnode previous to current
      newNode.prev = current;
      //Assign current next to newnode
      current.next = newNode ;
      //Increment size
      size++;
    }
  }

  public E removeLast() {
    // Remove the tail node and return the object
    //PRE:  None 
    //POST: Check if list is empty (return null if so)
    //      save tail element & adjust pointers
    //      decrement size
    //      return saved element 
    //If the list is empty, return null
    if (size == 0) 
      return null;
    //If the size is 1, empty out the list
    else if (size == 1) {
      E temp = tail.element;
      head = tail = null;
      size = 0;
      return temp;
    }
    else {
        //Otherwise remove the the last element, return the last element value
        E temp = tail.element;
        tail = tail.prev;
        tail.next = null;
        size --;
        return temp;

    }
  }

  public E removeFirst() {
      // PRE:  None 
      // POST: Check if list is empty (return null if so)
      //       Save head element, remove head node, adjust pointers
      //       Return saved element 

      //If the list is empty, return null
      if (head == null) return null;

      //Save the head element to return at the end
      E removedElement = head.element;

      //If only one element exists, set head and tail to null
      if (head.next == null) {
          head = null;
          tail = null; 
      } else {
          //Otherwise, make the second entry the new head, and remove the previous linkage
          head = head.next;  
          head.prev = null;  
      }

      //Decrement size
      size--;

      //Return the saved removed element
      return removedElement;
  }

  public void addBefore(E prior, E data) {
      // PRE: Accepts two elements: the search target (prior) and the new data (data)
      // POST: Inserts data at the front if empty, at the end if prior is not found, 
      //       or immediately before the first occurrence of prior.

      //Create the new node containing passed in data
      Node<E> newNode = new Node<>(data);

      //If the list is empty, add the new node as the head
      if (head == null) {
          //Assign new node to both head and tail
          head = newNode;
          tail = newNode;
          //Increment size and return
          size++;
          return;
      }

      //If provided prior element is found at the head, insert new node at the very front
      if (head.element.equals(prior)) {
          newNode.next = head;
          head.prev = newNode;
          head = newNode;
          size++;
          return;
      }

      //Create an iterator current to iterate through the list, starting at element 2
      Node<E> current = head.next;
      while (current != null) {
          if (current.element.equals(prior)) {
              //If found, place newnode right before the node with the found value
              newNode.next = current;
              newNode.prev = current.prev;
              //Update the current node previous linkage and the node prior to current next linkage with the new node
              current.prev.next = newNode;
              current.prev = newNode;
              //Increment size
              size++;
              return;
          }
          //Iterate to the next item in the list
          current = current.next;
      }

      //If the 'prior' element is not found, add to the end of the list
      tail.next = newNode;
      newNode.prev = tail;
      tail = newNode;
      //Increment size
      size++;
  }

  public E deleteBefore(E prior) {
      //PRE:   Function accepts the element value immediately AFTER node to be deleted
      //POST:  The node prior to the value given is deleted 
      
      //Ensure there's more than 1 item, otherwise there's nothing to delete "before"
      if (size <= 1 || head == null) return null;

      //If the search target is at the head, there is no element before it
      if (head.element.equals(prior)) return null;

      //Create new node, start from one after head
      Node<E> current = head.next;
      //Iterate through the list
      while (current != null) {
          //If the current element is the same as prior
          if (current.element.equals(prior)) {
              Node<E> target = current.prev;
              E removedElement = target.element;
              //If it's the head node, delete it
              if (target == head) {
                  head = current;
                  head.prev = null;
              } else {
                  // Deleting a middle node
                  target.prev.next = current;
                  current.prev = target.prev;
              }
              //Decrement size
              size--;
              //Return what was deleted
              return removedElement;
          }
          //Increment the next item in the list to search
          current = current.next; 
      }
      //Nothing found, return null
      return null;
  }
  
  @Override
  public E remove(int index) {   
    //Remove the element at the specified position in this list
    //PRE: accepts the index value
    //POST:verifies the value (return null if so)
    //     use remove first & last if applicable
    //     else find index position & adjust pointers  
    //     decrement size
    //     return value

    //If the index provided is less than zero or more than the size of the list, return null
    if (index < 0 || index >= size) {
      return null;
    }
    //If the index provided is 0, then call removeFirst
    else if (index == 0) {
      return removeFirst();
    }
    //If the index provided is the size of the list, call removeLast
    else if (index == size - 1) {
      return removeLast();
    }
    else {
      //Create a node and iterate through the list until we reach the index provided
      Node<E> target = head;
      for (int i = 0; i < index; i++) {
          target = target.next;
      }
      //Once we reach the index, update the prev and next linkages to remove the index value from the list
      target.prev.next = target.next;
      target.next.prev = target.prev;
      //Decrement size
      size--;
      return target.element;
    }
  }

  @Override 
  public String toString() {
    //Create a string that holds values in the array
    //PRE: none
    //POST:creates a string with array values & returns string
    StringBuilder result = new StringBuilder("[");

    Node<E> current = head;
    for (int i = 0; i < size; i++) {
      result.append(current.element);
      current = current.next;
      if (current != null) {
        result.append(", "); // Separate two elements with a comma
      }
      else {
        result.append("]"); // Insert the closing ] in the string
      }
    }

    return result.toString();
  }

  @Override 
  public void clear() {
    //Clear the list
    //PRE: none
    //POST:set head & tail to null & size to 0
    size = 0;
    head = tail = null;
  }

  @Override 
  public boolean contains(Object e) {
    //Return true if this list contains the element 
    //PRE: accepts the object
    //POST:checks data elements if found, returns true
    //     else returns false
    // Left as an exercise in lecture 8 (same code)
    Node<E> current = head;

    for (int i = 0; i < size; i++) {
      if (current.element.equals(e))
          return true;
      current = current.next;
    }

    return false;
  }

  @Override 
  public E get(int index) {
    //Retrieve the element at the index position
    //PRE: accepts the index
    //POST:verify the index & return null if invalid
    //     return the element 
    // Left as an exercise in lecture 8 (same code)
    if (size == 0 || index < 0 || index >= size) {
      return null;
    }
    Node<E> current = head;
    for (int i = 0; i < index; i++) 
        current = current.next;
    return current.element;    
  }

  @Override 
  public int indexOf(Object e) {
    // Return the index of the head matching element in 
    //  this list. Return -1 if no match. */
    // Left as an exercise in lecture 8 (same code)
    //Declare position iterator for the return index value
    int position = 0;
    Node<E> current = head;

    //Iterate through the entire list until the end is reached
    while (current != null) {
        //If we find the appropriate index based on element, return the position
        if (current.element.equals(e))
            return position;
        //Iterate through the list
        current = current.next;
        //Increment position counter
        position++;
    }
    //If not found, return -1
    return -1;
  }

  @Override 
  public int lastIndexOf(E e) {
      int position = size - 1;
      Node<E> current = tail;

      while (current != null) {
          if (current.element.equals(e)) return position;
          current = current.prev;
          position--;
      }
      return -1;
  }

  @Override 
  public E set(int index, E e) {
    //Replace the element at the specified position with new element
    //PRE: accepts the index value & new element
    //POST:verifies the value (will throw an exception if invalid)
    //     saves old value at the index
    //     sets index value to new element  
    //     returns element
    // Left as an exercise in lecture 8 (same code)
    //return null;
    if (size == 0 || index < 0 || index > size) {
      return null;
    }
    Node<E> current = head;
    for (int i = 0; i < index; i++) 
        current = current.next;

    //replace current position with new data
    E currentValue = current.element;
    current.element = e;
    return currentValue;
  }

  public boolean isCircular() {
      // TASK 4: Determine if the list is circular
      
      // 1. An empty list or a list that doesn't track head/tail cannot be circular in this context
      if (head == null || tail == null) return false;

      // 2. Check the wrap-around pointers
      // In a circular DLL:
      // - The node after the tail must be the head
      // - The node before the head must be the tail
      if (tail.next == head && head.prev == tail) {
          return true;
      }

      return false;
  }

  @Override 
  public java.util.Iterator<E> iterator() {
    // Override iterator() defined in Iterable 
    return new LinkedListIterator();
  }

  public java.util.Iterator<E> reverseIterator() {
    return new ReverseLinkedListIterator();
  }
  
  @Override
  public int size() {
     // Return the number of elements in this list
    return size;
  }
 
  //TODO: Not yet implemented
  @Override
  public boolean retainAll(Collection<?> c) {
      return false; // Not implemented yet
  }
  
  //TODO: Not yet implemented
  @Override
  public <T> T[] toArray(T[] a) {
      return null; // Not implemented yet
  }

  public void addOrdered(E e){
    if (isEmpty()) {
        addFirst(e);
        return;
    }
    Node<E> current = head; 
    while (current != null && (current.element).compareTo(e) < 0) {
        current = current.next;
    }

    if (current == null)  
        addLast(e);
    else 
        addBefore(current.element, e);
  }

  private class LinkedListIterator 
      implements java.util.Iterator<E> {
    private Node<E> current = head; // Current index 
    
    @Override
    public boolean hasNext() {
      return (current != null);
    }

    @Override
    public E next() {
      E e = current.element;
      current = current.next;
      return e;
    }
 
  }
  
  private class ReverseLinkedListIterator implements java.util.Iterator<E> {
    private Node<E> current = tail; // Start from tail instead of head

    @Override
    public boolean hasNext() {
        return (current != null);
    }    

    @Override
    public E next() {
        E e = current.element;
        current = current.prev; // Move backwards
        return e;
    }

}
  
  private static class Node<E> {
    E element;
    Node<E> next;
    Node<E> prev;

    public Node(E element) {
      this.element = element;
    }
  }
  

}

