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

import de.comicdb.comicdbcore.util.ImageUtil;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
//        setDisplayName(comic.getName() + " " + comic.getNr().toString());
        
        setIconBaseWithExtension("de/comicdb/comicdbcore/cdb_16x16.png");
        setComic(comic);
        fireDisplayNameChange(null, getHtmlDisplayName());
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
        children.getSerie().getComics().remove(getComic());
        children.remove(new Node[] {this});
    }
    
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
//        setDisplayName(comic.getName() + " " + comic.getNr().toString());
        fireShortDescriptionChange(null, getShortDescription());
        fireDisplayNameChange(null, getHtmlDisplayName());
    }
    
    public String getShortDescription() {
        StringBuffer ret = new StringBuffer("<html><body");
        ret.append("<TABLE width=\"100%\" cellspacing=\"1\" border=\"0\" cellpadding=\"0\" align=\"center\">");
        ret.append("<TR>");
        ret.append("<TD align=\"center\"><b>");
        ret.append(getHTMLName());
        ret.append("</b></TD>");
        ret.append("<TD rowspan=\"5\">");
        if (getComic().getImage() != null) {
            File img = new File(getComic().getImage().getDescription());
            if (!img.exists()) {
                img = ImageUtil.createTempImage(getComic().getImage(), img);
            }
            if( img != null) {
                ret.append("<img src=\"file:");
                ret.append(img.getAbsolutePath());
                ret.append("\">");
            }
        } else {
            ret.append("&nbsp;");
        }
        ret.append("</TD>");
        ret.append("</TR>");
        
        ret.append("<TR><TD>&nbsp;</TD></TR>");
        
        ret.append("<TR><TD>");
        ret.append(NbBundle.getMessage(ComicNode.class, "LBL_COMIC_STATE") + " " + getComic().getState());
        ret.append("</TD></TR>");
        
        DecimalFormat def = new DecimalFormat("##0.00");
        ret.append("<TR><TD>");
        ret.append(NbBundle.getMessage(ComicNode.class, "LBL_COMIC_COVER_PRICE") + " " + def.format(getComic().getCoverprice().doubleValue()));
        ret.append("</TD></TR>");
        
        DateFormat df = new SimpleDateFormat(java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("format.date"));
        ret.append("<TR><TD>");
        ret.append(NbBundle.getMessage(ComicNode.class, "LBL_COMIC_PAYDATE") + " " + df.format(getComic().getPaydate()));
        ret.append("</TD></TR>");
        
        ret.append("<TR><TD>");
        ret.append(NbBundle.getMessage(ComicNode.class, "LBL_COMIC_STORIES_QUANTITY") + " " + getComic().getStorys().size());
        ret.append("</TD></TR>");
        
        ret.append("<TR><TD colspan=\"2\">&nbsp;</TD></TR>");
        
        if (getComic().getNotes() != null) {
            ret.append("<TR><TD colspan=\"2\">");
            ret.append(getComic().getNotes());
            ret.append("</TD></TR>");
        }
        ret.append("</TABLE>");
        
        ret.append("</body></html>");
        return ret.toString();
    }
    
    public String getHtmlDisplayName() {
        
        StringBuffer ret = new StringBuffer("<html>");
        ret.append(getHTMLName());
        ret.append("</html>");
        return ret.toString();
//        return comic.getName() + " " + comic.getNr().toString();
    }
    
    private String getHTMLName() {
        String name = comic.getName() + " " + comic.getNr().toString();
        StringBuffer ret = new StringBuffer();
        if (comic.getState() != null) {
            if (comic.getState().equals(State.STATE_SEARCH)) {
                ret.append("<font color=\"#FFFF00\">" + name + "</font>");
            } else if (comic.getState().equals(State.STATE_NECESSARY)) {
                ret.append("<font color=\"#FF0000\">" + name + "</font>");
            } else if (comic.getState().equals(State.STATE_ORDER)) {
                ret.append("<font color=\"#0000FF\">" + name + "</font>");
            } else if (comic.getState().equals(State.STATE_MY_OWN)) {
                ret.append(name);
            } else {
                ret.append(name);
            }
        } else {
            ret.append(name);
        }
        return ret.toString();
    }
}
