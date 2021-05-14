package sociopath;

//initialize reputation (rep) here

public class Edge<T extends Comparable<T>, N extends Comparable<N>> {
    
    Vertex<T,N> toVertex;
    int rep;
    Edge<T,N> nextEdge;
    
    public Edge(){
        toVertex = null;
        rep=1;
        nextEdge = null;
    }
    public Edge(Vertex<T,N> destination, int r, Edge<T,N> a){
        toVertex = destination;
        if(rep>=1 && rep<=10) rep = r;
        nextEdge = a;
    }
    public Edge(Vertex<T,N> destination, Edge<T,N> a){
        toVertex = destination;
        rep = 1;
        nextEdge = a;
    }
    public void setRep(int newRep){
        this.rep = newRep;
    }


}
