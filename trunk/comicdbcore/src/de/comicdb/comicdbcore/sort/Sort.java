/*
 * Sort.java
 *
 * Created on 17. Januar 2007, 21:49
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.comicdb.comicdbcore.sort;

import java.io.Serializable;

/**
 *
 * @author dm
 */
public class Sort implements Serializable {
    
    public static int ASC = 1;
    public static int DESC = -1;
    
    private String property;
    private int order = ASC;
    
    /** Creates a new instance of Sort */
    public Sort(String property, int order) {
        setProperty(property);
        setOrder(order);
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
    
}
