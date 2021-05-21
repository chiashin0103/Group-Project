package sociopath;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*joc-output arrangement not the same as example output..*/
//Event-6
public class formingFriendBond {

    private int v;
    private ArrayList<Integer>[] adjList;

    // Constructor
    public formingFriendBond(int ver) {
        this.v = ver;
        getAdjList();
    }

    private void getAdjList() {
        adjList = new ArrayList[v];

        for (int i = 0; i < v; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    // add edge from one vertex to another
    //undirected graph
    public void addEdge(int s, int d) {
        adjList[s].add(d);
        adjList[d].add(s);
    }

    public void printAllPaths(int s, int d) {
        boolean[] isVisited = new boolean[v];
        ArrayList<Integer> path = new ArrayList<>();

        // add source to path
        path.add(s);

        printAllPathsHelper(s, d, isVisited, path);
    }

    private void printAllPathsHelper(Integer u, Integer d, boolean[] isVisited, List<Integer> pathsList) {
        //if u==d, then stop
        if (u.equals(d)) {
            System.out.println(pathsList);
            return;
        }

        // Mark the current node
        isVisited[u] = true;

        //loop through all adjlist
        for (Integer i : adjList[u]) {
            if (!isVisited[i]) {
                // store current node
                pathsList.add(i);
                printAllPathsHelper(i, d, isVisited, pathsList);
                // remove current node
                pathsList.remove(i);
            }
        }

        // Mark the current node
        isVisited[u] = false;
    }

    public static void main(String[] args) {
        //get number of vertices
        System.out.println("Input: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //initialize a graph
        formingFriendBond bff = new formingFriendBond(n + 1);
        int s = 0, d = 0;
        //get input 
        for (int i = 0; i < n; i++) {
            bff.addEdge(sc.nextInt(), sc.nextInt());
        }
        //print output
        System.out.println("");
        System.out.print("Output: ");
        for (int i = 1; i < n; i++) {
            System.out.println("");
            for (int j = i + 1; j <= n; j++) {
                bff.printAllPaths(i, j);
            }
        }

    }
}
