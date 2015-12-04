/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.objects;

import java.io.Serializable;

/**
 *
 * @author Thanos
 */
public class KeyValuePair implements Serializable, Comparable{

    private String key;   
    private int value;

    public KeyValuePair() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Object obj) {
              
        if (!(obj instanceof KeyValuePair)) {
            throw new ClassCastException("An KeyValuePair object expected.");
        }

        int otherScore = ((KeyValuePair) obj).getValue();
        if(this.value > otherScore) {
            return -1;
        }
        else {
            return 1;
        }
    }

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (this.key != null ? this.key.hashCode() : 0);
        hash = 29 * hash + this.value;
        return hash;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final KeyValuePair other = (KeyValuePair) obj;
        if (!this.key.equals(other.key)) {
            return false;
        }
        if (this.value != other.value) {
            return false;
        }
        return true;
    }

    
    
}
