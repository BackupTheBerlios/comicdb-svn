/*
 * StoryList.java
 *
 * Created on 24. Dezember 2006, 12:38
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.comicdb.comicdbcore.bean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 *
 * @author dm
 */
public class StoryList extends ArrayList {
    
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    /** Creates a new instance of StoryList */
    public StoryList() {
        super();
    }
    
    public void add(Story story) {
        super.add(story);
        support.firePropertyChange("add", false, true);
    }
    
    public Object remove(int i) {
        Object ret = super.remove(i);
        support.firePropertyChange("remove", false, true);
        return ret;
    }

    public boolean remove(Story story) {
        boolean ret = super.remove(story);
        support.firePropertyChange("remove", false, true);
        return ret;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
    
}
