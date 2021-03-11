package basic.bfs_dfs;

/*

        두 개의 단어 begin, target과 단어의 집합 words가 있습니다. 아래와 같은 규칙을 이용하여 begin에서 target으로 변환하는 가장 짧은 변환 과정을 찾으려고 합니다.

        1. 한 번에 한 개의 알파벳만 바꿀 수 있습니다.
        2. words에 있는 단어로만 변환할 수 있습니다.
        예를 들어 begin이 hit, target가 cog, words가 [hot,dot,dog,lot,log,cog]라면 hit -> hot -> dot -> dog -> cog와 같이 4단계를 거쳐 변환할 수 있습니다.

        hit
            - hot (o)
                - dot (o)
                    - dog (o)
                        - lot (x)
                        - log (o)
                            - lot (o)
                            - cog (matched) 5
                        - cog (matched) 4
                    - lot (o)
                        - dog (x)
                        - log (o)
                            - dog (o) ...
                            - cog (matched) 5
                        - cog (x)
                    - log (x)
                    - cog (x)
                - dog (x)
                - lot (o)
                    -  ...
                - log (x)
                - cog (x)
            - dot (x)
            - dog (x)
            - lot (x)
            - log (x)
            - cog (x)
        dfs()
        int minDepth = 0;

        dfs (현재방문노드, 노드리스트, depth, 방문관리배열) {
            if (현재 방문 노드 == final_target) {
                if minDepth != 0 && minDepth > depth
                    minDepth = depth;
                return depth;
            }
            if (노드리스트.size == 0) {
                return 0;
            }
            if (방문하지 않은 노드 || isChanged(현재방문노드, 노드리스트)) {
                노드리스트.remove(방문할 노드);
                방문관리배열[i] = true;
                dfs (방문할 노드, 노드리스트, depth + 1, 방문관리배열);
            }

            return 0;
        }


        isChanged(origin, target) {
            tokenize string
            문자열이 하나만 다른것을 찾는 방법?
             방법1 - ascii code 값의 차이는 24를 넘지 않아야한다? (x)
             방법2 - 하나하나 비교.
             origin.toCharArray
             target.toCharArray

             int diff_char_count = 0
             for (length) {
                if (다르면) {
                    diff_char_count ++
                }
                if (diff_char_count > 1) {
                   return false;
                }
             }
            return true;


            }



        두 개의 단어 begin, target과 단어의 집합 words가 매개변수로 주어질 때, 최소 몇 단계의 과정을 거쳐 begin을 target으로 변환할 수 있는지 return 하도록 solution 함수를 작성해주세요.

        제한사항
        각 단어는 알파벳 소문자로만 이루어져 있습니다.
        각 단어의 길이는 3 이상 10 이하이며 모든 단어의 길이는 같습니다.
        words에는 3개 이상 50개 이하의 단어가 있으며 중복되는 단어는 없습니다.
        begin과 target은 같지 않습니다.
        변환할 수 없는 경우에는 0를 return 합니다.
        입출력 예
        begin	target	words	return
        hit	cog	[hot, dot, dog, lot, log, cog]	4
        hit	cog	[hot, dot, dog, lot, log]	0
        입출력 예 설명
        예제 #1
        문제에 나온 예와 같습니다.

        예제 #2R
        target인 cog는 words 안에 없기 때문에 변환할 수 없습니다.

   */

import java.util.Arrays;

public class 단어변환 {
    public static void main(String[] args) {

        String begin = "hit";
        String target = "cog";

        String [] words = {"hot", "dot", "dog", "lot", "log", "cog"};

        단어변환 d = new 단어변환();
        System.out.println("answer : " + d.solution(begin, target, words));

    }
    public int solution(String begin, String target, String[] words) {
        return dfs(begin, target, words, new Boolean[words.length]);
    }

    public int dfs (String begin, String[] words, int depth, int ) {

    }

}
