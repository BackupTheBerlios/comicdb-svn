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
import de.comicdb.comicdbcore.sort.Sort;
import de.comicdb.comicdbcore.util.PropertyComparator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.swing.ImageIcon;

/**
 *
 * @author dm
 */
public class Publisher extends Model implements Serializable {
    
    private String name;
    private String url;
    private ImageIcon image;
    private Date modified = new Date();
    
    private List<Serie> series = new ArrayList<Serie>();
    
    private static final long serialVersionUID = -8855028506146192215L;
    
    /** Creates a new instance of Vendor */
    public Publisher() {
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        String oldValue = this.name;
        this.name = name;
        firePropertyChange("name", oldValue, this.name);
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        String oldValue = this.url;
        this.url = url;
        firePropertyChange("url", oldValue, this.url);
    }
    
    public ImageIcon getImage() {
        return image;
    }
    
    public void setImage(ImageIcon icon) {
        ImageIcon oldValue = this.image;
        this.image = icon;
        firePropertyChange("image", oldValue, this.image);
    }
    
    public List<Serie> getSeries() {
        return series;
    }
    
    public void setSeries(List<Serie> series) {
        this.series = series;
    }
    
    public SortedSet<Serie> getSerieSet(Sort sort) {
        SortedSet<Serie> ret = new TreeSet<Serie>(new PropertyComparator(sort));
        ret.addAll(getSeries());
        return ret;
    }
    
    public Date getModified() {
        return modified;
    }
    
    public void setModified(Date modified) {
        this.modified = modified;
    }
}
