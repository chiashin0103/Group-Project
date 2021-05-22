package sociopath;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Graph<Integer, Integer> graph = new Graph<>();

        //Random insert vertex information (id->10 people, diving rate, lunch start hour, lunch period)
        VertexInfo<Integer> v1 = new VertexInfo(1, 50, 1120, 15);
        VertexInfo<Integer> v2 = new VertexInfo(2, 60, 1130, 20);
        VertexInfo<Integer> v3 = new VertexInfo(3, 70, 1200, 30);
        VertexInfo<Integer> v4 = new VertexInfo(4, 30, 1230, 40);
        VertexInfo<Integer> v5 = new VertexInfo(5, 80, 1300, 50);
        VertexInfo<Integer> v6 = new VertexInfo(6, 20, 1320, 20);
        VertexInfo<Integer> v7 = new VertexInfo(7, 15, 1245, 30);
        VertexInfo<Integer> v8 = new VertexInfo(8, 80, 1145, 25);
        VertexInfo<Integer> v9 = new VertexInfo(9, 55, 1233, 28);
        VertexInfo<Integer> v10 = new VertexInfo(10, 35, 1125, 35);

        VertexInfo[] people = {v1, v2, v3, v4, v5, v6, v7, v8, v9, v10};

        for (VertexInfo i : people) {
            graph.addVertex(i);
        }

        //initialize relationship(edges) according to Figure 1 with reputation respectively
        System.out.println("Adding edges...");
        graph.addEdge(v1, v7, 3);
        graph.addEdge(v1, v2, 8);
        graph.addEdge(v2, v6, 7);
        graph.addEdge(v2, v3, 4);
        graph.addEdge(v2, v5, 2);
        graph.addEdge(v2, v1, 5);
        graph.addEdge(v3, v2, 5);
        graph.addEdge(v4, v8, 10);
        graph.addEdge(v4, v10, 7);
        graph.addEdge(v5, v2, 6);
        graph.addEdge(v6, v2, 9);
        graph.addEdge(v7, v1, 4);
        graph.addEdge(v8, v4, 7);
        graph.addEdge(v9, v10, 6);
        graph.addEdge(v10, v4, 7);
        graph.addEdge(v10, v9, 5);

        //Test for Graph.java
        System.out.println("The number of vertices in graph: " + graph.getSize());

        System.out.println("Vertices in graph : ");
        for (int i = 1; i <= graph.getSize() - 1; i++) {
            System.out.print(i + ": " + graph.getVertex(i) + "\t");
            System.out.println();
        }

        graph.printEdges();

        System.out.println("Has Vertex? : ");
        System.out.println(graph.hasVertex(v3));

        System.out.println("Has Edge? :  ");
        System.out.println(graph.hasEdge(v1, v2));
        System.out.println(graph.hasEdge(v2, v1));

        System.out.println("Get Vertex :  ");
        System.out.println(graph.getVertex(3));

        System.out.println("Get Neighbour :  ");
        System.out.println(graph.getNeighbours(v1));

        System.out.println("Reputation of each edges :  ");
        System.out.println(graph.getRep(v1, v2));
        System.out.println(graph.getRep(v2, v1));
        System.out.println(graph.getRep(v9, v10));
        System.out.println(graph.getRep(v10, v9));

        System.out.println("Set Reputation :  ");
        System.out.println(graph.setRep(v1, v2, 6));
        System.out.println(graph.getRep(v1, v2));

        //Event 1
         String str="";
         
         Random rn = new Random();
         
         int we =rn.nextInt(10)+1;          //we will become anyone one among 1 to 10 just for testing event 1 
         // later we can put in the initialazation
         int teaching = rn.nextInt(10) + 1;//the people we will be teaching in event 1
         System.out.println("people[we-1]");
         System.out.println(people[we-1]);
         
         ArrayList<Integer> Oureneighbour=graph.getNeighbours(people[we-1]);
         
         do{                                // to avoid the number for we and teaching is same and make sure is stranger
         int newrandom = rn.nextInt(10) + 1;
         teaching=newrandom;
         }while (teaching==we||Oureneighbour.contains(teaching));
         
         System.out.println("You are "+we );//tell we will represent which number
         System.out.println("You are teaching "+teaching+" now" );//tell in event 1 we will be teaching who
         System.out.println("Are you help him/her in the lab question? Answer YES or NO");//are you seccess to help her/him
         
         System.out.println(graph.getRep(people[we-1], people[teaching-1]));//testing
         
         Scanner input=new Scanner(System.in);
         String answer=input.nextLine();
         
             if(answer.equalsIgnoreCase("Yes")){//if success to help him/her
                 
                 {
                     graph.addEdge(people[we-1],people[teaching-1],10);// no edges so we connect them 
                 }
         }
             else{// answer is no
                 
                     graph.addEdge(people[we-1],people[teaching-1],2);//rep will set to 2
                 System.out.println(graph.getRep(people[we-1], people[teaching-1]));//for checking
             }
             //END OF EVENT 1
             
             //For event 2
             
             System.out.println(graph.getNeighbours(people[teaching-1]));// for checking later will delete
             
            
             ArrayList<Integer> tostoreneighbour=graph.getNeighbours(people[teaching-1]);
             // put all neighbour into arry list for later use
             
             
                
             for(int i = 0; i < tostoreneighbour.size();i++) {
                 System.out.println(tostoreneighbour.get(i));
                                   
                    switch (tostoreneighbour.get(i)) {
                      
                     case 1:
                         
                     chit_chat<Integer, Integer> cc1 = new chit_chat<>(graph,people[we-1],people[teaching-1],v1);
                     str = "Good";
                     cc1.strangerToFriend(str);
                      break;
                     case 2:
                     chit_chat<Integer, Integer> cc2 = new chit_chat<>(graph,people[we-1],people[teaching-1],v2);
                     str = "Good";
                     cc2.strangerToFriend(str);
                       break;
                     case 3:
                       chit_chat<Integer, Integer> cc3 = new chit_chat<>(graph,people[we-1],people[teaching-1],v3);
                       str = "Good";
                     cc3.strangerToFriend(str);
                       break;
                     case 4:
                       chit_chat<Integer, Integer> cc4 = new chit_chat<>(graph,people[we-1],people[teaching-1],v4);
                       str = "Good";
                     cc4.strangerToFriend(str);
                       break;
                    case 5:
                       chit_chat<Integer, Integer> cc5 = new chit_chat<>(graph,people[we-1],people[teaching-1],v5);
                       str = "Good";
                     cc5.strangerToFriend(str);
                     break;
                     case 6:
                       chit_chat<Integer, Integer> cc6 = new chit_chat<>(graph,people[we-1],people[teaching-1],v6);
                       str = "Good";
                     cc6.strangerToFriend(str);
                      break;
                   case 7:
                       chit_chat<Integer, Integer> cc7 = new chit_chat<>(graph,people[we-1],people[teaching-1],v7);
                       str = "Good";
                     cc7.strangerToFriend(str);
                       break;
                     case 8:
                       chit_chat<Integer, Integer> cc8 = new chit_chat<>(graph,people[we-1],people[teaching-1],v8);
                       str = "Good";
                     cc8.strangerToFriend(str);
                       break;
                     case 9:
                       chit_chat<Integer, Integer> cc9 = new chit_chat<>(graph,people[we-1],people[teaching-1],v9);
                       str = "Good";
                     cc9.strangerToFriend(str);
                       break;
                     case 10:
                       chit_chat<Integer, Integer> cc10 = new chit_chat<>(graph,people[we-1],people[teaching-1],v10);
                       str = "Good";
                     cc10.strangerToFriend(str);
                       break;
                   }
                         }
             //End of event 2

        //Test for Event 2 - Chit-chat
        System.out.println("-----------------------");
        System.out.println("Test for Chit-Chat");
        //chit_chat<Integer, Integer> cc1 = new chit_chat<>(graph, v1, v2, v5);
        //String str = "Good";
        cc1.strangerToFriend(str);
        System.out.println(graph.getRep(v1, v5));
        System.out.println(graph.getRep(v5, v1));
    }

}
