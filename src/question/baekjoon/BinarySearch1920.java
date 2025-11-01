package question.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BinarySearch1920 {

    /*
        <a href="https://www.acmicpc.net/problem/1920"></a>
     */
    
    private static int[] ARRAY;
    private static int[] TARGETS;
    
    public static void main(String[] args) throws IOException {
        solution();
    }
    
    private static void setInputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        ARRAY = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .sorted()
                .mapToInt(Integer::intValue)
                .toArray();
        
        int m = Integer.parseInt(br.readLine());
        TARGETS = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue)
                .toArray();
    }
    
    private static void solution() throws IOException {
        setInputData();

        for (int target : TARGETS) {
            binarySearch(0, ARRAY.length - 1, target);
        }
    }
    
    private static void binarySearch(int start, int end, int target) {
        if (start > end) {
            System.out.println(0);
            return;
        }

        int mid = (start + end) / 2;

        if (ARRAY[mid] == target) {
            System.out.println(1);
            return;
        }
        
        if (ARRAY[mid] > target) {
            binarySearch(start, mid - 1, target);
        } else {
            binarySearch(mid + 1, end, target);
        }
    }
}
