package sociopath;

/*Event 2 - Chit-chat
3 vertex involved: one done some stuff (source), one spread message (spreader), one receive message and give reputation (receiver)
eventually, source and receiver will become new friend*/

/*TO-DO :
- How to define good/bad message?
- How to spread?
*/

public class chit_chat<T> {
    String str;
    VertexInfo <T> source;
    VertexInfo <T> spreader;
    VertexInfo <T> receiver;
    Graph<T> graph;
    
    public chit_chat(){
        
    }
    public chit_chat(Graph<T> graph, VertexInfo <T> source, VertexInfo <T> spreader, VertexInfo <T> receiver){
        this.graph = graph;
        this.source = source;
        this.spreader = spreader;
        this.receiver = receiver;
    }

    public void setInvolvedVertex(VertexInfo <T> source, VertexInfo <T> spreader, VertexInfo <T> receiver){
        this.source = source;
        this.spreader = spreader;
        this.receiver = receiver;
    }

    /*calculate reputation gain, if good message, then gain spreader rep*0.5, else -spreader rep
      receiver and source from stranger to friend, create new edge between them
      Checking : source and receiver must not be friend && spreader and receiver must be friend*/
    public void strangerToFriend(String str){
        int repGain;

        if(graph.hasEdge(source,receiver)==true && graph.hasEdge(receiver,source)==true){
            System.out.println("They already knew each other!");
        }else if(graph.hasEdge(spreader,receiver)==true && graph.hasEdge(receiver, spreader)==true){
            if(!str.isEmpty() && str.equals("Good")){
                repGain = (int) (graph.getRep(spreader, source)*0.5);
                graph.addEdge(receiver, source, repGain);
                graph.addEdge(source,receiver,1);
                System.out.println("New friendship built : " + graph.getNeighbours(source) + graph.getNeighbours(receiver));
            }else if (str.isEmpty()){
                System.out.println("Empty string...");
            }else{
                repGain = (int) (-graph.getRep(spreader, source));
                graph.addEdge(receiver, source, repGain);
                graph.addEdge(source, receiver,1);
                System.out.println("New friendship built : " + graph.getNeighbours(source)  + graph.getNeighbours(receiver));
            }
        }else{
            System.out.println("Error");
        }

    }
    
    
}
