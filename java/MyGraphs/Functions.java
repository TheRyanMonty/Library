import java.util.*;

public class Functions {

    public static boolean isDigits(String inString){
        if (inString == null || inString.isEmpty()) 
            return false;
        for (char c : inString.toCharArray()) {
            if (!Character.isDigit(c)) 
               return false;
        }
        return true;
    }

    
  public static void example1(){
    System.out.println("EXAMPLE 1: Creating a Graph: Undirected & Unweighted");
    GenericGraph<Integer> graph1 = new GenericGraph<>(false, false);

    graph1.addEdge(0,1);
    graph1.addEdge(0,4);
    graph1.addEdge(1,2);
    graph1.addEdge(1,3);
    graph1.addEdge(1,4);
    graph1.addEdge(2,3);
    graph1.addEdge(3,4);

    System.out.println("\nGraph Representation (adjacency list): ");
    graph1.printAdjacencyList();

  /*
    System.out.print("Graph Breadth First Search Order:        ");
    List<Integer> bfsList = graph1.breadthFirstSearch(0);
    System.out.println(bfsList);

    GenericGraph.DFSOrder<Integer> dfsList = graph1.depthFirstSearch(0);

    System.out.print("\nGraph Depth First - Visit Order:         ");
    System.out.println(dfsList.visitOrder);

    System.out.print("Graph Depth First - Finish Order:        ");
    System.out.println(dfsList.finishOrder);
    if (graph1.isDirected()){
          System.out.print("Graph Depth First -Topological Order:   ");
      System.out.println(dfsList.topologicalOrder);
    }
    System.out.println("Does this graph contain a cycle?         " + dfsList.isCyclic);

   */
  }

  public static void example2() {
 
    System.out.println("\nExample 2: Creating a Graph: Directed & Unweighted ");

    // List of Edge objects for graph in Figure 28.3a
    String[] names = {"Peter", "Jane", "Mark", "Cindy", "Wendy"};
    int[][]edges = {{0,2}, {1,2}, {2,4}, {3,4}};

    GenericGraph<String> graph1 = new GenericGraph<>(true, false, names, edges);

    System.out.println("\nGraph Representation (adjacency list):  ");
    graph1.printAdjacencyList();
  }
 
  public static void example3() {
 
    System.out.println("\nExample 3: Creating a Graph: Directed & Weighted ");

    // List of Edge objects Example from Lecture
    Character[] names = {'A', 'B', 'C', 'D', 'E'};
    double[][]edges = {{0, 1, 3.0}, {0, 2, 1.0}, {0, 3, 7.0}, {2,1, 2.0}, {1,4, 5.0}, {2,4,4.0}, {3, 4, 1.0}};

    GenericGraph<Character> graph3 = new GenericGraph<>(true, true, names, edges);

    System.out.println("\nGraph Representation (adjacency list):  ");
    graph3.printAdjacencyList();

  }

  public static void example4() {
 
    System.out.println("\nExample 4: Creating a Graph: Undirected & Weighted ");

    // List of Edge objects Example from Lecture
    Character[] names = {'A', 'B', 'C', 'D', 'E'};
    int[][]edges = {{0, 1, 3}, {0, 2, 1}, {0, 3, 7}, {2, 1, 2}, {1, 4, 5}, {2, 4, 4}, {3, 4, 1}};

    GenericGraph<Character> graph4 = new GenericGraph<>(false, true, names, edges);

    System.out.println("\nGraph Representation (adjacency list):  ");
    graph4.printAdjacencyList();

  }


  public static void example5(){
    System.out.println("\n\nEXAMPLE 5:  BFS Example (Lecture)");
    GenericGraph<Integer> graph1 = new GenericGraph<>(true, false);

    graph1.addEdge(0,2);
    graph1.addEdge(1,0);
    graph1.addEdge(1,2);
    graph1.addEdge(1,4);
    graph1.addEdge(1,5);
    graph1.addEdge(2,5);
    graph1.addEdge(3,0);
    graph1.addEdge(3,1);
    graph1.addEdge(4,6);
    graph1.addEdge(5,6);

    System.out.println("\nGraph Representation (adjacency list):  ");
    graph1.printAdjacencyList();

    System.out.print("Graph Breadth First Search Order:       ");
    List<Integer> bfsList = graph1.breadthFirstSearch(3);
    System.out.println(bfsList);

    List<Integer> shortest = graph1.getShortestPathUnweighted(3, 6);
    System.out.println(shortest);

  }
  
