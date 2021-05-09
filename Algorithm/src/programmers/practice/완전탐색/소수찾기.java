package programmers.practice.완전탐색;

import java.util.ArrayList;
import java.util.List;

/**
 * 한자리 숫자가 적힌 종이 조각이 흩어져있습니다. 흩어진 종이 조각을 붙여 소수를 몇 개 만들 수 있는지 알아내려 합니다.
 *
 * 각 종이 조각에 적힌 숫자가 적힌 문자열 numbers가 주어졌을 때, 종이 조각으로 만들 수 있는 소수가 몇 개인지 return 하도록 solution 함수를 완성해주세요.
 *
 * 제한사항
 * numbers는 길이 1 이상 7 이하인 문자열입니다.
 * numbers는 0~9까지 숫자만으로 이루어져 있습니다.
 * "013"은 0, 1, 3 숫자가 적힌 종이 조각이 흩어져있다는 의미입니다.
 * 입출력 예
 * numbers	return
 * "17"	3
 * "011"	2
 * 입출력 예 설명
 * 예제 #1
 * [1, 7]으로는 소수 [7, 17, 71]를 만들 수 있습니다.
 *
 * 예제 #2
 * [0, 1, 1]으로는 소수 [11, 101]를 만들 수 있습니다.
 *
 * 11과 011은 같은 숫자로 취급합니다.
 */
public class 소수찾기 {
    public static List<Integer> permNumbers = new ArrayList<>();
    public static int primeCounter = 0;

    public static void main(String[] args) {
        String numbers = "17";
        소수찾기 s = new 소수찾기();
        s.solution(numbers);


    }

    public int solution(String numbers) {
        int answer = 0;
        String[] splitNumbers = numbers.split("");


        for (int i = 1; i <= numbers.length(); i++) {
            permutation(splitNumbers, new String[i], i, 0, new boolean[numbers.length()]);
        }

        for (int i = 0; i < permNumbers.size(); i++) {
            isPrimeSqrt(permNumbers.get(i));
        }
        System.out.println(permNumbers + ":" + primeCounter);

        return answer;
    }

    public void permutation(String[] input, String[] output, int r, int depth, boolean[] visited) {
        if (r == depth) {
            if ("0".equals(output[0]))
                return;

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < output.length; i++) {
                builder.append(output[i]);
            }

            int permNumber = Integer.parseInt(builder.toString());
            if(!permNumbers.contains(permNumber)) {
                permNumbers.add(permNumber);
            }
            return;
        }

        for (int i = 0; i < input.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                output[depth] = input[i];
                permutation(input, output, r, depth + 1, visited);
                visited[i] = false;
            }
        }
    }

    public static void isPrimeSqrt(int number) {
        if(number < 2) {
            return;
        }

        if(number == 2) {
            primeCounter++;
            return;
        }

        for(int i = 2; i <= Math.sqrt(number); i++) {
            if(number % i == 0) {
                return;
            }
        }

        primeCounter++;
        return;
    }
}
