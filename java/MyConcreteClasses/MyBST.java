import java.util.*;

public class MyBST<E extends Comparable<E>> implements MyTree<E> {

  protected TreeNode<E> root;
  protected int size = 0; // Number of elements in the list
  
  /** Create an empty list */
  public MyBST() {
  }

  /** Create a list from an array of objects */
  public MyBST(E[] objects) {
    for (int i = 0; i < objects.length; i++)
      insert(objects[i]); 
  }

  public int getSize(){return size;}

  public boolean isEmpty(){return size == 0;}



  @Override
  public boolean insert(E e){
    //PRE:  accepts an element to add into the tree
    //POST: if tree is empty, sets root to new node, 
    //           update size, returns true
    //      else searches for a place to insert
    //      if element is already in the tree, return false
    //      else add the node to the correct 'side' of parent
    //      update size and return true
    if (root == null){
      root = createNewNode(e);
      size ++;
      return true;
    }

    TreeNode<E> parent = null;
    TreeNode<E> current = root;
    while (current != null){
      if (e.compareTo(current.element) < 0){
        parent = current;
        current = current.left;
      }
      else if (e.compareTo(current.element) > 0){
        parent = current;
        current = current.right;
      }
      else
        return false;  //e is in the tree
    }

    if (e.compareTo(parent.element) < 0)
      parent.left = createNewNode(e);
    else
      parent.right = createNewNode(e);
  
    size ++;
    return true;
  }
    
  @Override
  public boolean search(E e){
    //PRE:  accepts an element to find in the tree
    //POST: if tree is found return true, else return false
    //Start at root, if empty, return false
    TreeNode<E> current = root;

    while (current != null){
      if (e.compareTo(current.element) < 0)
        current = current.left;
      else if (e.compareTo(current.element) > 0)
        current = current.right;
      else
       return true;
    }
    return false;
  }

  public void inOrder(){
    //PRE:  none
    //POST: prints tree inOrder
    inOrder(root);
  }

  public void inOrder(TreeNode<E> root){
    if (root == null) return;
    inOrder(root.left);
    System.out.print(root.element + " ");
    inOrder(root.right);
  }

  public void preOrder() {
    // PRE: none
    // POST: prints tree preOrder (Root, Left, Right)
    preOrder(root);
  }

  public void preOrder(TreeNode<E> root) {
    if (root == null) return;
    
    // Visit the Root
    System.out.print(root.element + " ");
    
    // Traverse Left
    preOrder(root.left);
    
    // Traverse Right
    preOrder(root.right);
  }

  public void postOrder() {
    // PRE:  none
    // POST: prints tree postOrder (Left, Right, Root)
    postOrder(root);
  }

  private void postOrder(TreeNode<E> root) {
    if (root == null) return;

    // Traverse Left subtree
    postOrder(root.left);

    // Traverse Right subtree
    postOrder(root.right);

    // Visit Root (print element)
    System.out.print(root.element + " ");
  }

  public boolean delete(E e){
    //PRE:  accepts an element to delete in the tree
    //POST: if element is found, delete & return true,
    //      else return false
    //find the element
    TreeNode<E> parent = null;
    TreeNode<E> current = root;

    while (current != null){
      if (e.compareTo(current.element) < 0){
        parent = current;
        current = current.left;
      }
      else if (e.compareTo(current.element) > 0){
        parent = current;
        current = current.right;
      }
      else
        break;
    }
    
    //case 1:  item not found
    if (current == null)
      return false;

    //case 2: current has no left child
    if (current.left == null){
      if (parent == null)
        root = current.right;
      else{
        if (parent.left == current)
          parent.left = current.right;
        else
          parent.right = current.right;
      }
      size --;
      return true;
    }

    //case 3: current has a left child
    //        find rightmost node in left child & swap
    TreeNode<E> parentofRightMost = current;
    TreeNode<E> rightMost = current.left;

    while (rightMost.right != null){
      parentofRightMost = rightMost;
      rightMost = rightMost.right;
    }

    //swap element at current with rightmost value
    current.element = rightMost.element;
    if (parentofRightMost.right == rightMost)
      parentofRightMost.right = rightMost.left;
    else
      parentofRightMost.left = rightMost.left;
  
    size --;
    return true;

  }

