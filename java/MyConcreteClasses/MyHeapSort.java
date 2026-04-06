public class MyHeapSort {
    //PRE:  Accepts a list of elements
    //POST: Sorts the list in ascending order (using a max heap)
    public static <E extends Comparable<E>>  void heapSort(E[] list){
        //Use the array-based constructor for O(n) build time
        MyHeap<E> heap = new MyHeap<>(list, true); 

        //Extract elements back into the array
        for (int i = list.length - 1; i >= 0; i--) {
            list[i] = heap.remove();
        }
    }
}