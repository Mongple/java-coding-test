package question.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Mst1197 {

    /*
        <a href="https://www.acmicpc.net/problem/1197"></a>
     */
    static class Node {
        int to, weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        ArrayList<Node>[] adjList = new ArrayList[V + 1];

        for (int i = 1; i <= V; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adjList[a].add(new Node(b, c));
            adjList[b].add(new Node(a, c));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        pq.add(new Node(1, 0));

        boolean[] visited = new boolean[V + 1];

        int total = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (visited[curr.to]) {
                continue;
            }

            visited[curr.to] = true;
            total += curr.weight;

            for (Node next : adjList[curr.to]) {
                if (!visited[next.to]) {
                    pq.add(new Node(next.to, next.weight));
                }
            }
        }

        System.out.println(total);

    }
}
