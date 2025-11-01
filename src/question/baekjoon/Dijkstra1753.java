package question.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Dijkstra1753 {
    
    /*
        <a href="https://www.acmicpc.net/problem/1753"></a>
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int V = Integer.parseInt(st.nextToken());   // 정점
        int E = Integer.parseInt(st.nextToken());   // 간선  
        int startVertex = Integer.parseInt(br.readLine());

        // 인접리스트
        List<List<Edge>> adjList = new ArrayList<>(V + 1);
        for (int i = 0; i <= V; i++) adjList.add(new ArrayList<>());
        
        // 비용 정보
        int[] wights = new int[V + 1];
        for (int i = 0; i <= V; i++) wights[i] = Integer.MAX_VALUE;
        
        // int u, v, w;
        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adjList.get(u).add(new Edge(v, w));
        }
        
        // heap 선언
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(startVertex, 0));
        wights[startVertex] = 0;
        
        while (!pq.isEmpty()) {
            Edge ce = pq.poll();
            
            if (ce.weight != wights[ce.to]) continue;
            
            List<Edge> nextEdgeList = adjList.get(ce.to);
            
            for (Edge ne : nextEdgeList) {
                if (wights[ne.to] > ce.weight + ne.weight) {
                    wights[ne.to] = ce.weight + ne.weight;
                    pq.add(new Edge(ne.to, wights[ne.to]));
                }
            }
        }
        
        for (int i = 1; i <= V; i++) {
            if (wights[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(wights[i]);
            }
        }
    }
}
