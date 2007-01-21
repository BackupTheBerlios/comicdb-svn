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
import org.openide.util.NbBundle;

/**
 *
 * @author dm
 */
public class ComicChildren extends Children.Keys implements Serializable, Sortable{
    private Serie serie;
    /** Creates a new instance of ComicDBNode */
    public ComicChildren(Serie serie) {
        setSerie(serie);
    }
    
    protected Node[] createNodes(Object key) {
        Comic obj = (Comic) key;
        return new Node[] { new ComicNode( obj ) };
    }
    
    public void addNotify() {
        super.addNotify();
        updateChildren();
    }
    
    public void updateChildren() {
        ComicDBOptionUtil util = new ComicDBOptionUtil();
        ComicDBOption options = util.retrieveSetting();
        // compatible to older versions
        if (options.getComicSort() == null)
            options.setComicSort(new Sort("nr", Sort.ASC));
        
        setKeys(serie.getComicSet(options.getComicSort()).toArray());
    }
    
    public void setSort(Sort sort) {
        ComicDBOptionUtil util = new ComicDBOptionUtil();
        ComicDBOption options = util.retrieveSetting();
        options.setComicSort(sort);
        util.storeSetting(options);
    }
    
    public SortableItem[] getSortableItems() {
        ComicDBOptionUtil util = new ComicDBOptionUtil();
        ComicDBOption options = util.retrieveSetting();
        Sort sort = options.getComicSort();
        return new SortableItem[] {
            new SortableItem("nr", NbBundle.getMessage(getClass(), "Comic.nr"), sort.getProperty().equalsIgnoreCase("nr") ? sort.getOrder() : 0),
            new SortableItem("name", NbBundle.getMessage(getClass(), "Comic.name"), sort.getProperty().equalsIgnoreCase("name") ? sort.getOrder() : 0),
            new SortableItem("coverprice", NbBundle.getMessage(getClass(), "Comic.coverprice"), sort.getProperty().equalsIgnoreCase("coverprice") ? sort.getOrder() : 0),
            new SortableItem("price", NbBundle.getMessage(getClass(), "Comic.price"), sort.getProperty().equalsIgnoreCase("price") ? sort.getOrder() : 0),
            new SortableItem("cost", NbBundle.getMessage(getClass(), "Comic.cost"), sort.getProperty().equalsIgnoreCase("cost") ? sort.getOrder() : 0),
            new SortableItem("quantity", NbBundle.getMessage(getClass(), "Comic.quantity"), sort.getProperty().equalsIgnoreCase("quantity") ? sort.getOrder() : 0),
            new SortableItem("pagecount", NbBundle.getMessage(getClass(), "Comic.pagecount"), sort.getProperty().equalsIgnoreCase("pagecount") ? sort.getOrder() : 0),
            new SortableItem("coverdate", NbBundle.getMessage(getClass(), "Comic.coverdate"), sort.getProperty().equalsIgnoreCase("coverdate") ? sort.getOrder() : 0),
            new SortableItem("paydate", NbBundle.getMessage(getClass(), "Comic.paydate"), sort.getProperty().equalsIgnoreCase("paydate") ? sort.getOrder() : 0),
            new SortableItem("modified", NbBundle.getMessage(getClass(), "Comic.modified"), sort.getProperty().equalsIgnoreCase("modified") ? sort.getOrder() : 0)
        };
    }
    
    public Serie getSerie() {
        return serie;
    }
    
    public void setSerie(Serie serie) {
        this.serie = serie;
    }
}
