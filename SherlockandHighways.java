import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

import javafx.util.Pair;

public class SherlockandHighways {

    static int maxLength = 0;


    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int nodes = sc.nextInt();
        int edges = sc.nextInt();
        Map<Integer, HashSet<Integer>> map = new HashMap<>();

        ArrayList<ArrayList<Integer>> adjList = contructGraph(map, nodes, edges, sc);

        HashSet<Pair<Integer, Integer>> artEdge = new HashSet<>();
        int [] low = new int[nodes];
        int [] disc = new int[nodes];
        int [] parent = new int[nodes];
        boolean[] visited= new boolean[nodes];
        int time =0;
        int src =0;
        parent[src]=-1;

        doDFS(src, parent, disc, visited, low, adjList, time, artEdge);
        int [] cc = compressGraph(adjList, artEdge, nodes);
        HashSet<Integer> ccset = new HashSet<>();
        for (int i=0;i<cc.length;i++) {
            ccset.add(cc[i]);
        }

        boolean[][] tree = contructTree(cc, artEdge, ccset.size());
        parent = new int[ccset.size()];

        //System.out.println("numCC" + ccset.size());

        int longestPath = findMaxLength(0, parent, tree);
        System.out.println(artEdge.size()/2 + " " + longestPath);
    }

    static int findMaxLength(int src, int[] parent, boolean [][] tree) {
        int max = 0;
        int secMax = 0;

        for(int i=0;i<tree.length;i++) {
            if(tree[src][i]==true && parent[src]!=i) {
                parent[i] = src;
                int length = findMaxLength(i, parent, tree);
                if(length>max){
                    max=length;
                    secMax=max;
                    //System.out.println("src" + src + "v" + i +"max: " + max + " SecMax: " + secMax);
                }
                else if(length>secMax) {
                    secMax=length;
                    //System.out.println("src" + src + "v" + i +"max: " + max + " SecMax: " + secMax);
                }
            }
        }

        if(1+max+secMax>maxLength){
            maxLength=2+max+secMax;
        }

        return 1+max;
    }

    static ArrayList<ArrayList<Integer>> contructGraph(Map<Integer, HashSet<Integer>> map /**/,
                                                       int nodes, int edges, Scanner sc) {

        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for(int i=0;i<nodes;i++)
            adjList.add(new ArrayList<>());

        for(int i=0;i<edges;i++) {
            int u = sc.nextInt()-1;
            int v = sc.nextInt()-1;

            if(map.containsKey(u)) {
                map.get(u).add(v);
            }else {
                HashSet<Integer> set =new HashSet<>();
                set.add(v);
                map.put(u, set);
            }

            if(map.containsKey(v)) {
                map.get(v).add(u);
            }else {
                HashSet<Integer> set = new HashSet<>();
                set.add(u);
                map.put(v, set);
            }

            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        return adjList;
    }

    static boolean [][] contructTree(int[] cc, HashSet<Pair<Integer, Integer>> artEdge, int ncc) {
        boolean mat[][] = new boolean [ncc][ncc];
        for(Pair<Integer, Integer> p : artEdge) {
            int u= p.getKey();
            int v= p.getValue();
            int setu= cc[u];
            int setv = cc[v];
            mat[setu][setv] = true;
            mat[setu][setv] = true;
        }
        return mat;
    }

    static int[] compressGraph( ArrayList<ArrayList<Integer>> adjList,
                                               HashSet<Pair<Integer, Integer>> artEdge, int nodes) {
        //ArrayList<HashSet<Integer>> cc = new ArrayList<>();
        HashSet<Integer> visited = new HashSet<>();
        int [] cc = new int[nodes];
        int counter = 0;

        for(int i=0;i<adjList.size(); i++) {
            if(!visited.contains(i)) {
                HashSet<Integer> compressed = doBFS(i, visited, adjList, artEdge);
                for(Integer u : compressed) {
                    cc[u]=counter;
                }
                counter++;
            }
        }
        return cc;
    }

    static void doDFS(int src, int [] parent, int []disc, boolean[]visited, int []low,
                      ArrayList<ArrayList<Integer>> adjList, int time, HashSet<Pair<Integer, Integer>> artEdge) {
        low[src]=disc[src]=time;
        visited[src]=true;

        for(Integer v : adjList.get(src)) {
            if(!visited[v]) {
                parent[v]=src;
                doDFS(v, parent, disc, visited, low, adjList, time+1, artEdge);
                low[src] = Math.min(low[src], low[v]);
                if(low[v] > disc[src]) {
                    artEdge.add(new Pair<>(src, v));
                    artEdge.add(new Pair<>(v, src));
                }
            }
            else {
                if(parent[src]!=v)
                    low[src] = Math.min(low[src], disc[v]);
            }
        }
    }

    static HashSet<Integer> doBFS(int src, HashSet<Integer> visited,
               ArrayList<ArrayList<Integer>> adjList, HashSet<Pair<Integer, Integer>> artEdge) {
        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        HashSet<Integer> compressed = new HashSet<>();
        compressed.add(src);
        while(!q.isEmpty()) {
            int u = q.poll();
            visited.add(u);
            for(Integer v: adjList.get(u)) {
                if(!compressed.contains(v) && !artEdge.contains(new Pair(u, v))) {
                    System.out.println(v);
                    compressed.add(v);
                    q.add(v);
                }
            }
        }
        return compressed;
    }
}
