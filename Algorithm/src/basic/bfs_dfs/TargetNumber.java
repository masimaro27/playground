package basic.bfs_dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 문제 설명
 * n개의 음이 아닌 정수가 있습니다. 이 수를 적절히 더하거나 빼서 타겟 넘버를 만들려고 합니다. 예를 들어 [1, 1, 1, 1, 1]로 숫자 3을 만들려면 다음 다섯 방법을 쓸 수 있습니다.
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * 사용할 수 있는 숫자가 담긴 배열 numbers, 타겟 넘버 target이 매개변수로 주어질 때 숫자를 적절히 더하고 빼서 타겟 넘버를 만드는 방법의 수를 return 하도록 solution 함수를 작성해주세요.
 *
 * 제한사항
 * 주어지는 숫자의 개수는 2개 이상 20개 이하입니다.
 * 각 숫자는 1 이상 50 이하인 자연수입니다.
 * 타겟 넘버는 1 이상 1000 이하인 자연수입니다.
 * 입출력 예
 * numbers	target	return
 * [1, 1, 1, 1, 1]	3	5
 * 입출력 예 설명
 * 문제에 나온 예와 같습니다.
 */
public class TargetNumber {

    private HashMap<Integer, Boolean> visitedMap;
    private Graph graph;
    private int count = 0;

    static class Graph {
        private int V;
        private LinkedList<Integer>[] adjList;

        Graph(int v, int e) {
            this.V = v;
            adjList = new LinkedList[];
            for (int i = 0 ; i < e; i++) {
                adjList[i] = new LinkedList<>();
            }
        }

        public void addEdge(Element elem, int e) {
            if (!hasEdge(elem,e)) {
                adjList[elem.getIndex()].offer(e);
            }
        }

        public boolean hasEdge(Element elem, int e) {
            return adjList[elem.getIndex()].contains(e);
        }

        public int getV() {
            return V;
        }

        public LinkedList<Integer>[] getAdjList() {
            return adjList;
        }
    }

    public void dfs(int v, int sum, int target) {

    }

    static class Element {
        private int index;
        private int value;

        public Element(int i, int v) {
            this.index = i;
            this.value = v;
        }

        public int getIndex() {
            return index;
        }

        public int getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        int arr[] = {1,1,1,1,1};

    }
}
