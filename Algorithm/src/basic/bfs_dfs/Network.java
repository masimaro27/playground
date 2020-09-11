package basic.bfs_dfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Network {

    public static void main(String[] args) {
        int n = 3;
        int[][] computers = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };

        Network nw = new Network();
        System.out.println(nw.solution(3, computers));

    }
    public int solution(int n, int[][] computers) {
        int answer = 0;
        Boolean[] c = new Boolean[computers.length];
        Arrays.fill(c, false);

        boolean isAllVisit = false;
        int v = 0, i;
        while(!isAllVisit) {
            for (i = 0 ; i < c.length; i++) {
                if (c[i]) {
                    continue;
                }
                v = i;
                break;
            }
            c = bfs(computers, v, c);
            isAllVisit = !Arrays.asList(c).contains(false);
            answer++;

        }
        return answer;
    }

    public Boolean[] bfs(int[][] adjArr, int v, Boolean[] c) {
        int nextNode, i;
        Queue<Integer> q = new LinkedList<>();
        q.offer(v);
        c[v] = true;


        while(!q.isEmpty()) {
            nextNode = q.peek();
            for (i = 0; i < adjArr[nextNode].length; i++) {
                if (adjArr[nextNode][i] == 0 || i == nextNode || c[i]) {
                    continue;
                }

                q.offer(i);
                c[i] = true;
            }

            q.poll();
        }

        return c;
    }
}
