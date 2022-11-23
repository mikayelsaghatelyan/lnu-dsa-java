package com.source.unweighted;

import java.util.ArrayList;

public class CF { // cycle find
    ArrayList<Integer> color;   // 0 - white, 1 - gray, 2 - black

    public CF(IGraph G) {
        color = new ArrayList<>();
        for (int i = 0; i < G.V(); ++i)
            color.add(0);
    }

    public boolean hasCycle(IGraph G) {
        for (int v = 0; v < G.V(); ++v)
            if (this.color.get(v) == 0)
                if (DFS(G, v))
                    return true;
        return false;
    }

    public boolean DFS(IGraph G, int v) {
        this.color.set(v, 1); // v marked gray
        for (Integer w : G.adjacent(v)) {
            if (this.color.get(w) == 1)
                return true;
            if (this.color.get(w) == 0 && this.DFS(G, w))
                return true;
        }
        this.color.set(v, 2); // v marked black
        return false;
    }


}
