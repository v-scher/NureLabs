package ua.nure.shcherbatenko.mmo.lab1;

import java.util.*;

public class GenAlg {
    static final int INF = Integer.MAX_VALUE;

    static final int dist[][] = {
            {0, 10, 30, 50, 10},
            {10, 0, INF, 40, INF},
            {30, INF, 0, 20, 10},
            {50, 40, 20, 0, 30},
            {10, INF, 10, 30, 0}
    };

    static int[] genAlg(int[][] dist, int initialNode) {
        int[] result = new int[1];



        return result;
    }

    static int[] dijAlg(int[][] dist, int initialNode) {
        int[] prevSequence = new int[dist.length];
        final int[] minDistances = new int[dist.length];
        boolean[] visitedNodes = new boolean[dist.length];

        for (int node = 0; node < dist.length; node++) {
            prevSequence[node] = initialNode;
            notVisitedNodes.add(node);

            if (node == initialNode) {
                minDistances[node] = 0;
            } else {
                minDistances[node] = INF;
            }
        }

        while (!notVisitedNodes.isEmpty()) {
            Integer minNode = notVisitedNodes.stream().min((a, b) -> a-b).get();
            notVisitedNodes.remove(minNode);

            final int[] alt = {0};
            notVisitedNodes.stream()
                    .filter(neighborNode -> {
                        alt[0] = dist[neighborNode][minNode] + minDistances[minNode];
                        return alt[0] < minDistances[neighborNode];
                    })
                    .forEach(neighborNode -> minDistances[neighborNode] = alt[0]);
        }

        return prevSequence;
    }

    public static void main(String[] args) {
        System.out.println("Gen algorithm started.");
        long startTime = System.currentTimeMillis();
        int[] genResult = genAlg(dist, 1);
        long genTime = System.currentTimeMillis() - startTime;
        System.out.println("Gen algorithm result: " + Arrays.toString(genResult) + "; time: " + genTime);

        System.out.println("Dijkstra algorithm started.");
        startTime = System.currentTimeMillis();
        int[] dijResult = dijAlg(dist, 1);
        long dijTime = System.currentTimeMillis() - startTime;
        System.out.println("Dijkstra algorithm result: " + Arrays.toString(dijResult) + "; time: " + dijTime);
    }
}
