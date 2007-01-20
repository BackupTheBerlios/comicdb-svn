/*
 * ComicWizardBean.java
 *
 * Created on 19. Januar 2007, 23:36
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.comicdb.comicdbcore;

/**
 *
 * @author dm
 */
public class ComicWizardBean {
    
    private String name;
    
    private boolean oneSelected = true;
    private int nr;
    
    private int nrfrom;
    private int nrto;
    
    
    /** Creates a new instance of ComicWizardBean */
    public ComicWizardBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOneSelected() {
        return oneSelected;
    }

    public void setOneSelected(boolean oneSelected) {
        this.oneSelected = oneSelected;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public int getNrfrom() {
        return nrfrom;
    }

    public void setNrfrom(int nrfrom) {
        this.nrfrom = nrfrom;
    }

    public int getNrto() {
        return nrto;
    }

    public void setNrto(int nrto) {
        this.nrto = nrto;
    }
    
}
