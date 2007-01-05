/*
 * State.java
 *
 * Created on 5. Januar 2007, 14:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.comicdb.comicdbcore.bean;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.NbBundle;

/**
 *
 * @author dm
 */
public class State {
    
    public final static State STATE_SEARCH = new State("STATE_SEARCH");
    public final static State STATE_NECESSARY = new State("STATE_NECESSARY");
    public final static State STATE_ORDER = new State("STATE_ORDER");
    public final static State STATE_MY_OWN = new State("STATE_MY_OWN");
    
    private String bundleName = null;
    /** Creates a new instance of State */
    public State(String bundleName) {
        setBundleName(bundleName);
    }

    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }
    
    public String toString() {
        return NbBundle.getMessage(State.class, getBundleName());
    }
    
    public static List getStates() {
        List<State> ret = new ArrayList<State>();
        
        ret.add(STATE_SEARCH);
        ret.add(STATE_NECESSARY);
        ret.add(STATE_ORDER);
        ret.add(STATE_MY_OWN);
                
        return ret;
    }
    
}
