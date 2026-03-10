public class MyHeapSort {

    //PRE:  accepts a list of elements
    //POST: sorts the list in ascending order (using a max heap)
    public static <E extends Comparable<E>>  void heapSort(E[] list){
        
        MyHeap<E> heap = new MyHeap<>();

        // Create a Heap of integers
        for (int i = 0; i < list.length; i++)
            heap.add(list[i]);

        // Remove elements from the heap
        
        for (int i = list.length - 1; i >= 0; i--)
            list[i] = heap.remove();

    }
}
