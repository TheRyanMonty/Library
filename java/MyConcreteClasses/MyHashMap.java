public class MyHashMap<K, V> implements MyMap<K, V> {
  // MYHASHMAP ATTRIBUTES
  
  // Define the default hash table size. Must be a power of 2
  private static int DEFAULT_INITIAL_CAPACITY = 4;
  
  // Define the maximum hash table size. 1 << 30 is same as 2^30
  private static int MAXIMUM_CAPACITY = 1 << 30; 
  
  // Current hash table capacity. Capacity is a power of 2
  private int capacity;
  
  // Define default load factor
  private static float DEFAULT_MAX_LOAD_FACTOR = 0.75f; 

  // Specify a load factor used in the hash table
  private float loadFactorThreshold; 
     
  // The number of entries in the map
  private int size = 0; 
  
  // Hash table is an array with each cell that is a linked list
  MyLinkedList<MyMap.Entry<K,V>>[] table;

  // MYHASHMAP CONSTRUCTORS
  // default constructor
  // POST: creates a map with the default capacity and load factor  
  public MyHashMap() {  
    this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);    
  }
  
  // overloaded constructor 
  // PRE:  accepts initial capacity
  // POST: creates a map with the specified initial capacity and 
  //       default load factor 
  public MyHashMap(int initialCapacity) { 
    this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);    
  }
  
  // overloaded constructor 
  // PRE:  accepts initial capacity & load factor  
  // POST: creates a map with the specified initial capacity and 
  //       given load factor 
  //       ensures capacity does not excede max & is a factor of 2
  public MyHashMap(int initialCapacity, float loadFactorThreshold) { 
    if (initialCapacity > MAXIMUM_CAPACITY)
      this.capacity = MAXIMUM_CAPACITY;
    else
      this.capacity = trimToPowerOf2(initialCapacity);
    
    this.loadFactorThreshold = loadFactorThreshold;    
    table = new MyLinkedList[capacity];
  }
  

  @Override  
  //PRE:  none
  //POST: removes all of the entries from this map 
  public void clear() {
    size = 0;
    removeEntries();
  }


  @Override  
  //PRE:  accepts a key value
  //POST: returns true if the specified key is in the map
  public boolean containsKey(K key) {    
    if (get(key) != null)
      return true;
    else
      return false;
  }
  
  @Override  
  //PRE:  accepts a value
  //POST: returns true if the specified value is in the map  
  public boolean containsValue(V value) {
    for (int i = 0; i < capacity; i++) {
      if (table[i] != null) {
        MyLinkedList<Entry<K, V>> bucket = table[i]; 
        for (Entry<K, V> entry : bucket) {
          if (entry.getValue().equals(value)) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  @Override  
  //PRE:  none
  //POST: creates a HashSet of Entry type & adds all
  //      entries in table to the set & returns the set 
  public java.util.Set<MyMap.Entry<K,V>> entrySet() {
    java.util.Set<MyMap.Entry<K, V>> set = 
      new java.util.HashSet<>();
    
    for (int i = 0; i < capacity; i++) {
      if (table[i] != null) {
        MyLinkedList<Entry<K, V>> bucket = table[i]; 
        for (Entry<K, V> entry: bucket)
          set.add(entry); 
      }
    }
    
    return set;
  }

  @Override  
  //PRE:  accepts a key value
  //POST: returns the value that matches the specified key.
  //      if not found, returns null
  public V get(K key) {
    int bucketIndex = hash(key.hashCode());
    if (table[bucketIndex] != null) {
      MyLinkedList<Entry<K, V>> bucket = table[bucketIndex]; 
      for (Entry<K, V> entry: bucket)
        if (entry.getKey().equals(key)) 
          return entry.getValue();
    }
    
    return null;
  }
  
  @Override  
  //PRE:  none
  //POST: returns true if this map contains no entries
  public boolean isEmpty() {
    return size == 0;
  }  
  
  @Override  
  //PRE:  none
  //POST: creates a HashSet of Key type & adds all
  //      the keys in the table to the set & returns the set 
  public java.util.Set<K> keySet() {
    java.util.Set<K> set = new java.util.HashSet<K>();
    
    for (int i = 0; i < capacity; i++) {
      if (table[i] != null) {
        MyLinkedList<Entry<K, V>> bucket = table[i]; 
        for (Entry<K, V> entry: bucket)
          set.add(entry.getKey()); 
      }
    }
    
    return set;
  }
      
  @Override /** Remove the entries for the specified key */
  //PRE: accepts the key
  //POST: uses the hash function to get LinkedList associated with entry
  //      searches linked list for item
  //      if found, removes item using remove function (from linked list)

  public void remove(K key) {
    int bucketIndex = hash(key.hashCode());
    
    if (table[bucketIndex] != null) {
        MyLinkedList<Entry<K, V>> bucket = table[bucketIndex]; 
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                bucket.remove(entry); 
                size--; // Decrease size
                break; // Optimization: exit after finding and removing
            }
        }
    }
  }
  
  
  @Override /** Add an entry (key, value) into the map */
  //PRE:  accepts a key & value
  //POST: determines if key exists in the table
  //           if so, update the value at the key location to the new value
  //           return the oldValue
  //      determine if the table needs to be resized
  //      get tableIndex for the key
  //      if table is null there, create an empty linkedList at tableIndex
  //
  //      add new entry to linkedList at the tableIndex
  //      update size
  //      return value
  public V put(K key, V value) {
    // Check if key is already in the map
    if (get(key) != null) { 
      //Obtain the bucket index
      int bucketIndex = hash(key.hashCode());
      //Initialize the bucket
      MyLinkedList<Entry<K, V>> bucket = table[bucketIndex];
      //Iterate through the bucket with the entry
      for (Entry<K, V> entry : bucket) {
        //If the entry equals the key, insert/update the value
        if (entry.getKey().equals(key)) {
          V oldValue = entry.value;
          entry.value = value;
          //Return the old value
          return oldValue;
        }
      }
    }

    //Check size against load factor and capacity
    if (size >= capacity * loadFactorThreshold) {
      if (capacity == MAXIMUM_CAPACITY)
        throw new RuntimeException("Exceeding maximum capacity");
      rehash();
    }

    //Create bucket index
    int bucketIndex = hash(key.hashCode());

    //Create a linked list entry for the bucket if not already created
    if (table[bucketIndex] == null) {
      table[bucketIndex] = new MyLinkedList<Entry<K, V>>();
    }

    //Add a new entry (key, value) to table[index]
    table[bucketIndex].add(new MyMap.Entry<K, V>(key, value));

    //Increase size
    size++; 

    //Return added value
    return value;  
  }
 

  
  @Override  
  //PRE:  none
  //POST: Return the number of entries in this map
  public int size() {
    return size;
  }


  /** Hash function */  
  //PRE:  accepts a given hashCode
  //POST: Returns the table index of the value provided

  private int hash(int hashCode) {
    return supplementalHash(hashCode) & (capacity - 1);
  }
  
  /** Ensure the hashing is evenly distributed */
  private static int supplementalHash(int h) {
    h ^= (h >>> 20) ^ (h >>> 12);
    return h ^ (h >>> 7) ^ (h >>> 4);
  }

  //PRE:  accepts initial capacity
  //POST: returns a capacity that is a multiple of 2
  //      that is less than the initial capacity
  private int trimToPowerOf2(int initialCapacity) {
    int capacity = 1;
    while (capacity < initialCapacity) {
      capacity <<= 1;
    }
    
    return capacity;
  }

  //PRE:  none
  //POST: Remove all entries from each bucket 
  private void removeEntries() {
    for (int i = 0; i < capacity; i++) {
      if (table[i] != null) {
        table[i].clear();
      }
    }
  }
  
  //PRE:  none
  //POST: it is determined the table needs to be resized
  //      the table is copied into a set
  //      the table is cleared & resized
  //      items from the set are ‘put’ into the new table
  private void rehash() {
    // Get entries
    java.util.Set<Entry<K, V>> set = entrySet(); 
    
    // Double capacity
    capacity <<= 1; 
    
    // Reset table and size
    table = new MyLinkedList[capacity]; 
    size = 0; 
    
    // Put entries back into the new, larger table
    for (Entry<K, V> entry : set) {
      put(entry.getKey(), entry.getValue()); 
    }
  }

  @Override  
  //PRE:  none
  //POST: Return a set consisting of the values in this map
  public java.util.Set<V> values() {
    java.util.Set<V> set = new java.util.HashSet<>();
    
    for (int i = 0; i < capacity; i++) {
      if (table[i] != null) {
        MyLinkedList<Entry<K, V>> bucket = table[i]; 
        for (Entry<K, V> entry : bucket) {
          set.add(entry.getValue());
        }
      }
    }
    
    return set;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("[");
    
    for (int i = 0; i < capacity; i++) {
      if (table[i] != null && table[i].size() > 0) 
        for (Entry<K, V> entry: table[i])
          builder.append(entry);
    }
    
    builder.append("]");
    return builder.toString();
  }
}