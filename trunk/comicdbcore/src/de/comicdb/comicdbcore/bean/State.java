/*
 * State.java
 *
 * Created on 5. Januar 2007, 14:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.comicdb.comicdbcore.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.NbBundle;

/**
 *
 * @author dm
 */

public class State implements Serializable {
    
    public final static State STATE_SEARCH = new State(new Integer(0),"STATE_SEARCH");
    public final static State STATE_NECESSARY = new State(new Integer(1),"STATE_NECESSARY");
    public static final State STATE_ORDER = new State(new Integer(2),"STATE_ORDER");
    public final static State STATE_MY_OWN = new State(new Integer(3),"STATE_MY_OWN");
    
    private String bundleName = null;
    private Integer index = null;
    private static final long serialVersionUID = 344L;
    /** Creates a new instance of State */
    public State() {
        
    }
    
    public State(Integer index,String bundleName) {
        super();
        setIndex(index);
        setBundleName(bundleName);
    }

    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }
    
    public static List getStates() {
        List<State> ret = new ArrayList<State>();
        
        ret.add(STATE_SEARCH);
        ret.add(STATE_NECESSARY);
        ret.add(STATE_ORDER);
        ret.add(STATE_MY_OWN);
                
        return ret;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String toString() {
        return NbBundle.getMessage(State.class, getBundleName());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof State))
            return false;
        
        State st = (State)obj;
        if (getIndex() == null || st.getIndex() == null )
            return false;
        
        return getIndex().intValue() == st.getIndex().intValue();
    }
    
    
    
}
