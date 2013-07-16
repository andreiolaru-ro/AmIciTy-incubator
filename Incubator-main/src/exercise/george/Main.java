package exercise.george;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {

        int adjacencyMatrix[][] = new int[Constants.NodeNumber][Constants.NodeNumber];
        adjacencyMatrix = GraphFileReader.GetAdjacencyMatrix("Graph.txt");

        // GraphUtility.printAdjacencyMatrix(adjacencyMatrix);
                        
         LowestIndegreeNode lowestInNode = new LowestIndegreeNode();
         int nodeNumber = lowestInNode.AlgorithmNode_process(adjacencyMatrix);
         System.out.println("Lowest Indegree Node: " + nodeNumber);
        
         HighestIndegreeNode highestInNode = new HighestIndegreeNode();
         nodeNumber = highestInNode.AlgorithmNode_process(adjacencyMatrix);
         System.out.println("Highest Indegree Node: " + nodeNumber);
        
         LowestOutdegreeNode lowestOutNode = new LowestOutdegreeNode();
         nodeNumber = lowestOutNode.AlgorithmNode_process(adjacencyMatrix);
         System.out.println("Lowest Outdegree Node: " + nodeNumber);
        
         HighestOutdegreeNode highestOutNode = new HighestOutdegreeNode();
         nodeNumber = highestOutNode.AlgorithmNode_process(adjacencyMatrix);
         System.out.println("Highest Outdegree Node: " +nodeNumber);
      
         int[] shortestPathVector = new int[Constants.NodeNumber];
         ShortestPath shortestPath = new ShortestPath();
         shortestPathVector = shortestPath.AlgorithmValue_process(adjacencyMatrix, 1, 6);
         for(int i = 0; i <  Constants.NodeNumber; i++)
         System.out.println(shortestPathVector[i]);

         
        

    }
}
