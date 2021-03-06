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

import de.comicdb.comicdbcore.sort.Sort;
import de.comicdb.comicdbcore.util.PropertyComparator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author dm
 */
public class ComicDB implements Serializable {
    
    private String file;
    
    private List<Publisher> publisher = new ArrayList<Publisher>();
    
    private static final long serialVersionUID = -1767772641631649840L;
    /** Creates a new instance of ComicDBFile */
    public ComicDB() {
    }
    
    public String getFile() {
        return file;
    }
    
    public void setFile(String file) {
        this.file = file;
    }
    
    public List<Publisher> getPublisher() {
        return publisher;
    }
    
    public void setPublisher(List<Publisher> publisher) {
        this.publisher = publisher;
    }

    public SortedSet<Publisher> getPublisherSet(Sort sort) {
        SortedSet<Publisher> ret = new TreeSet<Publisher>(new PropertyComparator(sort));
        ret.addAll(getPublisher());
        return ret;
    }
    
}
