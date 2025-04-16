import java.util.*;

import static java.lang.Math.min;

class Graph {
    private int[][] adjMatrix;
    private int size;

    public Graph(int size) {
        this.size = size;
        adjMatrix = new int[size][size];
    }

    public void addEdge(int from, int to, int capacity){
        adjMatrix[from][to] = capacity;
    }

    public int getSize() {
        return size;
    }

    private boolean bfs(int source, int sink, int[] parent) {
        boolean[] visited = new boolean[size];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v = 0; v < size; v++) {
                if (!visited[v] && adjMatrix[u][v] >0) {
                    queue.add(v);
                    visited[v] = true;
                    parent[v] = u;

                }
            }
        }

        return visited[sink];
    }

    public int edmondsKarp(int source, int sink, boolean print) {
        int[] parent = new int[size];
        int maxFlow = 0;

        while (bfs(source, sink, parent)) {
            int pathFlow = Integer.MAX_VALUE;

            //find min capacity in path which will be the increase in flow
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = min(pathFlow, adjMatrix[u][v]);
            }

            //update capacities in adj list
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                adjMatrix[u][v] -= pathFlow;
                adjMatrix[v][u] += pathFlow; //for reverse edge, the capacity increase will be the flow
            }

            //printing path
            if (print) {
                List<Integer> path = new ArrayList<>();
                for (int v = sink; v != source; v = parent[v]) {
                    path.add(v);
                }
                path.add(source);
                Collections.reverse(path);
                System.out.println("Path: " + path + ", Flow: " + pathFlow);
            }

            maxFlow += pathFlow;
        }
        return maxFlow;
    }
}
