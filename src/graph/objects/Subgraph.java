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
public class Subgraph {
 
    private List<String> vertices;
    private List<EdgeInfo> edges;

    public Subgraph() {
    }

    public List<String> getVertices() {
        return vertices;
    }

    public void setVertices(List<String> vertices) {
        this.vertices = vertices;
    }

    public List<EdgeInfo> getEdges() {
        return edges;
    }

    public void setEdges(List<EdgeInfo> edges) {
        this.edges = edges;
    }        
    
}
