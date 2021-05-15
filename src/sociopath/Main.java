package sociopath;
//test
//main class
public class Main {
    
    public static void main(String[] args){
        
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
        
        VertexInfo[] people = {v1,v2,v3,v4,v5,v6,v7,v8,v9,v10};
        
        for(VertexInfo i : people){
            graph.addVertex(i);
        }

        //initialize relationship(edges) according to Figure 1 with reputation respectively
        System.out.println("Adding edges...");
        graph.addEdge(v1,v7,3);
        graph.addEdge(v1,v2,8);
        graph.addEdge(v2,v6,7);
        graph.addEdge(v2,v3,4);
        graph.addEdge(v2,v5,2);
        graph.addEdge(v2,v1,5);
        graph.addEdge(v3,v2,5);
        graph.addEdge(v4,v8,10);
        graph.addEdge(v4,v10,7);
        graph.addEdge(v5,v2,6);
        graph.addEdge(v6,v2,9);
        graph.addEdge(v7,v1,4);
        graph.addEdge(v8,v4,7);
        graph.addEdge(v9,v10,6);
        graph.addEdge(v10,v4,7);
        graph.addEdge(v10,v9,5);


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
        System.out.println(graph.hasEdge(v1,v2));
        System.out.println(graph.hasEdge(v2,v1));

        System.out.println("Get Vertex :  ");
        System.out.println(graph.getVertex(3));

        System.out.println("Get Neighbour :  ");
        System.out.println(graph.getNeighbours(v1));

        System.out.println("Reputation of each edges :  ");
        System.out.println(graph.getRep(v1,v2));
        System.out.println(graph.getRep(v2,v1));
        System.out.println(graph.getRep(v9,v10));
        System.out.println(graph.getRep(v10,v9));

        System.out.println("Set Reputation :  ");
        System.out.println(graph.setRep(v1,v2,6));
        System.out.println(graph.getRep(v1,v2));

        //Test for Event 2 - Chit-chat
        System.out.println("-----------------------");
        System.out.println("Test for Chit-Chat");
        chit_chat<Integer, Integer> cc1 = new chit_chat<>(graph,v1,v2,v5);
        String str = "Good";
        cc1.strangerToFriend(str);
        System.out.println(graph.getRep(v1,v5));
        System.out.println(graph.getRep(v5,v1));







    }


    
    
}
