package exercise.george;


public class HighestOutdegreeNode implements AlgorithmInterface {

    public int[] AlgorithmValue_process(int[][] adjacencyMatrix, int sourceNode, int DestinationNode) {
        int result[] = new int[0];
        return result;
    }

    public int AlgorithmNode_process(int[][] adjacencyMatrix) {

        int value = 0, nodeNumber = -1, sum;

        for (int i = 1; i < adjacencyMatrix.length; i++) {

            sum = 0;
            for (int j = 1; j < adjacencyMatrix.length; j++) {
                sum += adjacencyMatrix[i][j];
            }

            if (sum > value) {
                value = sum;
                nodeNumber = i;

            }
        }

        return nodeNumber;
    }
}
