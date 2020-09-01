package basic.bp;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class DFS {

    private boolean c[];

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);
        graph.addEdge(3, 7);
        graph.addEdge(4, 7);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);

        DFS dfs = new DFS();
        dfs.dfsWithStackAndAdjacencyList(graph, 0);
    }

    public void dfsWithStackAndAdjacencyMatrix(int[][] a, boolean[] c, int v, boolean flag) {
        Stack<Integer> stack = new Stack<>();
        int n = a.length - 1;

        stack.push(v);
        c[v] = true;
        System.out.print(v + " ");

        while(!stack.isEmpty()) {
            int vv = stack.peek();

            flag = false;

            for (int i = 0 ; i <= n; i ++) {
                if (a[vv][i] == 1 && !c[i]) {
                    stack.push(i);
                    System.out.print(i + " ");
                    c[i] = true;
                    flag = true;

                    break;
                }
            }

            if (!flag) {
                stack.pop();
            }

        }
    }

    public void dfsWithStackAndAdjacencyList(Graph graph, int v) {
        Stack<Integer> stack = new Stack();
        boolean flag = false;
        Boolean c[] = new Boolean[graph.getV()];
        Arrays.fill(c, false);
        LinkedList<Integer> elem;
        int visitNode, i;

        stack.push(v);
        c[v] = true;

        System.out.print(v + " ");

        while(!stack.isEmpty()) {
            visitNode = stack.peek();
            elem = graph.getAdjList().get(visitNode);

            flag = false;

            for (i = 0 ; i < elem.size(); i++) {
                if (!c[elem.get(i)]) {
                    stack.push(elem.get(i));
                    c[elem.get(i)] = true;
                    flag = true;
                    System.out.print(elem.get(i) + " ");
                    break;
                }
            }

            if (!flag) {
                stack.pop();
            }
        }
    }

    public static class Graph {
        int v; // 정점 갯수
        ArrayList<LinkedList<Integer>> adjList;

        public Graph(int n) {
            this.v = n;
            adjList = new ArrayList();

            for (int i = 0 ; i < n; i++) {
                adjList.add(new LinkedList<>());
            }
        }

        public void addNode() {}
        public boolean contains(int v) {
            return adjList.contains(v);
        }
        public void removeNode(int v) {
        }
        public void addEdge(int v, int e) {
            adjList.get(v).offer(e);
        }
        public boolean hasEdge(int v, int e) {
            return adjList.get(v).contains(e);
        }
        public Integer removeEdge(int v, int e) {
            return adjList.get(v).remove(e);
        }
        public void forEachNode() {}

        public int getV() {
            return v;
        }

        public ArrayList<LinkedList<Integer>> getAdjList() {
            return adjList;
        }
    }


}
