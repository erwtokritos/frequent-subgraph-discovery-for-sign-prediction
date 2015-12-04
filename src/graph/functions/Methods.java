/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.functions;

import graph.objects.EdgeInfo;
import graph.objects.GNode;
import graph.objects.GNode.Relation;
import graph.objects.GSubgraph;
import graph.objects.Matrix;
import graph.objects.MyRelationshipTypes;
import graph.objects.Subgraph;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import org.neo4j.graphalgo.GraphAlgoFactory;
import org.neo4j.graphalgo.PathFinder;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.kernel.Traversal;

/**
 *
 * @author Thanos
 */
public class Methods {

    
    
    /**
     * Vectorize the adjacency matrix
     */
    public static int[] vectorize(Matrix m) {
        
        int[] response = new int[m.getRowDimension()*m.getColumnDimension()];
        for(int i = 0; i < m.getColumnDimension(); i++) {
            
            int[] col = m.getCol(i);
            for(int j = 0; j < col.length; j++) {
                response[i*m.getColumnDimension() + j] = col[j];
            }
            
        }
        
        return response;
    }
    
    

    public static ArrayList<Integer> mergeLists(ArrayList<Integer> input1, ArrayList<Integer> input2) {
        
        ArrayList<Integer> response = new ArrayList<Integer>();
        response.addAll(input1);
        response.addAll(input2);
        
        return response;
    }
    

    public static Node[] getNodesExceptSource (Node[] nodes, Node source) {

        ArrayList<Node> response = new ArrayList<Node>();
        for(int i = 0; i < nodes.length; i++) {

            Node candidate = nodes[i];
            if(candidate != source)
                response.add(nodes[i]); 
        }

        return response.toArray(new Node[0]);
    }

     
    public static ArrayList<Integer> setToArrayList(Set<Integer> set) {
        
        ArrayList<Integer> response = new ArrayList<Integer>();
        for(int key : set) 
            response.add(key);
        
        return response;
    }


    
    public static HashSet<Integer> randomIntegerSet(int count, int maximumValue) {

        HashSet<Integer> resp = new HashSet<Integer>();
        boolean ok = false;

        Random generator = new Random();
        while(!ok) {

            int nextInt = generator.nextInt(maximumValue);
            resp.add(nextInt);

            if(resp.size() == count)
                ok = true;
        }

        return resp;
    }


    public static int countRelationships(Node a, Direction direction, RelationshipType relType) {
        
        List<Node> nodes = getNodes(a, direction, relType);
        return nodes.size();
    }

    
    public static PathFinder<Path> getFinder(RelationshipType relType, Direction direction, int depth) {
                 
        return GraphAlgoFactory.shortestPath(
                    Traversal.expanderForTypes(relType, direction ), depth);
    }


    public static boolean existsRelFromAToB(Node a, Node b, Direction direction, RelationshipType relType) {

        Iterable<Relationship> relationships;

        if(relType == null) {
            relationships = a.getRelationships(direction);
        }
        else if(direction == null) {
            relationships = a.getRelationships(relType);
        }
        else {
            relationships= a.getRelationships(relType, direction);
        }


        Iterator<Relationship> it = relationships.iterator();
        while (it.hasNext()) {
            Relationship rel = it.next();
            if (rel.getOtherNode(a).equals(b)) {
                return true;
            }
        }

        return false;
    }
    
   
 
    public static Node[] mergeNodes(Node[] nodes1, Node[] nodes2) {
        ArrayList<Node> resp = new ArrayList<Node>();
        HashSet<Node> nodes = new HashSet<Node>();

        for(Node node : nodes1) {        
            nodes.add(node);
        }

        for(Node node : nodes2) {
            nodes.add(node);
        }

        Iterator<Node> it = nodes.iterator();
        while(it.hasNext()) {
            Node current = it.next();
            resp.add(current);
        }
        return resp.toArray(new Node[0]);
    }

    
        
