package question.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynamicProgramming11726 {
    
    /*
        <a href="https://www.acmicpc.net/problem/11726"></a>
     */
    public static void main(String[] args) throws IOException {
        solution();
    }
    
    private static int getInputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return Integer.parseInt(br.readLine());
    }
    
    private static void solution() throws IOException {
        int n = getInputData();
        // n = 1, 2, 3의 값을 미리 저장
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2));
        
        // 3부터 시작
        for (int i = 3; i <= n; i++) {
            list.add((list.get(i - 1) + list.get(i - 2)) % 10007);
        }
        
        System.out.println(list.get(n));
    }
}
