package question.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Floyd11404 {
    
    /*
        <a href="https://www.acmicpc.net/problem/11404"></a>
     */
    
    public static class Edge implements Comparable<Edge> {
        private final int to;
        private final int weight;
        
        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());   // 도시 개수
        int M = Integer.parseInt(br.readLine());   // 버스 개수
        
        // 거리 배열 (비용)
        long[][] dist = new long[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        // int u, v, w;
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            dist[a][b] = Math.min(dist[a][b], c);
        }
        
        for (int k = 1; k <= N; k++) {          // 거치는 값
            for (int j = 1; j <= N; j++) {      // 시작
                for (int i = 1; i <= N; i++) {  // 도착
                    if (dist[j][i] > dist[j][k] + dist[k][i]) {
                        dist[j][i] = dist[j][k] + dist[k][i];
                    }
                }
            }
        }

        for (int j = 1; j <= N; j++) {
            for (int i = 1; i <= N; i++) {
                if (dist[j][i] == Integer.MAX_VALUE) {
                    System.out.print(0 + " ");
                } else {
                    System.out.print(dist[j][i] + " ");
                }
            }
            System.out.println();
        }
    }
}
