package basic.bfs_dfs;

/*

        두 개의 단어 begin, target과 단어의 집합 words가 있습니다. 아래와 같은 규칙을 이용하여 begin에서 target으로 변환하는 가장 짧은 변환 과정을 찾으려고 합니다.

        1. 한 번에 한 개의 알파벳만 바꿀 수 있습니다.
        2. words에 있는 단어로만 변환할 수 있습니다.
        예를 들어 begin이 hit, target가 cog, words가 [hot,dot,dog,lot,log,cog]라면 hit -> hot -> dot -> dog -> cog와 같이 4단계를 거쳐 변환할 수 있습니다.

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

    public boolean compare (int index, String c1, String c2) {
        String t1 = "", t2 = "";

        if (index == 0) {
            t1 = c1.substring(index);
            t2 = c2.substring(index);
        }
        if (index == c1.length() - 1) {
            t1 = c1.substring(0, index);
            t2 = c2.substring(0, index);
        }
        if (index != 0 && index == c1.length() - 1) {
            t1 = c1.substring(0, index) + c1.substring(index + 1);
            t2 = c2.substring(0, index) + c2.substring(index + 1);
        }
        return t1.equals(t2);
    }

    public int diff(String begin, String target) {
        return 0;
    }

    public int dfs (String bg, String tg, String[] words, Boolean[] v) {
        int i , j;
        if (bg.equals(tg)) {

            return (int) Arrays.asList(v).stream().filter(b -> b).count();
        }
        System.out.println(String.format("bg [%s] tg [%s]", bg, tg));
        for (i = 0; i < bg.length(); i++) {
            for (j = 0 ; j < words.length; j++) {
                if (v[j] != null && v[j]) { continue; }
//                System.out.println(String.format("compare [%s] begin: [%s] target: [%s]", i, bg, tg));

                if (compare(i, bg, tg)) {
                    v[j] = true;
                    return dfs (words[j], tg, words, v);
                }
            }
        }
        return 0;
    }

}
