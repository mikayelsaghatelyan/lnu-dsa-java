# algorithms-and-data-structures-java
Algorithms and Advanced Data Structures from the Course 1DV516 of Linnaeus University.

The repository contains the following folders:

  **1. UnionFind** - classes that implement [union](https://en.wikipedia.org/wiki/Disjoint-set_data_structure) (disjoint set) data structures.
  - Union Find (UF),
  - Quick Union Find (QUF),
  - Weighted Quick Union Find (WQUF) with weighting and path compression,
  - Percolation Model based on WQUF, which uses Monte-Carlo method for estimating percolation threshold in a N x N grid. 
              
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

## Table of Contents
1. [General Info](#general-info)
2. [Technologies](#technologies)
3. [Installation](#installation)
4. [Collaboration](#collaboration)
5. [FAQs](#faqs)
### General Info
***
Write down general information about your project. It is a good idea to always put a project status in the readme file. This is where you can add it. 
### Screenshot
![Image text](https://www.united-internet.de/fileadmin/user_upload/Brands/Downloads/Logo_IONOS_by.jpg)
## Technologies
***
A list of technologies used within the project:
* [Technology name](https://example.com): Version 12.3 
* [Technology name](https://example.com): Version 2.34
* [Library name](https://example.com): Version 1234
## Installation
***
A little intro about the installation. 
```
$ git clone https://example.com
$ cd ../path/to/the/file
$ npm install
$ npm start
```
Side information: To use the application in a special environment use ```lorem ipsum``` to start
## Collaboration
***
Give instructions on how to collaborate with your project.
> Maybe you want to write a quote in this part. 
> Should it encompass several lines?
> This is how you do it.
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
