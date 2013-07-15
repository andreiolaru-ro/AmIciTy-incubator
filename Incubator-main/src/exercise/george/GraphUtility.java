package exercise.george;

public class GraphUtility {
    
    public static void printAdjacencyMatrix(int[][] adjacencyMatrix) {
        
        for (int i=1; i < Constants.NodeNumber; i++) {
            for (int j=1; j < Constants.NodeNumber; j++)
                System.out.print(adjacencyMatrix[i][j] + " ");
            System.out.println();
        }    
    }
}
