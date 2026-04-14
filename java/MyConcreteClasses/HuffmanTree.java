//This version implements the huffman tree using a binary tree structure

public class HuffmanTree implements Comparable<HuffmanTree> {
    // The root of the HuffmanTree
    private HuffmanNode root; 

    //Constructor: create a HuffmanTree with two sub-HuffmanTrees  
    public HuffmanTree(HuffmanTree t1, HuffmanTree t2) {
      root = new HuffmanNode();
      root.left = t1.root;
      root.right = t2.root;
      root.weight = t1.root.weight + t2.root.weight;
    }
    
    //Constructor: create a HuffmanTree containing a leaf node  
    public HuffmanTree(int weight, char element) {
      root = new HuffmanNode(weight, element);
    }
    
    //Compare HuffmanTrees based on their weights  
    @Override 
    public int compareTo(HuffmanTree t) {
        return Integer.compare(this.root.weight, t.root.weight); 
    }


    //PRE:  Accepts a String
    //POST: creates an integer array indexed by ASCII value
    //      representing the # of times the character occurs 
    public static int[] getCharacterFrequency(String text) {
        // there are 256 ASCII characters
        int[] counts = new int[256]; 
        
        // Count the character in text by ASCII character value
        for (int i = 0; i < text.length(); i++)
           counts[(int)text.charAt(i)]++; 
        
        return counts;
    }
    
    //PRE:  accepts an integer array that represents the count of 
    //      the occurrances of each ascii character  
    //POST: creates a MyHeap (default is minimum) 
    //      adds trees containing the count & character (leaf nodes)
    //      builds the final Huffman tree from the heap by
    //      combining leaf nodes to create the interior nodes

    public static HuffmanTree getHuffmanTree(int[] counts) {
        // Create a heap to hold huffman trees, ensure min heap is used by denoting false
        MyHeap<HuffmanTree> heap = new MyHeap<>(false);  

        // Add leaf nodes (where ascii count > 0) to the tree
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0)
                heap.add(new HuffmanTree(counts[i], (char)i)); 
        }
       
        while (heap.getSize() > 1) { 
            HuffmanTree t1 = heap.remove(); // Remove the smallest weight tree
            HuffmanTree t2 = heap.remove(); // Remove the next smallest weight 
            //System.out.println("\n\tt1:  " + t1.root.element + " :: " + t1.root.weight + "\n\tt2:  " + t2.root.element + " :: " + t2.root.weight);
            heap.add(new HuffmanTree(t1, t2)); // Combine two trees
        }

        return heap.remove(); // The final tree
    }
    

    //PRE:  assumes the HuffmanTree is built
    //POST: if not null, creates an array of strings
    //       for each ascii character (leaf nodes) in the tree
    public static String[] getCode(HuffmanNode root) {
        if (root == null) return null;    
        String[] codes = new String[256];
        assignCode(root, codes);
        return codes;
    }
    
    //TASK:  Complete Assign Code
    //PRE:  accepts current node & array of strings
    //POST: updates the code that represents the leaf nodes 
    private static void assignCode(HuffmanNode root, String[] codes) {
        if (root.left == null && root.right == null) {
            codes[(int)root.element] = root.code;
            return;
        }
        if (root.left != null) {
            root.left.code = root.code + "0";
            assignCode(root.left, codes);
        }
        if (root.right != null) {
            root.right.code = root.code + "1";
            assignCode(root.right, codes);
        }
    }
    

    //PRE:  accepts ascii array with counts of each character
    //POST: as long as the count is > 0
    //      print the index (ascii #), the character, the count, and
    //      the huffman code

    public void printHuffmanTree(int[] counts) {
        String[] codes = getCode(root); // Get codes
            
        for (int i = 0; i < codes.length; i++)
            //if the char is in the text, print the code for that letter
            if (counts[i] != 0) 
                System.out.printf("%-15d%-15s%-15d%-15s\n", 
                                             i, (char)i + "", counts[i], codes[i]);
    }


    //PRE:  accepts a string 'text'
    //POST: retrieve the code list (if none return null)
    //      otherwise, for each character in the text
    //      get the ascii code from 'codes'
    //      & append to the output string
    public String encode(String text) {
        String[] codes = getCode(root); // Get codes
        //If the root is null, return null
        if (root == null) return null;

        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()){
            String code = codes[(int)c];
            sb.append(code);
        }
        return sb.toString();
    }

    //PRE:  accepts a string 'text' which is 
    //      huffman encoded
    //POST: retrieve the code list (if none return null)
    //      otherwise, for each value, advance to the leaf
    //      by going left if it is '0' and right if '1'
    //      append the leaf value to the output string
    public String decode(String text) {
        if (root == null || text == null) return null;
        
        StringBuilder decodedString = new StringBuilder();
        HuffmanNode current = root;
        
        for (int i = 0; i < text.length(); i++) {
            char bit = text.charAt(i);
            
            if (bit == '0') {
                current = current.left;
            } else if (bit == '1') {
                current = current.right;
            }
            
            // If leaf is reached, extract character and reset to root
            if (current.left == null && current.right == null) {
                decodedString.append(current.element);
                current = root;
            }
        }
        return decodedString.toString();
    }

    private static class HuffmanNode {
      char element;         // Stores the character for a leaf node
      int weight;           // weight of the sub-HuffmanTree rooted at this node
      HuffmanNode left;     // Reference to the left sub-HuffmanTree
      HuffmanNode right;    // Reference to the right sub-HuffmanTree
      String code = "";     // The code of this node from the root

      //Constructor: create an empty node 
      public HuffmanNode() {
      }
      
      //Constructor: create a node with the specified weight and character  
      public HuffmanNode(int weight, char element) {
        this.weight = weight;
        this.element = element;
      }
    }
  }  
