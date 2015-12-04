/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.functions;

import graph.objects.EdgeInfo;
import graph.objects.GNode;
import graph.objects.GSubgraph;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thanos
 */
public class GraphLoader {
    
    private static HashMap<Integer, GNode> mapper = new HashMap<Integer, GNode>(120000);
    private static final String inputFile = "./datasets/wikipedia.txt";
    private static final String outputFile = "wikipedia.network.csv";;
    private static final boolean includeGenerator = false;
    
    public static void main(String[] args) {
        
        System.out.println("Starting... " + new java.util.Date());
        FileWriter fstream = null;
        
        try {
            BigFile file = new BigFile(inputFile);
            
            Iterator<String> iterator = file.iterator();
            int counter = 0;
            while(iterator.hasNext()) {
                
                String line = iterator.next();
                String[] splits = line.split("\t");
                int sourceId = Integer.parseInt(splits[0]);
                int targetId = Integer.parseInt(splits[1]);
                String sign = splits[2];
                double w = Double.parseDouble(sign);
                
                GNode src = mapper.get(sourceId);
                if(src == null) {
                    src = new GNode(sourceId);
                }
                
                         
                GNode dst = mapper.get(targetId);
                if(dst == null) {
                    dst = new GNode(targetId);
                }
                
                
                if(w > 0) {
                    src.addPositiveDestination(targetId);
                    dst.addPositiveInitiator(sourceId);
                }else {
                                      
                    src.addNegativeDestination(targetId);
                    dst.addNegativeInitiator(sourceId);
                }
                
                mapper.put(sourceId, src);
                mapper.put(targetId, dst);
                
                counter++;
                if(counter % 10000 == 0) {
                    System.out.println(counter + " edges so far");
                }
            }
            file.Close();
            System.out.println("Graph loaded at ... " + new java.util.Date());
            
            System.out.println("Calculating common neighbours.... " + new java.util.Date());
                                    
            //String outputFile = "epinions.network.csv";
            fstream = new FileWriter(outputFile); 
            
            BufferedWriter out = new BufferedWriter(fstream,  150 * 1024 * 1024);
            file = new BigFile(inputFile);
            
            iterator = file.iterator();
            counter = 0;
            int ok = 0, missed = 0, positive_missed = 0, negative_missed = 0;
            while(iterator.hasNext()) {
                
                counter++;
                if(counter % 10000 == 0) {
                    System.out.println(counter + " edges so far " + missed + " edges ," + positive_missed);
                     
                }                
                
                String line = iterator.next();
                String[] splits = line.split("\t");
                int sourceId = Integer.parseInt(splits[0]);
                int targetId = Integer.parseInt(splits[1]);
                String sign = splits[2];
                double w = Double.parseDouble(sign);
                
                GNode src = mapper.get(sourceId);                         
                GNode dst = mapper.get(targetId);
                
                List<Integer> commonNeighbours = src.commonNeighbours(dst);
                         
                
                if(commonNeighbours.size() < 25) {
                    continue;
                }
                    
                /* Avoid outliers.. (they are usually positively correlated)
                if(commonNeighbours.size() > 500) {
                    missed++;
                    if(w > 0) {
                        positive_missed++;
                    }else {
                        negative_missed++;
                    }
                    continue;
                }
                */
                
                ok++;
                //System.out.println(commonNeighbours.size());
                List<Integer> soFar = new ArrayList<Integer>();
                soFar.add(sourceId);
                soFar.add(targetId);
                soFar.addAll(commonNeighbours);
                
                     
                GSubgraph subgraph = new GSubgraph();
                List<GNode> nodes = new ArrayList<GNode>();
                        
                for(int d : soFar) {
                    nodes.add(mapper.get(d));
                }
                List<EdgeInfo> uncoverConnections = Methods.uncoverConnections2(nodes);
                //List<EdgeInfo> uncoverConnections = Methods.uncoverConnections(nodes);
        
                if(!includeGenerator) {
                    uncoverConnections.remove(0);
                }
        
                subgraph.setVertices(soFar);
                subgraph.setEdges(uncoverConnections);
                
 
                int[][] adjacencyMatrix = Methods.getAdjacencyMatrix(subgraph);
                String print = "";
                if (w > 0) {
                    print += "1\t";
                } else {
                    print += "0\t";

                }


                print += subgraph.getVertices().size() + "\t";

                for (int p = 0; p < adjacencyMatrix.length; p++) {
                    for (int q = 0; q < adjacencyMatrix[0].length; q++) {

                        print += adjacencyMatrix[p][q] + ",";
                    }
                }

                //remove last comma
                print = print.substring(0, print.length() - 1);
                //System.out.println(print);

                out.write(print);
                out.newLine();    
                                
            }
                    
            file.Close();
            
            out.flush();
            out.close();
            fstream.close();
            
            System.out.println("Finally " + missed + " edges ," + positive_missed + " of them positive with embeddedness higher than ...!" + ok + "   " + new java.util.Date());
            

            
        } catch (Exception ex) {
            Logger.getLogger(GraphLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
                     
            try {
                fstream.close();
            } catch (IOException ex) {
                Logger.getLogger(GraphLoader.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        
    }
}
