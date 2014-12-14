package ua.nure.course5.MMO.lab1.Shcherbatenko;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class DijkstraVSGeneticTreeAlg {
	
    static final int INF = Integer.MAX_VALUE;

    static final int startGraph[][] = {
        {0, 20, 30, 50, 10},
        {INF, 0, INF, INF, INF},
        {INF, INF, 0, INF, 10},
        {INF, 40, 20, 0, INF},
        {10, INF, 10, 30, 0}
    };

    public static int[] getDijkstraTree(int[][] graph, int source) {
        final int[] dist = new int[graph.length];
        int[] previous = new int[graph.length];
        dist[source] = 0;
        Arrays.fill(previous, source);
        Queue<Integer> q = new PriorityQueue<>(graph.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return dist[o1] - dist[o2];
            }
        });

        for (int vertex = 0; vertex < graph.length; vertex++) {
            if (vertex != source) {
                dist[vertex] = INF;
            }
            q.add(vertex);
        }

        while (!q.isEmpty()) {
            int u = q.poll();
            for (Integer v : q.toArray(new Integer[q.size()])) {
                if (graph[u][v] == INF) {
                    continue;
                }
                int alt = dist[u] + graph[u][v];
                if (alt < dist[v]) {
                    dist[v] = alt;
                    previous[v] = u;
                }
            }
        }

        return previous;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int[] dijResult = getDijkstraTree(startGraph, 0);
        long dijTime = System.currentTimeMillis() - startTime;
        System.out.println("Dijkstra algorithm result: " + Arrays.toString(dijResult) + "; time: " + dijTime);
    }
}
