/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graph.objects;

import org.neo4j.graphdb.RelationshipType;

/**
 *
 * @author Thanos
 */
public enum MyRelationshipTypes implements RelationshipType {
    LIKES, 
    HATES,
}
