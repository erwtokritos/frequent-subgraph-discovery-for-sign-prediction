/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.objects;

import java.util.List;

/**
 *
 * @author thanos
 */
public class GSubgraph {
 
    private List<Integer> vertices;
    private List<EdgeInfo> edges;

    public GSubgraph() {
    }

    public List<Integer> getVertices() {
        return vertices;
    }

    public void setVertices(List<Integer> vertices) {
        this.vertices = vertices;
    }

    public List<EdgeInfo> getEdges() {
        return edges;
    }

    public void setEdges(List<EdgeInfo> edges) {
        this.edges = edges;
    }        
    
}
