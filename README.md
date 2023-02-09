# algorithms-and-data-structures-java
Algorithms and Advanced Data Structures from the Course 1DV516 of Linnaeus University.

The repository contains the following folders:

  * **UnionFind** - Union Find (UF),
                Quick Union Find (QUF),
                Weighted Quick Union Find (WQUF) with weighting and path compression,
                Percolation Model based on WQUF, which uses Monte-Carlo method for estimating percolation threshold in a N x N grid. 
              
  * **Lists-Queues-Trees** - Single-Linked List,
                         Double-Ended Queue, 
                         Randomized Queue, 
                         Directory Tree, for listing files and folders inside a directory,
                         BinarySearchTree (BST),
                         AVL Balanced Tree.          
                       
  * **HashTables-Sorting** - Hash Table 
                         Insertion Sort, 
                         Heap Sort, 
                         Quick Sort, 
                         Merge Sort (Recursive and Iterative), 
                         Shell Sort (basic gap sequence, Knuth's increments and Hibbard's increments), 
                         Counting Sort
                         Radix Sort.
                       
  * **Graphs** - Graph implementations:
                  Basic Graph (only edge lists), 
                  IGraph interface: 
                      Graph (unweighted, undirected),
                      Digraph (unweighted, directed), 
                  IWGraph interface:
                      WeightedGraph (weighted, undirected),
                      WeightedDigraph (weighted, directed),
                  Edge class for creating edges used in WeightedGraph and WeightedDigraph classes,
                  GraphIterator, for iterating through edge lists, adjacency lists and DFS/BFS paths,
                  Depth-First Search (DFS and WeightedDFS classes),
                  Breadth-First Search (BFS and WeightedBFS classes),
                  Cycle Find (CF and WeightedCF classes),
                  Connected Components (CC and WeightedCC classes),
                  Topological Sorting (TopoSort and WeightedTS classes),
                  Strongly-Connected Components [Kosaraju-Sharir Algorithm] (SCC and WeightedCC classes),
                  Kruskal algorithm for minimum spanning tree/forest (Kruskal class, uses EdgeHeap class),
                  Prim algorithm for minimum spanning tree (Prim class),
                  Shortest path in a Directed Acyclic Graph (SPDAG class),
                  Dijkstra algorithm for shortest paths and shortest path distances (Dijkstra class, uses Pair and PairHeap classes),
                  Bellman-Ford algorithm (BellmanFord class),
                  
Each folder contains DriverClass for testing the algorithms and data structures from that folder.
                
## FAQs
***
A list of frequently asked questions
1. **This is a question in bold**
Answer to the first question with _italic words_. 
2. __Second question in bold__ 
To answer this question, we use an unordered list:
* First point
* Second Point
* Third point
3. **Third question in bold**
Answer to the third question with *italic words*.
4. **Fourth question in bold**
| Headline 1 in the tablehead | Headline 2 in the tablehead | Headline 3 in the tablehead |
|:--------------|:-------------:|--------------:|
| text-align left | text-align center | text-align right |
