package sociopath;

//initialize reputation (rep) here

public class Edge<T> {
    
    Vertex<T> toVertex;
    int rep;
    Edge<T> nextEdge;
    
    public Edge(){
        toVertex = null;
        rep=1;
        nextEdge = null;
    }
    public Edge(Vertex<T> destination, int r, Edge<T> a){
        toVertex = destination;
        if(rep>=1 && rep<=10) rep = r;
        nextEdge = a;
    }
    public Edge(Vertex<T> destination, Edge<T> a){
        toVertex = destination;
        rep = 1;
        nextEdge = a;
    }
    public void setRep(int newRep){
        this.rep = newRep;
    }


}
