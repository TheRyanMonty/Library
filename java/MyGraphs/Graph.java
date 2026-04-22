import java.util.List;

public interface Graph<V> {

    boolean addVertex(V v);
    boolean addEdge(V from, V to);

    boolean removeVertex(V v);
    boolean removeEdge(V from, V to);

    boolean containsVertex(V v);
    boolean containsEdge(V from, V to);

    List<V> vertices();
    List<V> neighborsOf(V v);

    boolean isDirected();
    boolean isWeighted();

}