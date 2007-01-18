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

import de.comicdb.comicdbcore.options.ComicDBOption;
import de.comicdb.comicdbcore.options.ComicDBOptionUtil;
import de.comicdb.comicdbcore.sort.Sort;
import de.comicdb.comicdbcore.sort.Sortable;
import de.comicdb.comicdbcore.sort.SortableItem;
import java.io.Serializable;
import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 *
 * @author dm
 */
public class SerieChildren extends Children.Keys implements Serializable, Sortable{
    
    private Publisher publisher;
    /** Creates a new instance of ComicDBNode */
    public SerieChildren(Publisher publisher) {
        setPublisher(publisher);
    }
    
    protected Node[] createNodes(Object key) {
        Serie serie = (Serie) key;
        return new Node[] { new SerieNode( serie ) };
    }
    
    protected void addNotify() {
        super.addNotify();
        updateChildren();
    }

    public void updateChildren() {
        ComicDBOptionUtil util = new ComicDBOptionUtil();
        ComicDBOption options = util.retrieveSetting();
        // compatible to older versions
        if (options.getSerieSort() == null)
            options.setSerieSort(new Sort("name", Sort.ASC));
        
        setKeys(publisher.getSerieSet(options.getSerieSort()).toArray());
    }
    
    public void setSort(Sort sort) {
        ComicDBOptionUtil util = new ComicDBOptionUtil();
        ComicDBOption options = util.retrieveSetting();
        options.setSerieSort(sort);
        util.storeSetting(options);
    }
    
    public SortableItem[] getSortableItems() {
        ComicDBOptionUtil util = new ComicDBOptionUtil();
        ComicDBOption options = util.retrieveSetting();
        Sort sort = options.getSerieSort();
        return new SortableItem[] {
            new SortableItem("name", "name", sort.getOrder())
        };
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
