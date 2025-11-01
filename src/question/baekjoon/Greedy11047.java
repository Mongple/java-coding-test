package question.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Greedy11047 {

    /*
        <a href="https://www.acmicpc.net/problem/11047"></a>
     */
    private static int N, K;
    private static int[] COINS;
    
    public static void main(String[] args) throws IOException {
        solution();
    }
    
    private static void setInputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nkArr = br.readLine().split(" ");
        N = Integer.parseInt(nkArr[0]);
        K = Integer.parseInt(nkArr[1]);
        COINS = new int[N];
        
        for (int i = 0; i < N; i++) {
            COINS[i] = Integer.parseInt(br.readLine());
        }
    }
    
    private static void solution() throws IOException {
        setInputData();
        
        int count = 0;
        int remaining = K;

        for (int i = N - 1; i >= 0; i--) {
            if (COINS[i] > remaining) continue;

            int use = remaining / COINS[i];
            count += use;
            remaining -= use * COINS[i];

            if (remaining == 0) break;
        }
        
        System.out.println(count);
    }
}
