package sociopath;

import java.util.ArrayList;

public class Graph<T extends Comparable<T>, N extends Comparable<N>> {
    Vertex<T,N> head;
    int size;
    
    public Graph(){
        head = null;
        size=0;
    }
    
    //get number of vertices
    public int getSize(){
        return this.size;
    }
    
    //is the vertex in graph?
    public boolean hasVertex(VertexInfo <T> v){
        if(head==null){
            return false;
        }
        Vertex<T,N> temp = head;
        while(temp!=null){
            if(temp.vertexInfo.equals(v)){
                return true;
            }
            temp=temp.nextVertex;
        }
        return false;
    }
    
    //add vertex
    public boolean addVertex(VertexInfo <T> v){
        if(hasVertex(v)==false){
            Vertex<T,N> temp = head;
            Vertex<T,N> newVertex = new Vertex<>(v, null);
            if(head==null){
                head=newVertex;
            }else{
                Vertex<T,N> previous = head;
                while(temp!=null){
                    previous = temp;
                    temp=temp.nextVertex;
                }
                previous.nextVertex=newVertex;
            }
            size++;
            return true;
        }else{
            return false;
        }
    }

    //Not using so far...
   //return all the vertex info to an ArrayList 
   public ArrayList<T> getAllVertexObjects(){
       ArrayList<T> list = new ArrayList<>();
       Vertex<T,N> temp = head;
       while(temp!=null){
           list.add((T) temp.vertexInfo);
           temp=temp.nextVertex;
       }
       return list;
   }

   
   //get vertex info at a specific index/position
   public T getVertex(int pos){
       if(pos>size-1 || pos<0){
           return null;
       }
       Vertex<T,N> temp = head;
       while(temp!=null){
           if(temp.vertexInfo.id==pos){
               return (T) temp.vertexInfo.toString();
           }
           temp=temp.nextVertex;
       }
       return null;
   }
   
   
   //check whether there is an edge
   public boolean hasEdge(VertexInfo<T> source, VertexInfo<T> destination){
       if(head==null){
           return false;
       }
       if(!hasVertex(source) || !hasVertex(destination)){
           return false;
       }
       Vertex<T,N> sourceVertex = head;
       while(sourceVertex!=null){
           if(sourceVertex.vertexInfo.equals(source)){
               Edge<T,N> currentEdge = sourceVertex.firstEdge;
               while(currentEdge!=null){
                   if(currentEdge.toVertex.vertexInfo.equals(destination)){
                       return true;
                   }
                   currentEdge=currentEdge.nextEdge;
               }
           }
           sourceVertex=sourceVertex.nextVertex;
       }
       return false;
   }
   
   //add a new edge from source to destination, with a reputation
   public boolean addEdge(VertexInfo<T> source, VertexInfo<T> destination, int rep){
       if(head==null){
           return false;
       }
       if(!hasVertex(source) || !hasVertex(destination)){
           return false;
       }
       Vertex<T,N> sourceVertex = head;
       while(sourceVertex!=null){
           if(sourceVertex.vertexInfo.equals(source)){
               Vertex<T,N> destinationVertex = head;
               while(destinationVertex!=null){
                   if(destinationVertex.vertexInfo.equals(destination)){
                       Edge<T,N> currentEdge = sourceVertex.firstEdge;
                       int r=0;
                       Edge<T,N> newEdge = new Edge<>(destinationVertex, r, currentEdge);
                       newEdge.setRep(rep);
                       sourceVertex.firstEdge=newEdge;
                       
                       return true;
                   }
                   destinationVertex=destinationVertex.nextVertex;
               }
           }
           sourceVertex=sourceVertex.nextVertex;
       }
       return false;
   }
   
  
   //return all neighbours of a vertex to an ArrayList
   public ArrayList<Integer> getNeighbours(VertexInfo<T> v){
       if(!hasVertex(v)){
           return null;
       }
       ArrayList<Integer> list = new ArrayList<Integer>();
       Vertex<T,N> temp = head;
       while(temp!=null){
           if(temp.vertexInfo.equals(v)){
               Edge<T,N> currentEdge = temp.firstEdge;
               while(currentEdge!=null){
                   list.add(currentEdge.toVertex.vertexInfo.id);
                   currentEdge=currentEdge.nextEdge;
               }
           }
           temp=temp.nextVertex;
       }
       return list;
   }
   
   //set reputation, assume edge already built between source and destination
   public boolean setRep(VertexInfo<T> source, VertexInfo<T> destination, int newR){
       if(head==null){
           return false;
       }
       if(!hasVertex(source) || !hasVertex(destination)){
           return false;
       }
       if(!hasEdge(source, destination)){
           return false;
       }
       Vertex<T,N> sourceVertex = head;
       while(sourceVertex!=null){
           if(sourceVertex.vertexInfo.equals(source)){
               Vertex<T,N> destinationVertex = head;
               while(destinationVertex!=null){
                   if(destinationVertex.vertexInfo.equals(destination)){
                       Edge<T,N> currentEdge = sourceVertex.firstEdge;
                       currentEdge.setRep(newR);
                       return true;
                   }
                   destinationVertex=destinationVertex.nextVertex;
               }
           }
           sourceVertex=sourceVertex.nextVertex;
       }
       return false;
   }

    //get the reputation
    public int getRep(VertexInfo<T> source, VertexInfo<T> destination){
        int notFound = -1;
        if(head==null){
            return notFound;
        }
        if(!hasVertex(source) || !hasVertex(destination)){
            return notFound;
        }
        Vertex<T, N> sourceVertex = head;
        while(sourceVertex!=null){
            if(sourceVertex.vertexInfo.equals(source)){
                Edge<T, N> currentEdge = sourceVertex.firstEdge;
                while(currentEdge!=null){
                    if(currentEdge.toVertex.vertexInfo.equals(destination)){
                        return currentEdge.rep;
                    }
                    currentEdge=currentEdge.nextEdge;
                }
            }
            sourceVertex=sourceVertex.nextVertex;
        }
        return notFound;
    }

   
   //print graph
   public void printEdges(){
       Vertex<T,N> temp = head;
       while(temp!=null){
           System.out.print("# " + temp.vertexInfo.id + " : ");
           Edge<T,N> currentEdge = temp.firstEdge;
           while(currentEdge!=null){
               System.out.print("[" + temp.vertexInfo.id + "," + currentEdge.toVertex.vertexInfo.id + "]");
               currentEdge = currentEdge.nextEdge;
           
           }
           System.out.println();
           temp=temp.nextVertex;
       }
   }
   
}
