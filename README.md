# Algorithms and Advanced Data Structures

This repository contains my solutions for the **4 Assignments** within the Course of **Algorithms and Advanced Data Structures, 1DV516** at Linnaeus University (exchange semester via Erasmus+, 2nd year of studies, Autumn 2022). The assignments are organized into the following folders:

  **1. UnionFind** - contains classes that implement [Union](https://en.wikipedia.org/wiki/Disjoint-set_data_structure) (disjoint-set) data structures.
  - Union Find (UF),
  - Quick Union Find (QUF),
  - Weighted Quick Union Find (WQUF) with weighting and path compression,
  - Percolation Model based on WQUF, which uses Monte-Carlo method for estimating percolation threshold in a N x N grid. 
              
  **2. Lists-Queues-Trees** - contains classes that implement [Linked-list](https://en.wikipedia.org/wiki/Linked_list), [Queue](https://en.wikipedia.org/wiki/Queue_(abstract_data_type)) and [Tree](https://en.wikipedia.org/wiki/Tree_(data_structure)) data structures.
  - Single-Linked List,
  - Double-Ended Queue, 
  - Randomized Queue, 
  - Directory Tree, for listing files and folders inside a directory,
  - BinarySearchTree (BST),
  - AVL Balanced Tree.          
                       
  **3. HashTables-Sorting** - contains a class with [Hash table](https://en.wikipedia.org/wiki/Hash_table), as well as classes with implementations of [Sorting algorithms](https://en.wikipedia.org/wiki/Sorting_algorithm).
  - Insertion Sort, 
  - Heap Sort, 
  - Quick Sort, 
  - Merge Sort (*recursive* and *iterative* instances), 
  - Shell Sort (instances that use *basic gap sequence*, as well as sequences that work through *Knuth's increments* and *Hibbard's increments*), 
  - Counting Sort,
  - Radix Sort.
                       
  * **4. Graphs** - contains classes with various implementations of [Graphs](https://en.wikipedia.org/wiki/Graph_(discrete_mathematics)) and algorithms used on them.
  - Simple graph implementation using only edge lists (**BasicGraph** class), 
  - **IGraph** interface that implements **Graph** (unweighted, undirected) and **Digraph** (unweighted, directed) classes, 
  - **IWGraph** interface that implements **WeightedGraph** (weighted, undirected), **WeightedDigraph** (weighted, directed) classes,
  - **Edge** class for creating edges used in WeightedGraph and WeightedDigraph classes,
  - **GraphIterator** class for iterating through edge lists, adjacency lists and DFS/BFS paths,
  - [Depth-First Search](https://en.wikipedia.org/wiki/Depth-first_search) (**DFS** and **WeightedDFS** classes),
  - [Breadth-First Search](https://en.wikipedia.org/wiki/Breadth-first_search) (**BFS** and **WeightedBFS** classes),
  - [Cycle](https://en.wikipedia.org/wiki/Cycle_(graph_theory))-Finding Algorithm (**CF** and **WeightedCF** classes),
  - [Connected Components](https://en.wikipedia.org/wiki/Component_(graph_theory)) (**CC** and **WeightedCC** classes),
  - [Topological Sorting](https://en.wikipedia.org/wiki/Topological_sorting) (**TopoSort** and **WeightedTS** classes),
  - Strongly-Connected Components [(Kosaraju's Algorithm)](https://en.wikipedia.org/wiki/Kosaraju%27s_algorithm) (**SCC** and **WeightedCC** classes),
  - [Kruskal's algorithm](https://en.wikipedia.org/wiki/Kruskal%27s_algorithm) for [minimum spanning tree/forest](https://en.wikipedia.org/wiki/Minimum_spanning_tree) (**Kruskal** class, uses **EdgeHeap** class),
  - [Prim's algorithm](https://en.wikipedia.org/wiki/Prim%27s_algorithm) for minimum spanning tree (**Prim** class),
  - Shortest path in a [Directed Acyclic Graph](https://en.wikipedia.org/wiki/Directed_acyclic_graph) (**SPDAG** class),
  - [Dijkstra's algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm) for shortest paths and shortest path distances (**Dijkstra** class, uses **Pair** and **PairHeap** classes),
  - [Bellman-Ford's algorithm](https://en.wikipedia.org/wiki/Bellman–Ford_algorithm) (**BellmanFord** class),
                  
Apart from the listed classes, each folder has its own **DriverClass** for testing mentioned algorithms and data structures.
