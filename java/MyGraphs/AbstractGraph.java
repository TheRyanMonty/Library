import java.util.*;

public abstract class AbstractGraph<V> implements Graph<V> {

    // Graph attribute - adjList
    protected final Map<V, Map<V, Double>> adjList = new HashMap<>();

    // Vertex operations
    //PRE:  accepts the vertex 
    //POST: verifies the vertex
    //      if valid & not in the graph, add the vertex and return true
    //      if valid & in the graph, return false
    @Override
    public boolean addVertex(V v) {
        if (v == null) throw new IllegalArgumentException("Vertex cannot be null");
        if (adjList.containsKey(v)) 
            return false;
        else{
            adjList.put(v, new HashMap<>());
            return true;
        }
    }

    //PRE:  accepts the vertex 
    //POST: throws and error if the vertex is not in the graph
    protected void requireVertex(V v) {
        if (!adjList.containsKey(v)) {
            throw new IllegalArgumentException("Vertex not found: " + v);
        }
    }

    //PRE:  accepts the vertex 
    //POST: returns true if the vertex is in vertices
    @Override
    public boolean containsVertex(V v) {
        return adjList.containsKey(v);
    }

    //PRE:  none
    //POST: returns a list of vertices
    @Override
    public List<V> vertices() {
        return new ArrayList<>(adjList.keySet());
    }
     

    // Edge operations
    //PRE:  accepts the from & to vertices 
    //POST: returns true if the edge can be added
    //      note: sets weight to a default of '1' for unweighted graph
    //      weighted edges would accept the weight 
    @Override
    public boolean addEdge(V from, V to) {
        return addEdge(from, to, 1.0);
    }

    //PRE:  accepts the from & to vertices and weight 
    //POST: verifies vertices (if invalid throws error)
    //      adds vertices to graph
    //      if the edge is in the graph, return false
    //      else add new edge with from, to & weight to the graph
    protected boolean addEdge(V from, V to, double weight) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Vertices cannot be null");
        }

        addVertex(from);
        addVertex(to);

        if (containsEdge(from, to)) return false;

        adjList.get(from).put(to, weight);

        // add mirror edge if undirected
        if (!isDirected()) {
            adjList.get(to).put(from, weight);
        }
        return true;
    }

    //PRE:  accepts the from & to vertices 
    //POST: returns true if the edge is in the graph
    @Override
    public boolean containsEdge(V from, V to) {
        if (from == null || to == null) return false;

        Map<V, Double> row = adjList.get(from);
        return row != null&&row.containsKey(to);
    }

    //PRE:  accepts the from & to vertices 
    //POST: returns true if the edge can be removed from the graph
    @Override
    public boolean removeEdge(V from, V to) {
        if (from == null || to == null) return false;

        boolean removed = false;

        Map<V, Double> row = adjList.get(from);
        if (row != null && row.remove(to) != null);
            removed = true;

        if (!isDirected()) {
            Map<V, Double> getRow = adjList.get(to);
            if (getRow != null)
                getRow.remove(from);
        }
        return removed;
    }

    //PRE:  accepts the vertex to delete 
    //POST: if vertex is not present, return false
    //      go through edges to remove vertex from edges

    @Override
    public boolean removeVertex(V v) {
        if (v == null) return false;
        if (!adjList.containsKey(v)) return false;

        // Remove all outgoing edges from v
        adjList.remove(v);

        // Remove all incoming edges to v (scan all adjacency rows)
        for (Map<V, Double> row : adjList.values()) {
            row.remove(v);
        }
        return true;
    }

    //PRE:  accepts the vertex 
    //POST: checks vertex (if not present, requireVertex will throw an exception
    //      Create a list of vertices, for those starting at V, add the 'to' 
    //      vertex to the list
    @Override
    public List<V> neighborsOf(V v) {
        requireVertex(v);
        return new ArrayList<>(adjList.get(v).keySet());
    }
}

