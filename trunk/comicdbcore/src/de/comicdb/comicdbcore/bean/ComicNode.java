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

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.openide.actions.CopyAction;
import org.openide.actions.CutAction;
import org.openide.actions.DeleteAction;
import org.openide.actions.PasteAction;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.NbBundle;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author dm
 */
public class ComicNode extends AbstractNode implements PropertyChangeListener {
    
    private static ResourceBundle bundle = NbBundle.getBundle(ComicNode.class);
    private Comic comic;
    /** Creates a new instance of ComicDBNode */
    public ComicNode(Comic comic) {
        super( Children.LEAF, Lookups.singleton(comic) );
        setDisplayName(comic.getName() + " " + comic.getNr().toString());
        setIconBaseWithExtension("de/comicdb/comicdbcore/cdb_16x16.png");
        setComic(comic);
    }
        
    public Action[] getActions(boolean popup) {
        return new Action[] {
            getPreferredAction(),
            null,
            SystemAction.get( CopyAction.class ),
            SystemAction.get( CutAction.class ),
            SystemAction.get( PasteAction.class),
            null,
            SystemAction.get( DeleteAction.class ) };
    }

    public Action getPreferredAction() {
        return new AbstractAction(NbBundle.getMessage(ComicNode.class,"LBL_Show")) {
            public void actionPerformed(ActionEvent e) {
                ComicTopComponent.getDefault().setComic(getComic());
                if (!ComicTopComponent.getDefault().isOpened())
                    ComicTopComponent.getDefault().open();
                ComicTopComponent.getDefault().requestActive();
            }
        };
    }

    public Comic getComic() {
        return comic;
    }

    public void setComic(Comic comic) {
        if (this.comic != null)
            this.comic.removePropertyChangeListener(this);
        this.comic = comic;
        if (comic != null)
            comic.addPropertyChangeListener(this);
        
    }

    public boolean canDestroy() {
        return true;
    }

    public void destroy() throws IOException {
        ComicChildren children = (ComicChildren)getParentNode().getChildren();
        children.remove(new Node[] {this});
        children.getSerie().getComics().remove(getComic());
        children.addNotify();
    }

    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        setDisplayName(comic.getName() + " " + comic.getNr().toString());
        fireShortDescriptionChange(null, getShortDescription());
    }

    public String getShortDescription() {
        String ret = "<html>";
        
        ret += "<b>" + getDisplayName() + "</b><br>";
        StoryList storys = getComic().getStorys();
        if (storys != null && storys.size() > 0) {
            ret += "<br>"+ NbBundle.getMessage(ComicNode.class, "LBL_Story") + "<br><table>";
            for (int i = 0; i < storys.size(); i++) {
                ret += "<tr>";
                ret += "<td>" + ((Story)storys.get(i)).getName() + "</td>";
                ret += "<td>" + ((Story)storys.get(i)).getAuthor() + "</td>";
                ret += "<td>" + ((Story)storys.get(i)).getPainter() + "</td>";
                ret += "</tr>";
            }
            ret += "</table><br>";
        }
        
        if (getComic().getNotes() != null)
            ret += "<br><br>"  + getComic().getNotes();
        
        return ret+="</html>";
    }
}
