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
public class Serie extends Model implements Serializable {
    
    private String name;
    private ImageIcon image;
    private Date modified = new Date();
    
    private List<Comic> comics = new ArrayList<Comic>();
    
    private static final long serialVersionUID = -6305804902243332896L;
    /** Creates a new instance of Serie */
    public Serie() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldValue = this.name;
        this.name = name;
        firePropertyChange("name", oldValue, this.name);
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        ImageIcon oldValue = this.image;
        this.image = image;
        firePropertyChange("image", oldValue, this.image);
    }

    public List<Comic> getComics() {
        return comics;
    }
    
    public SortedSet<Comic> getComicSet(Sort sort) {
        SortedSet<Comic> ret = new TreeSet<Comic>(new PropertyComparator(sort));
        ret.addAll(getComics());
        return ret;
    }

    public void setComics(List<Comic> comics) {
        this.comics = comics;
    }
    
    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
