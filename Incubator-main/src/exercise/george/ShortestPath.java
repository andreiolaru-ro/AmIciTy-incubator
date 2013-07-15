package exercise.george;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPath implements AlgorithmInterface {

    public int AlgorithmNode_process(int[][] adjacencyMatrix) {
        return 0;
    }
    
    public int[] NodeSuccesors(int[][] adjacencyMatrix, int node) {
        
        int[] succesorsList = new int[Constants.NodeNumber];
        int k = 0;
        
        for (int i=1; i < Constants.NodeNumber; i++)
            if(adjacencyMatrix[node][i] == 1)
                succesorsList[k++] = i;
        
        return succesorsList;
    }
    
    public int[] AlgorithmValue_process(int[][] adjacencyMatrix, int nodeSource, int nodeDestination) {
         
         int[] visited = new int[Constants.NodeNumber]; 
         int[] parents = new int[Constants.NodeNumber];
         int[] succesori = new int[Constants.NodeNumber];
         int[] resultPath = new int[Constants.NodeNumber];
         int node, auxNode, k;
         
         for(int i=1; i < Constants.NodeNumber; i++) {
             
             parents[i] = -1;
             visited[i] = 0;
         }
         
         visited[nodeSource] = 1;
         Queue<Integer> queue = new LinkedList<Integer>();
         queue.add(nodeSource);
         
         while(!queue.isEmpty()) {
             
             node = queue.poll();
             succesori = NodeSuccesors(adjacencyMatrix, node);
             for(int i=0; i < succesori.length ; i++) {
             
                 if(visited[succesori[i]] == 0) {
             
                     parents[succesori[i]] = node;
                     visited[succesori[i]] = 1;
                     queue.add(succesori[i]);
                 }
             }
             visited[node] = 2;
             queue.remove(node);
         }
         
         node = nodeDestination;
         auxNode = nodeDestination;
         k = 0;
         resultPath[k++] = node;
         while(node != nodeSource) {
             
             node = parents[auxNode];
             resultPath[k++] = node;
             auxNode = node;
         }
         
         return resultPath;
     }
}
