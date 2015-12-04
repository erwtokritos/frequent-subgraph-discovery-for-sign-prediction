/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.functions;


import graph.objects.Graphlets;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thanos
 */
public class ExploreNetwork {

    private static HashMap<String, Integer> map;
    private static final int min_neighbours = 25;
    private static final String inputFile = "wikipedia.network.csv";
    private static final int graphlet_size = 3;    
    //private static final double min_support = 0.075;

    public static void main(String[] args) {
        
        System.out.println("Starting... " + new Date());
        FileWriter fstream = null;
        try {
            
            BigFile file = new BigFile(inputFile);
            Iterator<String> it = file.iterator();
            
            String outputFile = inputFile.replace("network", "explore-k" + graphlet_size);
            
            fstream = new FileWriter(outputFile);            
            BufferedWriter out = new BufferedWriter(fstream, 150 * 1024 * 1024);
            int graphsConsidered = 0;
            int counter = 0;
            
            while(it.hasNext()) {
                
                counter++;
                if(counter % 10000 == 0) {
                    System.out.println(counter + " edges so far...." + new Date());
                }
                String line = it.next();
                String[] splits = line.split("\t");
                
                String label = splits[0];
                String noOfNeighbours = splits[1];                
                String graph = splits[2];
                
                
                //check the number of neighbors constraint
                if(Integer.parseInt(noOfNeighbours) < min_neighbours) {
                    continue;
                }
                
                graphsConsidered++;
                
                //graph is a nxn adjacency matrix
                String[] ns = graph.split(",");
                int numberOfEntries = ns.length;
                int size = (int) Math.sqrt(numberOfEntries);
                
                
                int[][] adjacencyMatrix = new int[size][size];
                for(int i = 0; i < size; i++){
                    for(int j = 0; j < size; j++){
                        adjacencyMatrix[i][j] = Integer.parseInt(ns[i*size + j]);
                    }
                }       
                
                
                //1st pass. subgraph selection
                HashMap<String, Integer> scanMatrix = Graphlets.getRawGraphlets(adjacencyMatrix, graphlet_size);
                if (scanMatrix != null) {
                                        
                    String print = "";
                    print += label + "," + noOfNeighbours + ",";
                    for (Entry<String, Integer> entry : scanMatrix.entrySet()) {

                        String key = entry.getKey();
                        Integer value = entry.getValue();
                        
                        print += key + ":" + value + ",";
                    }
                    
                    print = print.substring(0,print.length() - 1);
                    
                    out.write(print);
                    out.newLine();                    
                }
            }
               
                        
            out.flush();
            out.close();
            fstream.close();            
            file.Close();
            
            System.out.println("Finishing at... = " + new Date());                        

            
        } catch (Exception ex) {
            Logger.getLogger(ExploreNetwork.class.getName()).log(Level.SEVERE, null, ex);
        }

        finally {
        
            try {
                fstream.close();
            } catch (IOException ex) {
                Logger.getLogger(ExploreNetwork.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
        }

    }

    
       
    /*
    private static HashMap<Integer, String> computeFrequentGraphletCodes(HashMap<String, Integer> subgraphFreq, int noOfGraphs) {
                
        HashMap<Integer, String> response = new HashMap<Integer, String>(2000);
               
       
        int counter = 0;
        
        List<KeyValuePair> list = new ArrayList<KeyValuePair>(subgraphFreq.size());
        for(Entry<String,Integer> entry : subgraphFreq.entrySet()) {
            
            KeyValuePair pair = new KeyValuePair();
            pair.setKey(entry.getKey());
            pair.setValue(entry.getValue());
            list.add(pair);                             
        }
        
        
        //sort by frequency
        Collections.sort(list);

        
              
        for(int i = 0; i < list.size(); i++) {
            
            KeyValuePair keyValuePair = list.get(i);
           
            double support = (double)keyValuePair.getValue()/(double)noOfGraphs;
            if(support < min_support) {
                break;
            }             
            
            System.out.println("-" + keyValuePair.getKey());
            response.put(counter, keyValuePair.getKey());   
            counter++;

        }  
                

        System.out.println("There are " + counter + " graphlets with minimum support " + min_support +
                    " from a total of " + subgraphFreq.size() + "(" + (double)counter/(double)subgraphFreq.size() +")");
            
        return response;
    }
    */
 

}