    public static List<EdgeInfo> uncoverConnections2(List<GNode> nodes) {
    
        List<EdgeInfo> res = new ArrayList<EdgeInfo>();
              
        for(int i = 0; i < nodes.size() - 1; i++ ) {
                              
            GNode user1 = nodes.get(i);
            String user1Id = String.valueOf(user1.getId());
            
            List<Integer> l = new ArrayList<Integer>();
            for(int j = i + 1; j < nodes.size(); j++) {
                l.add(nodes.get(j).getId());
            }
            

            
            Map<Integer, List<Relation>> rels = user1.toGNodes(l);
            for(Entry<Integer, List<Relation>> entry : rels.entrySet()) {
                
                String user2Id = String.valueOf(entry.getKey());
                List<Relation> relations = entry.getValue();
                   
                for(Relation rel : relations) {
                        
                        RelationshipType relType;
                        if(rel.equals(Relation.OUT_POS)) {
                            
                            res.add(new EdgeInfo(user1Id, user2Id, MyRelationshipTypes.LIKES));
                        }else if(rel.equals(Relation.OUT_NEG)) {
                            res.add(new EdgeInfo(user1Id, user2Id, MyRelationshipTypes.HATES));
                        }else if(rel.equals(Relation.IN_POS)) {
                            res.add(new EdgeInfo(user2Id, user1Id, MyRelationshipTypes.LIKES));
                        }else if(rel.equals(Relation.IN_NEG)) {
                            res.add(new EdgeInfo(user2Id, user1Id, MyRelationshipTypes.HATES));
                        }
                    }                
                
            }
        }

        return res;
    }
    
    
    
    
    public static List<EdgeInfo> uncoverConnections(List<GNode> ids) {
    
        List<EdgeInfo> res = new ArrayList<EdgeInfo>();
              
        for(int i = 0; i < ids.size() - 1; i++ ) {
            for(int j = i + 1; j < ids.size(); j++) {
                

                GNode user1 = ids.get(i);
                GNode user2 = ids.get(j);
                
                List<Relation> relations = user1.toGNode(user2);
                if(relations != null) {
                   
                    for(Relation rel : relations) {
                        
                        RelationshipType relType;
                        if(rel.equals(Relation.OUT_POS)) {
                            
                            res.add(new EdgeInfo(String.valueOf(user1.getId()), String.valueOf(user2.getId()), MyRelationshipTypes.LIKES));
                        }else if(rel.equals(Relation.OUT_NEG)) {
                            res.add(new EdgeInfo(String.valueOf(user1.getId()), String.valueOf(user2.getId()), MyRelationshipTypes.HATES));
                        }else if(rel.equals(Relation.IN_POS)) {
                            res.add(new EdgeInfo(String.valueOf(user2.getId()), String.valueOf(user1.getId()), MyRelationshipTypes.LIKES));
                        }else if(rel.equals(Relation.IN_NEG)) {
                            res.add(new EdgeInfo(String.valueOf(user2.getId()), String.valueOf(user1.getId()), MyRelationshipTypes.HATES));
                        }
                    }
                    
                            
                }                            
            }
        }
        
        
        return res;
    }
    
    

