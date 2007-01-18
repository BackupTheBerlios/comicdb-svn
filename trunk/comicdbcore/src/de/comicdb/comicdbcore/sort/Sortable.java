/*
 * Sortable.java
 *
 * Created on 16. Januar 2007, 23:31
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.comicdb.comicdbcore.sort;

/**
 *
 * @author dm
 */
public interface Sortable {
    
    public void updateChildren();
    
    public void setSort(Sort sort);
    
    public SortableItem[] getSortableItems();
}
