/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.objects;

import java.util.HashMap;
import test.Combination;


/**
 *
 * @author thanos
 */
public class Graphlets {
    
    //matches subgraph
    //  0  0  0  0
    //  0  0  0  0 
    //  1  0  0  0 
    // -1  1  0  0
    public static boolean type1(int[][] adjMatrix) {
        
        
        
        return false;
    }
    
    
        
    //Returns a non-zero mxn matrix 
    public static void engineerFeature(int[][] values ) {
                
        Matrix mat = new Matrix(values);
        mat.show();
        for(int i = 0; i < mat.getColumnDimension(); i++) {
            System.out.print(countChar(mat.getCol(i), 1) + countChar(mat.getCol(i), -1) + "  ");
        }
        System.out.println();
        mat.reorderCols();
        mat.show();
        
        for(int i = 0; i < mat.getColumnDimension(); i++) {
            System.out.print(countChar(mat.getCol(i), 1) + countChar(mat.getCol(i), -1) + "  ");
        }        
        //return response;
    }
    
        /**
     * Examines all the  subgraphs with k vertices
     * @param adj The adjacency matrix of the whole graph
     * @param k The number of vertices in the subgraph
     */
    //public static HashMap<String, Integer> scanSubgraphs(int[][] adj, int k) {
     public static HashMap<String, Integer> getWikiRawGraphlets(int[][] adj, int k) {
        
         //the first two rows are the source and target nodes
         int n = adj.length;
        
        //if the graph is too small ignore
        if(n < k) {
            return null;
        }
        
        //System.out.println( "n = " + n + " , k = " + k);
        Matrix matrix = new Matrix(adj);
        //HashMap<String, Integer> countMap = new HashMap<String, Integer>(100000);
        HashMap<String, Integer> countMap = new HashMap<String, Integer>(500);
        int counter = 0;
        
        //get all available combinations 
        //combine the common neighbours with the source,target nodes
        Combination combinations = new Combination(n - 2, k - 2);        
        while (combinations.hasNext()) {

            int[] combination = combinations.next();
            int[] comb = new int[combination.length + 2];
            
            //add the source node
            comb[0] = 0;
            //add the target node
            comb[1] = 1;
            
            for(int m = 0; m < combination.length; m++) {
                comb[m + 2] = 2 + combination[m];
            }
           
            Matrix subgraph = matrix.subgraph(comb);
            if (subgraph.isZero() || !subgraph.isWeaklyConnected(true)) {
                continue;
            }

            //subgraph.standardForm();
            
            counter++;
            //int code = GraphletType.compute(subgraph);
            //int code = GraphletType.computeGraphletCode(subgraph);
            String code = encodeGraph(subgraph);
            
            //String code = encodeGraph(subgraph);
            Integer get = countMap.get(code);
            if (get == null) {
                countMap.put(code, 1);
            } else {
                countMap.put(code, get + 1);
            }

        }
        
        return countMap;
        
    }
     
     
   
