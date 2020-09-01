package basic;

public class PrimeNumber {

    public boolean isPrimeNumber(int number) {
        if (number == 2) return true;
        if (number == 1) return false;
        if (number % 2 == 0) return false; // 2를 제외한 소스는 항상 홀수

        // 소스는 홀수이므로 짝수로 나누는 과정을 생략한다.
        for (int n = 3; n < number; n+=2) {
            if (number % n == 0) {
                return false;
            }
        }

        return true;
    }

    public boolean isPrimeNumberWithSqrt(int number) {
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * 소수는 n의 배수가 아니어야 한다.
     *
     * 입력받은 수를 입력받은 수보다 작은 수 들로 나누어서 떨어지면 소수가 아니다.
     *
     * 그러나 모두 나누어볼 필요없이, 루트 n 까지만 나누어서 떨어지면 소수가 아니다.

     * 출처: https://marobiana.tistory.com/91 [Take Action]
     */

    public boolean isPrimeNumberWithEtt(int number) {
        return false;
    }



}
