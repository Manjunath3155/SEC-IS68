import java.util.*;

class BridgesTarjan {

    List<List<Integer>> graph;
    int[] disc, low;
    boolean[] visited;
    int time = 0;
    List<List<Integer>> bridges = new ArrayList<>();

    public List<List<Integer>> criticalConnections(
        int n,
        List<List<Integer>> edges
    ) {
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        for (List<Integer> e : edges) {
            graph.get(e.get(0)).add(e.get(1));
            graph.get(e.get(1)).add(e.get(0));
        }

        disc = new int[n];
        low = new int[n];
        visited = new boolean[n];

        dfs(0, -1);
        return bridges;
    }

    private void dfs(int u, int parent) {
        visited[u] = true;
        disc[u] = low[u] = ++time;

        for (int v : graph.get(u)) {
            if (v == parent) continue;

            if (!visited[v]) {
                dfs(v, u);
                low[u] = Math.min(low[u], low[v]);

                if (low[v] > disc[u]) bridges.add(Arrays.asList(u, v));
            } else {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    public static void main(String[] args) {
        BridgesTarjan b = new BridgesTarjan();
        List<List<Integer>> edges = List.of(
            List.of(0, 1),
            List.of(1, 2),
            List.of(2, 0),
            List.of(1, 3)
        );
        System.out.println(b.criticalConnections(4, edges));
    }
}
