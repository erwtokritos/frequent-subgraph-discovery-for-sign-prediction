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
public class IntegerPair implements Serializable, Comparable{

    private int key;   
    private int value;

    public IntegerPair() {
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
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
              
        if (!(obj instanceof IntegerPair))
            throw new ClassCastException("An IntegerPairRanked object expected.");

        int otherScore = ((IntegerPair) obj).getValue();
        if(this.value > otherScore)
            return -1;
        else
            return 1;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.key;
        hash = 13 * hash + this.value;
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
        final IntegerPair other = (IntegerPair) obj;
        if (this.key != other.key) {
            return false;
        }
        if (this.value != other.value) {
            return false;
        }
        return true;
    }

    
    
}