  public void clear(){
    root = null;
    size = 0;
  }

  public String inOrderString() {
    //PRE:  none
    //POST: prints tree in order *generic
    //      using recursive calls
      StringBuilder sb = new StringBuilder();
      inOrderString(root, sb);
      return sb.toString().trim();
  }

  private void inOrderString(TreeNode<E> root, StringBuilder sb) {
      if (root == null) return;

      inOrderString(root.left, sb);
      sb.append(root.element).append(" ");
      inOrderString(root.right, sb);
  }

  public boolean isLeaf(E e) {
    TreeNode<E> current = root;

    // Search for the node containing element e
    while (current != null) {
      if (e.compareTo(current.element) < 0) {
        current = current.left;
      } else if (e.compareTo(current.element) > 0) {
        current = current.right;
      } else {
        // Node has been found, check if it is a leaf
        return current.left == null && current.right == null;
      }
    }
    
    // The provided element was not found in the tree
    return false;
  }

  public int heightWrapper(){
    //PRE:  none - start at root
    //POST: returns the height of the tree
    return height(root);
  }

  public int height(TreeNode<E> root) {
    if (root == null) {
      return -1; 
    }
    
    return 1 + Math.max(height(root.left), height(root.right));
  }

  public int depth(E e){
    //PRE:  accepts an element to find in the tree
    //POST: returns the depth of the node in the tree
    //      or -1 if not found
    TreeNode<E> current = root;
    int depthCount = 0;

    while (current != null) {
      int comparison = e.compareTo(current.element);
      if (comparison < 0) {
        current = current.left;
        depthCount++;
      } else if (comparison > 0) {
        current = current.right;
        depthCount++;
      } else {
         //Node has been found
        return depthCount;
      }
    }
    //Element is not in the tree
    return -1; 
  }

  public E findMin(){
    //PRE:  none
    //POST: returns the minimum element in the BST
    if (root == null) return null;
    
    TreeNode<E> current = root;
    while (current.left != null) {
      current = current.left;
    }
    return current.element;
  }

  public E findMax(){
    //PRE:  none
    //POST: returns the maximum element in the BST
    if (root == null) return null;

    TreeNode<E> current = root;
    while (current.right != null) {
      current = current.right;
    }
    return current.element;
  }

  public boolean update(E origE, E newE){
    //PRE:  accepts original element value & new value
    //POST: updates the original value in the BST
    //If the original is found, delete, if not, return false
    if (delete(origE)) {
      //If deleted successfully, insert the new element
      insert(newE);
      return true;
    }
    
    //Original element was not found
    return false;
  }

  @Override /** Override iterator() defined in Iterable */
  public java.util.Iterator<E> iterator() {
    return new BSTTreeIterator();
  }
  
  private class BSTTreeIterator implements java.util.Iterator<E>{
    private ArrayList<E> BSTList = new ArrayList<>();
    private int current = 0;

    public BSTTreeIterator(){
      fillBSTList();
    }

    private void fillBSTList(){ 
      fillBSTList(root);
    }

    private void fillBSTList(TreeNode<E> root){
      if (root == null) return;
      fillBSTList(root.left);
      BSTList.add(root.element);
      fillBSTList(root.right);
    }

    @Override
    public boolean hasNext(){
      if (current < BSTList.size()) return true;
      return false;
    }

    @Override
    public E next(){
      return BSTList.get(current++);
    }

    @Override 
    public void remove(){
      delete (BSTList.get(current));
      BSTList.clear();
      fillBSTList();
    }

  }
  
  protected TreeNode<E> createNewNode(E e){
    return new TreeNode<E>(e);
  }

  private static class TreeNode<E extends Comparable<E>> {
     protected E element;
     protected TreeNode<E> left;
     protected TreeNode<E> right;

    public TreeNode(E element) {
      this.element = element;
    }
  }
  

}