   /**
     * Examines all the  subgraphs with k vertices
     * @param adj The adjacency matrix of the whole graph
     * @param k The number of vertices in the subgraph
     */
    //public static HashMap<String, Integer> scanSubgraphs(int[][] adj, int k) {
     public static HashMap<String, Integer> getCanonicalGraphlets(int[][] adj, int k) {
        
         //the first two rows are the source and target nodes
         int n = adj.length;
        
        //if the graph is too small ignore
        if(n < k) {
            return null;
        }
        
        //System.out.println( "n = " + n + " , k = " + k);
        Matrix matrix = new Matrix(adj);
        //HashMap<String, Integer> countMap = new HashMap<String, Integer>(100000);
        HashMap<String, Integer> countMap = new HashMap<String, Integer>(500);
        int counter = 0;
        
        //get all available combinations 
        //combine the common neighbours with the source,target nodes
        Combination combinations = new Combination(n - 2, k - 2);        
        while (combinations.hasNext()) {

            int[] combination = combinations.next();
            int[] comb = new int[combination.length + 2];
            
            //add the source node
            comb[0] = 0;
            //add the target node
            comb[1] = 1;
            
            for(int m = 0; m < combination.length; m++) {
                comb[m + 2] = 2 + combination[m];
            }
           
            Matrix subgraph = matrix.subgraph(comb);
            if (subgraph.isZero() || !subgraph.isWeaklyConnected()) {
                continue;
            }

            //subgraph.standardForm();
            
            counter++;
            //int code = GraphletType.compute(subgraph);
            //int code = GraphletType.computeGraphletCode(subgraph);
            String code = subgraph.getCanonicalLabel();
            
            //String code = encodeGraph(subgraph);
            Integer get = countMap.get(code);
            if (get == null) {
                countMap.put(code, 1);
            } else {
                countMap.put(code, get + 1);
            }

        }
        
        return countMap;
        
    }     
    
      
    /**
     * Examines all the  subgraphs with k vertices
     * @param adj The adjacency matrix of the whole graph
     * @param k The number of vertices in the subgraph
     */
    //public static HashMap<String, Integer> scanSubgraphs(int[][] adj, int k) {
     public static HashMap<String, Integer> getRawGraphlets(int[][] adj, int k) {
        
         //the first two rows are the source and target nodes
         int n = adj.length;
        
        //if the graph is too small ignore
        if(n < k) {
            return null;
        }
        
        //System.out.println( "n = " + n + " , k = " + k);
        Matrix matrix = new Matrix(adj);
        //HashMap<String, Integer> countMap = new HashMap<String, Integer>(100000);
        HashMap<String, Integer> countMap = new HashMap<String, Integer>(500);
        int counter = 0;
        
        //get all available combinations 
        //combine the common neighbours with the source,target nodes
        Combination combinations = new Combination(n - 2, k - 2);        
        while (combinations.hasNext()) {

            int[] combination = combinations.next();
            int[] comb = new int[combination.length + 2];
            
            //add the source node
            comb[0] = 0;
            //add the target node
            comb[1] = 1;
            
            for(int m = 0; m < combination.length; m++) {
                comb[m + 2] = 2 + combination[m];
            }
           
            Matrix subgraph = matrix.subgraph(comb);
            if (subgraph.isZero() || !subgraph.isWeaklyConnected()) {
                continue;
            }

            subgraph.standardForm();
            
            counter++;
            //int code = GraphletType.compute(subgraph);
            //int code = GraphletType.computeGraphletCode(subgraph);
            String code = encodeGraph(subgraph);
            
            //String code = encodeGraph(subgraph);
            Integer get = countMap.get(code);
            if (get == null) {
                countMap.put(code, 1);
            } else {
                countMap.put(code, get + 1);
            }

        }
        
        return countMap;
        
    }
    
    
       
    /**
     * Examines all the  subgraphs with k vertices
     * @param adj The adjacency matrix of the whole graph
     * @param k The number of vertices in the subgraph
     */
    //public static HashMap<String, Integer> scanSubgraphs(int[][] adj, int k) {
     public static HashMap<Integer, Integer> scanGraphlets(int[][] adj, int k) {
        
         //the first two rows are the source and target nodes
         int n = adj.length;
        
        //if the graph is too small ignore
        if(n < k) {
            return null;
        }
        
        Matrix matrix = new Matrix(adj);
        //HashMap<String, Integer> countMap = new HashMap<String, Integer>(100000);
        HashMap<Integer, Integer> countMap = new HashMap<Integer, Integer>(500);
        int counter = 0;
        
        //get all available combinations 
        //combine the common neighbours with the source,target nodes
        Combination combinations = new Combination(n - 2, k - 2);        
        while (combinations.hasNext()) {

            int[] combination = combinations.next();
            int[] comb = new int[combination.length + 2];
            
            //add the source node
            comb[0] = 1;
            //add the target node
            comb[1] = 2;
            
            for(int m = 0; m < combination.length; m++) {
                comb[m + 2] = combination[m];
            }
           
            Matrix subgraph = matrix.subgraph(comb);
            if (subgraph.isZero() || !subgraph.isWeaklyConnected()) {
                continue;
            }

            subgraph.standardForm();
            
            counter++;
            //int code = GraphletType.compute(subgraph);
            int code = GraphletType.computeGraphletCode(subgraph);
            
            //String code = encodeGraph(subgraph);
            Integer get = countMap.get(code);
            if (get == null) {
                countMap.put(code, 1);
            } else {
                countMap.put(code, get + 1);
            }

        }
        
        return countMap;
        
    }
    
    
       
