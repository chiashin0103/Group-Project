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
   
   
       /*Event 3 - find the maximum reputation that can obtain by having lunch with friends.(1 person = 1 rep)
    -Method : find maximum number of friends that can have lunch with within your lunch period        
    
    */
    
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
        for(int i=0;i<AvailableFriends;i++){
            
            //set starting time to eat with each friend as ending time of previous friend
            if(i!=0){
                if(canHaveLunch[i][1]<=canHaveLunch[i-1][2])
                    canHaveLunch[i][1]=canHaveLunch[i-1][2]+1;
            }
            
            //set ending time to eat with each friend
            if(canHaveLunch[i][2]==endingTime){
                if(i!=AvailableFriends-1){
                    if(canHaveLunch[i][1]>=canHaveLunch[i+1][1])
                        canHaveLunch[i][2] = canHaveLunch[i][1];
                    else
                        canHaveLunch[i][2] = canHaveLunch[i+1][1]-1;
                }
            }
            
            //if friends have same ending time            
            if(canHaveLunch[i][2]==canHaveLunch[i+1][2])
                canHaveLunch[i][2]=canHaveLunch[i][1]+1;
            
            //checking
            String check = "";
            for(int z=0;z<2;z++){
                check = String.valueOf(canHaveLunch[i][z+1]);
                if(check.charAt(2)=='6')
                    canHaveLunch[i][z+1]+=40;
                else if(check.charAt(2)>'6')
                    canHaveLunch[i][z+1]-=40;
            }
            
        }
           
        
        //printing
        int righttime = 0;        
        for(int i=0;i<AvailableFriends;i++){
                righttime++;   
                System.out.println("Have Lunch with "+canHaveLunch[i][0]+" from "+canHaveLunch[i][1]+" to "+canHaveLunch[i][2]);

        }
        return righttime;
    }
    
    //return lunch interval(start and end) of each friends
    private int[][] getLunchInterval(){
        Vertex<T,N> vertex = head;
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
   
}