  public static void example6() {
 
    System.out.println("\nExample 6: Shortest Path: Directed & Unweighted ");

    // List of Edge objects Example from Lecture
    Character[] names = {'A', 'B', 'C', 'D', 'E', 'F'};
    int[][]edges = {{0, 1}, {1, 0}, {1, 4}, {2, 5}, {3, 0}, {3, 5}, {4, 3}, {4, 2}, {5, 4}};

    GenericGraph<Character> graph1 = new GenericGraph<>(true, false, names, edges);

    System.out.println("\nGraph Representation (adjacency list):  ");
    graph1.printAdjacencyList();

    System.out.print("Graph Breadth First Search Order:       ");
    List<Character> bfsList = graph1.breadthFirstSearch('A');
    System.out.println(bfsList);

    List<Character> shortest = graph1.getShortestPathUnweighted('A', 'F');
    System.out.println(shortest);

  }

  public static void example7(){
      System.out.println("\n\nEXAMPLE 7: Test Shortest Path (Undirected & Unweighted)\n");

    // List of Edge objects Example from Lecture
    Character[] names = {'S', 'A', 'B', 'C', 'D', 'E'};
    int[][]edges = {{0, 1}, {0, 2}, {0, 5}, {1, 2}, {2, 3}, {2, 4}, {4, 5}};

    GenericGraph<Character> graph1 = new GenericGraph<>(false, false, names, edges);

    System.out.println("\nGraph Representation (adjacency list):  ");
    graph1.printAdjacencyList();

    System.out.print("Graph Breadth First Search Order:       ");
    List<Character> bfsList = graph1.breadthFirstSearch('S');
    System.out.println(bfsList);

    List<Character> shortest = graph1.getShortestPathUnweighted('S', 'D');
    System.out.println(shortest);
  }


  public static void example8(){
      System.out.println("\n\nEXAMPLE 8: Test Shortest Path (Undirected & Unweighted)\n");

    // List of Edge objects Example from Lecture
    Character[] names = {'S', 'A', 'B', 'C', 'D', 'E', 'F'};
    int[][]edges = {{0, 2}, {0, 5}, {1, 2}, {1, 5}, {2, 3}, {2, 6}, 
                    {3, 4}, {4, 5}, {5, 6}};

    GenericGraph<Character> graph1 = new GenericGraph<>(false, false, names, edges);

    System.out.println("\nGraph Representation (adjacency list):  ");
    graph1.printAdjacencyList();

    System.out.print("Graph Breadth First Search Order:       ");
    List<Character> bfsList = graph1.breadthFirstSearch('S');
    System.out.println(bfsList);

    List<Character> shortest = graph1.getShortestPathUnweighted('S', 'D');
    System.out.println(shortest);
  }

  
   public static void example9(){
      System.out.println("\n\nEXAMPLE 9: Shortest Path (Undirected)\n");
      GenericGraph<Integer> graph1 = new GenericGraph<>(false, false);

      graph1.addEdge(0, 1);
      graph1.addEdge(1, 2);
      graph1.addEdge(1, 4);
      graph1.addEdge(2, 7);
      graph1.addEdge(2, 8);
      graph1.addEdge(4, 10);
      graph1.addEdge(4, 5);
      graph1.addEdge(5, 3);
      graph1.addEdge(5, 6);
      graph1.addEdge(5, 10);
      graph1.addEdge(6, 11);
      graph1.addEdge(7, 6);
      graph1.addEdge(7, 9);
      graph1.addEdge(10, 11);
      graph1.addEdge(8, 12);
      graph1.addEdge(11, 8);

    System.out.println("\nGraph Representation (adjacency list): ");
    graph1.printAdjacencyList();

    System.out.print("Graph Breadth First Search Order:       ");
    List<Integer> bfsList = graph1.breadthFirstSearch(0);
    System.out.println(bfsList);

    System.out.print("Shortest Path Order:                    ");
    List<Integer> r = graph1.getShortestPathUnweighted(0, 12);
    System.out.println(r);
  }

  public static void example10(){
    System.out.println("\n\nEXAMPLE 10: BFS Example (Lecture)");
    Integer[] nodes = {0,1,2,3,4,5,6,7,8,9};
    int[][]edges4 = {
      {0,1}, {0,3},
      {1,2}, {1,4}, {1,6}, {1,7},
      {2,3}, {2,8}, {2,9},
      {4,5}, {4,6}, {4,7}, {6,7}};

    GenericGraph<Integer> graph1 = new GenericGraph<>(false, false, nodes, edges4);

    System.out.println("\nGraph Representation (adjacency list):  ");
    graph1.printAdjacencyList();

    System.out.print("Graph Breadth First Search Order:       ");
    List<Integer> bfsList = graph1.breadthFirstSearch(0);
    System.out.println(bfsList);

    List<Integer> shortest = graph1.getShortestPathUnweighted(8, 7);
    System.out.println(shortest);

  }

