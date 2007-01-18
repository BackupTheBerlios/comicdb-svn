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

import de.comicdb.comicdbcore.sort.SortAction;
import de.comicdb.comicdbcore.util.CopyUtil;
import de.comicdb.comicdbcore.util.ImageUtil;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.actions.CopyAction;
import org.openide.actions.CutAction;
import org.openide.actions.DeleteAction;
import org.openide.actions.NewAction;
import org.openide.actions.PasteAction;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Node;
import org.openide.nodes.NodeTransfer;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.SystemAction;
import org.openide.util.datatransfer.NewType;
import org.openide.util.datatransfer.PasteType;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author dm
 */
public class PublisherNode extends AbstractNode implements PropertyChangeListener {
    
    private static ResourceBundle bundle = NbBundle.getBundle(PublisherNode.class);
    private Publisher publisher;
    
    /** Creates a new instance of ComicDBNode */
    public PublisherNode(Publisher publisher) {
        super( new SerieChildren(publisher), Lookups.singleton(publisher) );
        setPublisher(publisher);
        setDisplayName(publisher.getName());
        setIconBaseWithExtension("de/comicdb/comicdbcore/cdb_16x16.png");
    }
    
    public Action[] getActions(boolean popup) {
        return new Action[] {
            getPreferredAction(),
            null,
            SystemAction.get( NewAction.class),
            null,
            SystemAction.get( SortAction.class),
            null,
            SystemAction.get( CopyAction.class ),
            SystemAction.get( CutAction.class ),
            SystemAction.get( PasteAction.class),
            null,
            SystemAction.get( DeleteAction.class ) };
    }
    
    public Action getPreferredAction() {
        return new AbstractAction(NbBundle.getMessage(PublisherNode.class, "LBL_Show")) {
            public void actionPerformed(ActionEvent e) {
                PublisherTopComponent.getDefault().setPublisher(getPublisher());
                if (!PublisherTopComponent.getDefault().isOpened())
                    PublisherTopComponent.getDefault().open();
//                ComicTopComponent.getDefault().toFront();
                PublisherTopComponent.getDefault().requestActive();
            }
        };
    }
    
    public NewType[] getNewTypes() {
        
        return new NewType[] { new NewType() {
            public String getName() {
                return bundle.getString("LBL_NewSerie");
            }
            public HelpCtx getHelpCtx() {
                return new HelpCtx("org.myorg.systemproperties");
            }
            public void create() throws IOException {
                String title = bundle.getString("LBL_NewSerie_dialog");
                String msg = bundle.getString("MSG_NewSerie_dialog");
                NotifyDescriptor.InputLine desc = new NotifyDescriptor.InputLine(msg, title);
                DialogDisplayer.getDefault().notify(desc);
                String name = desc.getInputText();
                
                if (name.length() == 0) return;
                
                Serie serie = new Serie();
                serie.setName(name);
                SerieChildren children = (SerieChildren)getChildren();
                children.getPublisher().getSeries().add(serie);
                children.addNotify();
//                boolean added = children.add(new Node[]{
//                    new SerieNode(serie)
//                });
//                if (added) {
                    SerieTopComponent.getDefault().setSerie(serie);
                    if (!SerieTopComponent.getDefault().isOpened())
                        SerieTopComponent.getDefault().open();
                    SerieTopComponent.getDefault().requestActive();
                    fireShortDescriptionChange(null, getShortDescription());
//                }
            }
        } };
    }
    
    public boolean canDestroy() {
        return true;
    }
    
    public boolean canCopy() {
        return true;
    }
    
    public void destroy() throws IOException {
        ComicDBChildren children = (ComicDBChildren)getParentNode().getChildren();
        children.getComicDB().getPublisher().remove(publisher);
        children.remove(new Node[] {this});
//        children.addNotify();
    }
    
    public Publisher getPublisher() {
        return publisher;
    }
    
    public void setPublisher(Publisher publisher) {
        if (this.publisher != null)
            this.publisher.removePropertyChangeListener(this);
        this.publisher = publisher;
        if (publisher != null)
            publisher.addPropertyChangeListener(this);
    }
    
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        setDisplayName(publisher.getName());
        fireShortDescriptionChange(null, getShortDescription());
    }
    
    public String getShortDescription() {
        StringBuffer ret = new StringBuffer("<html><body");
        ret.append("<TABLE width=\"100%\" cellspacing=\"1\" border=\"0\" cellpadding=\"0\" align=\"center\">");
        ret.append("<TR>");
        ret.append("<TD align=\"center\"><b>");
        ret.append(getDisplayName());
        ret.append("</b></TD>");
        ret.append("<TD rowspan=\"3\">");
        if (getPublisher().getImage() != null) {
            File img = new File(getPublisher().getImage().getDescription());
            if (!img.exists()) {
                img = ImageUtil.createTempImage(getPublisher().getImage(), img);
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
        
        ret.append("<TR><TD valign=\"top\">");
        ret.append( NbBundle.getMessage(PublisherNode.class, "LBL_Serie") + " " + getPublisher().getSeries().size());
        ret.append("</TD></TR>");
        
        ret.append("<TR><TD>&nbsp;</TD></TR>");
        
        ret.append("</TABLE>");
        
        ret.append("</body></html>");
        return ret.toString();
    }
    
    protected void createPasteTypes(Transferable t, List s) {
        super.createPasteTypes(t, s);
        PasteType paste = getDropType( t, DnDConstants.ACTION_COPY, -1 );
        if( null != paste )
            s.add( paste );
    }
    
    public PasteType getDropType(Transferable t, final int action, int index) {
        final Node dropNode = NodeTransfer.node( t,
                DnDConstants.ACTION_COPY_OR_MOVE+NodeTransfer.CLIPBOARD_CUT );
        if( null != dropNode ) {
            final Serie serie = (Serie)dropNode.getLookup().lookup( Serie.class );
            int copy = action & DnDConstants.ACTION_COPY;
            if( null != serie  && (copy != 0 || !this.equals( dropNode.getParentNode()))) {
                return new PasteType() {
                    public Transferable paste() throws IOException {
                        Serie serie_1 = null;
                        if ( (action & DnDConstants.ACTION_COPY) != 0) {
                            serie_1 = CopyUtil.copySerie(serie);
                            serie_1.setName(serie.getName() + "_1");
                        } else {
                            serie_1 = serie;
                        }
                        publisher.getSeries().add(serie_1);
                        getChildren().add( new Node[] { new SerieNode(serie_1) } );
                        if( (action & DnDConstants.ACTION_MOVE) != 0 ) {
                            dropNode.getParentNode().getChildren().remove( new Node[] {dropNode} );
                        }
                        return null;
                    }
                };
            }
        }
        return null;
    }
    
}
