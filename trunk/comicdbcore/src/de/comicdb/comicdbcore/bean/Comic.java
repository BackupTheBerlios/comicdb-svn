/**
 * ComicDB - overview you comics
 * Copyright (C) 2006  Daniel Moos
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51 Franklin
 * St, Fifth Floor, Boston, MA 02110, USA
 */
package de.comicdb.comicdbcore.bean;

import com.jgoodies.binding.beans.Model;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author dm
 */
public class Comic extends Model implements Serializable, PropertyChangeListener {
    private String name;
    private String covertype;
    private String notes;
    
    private Double coverprice = new Double(0.0);
    private Double price = new Double(0.0);
    private Double cost = new Double(0.0);
    
    private Integer nr = new Integer(1);
    private Integer pagecount = new Integer(0);
    private Integer quantity = new Integer(1);
    private Integer condition = new Integer(1);
    
    private Date coverdate = new Date();
    private Date paydate = new Date();
    private Date modified = new Date();
    
    
    private ImageIcon image;
    
    private StoryList storys = new StoryList();
    
    /** Creates a new instance of Comic */
    public Comic() {
        super();
        storys.addPropertyChangeListener(this);
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        String oldValue = this.name;
        this.name = name;
        firePropertyChange("name", oldValue, this.name);
    }
    
    public String getCovertype() {
        return covertype;
    }
    
    public void setCovertype(String covertype) {
        String oldValue = this.covertype;
        this.covertype = covertype;
        firePropertyChange("covertype", oldValue, this.covertype);
    }
    
    public Double getCoverprice() {
        return coverprice;
    }
    
    public void setCoverprice(Double coverprice) {
        Double oldValue = this.coverprice;
        this.coverprice = coverprice;
        firePropertyChange("coverprice", oldValue, this.coverprice);
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        Double oldValue = this.price;
        this.price = price;
        firePropertyChange("price", oldValue, this.price);
    }
    
    public Double getCost() {
        return cost;
    }
    
    public void setCost(Double cost) {
        Double oldValue = this.cost;
        this.cost = cost;
        firePropertyChange("cost", oldValue, this.cost);
    }
    
    public Integer getNr() {
        return nr;
    }
    
    public void setNr(Integer nr) {
        Integer oldValue = this.nr;
        this.nr = nr;
        firePropertyChange("nr", oldValue, this.nr);
    }
    
    public Integer getPagecount() {
        return pagecount;
    }
    
    public void setPagecount(Integer pagecount) {
        Integer oldValue = this.pagecount;
        this.pagecount = pagecount;
        firePropertyChange("pagecount", oldValue, this.pagecount);
    }
    
    public Date getCoverdate() {
        return coverdate;
    }
    
    public void setCoverdate(Date coverdate) {
        Date oldValue = this.coverdate;
        this.coverdate = coverdate;
        firePropertyChange("coverdate", oldValue, coverdate);
    }
    
    public Date getPaydate() {
        return paydate;
    }
    
    public void setPaydate(Date paydate) {
        Date oldValue = this.paydate;
        this.paydate = paydate;
        firePropertyChange("paydate", oldValue, this.paydate);
    }
    
    public Date getModified() {
        return modified;
    }
    
    public void setModified(Date modified) {
        this.modified = modified;
    }
    
    public ImageIcon getImage() {
        return image;
    }
    
    public void setImage(ImageIcon image) {
        ImageIcon oldValue = this.image;
        this.image = image;
        firePropertyChange("image", oldValue, this.image);
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        Integer oldValue = this.quantity;
        this.quantity = quantity;
        firePropertyChange("quantity", oldValue, quantity);
    }
    
    public Integer getCondition() {
        return condition;
    }

    public void setCondition(Integer condition) {
        Integer oldValue = this.condition;
        this.condition = condition;
        firePropertyChange("condition", oldValue, this.condition);
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        String oldValue = this.notes;
        this.notes = notes;
        firePropertyChange("notes", oldValue, this.notes);
    }

    public StoryList getStorys() {
        return storys;
    }

    public void setStorys(StoryList storys) {
        if (this.storys != null)
            this.storys.removePropertyChangeListener(this);
        
        this.storys = storys;
        
        if (this.storys != null)
            this.storys.addPropertyChangeListener(this);
    }
        
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource() instanceof StoryList)
            firePropertyChange("storys", null, getStorys());
    }
}
