package basic;

import java.util.Scanner;

public class 소수판별 {
    public static void main(String[] args) {

    		Scanner in = new Scanner(System.in);

    		is_prime(in.nextInt());

    	}

    	// 소수 판별 메소드
    	public static void is_prime(int number) {

    		// 0 과 1 은 소수가 아니다
    		if(number < 2) {
    			System.out.print("소수가 아닙니다");
    			return;
    		}

    		// 2 는 소수다
    		if(number == 2) {
    			System.out.print("소수입니다");
    			return;
    		}


    		for(int i = 2; i < number; i++) {

    			// 소수가 아닐경우
    			if(number % i == 0) {
    				System.out.print("소수가 아닙니다");
    				return;
    			}
    		}
    		// 위 반복문에서 약수를 갖고 있지 않는경우 소수다.
    		System.out.print("소수입니다");
    		return;
    	}

    // 소수 판별 메소드
    	public static void is_prime_sqrt(int number) {

    		// 0 과 1 은 소수가 아니다
    		if(number < 2) {
    			System.out.print("소수가 아닙니다");
    			return;
    		}

    		// 2 는 소수다
    		if(number == 2) {
    			System.out.print("소수입니다");
    			return;
    		}

    		// 제곱근 함수 : Math.sqrt()
    		for(int i = 2; i <= Math.sqrt(number); i++) {

    			// 소수가 아닐경우
    			if(number % i == 0) {
    				System.out.print("소수가 아닙니다");
    				return;
    			}
    		}
    		// 위 반복문에서 약수를 갖고 있지 않는경우 소수다.
    		System.out.print("소수입니다");
    		return;
    	}
}
