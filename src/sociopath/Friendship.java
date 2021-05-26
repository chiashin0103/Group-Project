package sociopath;

import java.util.*;
import java.lang.*;
import java.io.*;

/*left get input*/
//Event-6
public class Friendship {

    int V;
    Map<Integer, List<Integer>> adj; // Adjacency list

    public Friendship(int v) {
        V = v;
        adj = new HashMap<Integer, List<Integer>>();
    }

    public void addEdge(int u, int v) {
        if (!adj.containsKey(u)) {
            adj.put(u, new ArrayList<Integer>());
        }
        adj.get(u).add(v);

    }

    public List<List<Integer>> getAllPaths(Integer u, Integer v) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (u.equals(v)) {
            List<Integer> temp = new ArrayList<Integer>();
            temp.add(u);
            result.add(temp);
            return result;
        }
        boolean[] visited = new boolean[V];
        Deque<Integer> path = new ArrayDeque<Integer>();
        getAllPathsDFS(u, v, visited, path, result);
        return result;
    }

    public void getAllPathsDFS(Integer u, Integer v, boolean[] visited, Deque<Integer> path, List<List<Integer>> result) {
        visited[u] = true; // Mark visited
        path.add(u); // Add to the end
        if (u.equals(v)) {
            result.add(new ArrayList<Integer>(path));
        } else {
            if (adj.containsKey(u)) {
                for (Integer i : adj.get(u)) {
                    if (!visited[i]) {
                        getAllPathsDFS(i, v, visited, path, result);
                    }
                }
            }
        }
        path.removeLast();
        visited[u] = false;
    }

    public static List<List<Integer>> sortList(List<List<Integer>> sortList) {
        Collections.sort(sortList, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> obj1, List<Integer> obj2) {
                return Integer.compare(obj1.size(), obj2.size());
            }
        });
        
        return sortList;
    }

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
        int n = 4;
        Friendship g = new Friendship(n + 1);

//        for (int i = 0; i < n; i++) {
//            g.addEdge(sc.nextInt(), sc.nextInt());
//        }
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 2);
        g.addEdge(2, 1);
        g.addEdge(3, 2);
        g.addEdge(4, 3);
        g.addEdge(2, 4);

        List<List<List<Integer>>> list = new ArrayList<List<List<Integer>>>();
        List<List<Integer>> inlist = new ArrayList<List<Integer>>();

        for (int i = 1; i < n; i++) {
            System.out.println("");
            for (int j = i + 1; j <= n; j++) {
                list.add(g.getAllPaths(i, j));
                list.removeIf(p -> p.isEmpty()); // remove empty bracket []
            }
        }

        for (int k = 0; k < list.size(); k++) {

            for (int l = 0; l < list.get(k).size(); l++) {

                inlist.add(list.get(k).get(l));

            }

        }
        
        sortList(inlist);
        System.out.println("You can form the following friendship(s): ");

        for (List<Integer> m : inlist) {
            System.out.println(m);
        }

    }

}
