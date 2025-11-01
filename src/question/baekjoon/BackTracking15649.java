package question.baekjoon;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class BackTracking15649 {

    /*
        <a href="https://www.acmicpc.net/problem/15649"></a>
        
        [문제]
        자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
            - 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
        [입력]
        첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)

        [출력]
        한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
        수열은 사전 순으로 증가하는 순서로 출력해야 한다.
        
        1. 설계 : 2중 for문, 방문x -> BFS
        2. 시간 : 
            - O(b^d)
                b: 한 상태에서의 평균 분기 수(가지 수)
                d: 탐색 깊이(결정해야 할 선택의 수)
            - 중복불가 O(n!) -> 가능
           
        3. 자료구조
     */
    
    private static int N = 0;
    private static int M = 0;
    private static boolean[] VISITED;
    private static final Deque<Integer> QUEUE = new ArrayDeque<>();
    
    public static void main(String[] args) throws IOException {
        solution();
    }
    
    private static String[] getInputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine().split(" ");
    }
    
    private static void solution() throws IOException {
        String[] inputData = getInputData();
        N = Integer.parseInt(inputData[0]);
        M = Integer.parseInt(inputData[1]);
        VISITED = new boolean[N + 1];
        recursion(0);
    }
    
    private static void recursion(int num) {
        if (num == M) {
            System.out.println(QUEUE.stream().map(String::valueOf).collect(Collectors.joining(" ")));
            return;
        }
        
        for (int i = 1; i <= N; i++) {
            if (!VISITED[i]) {
                VISITED[i] = true;
                QUEUE.addLast(i);
                recursion(num + 1);
                VISITED[i] = false;
                QUEUE.pollLast();
            }
        }
    }
}
