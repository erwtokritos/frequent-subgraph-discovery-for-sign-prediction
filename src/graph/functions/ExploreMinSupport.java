/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.functions;


import graph.objects.EdgeInfo;
import graph.objects.KeyValuePair;
import graph.objects.MyRelationshipTypes;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thanos
 */
public class ExploreMinSupport {

    private static HashMap<String, Integer> map;
    private static final int min_neighbours = 25;
    private static final String inputFile = "wikipedia.explore-k3.csv";
    //private static final int graphlet_size = 4;    
    private static final double min_support = 0.05;

    public static void main(String[] args) {
        
        int pos = inputFile.indexOf("explore-k");
        int length = "explore-k".length();
        String n = inputFile.substring(pos + length, pos + length + 1);
        int graphlet_size = Integer.parseInt(n);
        
        System.out.println("n = " + n);
        System.out.println("Starting... " + new Date()); 
        System.out.println("graphlet_size " + graphlet_size);
        System.out.println("min_support " + min_support);
        FileWriter fstream = null;
        try {
            

            HashMap<Integer, String> codes = computeFrequentGraphletCodes();

            //2nd pass. process the networks                       
            String outputFile = inputFile + "-k" + graphlet_size +  "-minsupport" + min_support +".csv";            
            fstream = new FileWriter(outputFile);            
            BufferedWriter out = new BufferedWriter(fstream, 150 * 1024 * 1024);
            BigFile file = new BigFile(inputFile);
            Iterator<String> it = file.iterator();
                       
            int counter = 0;
            
            while (it.hasNext()) {

                
                counter++;
                if(counter % 10000 == 0) {
                    System.out.println(counter + " edges so far..." + new java.util.Date());
                }
                
                String line = it.next();
                String[] splits = line.split(",");
                
                if(splits.length < 3) {
                    continue;
                }
                
                int noSplits = splits.length;
                String label = splits[0];
                String noOfNeighbours = splits[1];                

                //check the number of neighbors constraint
                if (Integer.parseInt(noOfNeighbours) < min_neighbours) {
                    continue;
                }

                HashMap<String, Integer> scan = new HashMap<String, Integer>(noSplits - 2);
                for(int i = 2; i < noSplits; i++) {
                    
                    String ss = splits[i];
                    String[] ssplit = ss.split(":");
                    
                    String subgraphCode = ssplit[0];
                    int freq  = Integer.parseInt(ssplit[1]);
                            
                    scan.put(subgraphCode, freq);
                }     


                String print = "";
                print += label + ",";

                for (int codeCount = 0; codeCount < codes.size(); codeCount++) {
                    String key = codes.get(codeCount);
                    Integer get = scan.get(key);

                    if (get == null) {
                        print += "0";
                    } else {
                        print += String.valueOf(get);
                    }

                    if (codeCount != codes.size() - 1) {
                        print += ",";
                    }
                    //System.out.println(print);


                }
                  
                out.write(print);                
                out.newLine();                
            }

            System.out.println("Writing results " + new Date());
            out.flush();
            out.close();
            fstream.close();
            System.out.println("Finishing... " + new Date());
            
        } catch (Exception ex) {
            Logger.getLogger(ExploreMinSupport.class.getName()).log(Level.SEVERE, null, ex);
        }

        finally {
        
            /*
            try {
                fstream.close();
            } catch (IOException ex) {
                Logger.getLogger(ExploreMinSupport.class.getName()).log(Level.SEVERE, null, ex);
            }              
            */
            
        }

    }

    
       
    private static HashMap<Integer, String> computeFrequentGraphletCodes() {
        
        try{
                    
            BigFile file = new BigFile(inputFile);
            Iterator<String> it = file.iterator();
            HashMap<String, Integer> subgraphFreq = new HashMap<String, Integer>(200000);
            int graphsConsidered = 0;
            
            while(it.hasNext()) {
                
                String line = it.next();
                String[] splits = line.split(",");
                
                String label = splits[0];
                String noOfNeighbours = splits[1];    
                
                int noSplits = splits.length;

                //check the number of neighbors constraint
                if(Integer.parseInt(noOfNeighbours) < min_neighbours) {
                    continue;
                }
                
                graphsConsidered++;
                
                for(int i = 2; i < noSplits; i++) {
                    
                    String ss = splits[i];
                    String[] ssplit = ss.split(":");
                    String subgraphCode = ssplit[0];
                    int freq  = Integer.parseInt(ssplit[1]);
                            
                                        
                    Integer get = subgraphFreq.get(subgraphCode);
                    if (get == null) {
                        //subgraphFreq.put(key, entry.getValue());
                        subgraphFreq.put(subgraphCode, 1);
                    } else {
                        //subgraphFreq.put(key, get + entry.getValue());
                        subgraphFreq.put(subgraphCode, get + 1);
                    }
                    
                }                  
            }
            
             file.Close();
            
            List<KeyValuePair> list = new ArrayList<KeyValuePair>(subgraphFreq.size());
            for(Entry<String,Integer> entry : subgraphFreq.entrySet()) {

                KeyValuePair pair = new KeyValuePair();
                pair.setKey(entry.getKey());
                pair.setValue(entry.getValue());
                list.add(pair);                             
            }

                      
            //sort by frequency
           Collections.sort(list);  
            
            HashMap<Integer, String> response = new HashMap<Integer, String>(2000);
            int counter = 0;
            for(int i = 0; i < list.size(); i++) {

                KeyValuePair keyValuePair = list.get(i);

                double support = (double)keyValuePair.getValue()/(double)graphsConsidered;
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
               
            
           
        
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        
        return null;
    }
        

}
