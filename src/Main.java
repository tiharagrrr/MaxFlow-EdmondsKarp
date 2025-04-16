import java.io.*;

public class Main {
    public static void main(String[] args) {
        String filename = "Benchmarks/bridge_2.txt";
        try {
            Graph g = readGraphFromFile(filename);
            int maxFlow = g.edmondsKarp(0, g.getSize()-1, true);
            System.out.println("Maximum Flow: " + maxFlow);
        } catch (IOException e) {
            System.err.println("Failed to read the file: " + e.getMessage());
        }
    }

    public static Graph readGraphFromFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        int n = Integer.parseInt(br.readLine().trim());
        Graph g = new Graph(n);

        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.trim().split("\\s+");
            if (parts.length != 3) continue; // skip malformed lines
            int from = Integer.parseInt(parts[0]);
            int to = Integer.parseInt(parts[1]);
            int capacity = Integer.parseInt(parts[2]);
            g.addEdge(from, to, capacity);
        }

        br.close();
        return g;
    }

}
