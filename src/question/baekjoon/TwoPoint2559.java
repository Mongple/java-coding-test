package question.baekjoon;

import java.io.*;
import java.util.*;

public class TwoPoint2559 {

    /*
        <a href="https://www.acmicpc.net/problem/2559"></a>
     */
    
    private static int K;   // 합의 연속 날짜수
    private static int[] ARRAY;
    
    public static void main(String[] args) throws IOException {
        solution();
    }
    
    private static void setInputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nkArr = br.readLine().split(" ");
        K = Integer.parseInt(nkArr[1]);
        
        ARRAY = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue)
                .toArray();
    }
    
    private static void solution() throws IOException {
        setInputData();
        
        // 기본적인 구현
//        for (int i = 0; i + K <= ARRAY.length; i++) {
//            maxValue = Math.max(maxValue, getSumValue(i));
//        }
        
        // 투포인트 구현
        // 1. k - 1까지 값 더함
        int each = Arrays.stream(ARRAY)
                .limit(K)
                .sum();
        
        int maxValue = each;
        
        for (int i = K; i < ARRAY.length; i++) {
            each += ARRAY[i];
            each -= ARRAY[i - K];
            
            maxValue = Math.max(each, maxValue);
        }
        
        System.out.println(maxValue);
    }

    private static int getSumValue(int index) {

        int sum = 0;
        
        for (int i = index; i < index + K; i++) {
            sum = sum + ARRAY[i];
        }
        
        return sum;
    }

    private static void solution2() throws IOException {

        int maxValue = Integer.MIN_VALUE;

        setInputData();

        for (int i = 0; i + K <= ARRAY.length; i++) {
            maxValue = Math.max(maxValue, getSumValue(i));
        }

        System.out.println(maxValue);
    }
}
