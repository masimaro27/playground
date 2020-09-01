package basic.bp;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.util.Iterator;
import java.util.LinkedList;

public class BFS {
    public static void main(String[] args) {
        BFS bfs = new BFS();
        BFS.Graph graph = bfs.new Graph(4);

        graph.addEdge(0,1 );
        graph.addEdge(0,2 );
        graph.addEdge(1,2 );
        graph.addEdge(2,0 );
        graph.addEdge(2,3 );
        graph.addEdge(3,3 );

        graph.BFS(2);
    }

    class Graph {
        private int V;
        private LinkedList<Integer> adj[];

        public Graph(int v) {
            V = v;
            adj = new LinkedList[v];
            for (int i = 0 ; i < v ; ++i) {
                adj[i] = new LinkedList();
            }
        }

        void addEdge(int v, int w) {
            adj[v].add(w);
        }

        void BFS(int s) {
            boolean visited[] = new boolean[V];
            LinkedList<Integer> queue = new LinkedList<Integer>();

            visited[s] = true;
            queue.add(s);

            while(queue.size() != 0) {
                s = queue.poll();
                System.out.print(s + " ");

                Iterator<Integer> i = adj[s].listIterator();
                while(i.hasNext()) {
                    int n = i.next();
                    if (!visited[n]) {
                        visited[n] = true;
                        queue.add(n);
                    }
                }
            }
        }
    }

}
