package sociopath;

public class Vertex<T extends Comparable<T>, N extends Comparable<N>> {
    
    VertexInfo<T> vertexInfo;
    Vertex<T,N> nextVertex;
    Edge<T,N> firstEdge;
    
    public Vertex(){
        vertexInfo = null;
        nextVertex = null;
        firstEdge = null;
    }
    
    public Vertex(VertexInfo<T> vInfo, Vertex<T,N> next){
        vertexInfo = vInfo;
        nextVertex = next;
        firstEdge = null;
    }
    
}
