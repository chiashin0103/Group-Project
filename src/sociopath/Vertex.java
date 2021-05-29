package sociopath;
//vertex
public class Vertex<T> {
    
    VertexInfo<T> vertexInfo;
    Vertex<T> nextVertex;
    Edge<T> firstEdge;
    
    public Vertex(){
        vertexInfo = null;
        nextVertex = null;
        firstEdge = null;
    }
    
    public Vertex(VertexInfo<T> vInfo, Vertex<T> next){
        vertexInfo = vInfo;
        nextVertex = next;
        firstEdge = null;
    }
    
}