    public static void registerShutdownHook(final GraphDatabaseService graphDb) {
        // Registers a shutdown hook for the Neo4j instance so that it
        // shuts down nicely when the VM exits (even if you "Ctrl-C" the
        // running example before it's completed)
        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                graphDb.shutdown();
            }
        });
    }
    
        
    public static List<Integer> common(List<Integer> set1 , List<Integer> set2) {
       
        List<Integer> response = new ArrayList<Integer>();
        if(set1 == null || set2 == null) {
            return null;
        }
        if(set1.size() < set2.size()) {
            for(int id : set1) {
                if(set2.contains(id)) {
                    response.add(id);
                }
            }
        }else {                       
            for(int id : set2) {
                if(set1.contains(id)) {
                    response.add(id);
                }
            }
        }
        
        return response;
    }
    
    public static Node[] findCommonNodes(Node[] as, Node[] bs) {

        ArrayList<Node> response = new ArrayList<Node>();
        HashSet<Node> bSet = new HashSet<Node>();
        bSet.addAll(Arrays.asList(bs));

        for (int i = 0; i < as.length; i++) {

            Node a = as[i];
            if (bSet.contains(a)) {
                response.add(a);
            }
        }
        return response.toArray(new Node[0]);
    }

    
        
    public static List<Node> intersect(List<Node> as, List<Node> bs) {

        ArrayList<Integer> response = new ArrayList<Integer>();
        List<Node> smaller = null;
        List<Node> larger = null;
                
        HashSet<Node> bSet = new HashSet<Node>();
        
        if(as.size()  <= bs.size()) {
            smaller = as;
            larger = bs;
        }else {
            smaller = bs;
            larger = as;
        }
                           
        for(Node item : smaller) {
               
            if(larger.contains(item))            
                bSet.add(item);    
        }
       
        
        return hashSetToList(bSet);
        
    }
    
    
  
    public static List<String> intersectString(List<String> as, List<String> bs) {

        List<String> smaller = null;
        List<String> larger = null;
                
        HashSet<String> bSet = new HashSet<String>();
        
        if(as.size()  <= bs.size()) {
            smaller = as;
            larger = bs;
        }else {
            smaller = bs;
            larger = as;
        }
                           
        for(String item : smaller) {
               
            if(larger.contains(item))            
                bSet.add(item);    
        }
       
        
        return hashSetToListString(bSet);
        
    }
        
        
    public static List<Node> union(List<Node> as, List<Node> bs) {


                
        HashSet<Node> bSet = new HashSet<Node>();
        bSet.addAll(as);
        bSet.addAll(bs);
               
        return hashSetToList(bSet);
        
    }
    
        
    public static List<String> unionString(List<String> as, List<String> bs) {
               
        HashSet<String> bSet = new HashSet<String>();
        bSet.addAll(as);
        bSet.addAll(bs);
               
        return hashSetToListString(bSet);
        
    }
    
    
     
    
    public static double hammingSimilarity(List<RelationshipType> path1 , List<RelationshipType> path2, boolean normalized ) {
        
        if(path1.size() != path2.size()) {
            return 0;
        }
        
        int same = 0;
        for(int i = 0 ; i < path1.size(); i++) {
            
            if(path1.get(i).equals(path2.get(i))) {
                same++;
            }
        }
        
        
        if(normalized) {
            return (double)same/(double)path1.size();
        }else {
            return (double)same;
        }
    }
    
    
    
    public static List<Node> hashSetToList(HashSet<Node> set ) {
        List<Node> response = new ArrayList<Node>();
        Iterator<Node> iterator = set.iterator();
        while(iterator.hasNext()) {
            Node next = iterator.next();
            response.add(next);
        }
        
        return response;
    }
    
    public static List<String> hashSetToListString(HashSet<String> set ) {
       
        List<String> response = new ArrayList<String>();
        Iterator<String> iterator = set.iterator();
        while(iterator.hasNext()) {
            String next = iterator.next();
            response.add(next);
        }
        
        return response;
    }    
    
    public static Integer[] findCommonItems(ArrayList<Integer> as, ArrayList<Integer> bs) {

        ArrayList<Integer> response = new ArrayList<Integer>();
        ArrayList<Integer> smaller = null;
        ArrayList<Integer> larger = null;
                
        HashSet<Integer> bSet = new HashSet<Integer>();
        
        if(as.size()  <= bs.size()) {
            smaller = as;
            larger = bs;
        }else {
            smaller = bs;
            larger = as;
        }
                           
        for(Integer item : smaller) {
               
            if(larger.contains(item))            
                bSet.add(item);    
        }
       
        return bSet.toArray(new Integer[0]);
        
    }
    



    public static Node[] getNodesTargetingB(Node[] allNodes, Node b) {

        if(allNodes == null)
            return null;
        
        ArrayList<Node> response = new ArrayList<Node>();
        int added = 0;

        for(int i = 0; i < allNodes.length; i++) {
            if(existsRelFromAToB(allNodes[i], b, Direction.OUTGOING, null)) {
                response.add(allNodes[i]);
                added++;
            }
        }
        
        if(added > 0)
            return response.toArray(new Node[0]);
        else
            return null;

    }

    /*
    public static Jama.EigenvalueDecomposition eigenDecomposition(int[][] data) {
        
        Jama.Matrix A = new Jama.Matrix(toDouble(data));
        return  A.eig();
    }
    */
    public static double[][] toDouble(int[][] data) {
       
        int m = data.length;
        int n = data[0].length;
        
        double[][] output = new double[m][n];
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                output[i][j] = (double)data[i][j];
            }
        }
        return output;
    }
    
    public static int[][] getLaplacian(Subgraph graph) {
                
        int[][] adjMatrix = getAdjacencyMatrix(graph);
        int n = adjMatrix.length;
        int[][] laplacian = new int[n][n];
        
        for(int i = 0; i < n; i++) {
            int degree = 0;
            for(int j = 0; j < n; j++) {
                if(adjMatrix[i][j] != 0) {
                    laplacian[i][j] = -1; 
                }                                
            }
            laplacian[i][i] = degree;
        }
        
        return laplacian;
    }
    
    
      
    public static int[][] getAdjacencyMatrix(GSubgraph graph) {
        
        int n = graph.getVertices().size();        
        int[][] response = new int[n][n];
         
        Map<Integer, Integer> codes = new HashMap<Integer, Integer>(n);
        for(int i = 0; i < n; i++) {
            codes.put(graph.getVertices().get(i), i);
        }
        
        //initialize matrix
        for(int i = 0; i <  n; i++) {
            for(int j = 0; j < n; j++) {
                response[i][j] = 0;
            }
        }
        
        for(EdgeInfo edge : graph.getEdges()) {

            int src = Integer.parseInt(edge.getSource());
            int dst = Integer.parseInt(edge.getTarget());
            
            if(edge.getType().equals(MyRelationshipTypes.LIKES)) {
                response[codes.get(src)][codes.get(dst)] = 1;
            }else {
                response[codes.get(src)][codes.get(dst)] = -1;                
            }
        }
        
        return response;
    }
    
    
    public static int[][] getAdjacencyMatrix(Subgraph graph) {
        
        int n = graph.getVertices().size();        
        int[][] response = new int[n][n];
        
        Map<String, Integer> codes = new HashMap<String, Integer>(n);
        for(int i = 0; i < n; i++) {
            codes.put(graph.getVertices().get(i), i);
        }
        
        //initialize matrix
        for(int i = 0; i <  n; i++) {
            for(int j = 0; j < n; j++) {
                response[i][j] = 0;
            }
        }
        
        for(EdgeInfo edge : graph.getEdges()) {
            if(edge.getType().equals(MyRelationshipTypes.LIKES)) {
                response[codes.get(edge.getSource())][codes.get(edge.getTarget())] = 1;
            }else {
  
                response[codes.get(edge.getSource())][codes.get(edge.getTarget())] = -1;
            }
        }
        
        return response;
    }
    
    
    public static RelationshipType getSimpleConnectionType(Node a, Node b) {


        for(RelationshipType relType : MyRelationshipTypes.values()) {            
            if(existsRelFromAToB(a, b, Direction.OUTGOING, relType))
                return relType;
        }

        return null;
    }
    
    
    public static Node[] getNodesWithRelationWithNodeB(Node[] allNodes, Node b) {

        if(allNodes == null)
            return null;

        ArrayList<Node> response = new ArrayList<Node>();
        int added = 0;

        for(int i = 0; i < allNodes.length; i++) {
            if(existsRelFromAToB(allNodes[i], b, Direction.OUTGOING, null)) {
                response.add(allNodes[i]);
                added++;
            }else if(existsRelFromAToB(allNodes[i], b, Direction.INCOMING, null)) {
                response.add(allNodes[i]);
                added++;
            }
        }

        if(added > 0)
            return response.toArray(new Node[0]);
        else
            return null;

    }


    

    public static List<Node> getNodes(Node a, Direction direction, RelationshipType type) {

        ArrayList<Node> response = new ArrayList<Node>();
        Iterable<Relationship> relationships;

        if (type != null) {
            relationships = a.getRelationships(type, direction);
        } else {
            relationships = a.getRelationships(direction);
        }

        Iterator<Relationship> iterator = relationships.iterator();
        while (iterator.hasNext()) {

            Relationship next = iterator.next();
            response.add(next.getOtherNode(a));
        }

        return response;

    }


    public static List<Node> remove(List<Node> list, Node node) {
        List<Node> response = list;
        response.remove(node);
        return response;
    }
    
    public static List<String> removeString(List<String> list, String node) {
        List<String> response = list;
        response.remove(node);
        return response;
    }
        
    /*
    public static List<String> getRels(Node node, Direction direction, RelationshipType relType ) {
        
        List<String> response = new ArrayList<String>();
        
        Iterable<Relationship> relationships = node.getRelationships(relType, direction);
        for(Relationship rel : relationships ) {
            Node other = rel.getOtherNode(node);
            response.add(other.getProperty("id").toString());
        }
        
        return response;
    }       
*/
    public static double roundTwoDecimals(int nominator, int denominator) {

        double d = (double)nominator/(double)denominator;
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));

    }


    public static ArrayList<Node> getNodeArrayList(Node a, Direction direction, RelationshipType type) {

        ArrayList<Node> response = new ArrayList<Node>();
        Iterable<Relationship> relationships;

        if (type != null) {
            relationships = a.getRelationships(type, direction);
        } else {
            relationships = a.getRelationships(direction);
        }

        Iterator<Relationship> iterator = relationships.iterator();
        while (iterator.hasNext()) {

            Relationship next = iterator.next();
            response.add(next.getOtherNode(a));
        }

        return response;

    }




}
