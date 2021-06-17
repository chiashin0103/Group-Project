package sociopath;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
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
    
   public int getIndex(VertexInfo<T> v){
        Vertex<T> temp = head;
        int pos = 0;
        while(temp!=null){
            if(temp.vertexInfo.equals(v))
                return pos;
            temp = temp.nextVertex;
            pos++;
        }
        return -1;
        
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
   
   //set reputation
   public boolean setRep(VertexInfo<T> source, VertexInfo<T> destination, int newR){
       if(head==null){
           return false;
       }
       if(!hasVertex(source) || !hasVertex(destination)){
           return false;
       }
       if(!hasEdge(source, destination)){
           addEdge(source, destination, newR);
           return true;
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
   
    
    public int eatLunchwMaxRep(int lunchstart,int lunchperiod,VertexInfo<T> user,int divingrate){
        System.out.println("");
        //get lunch interval of user
        int startingTime = lunchstart;
        int endingTime = lunchstart+lunchperiod;
        String temp = String.valueOf(endingTime);
        if(temp.charAt(2)>='6')
            endingTime+=40;
        
        //get lunch interval of all friends
        int[][] friendsLunchTime = getLunchInterval(divingrate);

        //friends who have lunch within the same period
        int[][] canHaveLunch = new int[size][3];    //store friends' id, lunchstart time, lunchend time
        int AvailableFriends = 0;                   //number of friends    
        int exceed = 0;
        for(int i=0;i<friendsLunchTime.length;i++){
            if(user.id.equals(friendsLunchTime[i][0]))
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
        
        //if no friend available
        if(AvailableFriends==0){
            System.out.println("Sorry! No one is available on your lunch time.\nHave a good lunch on yourself!");
            return 0;
        }
        
        int righttime;
        //if only 1 friend available
        if(AvailableFriends==1){
            System.out.println("Congrats! You can gain maximum of 1 rep points by having lunch following the suggested time:");
            System.out.println("Have Lunch with "+canHaveLunch[0][0]+" from "+canHaveLunch[0][1]+" to "+canHaveLunch[0][2]);
            righttime = 1;
            
        }else{

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






            //printing
            righttime = 0; 
            String print = "";
            for(int i=0;i<AvailableFriends;i++){
                if(i==0){
                    print+="Have Lunch with "+canHaveLunch[i][0]+" from "+canHaveLunch[i][1]+" to "+canHaveLunch[i][2];

                }else{
                    if(canHaveLunch[i][2]==canHaveLunch[i-1][2])
                        continue;
                    else if(canHaveLunch[i][1]>canHaveLunch[i-1][2])
                        print+="\nHave Lunch with "+canHaveLunch[i][0]+" from "+canHaveLunch[i][1]+" to "+canHaveLunch[i][2];
                    else
                        print+="\nHave Lunch with "+canHaveLunch[i][0]+" from "+(canHaveLunch[i-1][2]+1)+" to "+canHaveLunch[i][2];
                }

                righttime++;   

            }

            System.out.println("Congrats! You can gain maximum of " + righttime + " rep points by having lunch following the suggested time:");
            System.out.println(print + "\n");
        }
        
        Scanner in = new Scanner(System.in);
        System.out.print("Do you want to follow the suggested plan and have lunch with the above people? (YES/NO): ");
        String text = in.nextLine();
        if(text.equalsIgnoreCase("yes")){
           
            for(int i=0;i<AvailableFriends;i++){
                if(i!=0&&canHaveLunch[i][2]==canHaveLunch[i-1][2])
                    continue;
                Vertex<T> currentVertex = head;
                while(currentVertex!=null){
                    if(currentVertex.vertexInfo.id.equals(canHaveLunch[i][0])){
                        int temporary = getRep(currentVertex.vertexInfo,user);
                        if(temporary<0)                            
                            setRep(currentVertex.vertexInfo,user,1);
                        else
                            setRep(currentVertex.vertexInfo,user,temporary+1);
                        System.out.println("Your rep with " + currentVertex.vertexInfo.id + " is now " +getRep(currentVertex.vertexInfo,user));
                        break;
                    }
                    currentVertex = currentVertex.nextVertex;
                }
            }
        }else{
            System.out.println("OK! Have a good lunch!");
        }
            
        return righttime;
    }
    
    
    //return lunch interval(start and end) of each friends
    private int[][] getLunchInterval(int divingrate){
        Vertex<T> vertex = head;
        int[][] lunchtime = new int[size][3];
        int i=0;
        while(vertex!=null){
            if(vertex.vertexInfo.dive<=divingrate){
                int lunchEnd = vertex.vertexInfo.lunchStart + vertex.vertexInfo.lunchPeriod;
                String temp = String.valueOf(lunchEnd);
                if(temp.charAt(2)>='6')
                    lunchEnd+=40;
                lunchtime[i][0] = (Integer)vertex.vertexInfo.id;
                lunchtime[i][1] = vertex.vertexInfo.lunchStart;
                lunchtime[i][2] = lunchEnd;

                i++;
            }
            vertex = vertex.nextVertex;
        }
        
        return lunchtime;
    }
    
    //End of Event 3
    
    
    /*Event 5: can I prevent the rumour from spreading?
    two vertex, one node one stranger, find the path between them and check if it is possible to stop them
    method: use breadth first search to propagate rumour
    */
    
     
     
    private int spreadPath(VertexInfo<T> stranger,VertexInfo<T> crush){
        Queue<Vertex<T>> queue = new ArrayDeque<>();
        Set<Vertex<T>> alreadyVisited = new HashSet<>();    //to avoid visiting same vertex again
        
        
        //set sourceVertex(stranger) as the start of bfs
        Vertex<T> startVertex = head;
        while(startVertex!=null){
            if(startVertex.vertexInfo.equals(stranger)){
                queue.add(startVertex);
                break;
            }
            startVertex=startVertex.nextVertex;
        }
        
        Vertex<T> currentVertex;            
        int day=0;
        System.out.print("\n----------The rumour is propagating...----------");
        while(!queue.isEmpty()){
            int day_size = queue.size();
            System.out.print("\nDay " + day + "--->");
            while(day_size--!=0){
                currentVertex = queue.remove();
                System.out.print(currentVertex.vertexInfo.id + " ");
                
                alreadyVisited.add(currentVertex);
                ArrayList<Vertex<T>> neighbours = getNeighbors(currentVertex.vertexInfo);
                
                
                queue.addAll(neighbours);//avoid having 2 same vertex in queue
                queue.removeAll(alreadyVisited);
                
                //if reached destination(crush)
                if(currentVertex.vertexInfo.equals(crush)){
                    return day;
                   
                }
            }day++;
            
            
            
        }
        return -1;
     }
     
      public void canPreventRumour(VertexInfo<T> stranger,VertexInfo<T> crush){
        int dayspread = spreadPath(stranger,crush);
        if(dayspread>0){
            System.out.println("\nLook! The rumour only takes "+dayspread+" days to reach your crush.");
        
        }else{
            System.out.println("\nDon't worry, your crush will never know about the rumour!");
            return;
        }
        Queue<List<Vertex<T>>> queue = new ArrayDeque<>();  //to store the paths
        List<Vertex<T>> pathed = new ArrayList<>();         //to store current path
        List<List<Vertex<T>>> toCrush = new ArrayList<>();
        ArrayList<Vertex<T>> neighbours;
      
        //set sourceVertex as the start of bfs
        Vertex<T> startVertex = head;
        while(startVertex!=null){
            if(startVertex.vertexInfo.equals(stranger)){
                pathed.add(startVertex);
                break;
            }
            startVertex=startVertex.nextVertex;
        }
        queue.offer(pathed);
        Vertex<T> currentVertex;            
        
        while(!queue.isEmpty()){
        
                pathed = queue.remove();
                currentVertex = pathed.get(pathed.size()-1);
                if(currentVertex.vertexInfo.equals(crush)){
                    toCrush.add(pathed);
                }
                neighbours = getNeighbors(currentVertex.vertexInfo);

                for(int i=0;i<neighbours.size();i++){
                    if(!pathed.contains(neighbours.get(i))){
                        List<Vertex<T>> newpath = new ArrayList<>(pathed);
                        newpath.add(neighbours.get(i));
                        queue.offer(newpath);
                    }
                }
          
        }
        
        if(toCrush.size()!=0){
            System.out.println("\n----------Checking possible path----------\nHere are the " + toCrush.size() + " possible path to let your crush know. ");
            for(int i=0;i<toCrush.size();i++){
                System.out.print("Path " +(i+1) + ": ");
                for(int j=0;j<toCrush.get(i).size();j++){
                    System.out.print(toCrush.get(i).get(j).vertexInfo.id);
                    if(j!=toCrush.get(i).size()-1)
                        System.out.print("---->");
                    else
                        System.out.println("");
                    
                }
                
            }
            System.out.println("\n------Checking ways to stop the rumour-----");
            if(toCrush.get(0).size()>1){//shortest path must take more than 1 day
                if(toCrush.size()==1){//only one path
                    System.out.println("GOOD NEWS! It is possible to stop the spread!\nTo stop the rumour, you will need to\nOn Day 1: Convince " + toCrush.get(0).get(toCrush.get(0).size()-2).vertexInfo.id );
                }else{//more than 1 path
                    int[] mutualnode = new int[toCrush.get(0).size()];
                    
                    //case 1: every path have mutual node
                    for(int i=1;i<toCrush.get(0).size()-1;i++){
                        Vertex<T> lastVertex = toCrush.get(0).get(i);
                        boolean truenode = true;
                        for(int j=1;j<toCrush.size();j++){
                            if(!toCrush.get(j).contains(lastVertex)){//all path have mutual node
                                truenode = false;
                                continue;
                            }
                            if(truenode)
                                mutualnode[i]++;
                        }
                        if(truenode){
                            System.out.println("GOOD NEWS! It is possible to stop the spread!\nTo stop the rumour, you will need to: \nOn Day 1: Convince " + toCrush.get(0).get(i).vertexInfo.id );
                            return;
                        }
                    }
                    
                    //case 2: path do not have mutual node
                    int max = mutualnode[0];
                    int maxindex = 0;
                    for(int i=1;i<mutualnode.length;i++){
                        if(mutualnode[i]>=max){
                            max = mutualnode[i];
                            maxindex = i;
                        }
                    }
                    Vertex<T> mutualNode = toCrush.get(0).get(maxindex);
                    int nodetoconvince = toCrush.size()-max;// total number of path - mutual node between the path + 1(didnt add 1 in the formula because max doesnt include path 1)  
                    if(nodetoconvince<dayspread){
                        System.out.println("GOOD NEWS! It is possible to stop the spread!\nTo stop the rumour, you will need to: \nOn Day 1: Convince " + mutualNode.vertexInfo.id);
                        
                        for(int z=0,i=2;z<toCrush.size()&&i<=nodetoconvince;z++){
                            if(!toCrush.get(z).contains(mutualNode)){
                                System.out.println("On Day " + i + ": Convince " + toCrush.get(z).get(toCrush.get(z).size()-2));
                                i++;
                            }
                        }
                    }else{
                        System.out.println("Sorry, you do not have time to convince enough people!");
                    }
                }
            }else{
                System.out.println("Sorry, you do not have time to convince enough people!");
            }
            
           
        }else{
            System.out.println("Don't worry, your crush will never know about the rumour!");
            
        }
            
        
         
         
     }    
     //End of event 5
     
     //extra feature
     public int UpgradedEatLunchwMaxRep(int lunchstart, int lunchperiod, VertexInfo<T> user, int divingrate){
        //get lunch interval of user
        int startingTime = lunchstart;
        int endingTime = lunchstart+lunchperiod;
        String temp = String.valueOf(endingTime);
        if(temp.charAt(2)>='6')
            endingTime+=40;
        
        //get lunch interval of all friends
        int[][] friendsLunchTime = getLunchInterval(divingrate);
        
        //friends who have lunch within the same period
        int[][] canHaveLunch = new int[size][3];    //store friends' id, lunchstart time, lunchend time
        int AvailableFriends = 0;                   //number of friends    
        int exceed = 0;
        for(int i=0;i<friendsLunchTime.length;i++){
            if(user.id.equals(friendsLunchTime[i][0]))
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
        
        //if no friend available
        if(AvailableFriends==0){
            System.out.println("Sorry! No one is available on your lunch time.\nHave a good lunch on yourself!");
            return 0;
        }
        
        //if only 1 friend available
        if(AvailableFriends==1){
            System.out.println("Congrats! You can gain maximum of 1 rep points by having lunch following the suggested time:");
            System.out.println("Have Lunch with "+canHaveLunch[0][0]+" from "+canHaveLunch[0][1]+" to "+canHaveLunch[0][2]);
            
        }else{

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

            if(AvailableFriends<=3){
                System.out.println("\nCongrats! You can gain maximum of " + AvailableFriends + " rep points\nThe table will move as follows");

                System.out.println("--------------TABLE--------------");
                for(int i=0;i<AvailableFriends;i++){
                    for(int a:canHaveLunch[i])
                        System.out.print(a + " ");
                System.out.println("");
                }
                System.out.println("");

            }else{

                    for(int i=3;i<AvailableFriends;i++){
                        if(canHaveLunch[i][1]<=canHaveLunch[i-3][2])
                            canHaveLunch[i][1] = canHaveLunch[i-3][2]+1;
                        if(canHaveLunch[i][1]>=canHaveLunch[i][2]){
                            for(int z=i;z<AvailableFriends;z++){//remove unavailable friend
                                canHaveLunch[z] = canHaveLunch[z+1];
                            }
                            i--;
                            AvailableFriends--;
                        }

                    }
                
            
            System.out.println("\nCongrats! You can gain maximum of " + AvailableFriends + " rep points\nThe table will move as follows");

            for(int i=0;i<AvailableFriends-2;i++){
                System.out.println("--------------TABLE--------------");
                for(int j=i;j<i+3;j++){
                    System.out.println(canHaveLunch[j][0] + " from " + canHaveLunch[j][1] + " to " + canHaveLunch[j][2]);
                }
                System.out.println("---------------------------------");

                if(i!=AvailableFriends-3){
                    System.out.println("On " + canHaveLunch[i][2] + ",  "+canHaveLunch[i][0] + " finished lunch and left the table");
                    System.out.println("On " + canHaveLunch[i+3][1] + ",  "+canHaveLunch[i+3][0] + " joined the table");
                }
            }

            }
        }
        
        Scanner in = new Scanner(System.in);
        System.out.print("Do you want to follow the suggested plan and have lunch with the above people? (YES/NO): ");
        String text = in.nextLine();
        if(text.equalsIgnoreCase("yes")){
           
            for(int i=0;i<AvailableFriends;i++){
                Vertex<T> currentVertex = head;
                while(currentVertex!=null){
                    if(currentVertex.vertexInfo.id.equals(canHaveLunch[i][0])){
                        int temporary = getRep(currentVertex.vertexInfo,user);
                        if(temporary<0)                            
                            setRep(currentVertex.vertexInfo,user,1);
                        else
                            setRep(currentVertex.vertexInfo,user,temporary+1);
                        System.out.println("Your rep with " + currentVertex.vertexInfo.id + " is now " +getRep(currentVertex.vertexInfo,user));
                        break;
                    }
                    currentVertex = currentVertex.nextVertex;
                }
            }
        }else{
            System.out.println("OK! Have a good lunch!");
        }
        
        return AvailableFriends;

        
    }
     
     
                
   
}
