import java.util.*;

public class MyLinkedList<E> implements MyList<E> {
  ///////////////////////////
  //Variable Declaration
  ///////////////////////////
  private Node<E> head, tail;
  private int size = 0; // Number of elements in the list
  
  ///////////////////////////
  //Constructors
  ///////////////////////////
  //Constructor:  Create a default list 
  public MyLinkedList() {}

  //Constructor: Create a list from an array of objects 
  public MyLinkedList(E[] objects) {
    for (int i = 0; i < objects.length; i++)
      add(objects[i]); 
  }

  ///////////////////////////
  //Methods
  ///////////////////////////

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
    //Retrieve the element at the tail
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
    //Create a new node
    Node<E> newNode = new Node<>(e);  
    //Link the new node with the head
    newNode.next = head; 
    //Assign newnode to head           
    head = newNode;           
    //Increment list size        
    size++;                           
    //If the new node is the only node in list
    if (tail == null)
      //Assign head to tail      
      tail = head;
  }

  public void addLast(E e) {
    //Add an element to the end of the list
    //PRE: accepts the element to add
    //POST:creates the new node
    //     adds the element as the tail element
    //     adjusts head if the list was empty 
    //     increases the size of the list
    //Create a new node for imported element e
    Node<E> newNode = new Node<>(e);  
    
    //If the list is empty
    if (tail == null) {
      //Assign newnode to both head and tail
      head = tail = newNode;
    }
    else {
      //Otherwise, assign newnode to tail.next and tail
      tail.next = newNode;
      tail = newNode; 
    }
    //Increment size counter
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

    if (index == 0) {
      //If the index is at the front, add first
      addFirst(e);
    } else if (index >= size) {
      //If the index is past the size, add to the end
      addLast(e);
    } else {
      //Create the new node
      Node<E> current = head;
      //Move to the node at provided index value - 1
      for (int i = 1; i < index; i++) {
          current = current.next;
      }
      //Stitch in the new node
      Node<E> temp = current.next;
      current.next = new Node<>(e);
      (current.next).next = temp;
      size++;
    }
  }

  public void addBefore(E prior, E data) {
    //PRE:  Accepts 2 values: the Prior value & the value to add 
    //POST: New node is added prior to the location of insertion

    //If the list is empty, add the new node to create the list
    if (size == 0 || (head != null && head.element.equals(prior))) {
        addFirst(data);
        return;
    }

    //Assign previous to head, and current to head's next temporarily for switching
    Node<E> previous = head;
    Node<E> current = head.next;

    //Iterate through the list and search for the prior entry
    for (int i = 1; i<=size-1; i++) {
        //Once prior entry is found/matches current elemnt, and it is not null, assign new node to it
        if (current != null && Objects.equals(current.element, prior)) {
            Node<E> newNode = new Node<>(data);
            //New node points forward to target
            newNode.next = current;  
            //Previous node points to new node  
            previous.next = newNode;  
            //Increment size 
            size++;
            //Return when done
            return; 
        }
        //Move both pointers forward
        previous = current;
        current = current.next;
    }

  }

  public E removeFirst() {
    // Remove the head node and return the object
    //PRE:  None 
    //POST: Check if list is empty (return null if so)
    //      save head element, remove head value & adjust pointers
    //      return saved element 

    //If the array is empty, return null
    if (size == 0) {
      return null;
    }
    //If the array has 1 entry, be sure to set head and tail to null
    else if (size == 1) {
      E temp = head.element;
      head = tail = null;
      size = 0;
      return temp;
    }
    //Otherwise set the temp value to be returned
    else {
      E temp = head.element;
      //Set the next value to current value
      head = head.next;
      //Decremenet size
      size--;
      //Return temp value removed
      return temp;
    }
  }

  public E removeLast() {
    // Remove the last node and return the object that is contained in the removed node.
    //PRE: None
    //POST: Return deleted value, if a value is deleted

    //If the size is 0, return null as nothing is removed
    if (size == 0) 
      return null;
    //If the size is 1, remove the element and set head and tail to null
    else if (size == 1) {
      E temp = head.element;
      head = tail = null;
      size = 0;
      return temp;
    }
    //Otherwise, assign current as temp value
    else {
      Node<E> current = head;
      //Iterate throught he loop to the second to last value (future last value)
      // and assign it to current
      for (int i = 0; i < size - 2; i++) {
        current = current.next;
      }
      //Remove final element, reset tail to current with no next
      E temp = tail.element;
      tail = current;
      tail.next = null;
      //Decrement size
      size--;
      //Return the removed value
      return temp;
    }
  }

  public E deleteBefore(E prior) {
    //PRE:   Function accepts the element value immediately AFTER node to be deleted
    //POST:  The node prior to the value given is deleted 

    //If the list too small or nothing before the head, return null and error
    if (size < 2 ) {
        System.out.println("Error: List is too small to manipulate!");
        return null;
    }

    //If the node to delete is the head, remove the first entry
    if (Objects.equals(head.next.element, prior)) {
        return removeFirst();
    }

    //Assign head to temp
    Node<E> temp = head;
    
    //Iterate through the loop to search for prior node
    for (int i = 0; i < size - 2; i++) {
        //Stop when temp.next.next is the 'prior' node
        if (temp.next != null && Objects.equals(temp.next.next.element, prior)) {
            //Save data to return
            E deletedData = temp.next.element; 
            //Jump over the middle node
            temp.next = temp.next.next;  
            //Decrement size
            size--;
            //Return the deleted data
            return deletedData;
        }
        //Iterate temp after checking for equivalence with prior
        temp = temp.next;
    }

    //Prior not found, return null
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

    //If the index value is outside of range, return null
    if (index < 0 || index >= size) {
      return null;
    }
    //If there's only one entry, call removefirst
    else if (index == 0) {
      return removeFirst();
    }
    //If we're removing the last value, call removelast
    else if (index == size - 1) {
      return removeLast();
    }
    //Otherwise, assign temp to iterate through the list starting with head
    else {
      Node<E> temp = head;
      //Iterate through the list, stopping at 1 before index
      for (int i = 1; i < index; i++) {
        temp = temp.next;
      }
      //Copy node to be removed to current
      Node<E> current = temp.next;
      //Connect item previous to index and item after index
      temp.next = current.next;
      //Decrement size
      size--;
      //Return removed value
      return current.element;
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
    //Use index of to reflect boolean value
    return indexOf(e) >= 0;
  }

  @Override 
  public E get(int index) {
    //Retrieve the element at the index position
    //PRE: accepts the index
    //POST:verify the index & return null if invalid
    //     return the element 
    if (index < 0 || index >= size) return null;
    Node<E> current = head;
    for (int i = 0; i < index; i++) {
        current = current.next;
    }
    return current.element;
  }

  @Override 
  public int indexOf(Object e) {
    //Return the index of the first matching object or -1 if not found
    //PRE: accepts an object
    //POST: returns the index if found or -1 if not 
    
    //Assign head to current
    Node<E> current = head;
    //If imported object is null, iterate and search for null specifically to avoid 
    // null pointer exception
    if (e == null) {
        for (int i = 0; i < size; i++) {
            if (current.element == null) return i;
            current = current.next;
        }
    } else {
      //Otherwise iterate through the list to identify the index and return it
        for (int i = 0; i < size; i++) {
            if (e.equals(current.element)) return i;
            current = current.next;
        }
    }
    //If not found, return -1
    return -1;
  }

  @Override 
  public int lastIndexOf(E e) {
    //Returns the last index of the matching object or -1 if not found
    //PRE: accepts an object
    //POST:returns the last index if found or -1 if not 
    //Temp value to hold index position
    int lastIndex = -1;
    //Assign current temp node to iterate through to find the passed in value
    Node<E> current = head;
    //Iterate through the list based on index to find the element
    for (int i = 0; i < size; i++) {
        //If it's found, assign lsatIndex to index position
        if (Objects.equals(current.element, e)) {
            lastIndex = i;
        }
        //Iterate through the next item in the list
        current = current.next;
    }
    //Return the lastIndex value
    return lastIndex;
  }

  @Override 
  public E set(int index, E e) {
    //Replace the element at the specified position with new element
    //PRE: accepts the index value & new element
    //POST:verifies the value (will throw an exception if invalid)
    //     saves old value at the index
    //     sets index value to new element  
    //     returns element
    //If the index range is invalid, return null
    if (index < 0 || index >= size) return null;
    //Assign the current node to head to iterate through
    Node<E> current = head;
    //For loop to iterate through the loop, moving to the index item
    for (int i = 0; i < index; i++) {
        //Move each item over
        current = current.next;
    }
    //Assign current element to old element
    E oldElement = current.element;
    //Assign the new element to the current element
    current.element = e;
    //Return the replaced element
    return oldElement;
  }
  
  @Override 
  public java.util.Iterator<E> iterator() {
    //Override iterator() defined in Iterable 
    //PRE: none
    //POST return a new array list iterator
    return new LinkedListIterator();
  }
  
  @Override 
  public int size() {
    //Return the number of elements in this list
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

  ///////////////////////////
  //Classes
  ///////////////////////////
  private class LinkedListIterator implements java.util.Iterator<E> {
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

    @Override
    public void remove() {
      // This will not be implemented
    }
  }
  
  private static class Node<E> {
    E element;
    Node<E> next;

    public Node(E element) {
      this.element = element;
    }
  }
  

}