    /**
     * Examines all the  subgraphs with k vertices
     * @param adj The adjacency matrix of the whole graph
     * @param k The number of vertices in the subgraph
     */
    //public static HashMap<String, Integer> scanSubgraphs(int[][] adj, int k) {
     public static HashMap<Integer, Integer> scanSubgraphs(int[][] adj, int k) {
        int n = adj.length;
        
        //if the graph is too small ignore
        if(n < k) {
            return null;
        }
        
        Matrix matrix = new Matrix(adj);
        //HashMap<String, Integer> countMap = new HashMap<String, Integer>(100000);
        HashMap<Integer, Integer> countMap = new HashMap<Integer, Integer>(500);
        int counter = 0;
        
        //get all available combinations 
        Combination combinations = new Combination(n, k);        
        while (combinations.hasNext()) {

            int[] combination = combinations.next();
            Matrix subgraph = matrix.subgraph(combination);
            if (subgraph.isZero() || !subgraph.isWeaklyConnected()) {
                continue;
            }

            subgraph.standardForm();
            
            counter++;
            int code = GraphletType.compute(subgraph);
            
            //String code = encodeGraph(subgraph);
            Integer get = countMap.get(code);
            if (get == null) {
                countMap.put(code, 1);
            } else {
                countMap.put(code, get + 1);
            }

        }
        
        return countMap;
        
    }
    
    
    
    /**
     * Scans the matrix using a windows kxk
     * @param matrix
     * @param k 
     */
    public static HashMap<String, Integer> scanMatrix(int[][] adj, int k) {
     
        if(adj.length < k) {
            return null;
        }
        
        Matrix matrix = new Matrix(adj);
        matrix.reorderCols();
        HashMap<String, Integer> countMap = new HashMap<String, Integer>(10);
        int counter = 0;
        for(int row = 0; row < matrix.getRowDimension() - k; row++) {
            for(int i = 0; i < matrix.getLastNotZeroColumn() - k; i++) {
                
                Matrix sub = matrix.submatrix(row, i, k);
                if(sub.isZero() || !sub.isWeaklyConnected()) {
                    continue;
                }

                sub.reorderCols();
                sub.reorderRows();
                
                counter++;
                String code = encodeGraph(sub);                
                Integer get = countMap.get(code);
                if(get == null) {
                    countMap.put(code, 1);
                }else {
                    countMap.put(code, get + 1);
                }
            }
        }
        
        return countMap;
        
    }
    
    public static String encodeGraph(Matrix m) {

        String response = "";

        for (int j = 0; j < m.getColumnDimension(); j++) {
            for (int i = 0; i < m.getRowDimension(); i++) {

                response += String.valueOf(m.getValue(i, j));
            }
        }

        return response;
    }
       
    public static int countCharInAllCols(int[][] matrix, int sign) {
        
        int count = 0;
        for(int i = 0; i < matrix[0].length; i++) {

            count += countChar(matrix[i], sign);
        }
            
        return count;    
    }    
    
    
    public static int countCharInAlRows(int[][] matrix, int sign) {
        
        int count = 0;
        for(int i = 0; i < matrix.length; i++) {

            count += countChar(matrix[i], sign);
        }
            
        return count;    
    }
    

    
    public static int countChar(int[] slice, int sign) {
        
        int count = 0;
        for(int i = 0; i < slice.length; i++) {
            if(slice[i] == sign) {
                count++;
            }
        }
            
        return count;    
    }
    
}
