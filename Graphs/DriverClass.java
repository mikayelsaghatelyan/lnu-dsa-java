package com.source;

import com.source.unweighted.*;

import java.util.*;
import java.util.stream.Collectors;

public class DriverClass {
    public static boolean debug = false;

    public static void GraphDefaultTest(int size) {
        System.out.println("\nGRAPH CREATION AND ITERATION TEST\n");
        WeightedGraph g = randomWG(size, 1);
        Random r = new Random();

        System.out.println("\nGRAPH INFORMATION:\n");
        System.out.print("Vertices:\t\t" + g.V() + "\nEdges:\t\t\t" + g.E() + "\nEdge list:\t\t");
        for (Edge e : g.edges())
            e.print(false);
        System.out.print("\nVertex degrees: ");
        for (int i = 0; i < g.V() - 1; ++i)
            System.out.print("Deg(" + i + ") = " + g.degree(i) + ", ");
        System.out.println("Deg(" + (g.V() - 1) + ") = " + g.degree(g.V() - 1));
        System.out.print("Adjacency lists:");
        for (int i = 0; i < g.V(); ++i) {
            System.out.print("\n" + i + ": ");
            for (Edge e : g.adjacent(i))
                e.print(false);
        }
        System.out.println();

        System.out.println("\nTESTING CONNECTED COMPONENTS ALGORITHM\n");
        WeightedCC c = new WeightedCC(g);
        System.out.println("Connected components: " + c.getCount());
        for (int i = 0; i < c.getCount(); ++i)
            System.out.println("Component " + i + ": " + c.getComp(i));

        System.out.println("\nTESTING EDGE LIST ITERATOR:\n");
        GraphIterator gi = new GraphIterator(g);
        while (gi.hasNext())
            gi.next().print(false);
        System.out.println();

        System.out.println("\nTESTING VERTEX ADJACENCY LIST ITERATOR:\n");
        int vertex = r.nextInt(0, size);
        System.out.println("Iteration vertex: " + vertex);
        GraphIterator gv = new GraphIterator(g, vertex);
        while (gv.hasNext())
            gv.next().print(false);
        System.out.println();
    }

    public static void DFSBFSTest(int size) {
        System.out.println("\nDFS/BFS ALGORITHM AND SEARCH ITERATOR TEST\n");
        WeightedGraph g = randomWG(size, 1);
        Random r = new Random();

        System.out.println("\nTESTING DEPTH FIRST SEARCH (DFS)\n");
        int vertex = r.nextInt(0, size);
        System.out.println("Search vertex: " + vertex);
        WeightedDFS d = new WeightedDFS(g, vertex);
        for (int i = 0; i < 5; ++i) {
            int a = r.nextInt(0, size);
            System.out.print("Has path to " + a + "? " + (d.hasPathTo(a) ? "Yes, " + d.pathTo(a).toString() + "\n" : "No\n"));
        }

        System.out.println("\nTESTING BREADTH FIRST SEARCH (BFS)\n");
        vertex = r.nextInt(0, size);
        System.out.println("Search vertex: " + vertex);
        WeightedBFS b = new WeightedBFS(g, vertex);
        for (int i = 0; i < 5; ++i) {
            int a = r.nextInt(0, size);
            System.out.print("Has path to " + a + "? " + (b.hasPathTo(a) ? "Yes, " + b.pathTo(a).toString()+"\n" : "No\n"));
        }

        System.out.println("\nTESTING DFS ITERATOR\n");
        int v1 = r.nextInt(0, size);
        int v2 = r.nextInt(0, size);
        System.out.println("Iterating DFS path between vertices: " + v1 + " and " + v2);
        GraphIterator ds = new GraphIterator(g, v1, v2, "DFS");
        while (ds.s_hasNext())
            System.out.print("[" + ds.s_next() + "]");
        System.out.println();

        System.out.println("\nCHECKING BFS ITERATOR\n");
        int v3 = r.nextInt(0, size);
        int v4 = r.nextInt(0, size);
        System.out.println("Iterating BFS path between vertices: " + v3 + " and " + v4);
        GraphIterator bs = new GraphIterator(g, v1, v2, "BFS");
        while (bs.s_hasNext())
            System.out.print("[" + bs.s_next() + "]");
        System.out.println();
    }

    public static void KruskalTest(int size, int range) {
        System.out.println("\nKRUSKAL ALGORITHM TEST (MINIMUM SPANNING FOREST)\n");
        WeightedGraph wg = randomWG(size, range);
        for (ArrayList<Edge> tree : minSpanForest(wg))
            for (Edge e : tree)
                e.print(true);
        System.out.println("\n\n");
    }

