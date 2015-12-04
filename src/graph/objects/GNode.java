/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.objects;

import graph.functions.Methods;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


/**
 *
 * @author thanos
 */
public class GNode {
    
    private int id;
    private List<Integer> inPositive;
    private List<Integer> inNegative;
    private List<Integer> outPositive;
    private List<Integer> outNegative;
    private HashMap<Integer, List<Relation>> indexer;

    public GNode(int id) {
        
        this.id = id;
       
        this.inPositive = new ArrayList<Integer>();
        this.inNegative = new ArrayList<Integer>();        
        this.outPositive = new ArrayList<Integer>();
        this.outNegative = new ArrayList<Integer>();
        indexer = new HashMap<Integer, List<Relation>>();                
    }
    
    
       
    public int getId() {
        return id;
    }
    
    public void addPositiveDestination(int otherId) {
        this.outPositive.add(otherId);
        fixIndexer(otherId, Relation.OUT_POS);
    }
    
    public void addPositiveInitiator(int otherId) {
        this.inPositive.add(otherId);
        fixIndexer(otherId, Relation.IN_POS);
    }    

    public void addNegativeDestination(int otherId) {
        this.outNegative.add(otherId);
        fixIndexer(otherId, Relation.OUT_NEG);
    }
    
    public void addNegativeInitiator(int otherId) {
        this.inNegative.add(otherId);
        fixIndexer(otherId, Relation.IN_NEG);
    }    
    
    
    public List<Relation> toGNode(GNode other) {
                
        return indexer.get(other.id);                
    }
    
    
        
    public Map<Integer, List<Relation>> toGNodes(List<Integer> others) {
                
        Map<Integer, List<Relation>> list = new HashMap<Integer, List<Relation>>(others.size());
        for(int otherId : others) {
            List<Relation> get = indexer.get(otherId);
            if(get != null) {
                list.put(otherId, get);
            }            
        }       
        return list;                       
    }
    
    
    public List<Integer> sweepNeighborIds() {
        
        
        int count = inNegative.size() + inPositive.size() + outNegative.size() + outPositive.size();
        HashSet<Integer> set = new HashSet<Integer>(count);
       
        for(int t : inNegative) {
            set.add(t);
        }
                
        for(int t : inPositive) {
             set.add(t);
        }
        
        for(int t : outNegative) {
             set.add(t);
        }
                
        for(int t : outPositive) {
             set.add(t);
        }     
        
        List<Integer> res = new ArrayList<Integer>(set);
        return res;
        
    }
    
    
    public List<Integer> commonNeighbours(GNode other) {
        
        List<Integer> myIds = sweepNeighborIds();
        List<Integer> otherIds = other.sweepNeighborIds();
        
        return Methods.common(myIds, otherIds);
    }

    private void fixIndexer(int otherId, Relation relation) {
        
        List<Relation> get = indexer.get(otherId);
        if(get == null) {
            get = new ArrayList<Relation>();
        }
        
        if(!get.contains(relation)) {
            
            get.add(relation);
        }
        
        indexer.put(otherId, get);
    }
    
    
    public enum Relation {
        IN_POS,
        IN_NEG,
        OUT_POS,
        OUT_NEG
    }
}
