package exercise.george;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class GraphFileReader {
    
    public static int[][] GetAdjacencyMatrix(String filePath) {
        
        int adjacencyMatrix[][] = new int[Constants.NodeNumber][Constants.NodeNumber];
        int leftNode, rightNode;
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
 
            String sCurrentLine;
 
            while ((sCurrentLine = br.readLine()) != null) {
		
                StringTokenizer stk = new StringTokenizer(sCurrentLine, Constants.EdgeSymbol);
                leftNode = Integer.parseInt(stk.nextToken());
                rightNode = Integer.parseInt(stk.nextToken());
                adjacencyMatrix[leftNode][rightNode] = 1;
            }
			
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return adjacencyMatrix;
    }
}

