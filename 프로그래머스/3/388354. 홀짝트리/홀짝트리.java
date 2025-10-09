import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(int[] nodes, int[][] edges) {
        Map<Integer, List<Integer>> adjacencies = new HashMap<>();
        for (int node : nodes) {
            adjacencies.put(node, new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            adjacencies.get(edge[0]).add(edge[1]);
            adjacencies.get(edge[1]).add(edge[0]);
        }
        
        Set<Integer> visited = new HashSet<>();
        
        List<Set<Integer>> nodesPerTree = new ArrayList<>();
        for (int node : nodes) {
            if (visited.contains(node)) {
                continue;
            }
            
            Set<Integer> treeNode = new HashSet<>();
            findTree(node, adjacencies, treeNode, visited);
            nodesPerTree.add(treeNode);
        }
        
        List<int[]> count = new ArrayList<>();
        
        for (Set<Integer> treeNode : nodesPerTree) {
            count.add(check(treeNode, adjacencies));
        }
        
        int[] sum = { 0, 0 };
        for (int[] c : count) {
            sum[0] += c[0];
            sum[1] += c[1];
        }
        
        return sum;
    }
    
    void findTree(int vertex,
                  Map<Integer, List<Integer>> adjacencies,
                  Set<Integer> treeNodes,
                  Set<Integer> visited) {
        if (visited.contains(vertex)) {
            return;
        }
        
        visited.add(vertex);
        treeNodes.add(vertex);
        for (Integer nextVertex : adjacencies.get(vertex)) {
            findTree(nextVertex,
                    adjacencies,
                    treeNodes,
                    visited);
        }
    }
    
    int[] check(Set<Integer> treeNode, Map<Integer, List<Integer>> adjacencies) {
        Map<Integer, Integer> edgeCountPerNode = new HashMap<>();
        int countA = 0;
        int countB = 0;
        for (Integer node : treeNode) {
            int adjCount = adjacencies.get(node).size();
            countA += node % 2 == adjCount % 2 ? 1 : 0;
            countB += node % 2 != adjCount % 2 ? 1 : 0;
        }
        
        return new int[] {
            countA == 1 ? 1 : 0,
            countB == 1 ? 1 : 0
        };
    }
}