    public static void DijkstraTest(int size, int range) {
        System.out.println("\nDIJKSTRA ALGORITHM TEST (SHORTEST PATHS AND DISTANCES)\n");
        WeightedDigraph wdg = randomWDG(size, range, false);
        Random r = new Random();
        int vertex = r.nextInt(0, size);
        System.out.println("shortest path distances from vertex " + vertex);
        Dijkstra spd = new Dijkstra(wdg, vertex);
        System.out.println(spd.distTo);
        for (int i = 0; i < size; ++i) {
            if (i == vertex)
                continue;
            System.out.print("shortest path to vertex " + i + ": ");
            for (Edge e : spd.pathTo(i))
                e.print(false);
            System.out.println();
        }
        System.out.println("\n\n\n");
    }

    public static void CourseSequenceTest() {
        System.out.println("\nCOURSE TOPOLOGICAL SORTING TEST\n");
        String input = """
                1DT301;1DV506
                1DV507;1DV506
                1DV508;1DV506
                1DV508;1DV507
                1DV512;1DV506
                1DV512;1DV507
                1DV516;1DV506
                1DV516;1DV507
                1DV516;1MA441
                1DV516;1MA462
                1DV517;1DV506
                1DV517;1DV507
                1DV523;1DV022
                1DV523;1DV525
                1DV523;1ME321
                1DV525;1DV506
                1DV525;1DV506
                1DV600;1DV506
                1DV607;1DV024
                1DV607;1DV506
                1DV700;1DV506
                1DV701;1DV506
                1DV701;1DV512
                1DV702;1DV506
                1DV702;1DV701
                1DV720;1DV506
                1DV720;1DV512
                1DV720;1DV700
                1MA462;1MA441
                1MA464;1MA462
                2DV513;1DV507
                2DV603;1DV506
                2DV603;1DV507
                2DV603;1DV607
                2DV603;2DV610
                2DV604;1DV600
                2DV604;1DV607
                2DV610;1DV506
                2DV610;1DV507
                2DV610;1DV604
                2DV610;1DV607
                2DV702;1DV506
                2DV702;1DV701
                2DV703;1DV701
                2DV703;2DV702""";
        System.out.println(courseSequence(input));
    }

    public static void BellmanFordTest(int size, int range) {
        System.out.println("\nBELLMAN FORD ALGORITHM TEST\n");
        WeightedDigraph k = randomWDGNoNegCycles(size, range);
        Random r = new Random();
        int vertex = r.nextInt(0, size);
        BellmanFord bf = new BellmanFord(k, vertex);
        System.out.println("shortest path distances from vertex " + vertex);
        System.out.println(bf.distTo);
        for (int i = 0; i < size; ++i) {
            if (i == vertex)
                continue;
            if(bf.distTo.get(i)!=Double.POSITIVE_INFINITY) {
                System.out.print("shortest path to vertex " + i + ": ");
                for (Edge e : bf.pathTo(i))
                    e.print(false);
                System.out.println();
            }
        }
        System.out.println("\n\n\n");
    }

    public static void BridgeTest(int size) {
        System.out.println("\nBRIDGE TEST\n");
        WeightedGraph w = randomWG(size, 1);
        System.out.println("\nChecking whether the following edges are bridges:\n");
        for (Edge e : w.edges()) {
            System.out.print("Is edge ");
            e.print(false);
            System.out.println(" a bridge?\t" + (isBridge(w, e) ? "Yes" : "No"));
        }
    }

