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
    Node<E> newNode = new Node<>(e);  // Create a new node
    newNode.next = head;              // link the new node with the head
    head = newNode;                   // head points to the new node
    size++;                           // Increase list size

    if (tail == null)                 // the new node is the only node in list
      tail = head;
  }

  public void addLast(E e) {
    //Add an element to the end of the list
    //PRE: accepts the element to add
    //POST:creates the new node
    //     adds the element as the tail element
    //     adjusts head if the list was empty 
    //     increases the size of the list
    Node<E> newNode = new Node<>(e);  // Create a new for element e

    if (tail == null) {
      head = tail = newNode;          // The new node is the only node in list
    }
    else {
      tail.next = newNode;            // Link the new with the last node
      tail = newNode;                 // tail now points to the last node
    }

    size++;                           // Increase size
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
        addFirst(e);
    } else if (index >= size) {
        addLast(e);
    } else {
        Node<E> current = head;
        // Move to the node at index - 1
        for (int i = 1; i < index; i++) {
            current = current.next;
        }
        // Stitch in the new node
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

    //Search for the prior element
    Node<E> previous = head;
    Node<E> current = head.next;

    //Iterate through the list and search for the prior entry
    for (int i = 1; i<=size-1; i++) {
        //Once prior entry is found/matches current elemnt, and it is not null, assign new node to it
        if (current != null && Objects.equals(current.element, prior)) {
            Node<E> newNode = new Node<>(data);
            newNode.next = current;    // New node points forward to target
            previous.next = newNode;   // Previous node points to new node
            size++;
            return; // Exit once added
        }
        // Move both pointers forward
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
    if (size == 0) {
      return null;
    }
    else if (size == 1) {
      E temp = head.element;
      head = tail = null;
      size = 0;
      return temp;
    }
    else {
      E temp = head.element;
      head = head.next;
      size--;
      return temp;
    }
  }

  public E removeLast() {
    // Remove the last node and return the object that is contained in the removed node.
    if (size == 0) 
      return null;

    else if (size == 1) {
      E temp = head.element;
      head = tail = null;
      size = 0;
      return temp;
    }
    else {
      Node<E> current = head;

      for (int i = 0; i < size - 2; i++) {
        current = current.next;
      }

      E temp = tail.element;
      tail = current;
      tail.next = null;
      size--;
      return temp;
    }
  }

  public E deleteBefore(E prior) {
    //PRE:   Function accepts the element value immediately AFTER node to be deleted
    //POST:  The node prior to the value given is deleted 

    //If the list too small or nothing before the head, return null
    if (size < 2 || Objects.equals(head.element, prior)) {
        return null;
    }

    //If the node to delete is the head, remove the first entry
    if (Objects.equals(head.next.element, prior)) {
        return removeFirst();
    }

    Node<E> previous = head;
    
    // We stop when grandPrev.next.next is the 'prior' node
    for (int i = 0; i < size - 2; i++) {
        if (previous.next.next != null && Objects.equals(previous.next.next.element, prior)) {
            E deletedData = previous.next.element; // Save data to return
            previous.next = previous.next.next;  // Jump over the middle node
            size--;
            return deletedData;
        }
        previous = previous.next;
    }

    return null; // Prior not found
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
    if (index < 0 || index >= size) {
      return null;
    }
    else if (index == 0) {
      return removeFirst();
    }
    else if (index == size - 1) {
      return removeLast();
    }
    else {
      Node<E> previous = head;

      for (int i = 1; i < index; i++) {
        previous = previous.next;
      }

      Node<E> current = previous.next;
      previous.next = current.next;
      size--;
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
    Node<E> current = head;
    if (e == null) {
        for (int i = 0; i < size; i++) {
            if (current.element == null) return i;
            current = current.next;
        }
    } else {
        for (int i = 0; i < size; i++) {
            if (e.equals(current.element)) return i;
            current = current.next;
        }
    }
    return -1;
  }

  @Override 
  public int lastIndexOf(E e) {
    //Returns the last index of the matching object or -1 if not found
    //PRE: accepts an object
    //POST:returns the last index if found or -1 if not 
    int lastIndex = -1;
    Node<E> current = head;
    for (int i = 0; i < size; i++) {
        if (Objects.equals(current.element, e)) {
            lastIndex = i;
        }
        current = current.next;
    }
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
    if (index < 0 || index >= size) return null;
    Node<E> current = head;
    for (int i = 0; i < index; i++) {
        current = current.next;
    }
    E oldElement = current.element;
    current.element = e;
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

