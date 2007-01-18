/*
 * SortableItem.java
 *
 * Created on 17. Januar 2007, 00:26
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.comicdb.comicdbcore.sort;

/**
 *
 * @author dm
 */
public class SortableItem {
    private String property;
    private String menuName;
    private int order = 0;
    /** Creates a new instance of SortableItem */
    public SortableItem(String property, String menuName, int order) {
        setProperty(property);
        setMenuName(menuName);
        setOrder(order);
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
    
}