  public static void example11(){
      System.out.println("\n\nEXAMPLE 11: DFS Example (Lecture) Undirected & Unweighted");
      Integer[] nodes = {0,1,2,3,4,5,6};
      int[][]edges4 = {
        {0,1}, {0,2}, {0,3}, {0,4}, 
        {1,3}, {1,4}, 
        {2,5}, {2,6}, 
        {3,4},
        {5,6}};

      GenericGraph<Integer> graph = new GenericGraph<>(false, false, nodes, edges4);

      System.out.println("\nGraph Representation (adjacency list):  ");
      graph.printAdjacencyList();
      GenericGraph.DFSOrder<Integer> dfsList = graph.depthFirstSearch(0);

      System.out.print("\nGraph Depth First - Visit Order:        ");
      System.out.println(dfsList.visitOrder);

      System.out.print("Graph Depth First - Finish Order:       ");
      System.out.println(dfsList.finishOrder);
      if (graph.isDirected()){
          System.out.print("Graph Depth First - Topological Order:  ");
          System.out.println(dfsList.topologicalOrder);
      }
  }

    public static void example12(){
      System.out.println("\n\nEXAMPLE 12:  DFS Example (same as BFS Example 5)");
      GenericGraph<Integer> graph = new GenericGraph<>(true, false);

      graph.addEdge(0,2);
      graph.addEdge(1,0);
      graph.addEdge(1,2);
      graph.addEdge(1,4);
      graph.addEdge(1,5);
      graph.addEdge(2,5);
      graph.addEdge(3,0);
      graph.addEdge(3,1);
      graph.addEdge(4,6);
      graph.addEdge(5,6);

      System.out.println("\nGraph Representation (adjacency list):  ");
      graph.printAdjacencyList();
      GenericGraph.DFSOrder<Integer> dfsList = graph.depthFirstSearch(3);

      System.out.print("\nGraph Depth First - Visit Order:        ");
      System.out.println(dfsList.visitOrder);

      System.out.print("Graph Depth First - Finish Order:       ");
      System.out.println(dfsList.finishOrder);
      if (graph.isDirected()){
          System.out.print("Graph Depth First - Topological Order:  ");
          System.out.println(dfsList.topologicalOrder);
      }
  }
  
  public static void example13() {
  
      System.out.println("\nExample 13: DFS EXAMPLE: Directed & Unweighted ");

      // List of Edge objects Example from Lecture
      Character[] names = {'A', 'B', 'C', 'D', 'E'};
      int[][]edges = {{0, 1}, {0, 3}, {1, 2}, {2, 3}, {2, 4}, {3, 4}};

      GenericGraph<Character> graph = new GenericGraph<>(true, false, names, edges);

      System.out.println("\nGraph Representation (adjacency list):  ");
      graph.printAdjacencyList();
      GenericGraph.DFSOrder<Character> dfsList = graph.depthFirstSearch('A');

      System.out.print("\nGraph Depth First - Visit Order:        ");
      System.out.println(dfsList.visitOrder);

      System.out.print("Graph Depth First - Finish Order:       ");
      System.out.println(dfsList.finishOrder);
      if (graph.isDirected()){
        System.out.print("Graph Depth First - Topological Order:  ");
        System.out.println(dfsList.topologicalOrder);
      }
  }

  public static void example14(){
      System.out.println("\n\nEXAMPLE 14: DFS Example (Lecture) Directed & Unweighted");
      Integer[] nodes = {0,1,2,3,4,5,6,7,8};
      int[][]edges = {
        {0,1}, {0,3}, {0,4}, {1,2}, {1,4}, {1,5}, 
        {2,5}, {3,6}, {4, 6}, {4,7}, 
        {5,7}, {6,8}, {7,8}};

      GenericGraph<Integer> graph = new GenericGraph<>(true, false, nodes, edges);

      System.out.println("\nGraph Representation (adjacency list):  ");
      graph.printAdjacencyList();
      GenericGraph.DFSOrder<Integer> dfsList = graph.depthFirstSearch(0);

      System.out.print("\nGraph Depth First - Visit Order:        ");
      System.out.println(dfsList.visitOrder);

      System.out.print("Graph Depth First - Finish Order:       ");
      System.out.println(dfsList.finishOrder);
      if (graph.isDirected()){
          System.out.print("Graph Depth First - Topological Order:  ");
          System.out.println(dfsList.topologicalOrder);
      }
  }

}
