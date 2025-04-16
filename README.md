This contains the code to find the maximum possible flow from source to target node in a directed graph.

The benchmark folder contains sample problems to run the code, where the value in the first row (n) indicates 
the number of nodes followed by n lines of 3 values each which will be the from node, to node and the capacity 
of the edge between the nodes.

The Graph class contains 
- the adjacency list data structure to store the graph data
- bfs method to find augmented paths, returning whether there is an augmented path from source to target or not
- the edmondskarp method which uses each augmented path to increase the flow along the path and update the
  capacities of each edge.

The main class contains a parser to read the data from the benchmark files and store it in the graph.