    //method for testing BFS, DFS and connected component algorithms on an unweighted graph
    public static void graphTest() {
        System.out.println("\nUNWEIGHTED GRAPH TEST\n");
        Graph g = new Graph(15);
        g.addEdge(0, 2);
        g.addEdge(2, 3);
        g.addEdge(1, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(4, 6);
        g.addEdge(5, 6);
        g.addEdge(6, 7);
        g.addEdge(7, 8);
        g.addEdge(8, 9);
        g.addEdge(9, 10);
        g.addEdge(10, 6);
        g.addEdge(11, 12);
        g.addEdge(12, 13);
        g.addEdge(12, 14);
        g.addEdge(13, 14);
        g.removeEdge(12, 14);

        System.out.println("DFS");
        DFS d = new DFS(g, 9);
        System.out.println(d.hasPathTo(5));
        System.out.println(d.hasPathTo(11));
        System.out.println(d.pathTo(8).toString());

        System.out.println("BFS");
        BFS b = new BFS(g, 9);
        System.out.println(b.hasPathTo(5));
        System.out.println(b.hasPathTo(11));
        System.out.println(b.pathTo(8).toString());

        System.out.println("CC");
        CC c = new CC(g);
        System.out.println(c.getCount());
        System.out.println(c.getComp(0));
        System.out.println(c.getComp(1));
    }

    //method for testing the topological sorting algorithm on a directed unweighted graph
    public static void topoSortTest() {
        System.out.println("\nTOPOLOGICAL SORTING TEST\n");
        Digraph dg = new Digraph(7);
        dg.addEdge(0, 1);
        dg.addEdge(0, 2);
        dg.addEdge(0, 5);
        dg.addEdge(1, 4);
        dg.addEdge(3, 2);
        dg.addEdge(3, 4);
        dg.addEdge(3, 5);
        dg.addEdge(3, 6);
        dg.addEdge(5, 2);
        dg.addEdge(6, 0);
        dg.addEdge(6, 4);
        TopoSort t = new TopoSort(dg);
        System.out.println(t.reversePost());
    }

    //method for finding whether the directed unweighted graph has a cycle
    public static void cycleFindTest() {
        System.out.println("\nCYCLE FIND TEST\n");
        Digraph g = new Digraph(4);
        g.addEdge(0, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        g.addEdge(1, 0);
        CF c = new CF(g);
        System.out.println(c.hasCycle(g));
    }

    //method for finding the minimum spanning forest of a graph (not necessarily connected)
    public static ArrayList<ArrayList<Edge>> minSpanForest(WeightedGraph WG) {
        ArrayList<ArrayList<Edge>> forest = new ArrayList<>();
        Kruskal k = new Kruskal(WG);
        WeightedCC wcc = new WeightedCC(WG);
        for (int i = 0; i < wcc.getCount(); ++i) {
            ArrayList<Edge> tree = new ArrayList<>();
            for (Edge e : k.mst) {
                if (wcc.ids.get(e.src()) == i)
                    tree.add(e);
            }

            if (debug) {
                for (Edge e : tree)
                    System.out.print("[" + e.v + "," + e.u + "," + e.weight + "] ");
                System.out.println();
            }
            forest.add(tree);
        }
        return forest;
    }

    // method for sorting the courses in the topological order
    public static ArrayList<String> courseSequence(String str) {
        String input = str;
        HashMap<String, Integer> courses = new HashMap<>();
        int i = 0;
        while (input.length() > 6) {
            if (!courses.containsKey(input.substring(0, 6))) {
                courses.put(input.substring(0, 6), i);
                i++;
            }
            input = input.substring(7);
        }
        if (!courses.containsKey(input))
            courses.put(input, i);
        input = str;
        WeightedDigraph G = new WeightedDigraph(courses.size());
        while (input.length() > 13) {
            G.addEdge(courses.get(input.substring(0, 6)), courses.get(input.substring(7, 13)));
            input = input.substring(14);
        }
        G.addEdge(courses.get(input.substring(0, 6)), courses.get(input.substring(7, 13)));
        WeightedTS wt = new WeightedTS(G);
        ArrayList<String> order = new ArrayList<>();
        for (Integer k : wt.postorder)
            order.add(courses.entrySet().
                    stream().
                    filter(entry -> Objects.equals(entry.getValue(), k))
                    .map(HashMap.Entry::getKey)
                    .collect(Collectors.toSet()).toString().substring(1, 7));
        return order;
    }

    // method for checking whether a certain edge is a bridge i.e. graph will have two components if it's removed
    public static boolean isBridge(WeightedGraph WG, Edge e) {
        boolean t = false;
        WG.removeEdge(e);
        if (debug) {
            System.out.println("after removing edge " + e.src() + " " + e.dst());
            for (Edge g : WG.edges())
                System.out.println(g.src() + " " + g.dst());
        }
        WeightedCC wcc = new WeightedCC(WG);
        if (wcc.count > 1)
            t = true;
        WG.addEdge(e); // adding back removed edge after checking
        if (debug) {
            System.out.println("after adding back " + e.src() + " " + e.dst());
            for (Edge g : WG.edges())
                System.out.println(g.src() + " " + g.dst());
        }
        return t;
    }

    // method for finding the diameter [maximum-weight shortest path distance between all pairs of vertices] of a weighted directed graph
    public static Double getDiameter(WeightedDigraph G) {
        Double inf = Double.POSITIVE_INFINITY;
        ArrayList<ArrayList<Double>> distances = new ArrayList<>();
        for (int i = 0; i < G.V(); ++i) {
            BellmanFord bf = new BellmanFord(G, i);
            distances.add(bf.distTo);
        }
        Double max = distances.get(0).get(0);
        for (var dist : distances)
            for (int i = 0; i < G.V(); ++i)
                if (!Objects.equals(dist.get(i), inf) && dist.get(i) > max) max = dist.get(i);
        return max;
    }

    public static void diameterTest(int size, int range) {
        WeightedDigraph wdg = randomWDG(size, range, false);
        System.out.println("Diameter is " + getDiameter(wdg));
    }

    // method for creating a random weighted undirected graph
    public static WeightedGraph randomWG(int size, int range) {
        WeightedGraph wg = new WeightedGraph(size);
        Random r = new Random();
        int inserts = r.nextInt(1, size * size);
        ArrayList<int[]> E = new ArrayList<>();
        for (int i = 0; i < inserts; ++i) {
            int v = r.nextInt(0, size);
            int u = r.nextInt(0, size);
            if (v == u)
                continue;
            int[] e = new int[]{v, u};
            boolean contains = false;
            for (int[] edge : E)
                if ((v == edge[0] && u == edge[1]) || (v == edge[1] && u == edge[0])) {
                    contains = true;
                    break;
                }
            if (!contains) {
                E.add(e);
                double weight = (double) r.nextInt(0, range * 1000);
                wg.addEdge(v, u, weight / 1000);
            }
        }
        return wg;
    }

    // method for creating a random weighted directed graph (with or without negative weights)
    public static WeightedDigraph randomWDG(int size, int range, boolean negWeights) {
        WeightedDigraph wdg = new WeightedDigraph(size);
        Random r = new Random();
        int inserts = r.nextInt(1, size * size);
        ArrayList<int[]> E = new ArrayList<>();
        for (int i = 0; i < inserts; ++i) {
            int v = r.nextInt(0, size);
            int u = r.nextInt(0, size);
            if (v == u)
                continue;
            int[] e = new int[]{v, u};
            boolean contains = false;
            for (int[] edge : E)
                if (v == edge[0] && u == edge[1]) {
                    contains = true;
                    break;
                }
            if (!contains) {
                E.add(e);
                double weight = (double) r.nextDouble(negWeights? -range : 0, range);
                wdg.addEdge(v, u, weight);
            }
        }

        return wdg;
    }

    // method for creating a random weighted directed graph with negative weights but without negative weight cycles
    public static WeightedDigraph randomWDGNoNegCycles(int size, int range) {
        WeightedDigraph wdg;
        while (true) {
            wdg = randomWDG(size, range, true);
            SPDAG s = new SPDAG(wdg, 0);
            if (!s.hasNegativeCycle(wdg))
                break;
        }
        return wdg;
    }

    // method for printing the [outgoing] adjacency lists of a weighted directed graph
    public static void printAdjListsWDG(WeightedDigraph wdg) {
        System.out.println("printing adjacency lists of the weighted directed graph:");
        for (int i = 0; i < wdg.adjListOut.size(); ++i) {
            if (wdg.adjListOut.get(i) != null && wdg.adjListOut.get(i).size() != 0) {
                System.out.print("[" + wdg.adjListOut.get(i).get(0).src() + "] -> ");
                for (Edge e : wdg.adjListOut.get(i))
                    System.out.print("[" + e.dst() + ", " + e.weight + "]");
                System.out.println();
            } else
                System.out.println("[" + i + "]");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int s = 0;
        while (s != -1) {
            System.out.println("""
                    
                    
                    PLEASE, CHOOSE THE TASK:

                    1:\t Creating an undirected graph, testing edge list and adjacency list iterators,
                    2:\t BFS/DFS algorithms and their iterators in an undirected graph,
                    3:\t Kruskal algorithm for minimum spanning forest (the graph is not necessarily connected),
                    4:\t Dijkstra algorithm for shortest paths and shortest path distances,
                    5:\t Topological sort for custom data (course prerequisite),
                    6:\t Bellman-Ford algorithm with preliminary checking whether the graph contains negative-weight cycles,
                    7:\t Checking whether an edge in a weighted undirected graph is a bridge (i.e. it connects two components),
                    8:\t BFS/DFS/CC algorithms in an unweighted graph,
                    9:\t Cycle Find algorithm in an unweighted graph,
                    10:\t Topological sorting in an unweighted graph,
                    11:\t Graph diameter in a weighted directed graph.
                    """
            );
            s = input.nextInt();
            if (s == -1)
                break;
            int size = 1;
            if ((s >= 1 && s <= 4) || (s >= 6 && s <= 7) || (s == 11)) {
                System.out.println("Enter graph size: ");
                size = input.nextInt();
            }
            int range = 1;
            if (s == 3 || s == 4 || s == 6 || s == 11) {
                System.out.println("\nEnter edge weight range: ");
                range = input.nextInt();
            }
            switch (s) {
                case 1 -> GraphDefaultTest(size);
                case 2 -> DFSBFSTest(size);
                case 3 -> KruskalTest(size, range);
                case 4 -> DijkstraTest(size, range);
                case 5 -> CourseSequenceTest();
                case 6 -> BellmanFordTest(size, range);
                case 7 -> BridgeTest(size);
                case 8 -> graphTest();
                case 9 -> cycleFindTest();
                case 10 -> topoSortTest();
                case 11 -> diameterTest(size, range);
                default -> System.out.println("Unsupported. Type number in range 1 to 11");
            }
        }
    }
}