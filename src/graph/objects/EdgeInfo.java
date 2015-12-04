/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.objects;

import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;

/**
 *
 * @author thanos
 */
public class EdgeInfo {
    
    private String source;
    private String target;
    private RelationshipType type;

    public EdgeInfo(String source, String target, RelationshipType type) {
        this.source = source;
        this.target = target;
        this.type = type;
    }
           
    public EdgeInfo(Relationship relationship) {
        this.source = relationship.getStartNode().getProperty(Constants.ID_KEY).toString();
        this.target = relationship.getEndNode().getProperty(Constants.ID_KEY).toString();
        this.type = relationship.getType();                    
    }
    
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarger(String targer) {
        this.target = targer;
    }

    public RelationshipType getType() {
        return type;
    }

    public void setType(RelationshipType type) {
        this.type = type;
    }        

    @Override
    public String toString() {
        return "EdgeInfo{" + "source=" + source + ", target=" + target + ", type=" + type.name() + '}';
    }

}
