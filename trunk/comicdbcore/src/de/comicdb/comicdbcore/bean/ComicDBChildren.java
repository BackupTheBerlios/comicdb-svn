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

import java.io.Serializable;
import org.openide.nodes.Children;
import org.openide.nodes.Node;


/**
 *
 * @author dm
 */
public class ComicDBChildren extends Children.Keys implements Serializable{
    
    private ComicDB comicDB;
    /** Creates a new instance of ComicDBNode */
    public ComicDBChildren(ComicDB comicDB) {
        setComicDB(comicDB);
    }
    
    protected Node[] createNodes(Object key) {
        Publisher publisher = (Publisher) key;
        return new Node[] { new PublisherNode( publisher ) };
    }
    
    protected void addNotify() {
        super.addNotify();
        setKeys(getComicDB().getPublisher()); 
    }

    public ComicDB getComicDB() {
        return comicDB;
    }

    public void setComicDB(ComicDB comicDB) {
        this.comicDB = comicDB;
    }
}
