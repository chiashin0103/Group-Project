package sociopath;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Graph<T> {
    Vertex<T> head;
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
        Vertex<T> temp = head;
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
            Vertex<T> temp = head;
            Vertex<T> newVertex = new Vertex<>(v, null);
            if(head==null){
                head=newVertex;
            }else{
                Vertex<T> previous = head;
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
       Vertex<T> temp = head;
       while(temp!=null){
           list.add((T) temp.vertexInfo);
           temp=temp.nextVertex;
       }
       return list;
   }

   
   //get vertex info at a specific index/position
    public VertexInfo<T> getVertex(int pos){
        if(pos>size-1||pos<0)
            return null;
        Vertex<T> temp = head;
        for(int i=0;i<pos;i++)
            temp = temp.nextVertex;
        return temp.vertexInfo;
    }
   
   
   //check whether there is an edge
   public boolean hasEdge(VertexInfo<T> source, VertexInfo<T> destination){
       if(head==null){
           return false;
       }
       if(!hasVertex(source) || !hasVertex(destination)){
           return false;
       }
       Vertex<T> sourceVertex = head;
       while(sourceVertex!=null){
           if(sourceVertex.vertexInfo.equals(source)){
               Edge<T> currentEdge = sourceVertex.firstEdge;
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
       Vertex<T> sourceVertex = head;
       while(sourceVertex!=null){
           if(sourceVertex.vertexInfo.equals(source)){
               Vertex<T> destinationVertex = head;
               while(destinationVertex!=null){
                   if(destinationVertex.vertexInfo.equals(destination)){
                       Edge<T> currentEdge = sourceVertex.firstEdge;
                       int r=0;
                       Edge<T> newEdge = new Edge<>(destinationVertex, r, currentEdge);
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
   public ArrayList<T> getNeighbours(VertexInfo<T> v){
       if(!hasVertex(v)){
           return null;
       }
       ArrayList<T> list = new ArrayList<T>();
       Vertex<T> temp = head;
       while(temp!=null){
           if(temp.vertexInfo.equals(v)){
               Edge<T> currentEdge = temp.firstEdge;
               while(currentEdge!=null){
                   list.add(currentEdge.toVertex.vertexInfo.id);
                   currentEdge=currentEdge.nextEdge;
               }
           }
           temp=temp.nextVertex;
       }
       return list;
   }
   
   //getNeighbours return vertex
   public ArrayList<Vertex<T>> getNeighbors(VertexInfo<T> v){
       if(!hasVertex(v)){
           return null;
       }
       ArrayList<Vertex<T>> list = new ArrayList<>();
       Vertex<T> temp = head;
       while(temp!=null){
           if(temp.vertexInfo.equals(v)){
               Edge<T> currentEdge = temp.firstEdge;
               while(currentEdge!=null){
                   list.add(currentEdge.toVertex);
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
       Vertex<T> sourceVertex = head;
       while(sourceVertex!=null){
           if(sourceVertex.vertexInfo.equals(source)){
               Vertex<T> destinationVertex = head;
               while(destinationVertex!=null){
                   if(destinationVertex.vertexInfo.equals(destination)){
                       Edge<T> currentEdge = sourceVertex.firstEdge;
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
        Vertex<T> sourceVertex = head;
        while(sourceVertex!=null){
            if(sourceVertex.vertexInfo.equals(source)){
                Edge<T> currentEdge = sourceVertex.firstEdge;
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
       Vertex<T> temp = head;
       while(temp!=null){
           System.out.print("# " + temp.vertexInfo.id + " : ");
           Edge<T> currentEdge = temp.firstEdge;
           while(currentEdge!=null){
               System.out.print("[" + temp.vertexInfo.id + "," + currentEdge.toVertex.vertexInfo.id + "]");
               currentEdge = currentEdge.nextEdge;
           
           }
           System.out.println();
           temp=temp.nextVertex;
       }
   }
   
   
       /*Event 3 - find the maximum reputation that can obtain by having lunch with friends.(1 person = 1 rep)
    -Method : find maximum number of friends that can have lunch with within your lunch period        
    
    */
   //change input to lunch time?
   //use average? - use stack or linkedlist
    
    public int getMaxRep(VertexInfo<T> v){
        //get lunch interval of v
        int startingTime = v.lunchStart;
        int endingTime = v.lunchStart + v.lunchPeriod;
        String temp = String.valueOf(endingTime);
        if(temp.charAt(2)>='6')
            endingTime+=40;
        
        //get lunch interval of all friends
        int[][] friendsLunchTime = getLunchInterval();
        
        //friends who have lunch within the same period
        int[][] canHaveLunch = new int[size][3];    //store friends' id, lunchstart time, lunchend time
        int AvailableFriends = 0;                   //number of friends    
        int exceed = 0;
        for(int i=0;i<friendsLunchTime.length;i++){
            if(startingTime==friendsLunchTime[i][1]&&endingTime==friendsLunchTime[i][2])
                continue;
            if(friendsLunchTime[i][1]<endingTime&&friendsLunchTime[i][2]>startingTime){ //must start before you end or end after you start
                canHaveLunch[AvailableFriends] = friendsLunchTime[i];
                if(friendsLunchTime[i][1]<startingTime)
                    canHaveLunch[AvailableFriends][1] = startingTime;                   //only save available time of v
                if(friendsLunchTime[i][2]>endingTime){
                    canHaveLunch[AvailableFriends][2] = endingTime;                     //only save available time of v
                    exceed++;
                }
                AvailableFriends++;                
            }
                
        }
        
        
        
        //if only 1 friend available
        if(AvailableFriends==1){
            System.out.println("Have Lunch with "+canHaveLunch[1][0]+" from "+canHaveLunch[1][1]+" to "+canHaveLunch[1][2]);
            return 1;
        }
                      

        //sort friends' lunch period increasingly according to their lunch end time
        for(int i=0;i<AvailableFriends;i++){
            for(int j=0;j<AvailableFriends-1-i;j++){
                if(canHaveLunch[j][2]>canHaveLunch[j+1][2]){
                int[] hold = canHaveLunch[j];
                canHaveLunch[j] = canHaveLunch[j+1];
                canHaveLunch[j+1] = hold;
                }
                else if(canHaveLunch[j][2]==canHaveLunch[j+1][2]){//if lunch end same, sort according to lunch start time
                    if(canHaveLunch[j][1]>canHaveLunch[j+1][1]){
                        int[] hold = canHaveLunch[j];
                        canHaveLunch[j] = canHaveLunch[j+1];
                        canHaveLunch[j+1] = hold;
                        }
                }
                    
            }
            
        }
        

        
        //To get suitable time for each friends
        System.out.println("Suggested time");
          
        
        //printing
        int righttime = 0;        
        for(int i=0;i<AvailableFriends;i++){
            if(i==0){
                System.out.println("Have Lunch with "+canHaveLunch[i][0]+" from "+canHaveLunch[i][1]+" to "+canHaveLunch[i][2]);
                
            }else{
                if(canHaveLunch[i][2]==canHaveLunch[i-1][2])
                    continue;
                else if(canHaveLunch[i][1]>canHaveLunch[i-1][2])
                    System.out.println("Have Lunch with "+canHaveLunch[i][0]+" from "+canHaveLunch[i][1]+" to "+canHaveLunch[i][2]);
                else
                    System.out.println("Have Lunch with "+canHaveLunch[i][0]+" from "+(canHaveLunch[i-1][2]+1)+" to "+canHaveLunch[i][2]);
            }
            
            righttime++;   

        }
        return righttime;
    }
    
    
    //return lunch interval(start and end) of each friends
    private int[][] getLunchInterval(){
        Vertex<T> vertex = head;
        int[][] lunchtime = new int[size][3];
        int i=0;
        while(vertex!=null){
            int lunchEnd = vertex.vertexInfo.lunchStart + vertex.vertexInfo.lunchPeriod;
            String temp = String.valueOf(lunchEnd);
            if(temp.charAt(2)>='6')
                lunchEnd+=40;
            lunchtime[i][0] = (Integer)vertex.vertexInfo.id;
            lunchtime[i][1] = vertex.vertexInfo.lunchStart;
            lunchtime[i][2] = lunchEnd;
            
            i++;
            vertex = vertex.nextVertex;
        }
        
        return lunchtime;
    }
    
    //End of Event 3
    
    
    /*Event 5: can I prevent the rumour from spreading?
    
    */
    
     
     
     public int canPreventRumour(VertexInfo<T> stranger,VertexInfo<T> crush){
        Queue<Vertex<T>> queue = new ArrayDeque<>();
        Vertex<T> startVertex = head;
        //set sourceVertex as the start of bfs
        while(startVertex!=null){
            if(startVertex.vertexInfo.equals(stranger)){
                queue.add(startVertex);
                break;
            }
            startVertex=startVertex.nextVertex;
        }
        Vertex<T> currentVertex;            
        Set<Vertex<T>> alreadyVisited = new HashSet<>();    //to avoid visiting same vertex again
        int day=0;
        
        while(!queue.isEmpty()){
            int day_size = queue.size();
            System.out.println("\nDay " + day + ":");
            while(day_size--!=0){
                currentVertex = queue.remove();
                System.out.print(currentVertex.vertexInfo.id + " ");

                if(currentVertex.vertexInfo.equals(crush)){
                    System.out.println("\nIt only takes " + day + " days to reach your crush!");
                    ArrayList<Vertex<T>> neighbours = getNeighbors(currentVertex.vertexInfo);
                    ArrayList<Vertex<T>> upper = new ArrayList<>();
                    for(int i=0;i<neighbours.size();i++){
                        if(alreadyVisited.contains(neighbours.get(i))){
                            upper.add(neighbours.get(i));
                        }
                    }
                    
                    
                    if(upper.size()<day){
                        int temp = 0;
                        System.out.println("To stop it, you need to convince: " );
                        for(Vertex<T> a:upper){
                            System.out.print(a.vertexInfo.id + " on day " + ++temp);
                            System.out.println("");
                            
                        }

                    }else{
                        
                    }
                    
                    return day;
                }else{
                    alreadyVisited.add(currentVertex);
                    ArrayList<Vertex<T>> neighbours = getNeighbors(currentVertex.vertexInfo);
                    queue.removeAll(neighbours);
                    queue.addAll(neighbours);
                    queue.removeAll(alreadyVisited);
                }
            }day++;
            
        }
        System.out.println("Don't worry, your crush will never know about the rumour!");
        return -1;
         
         
     }
                
   
}
