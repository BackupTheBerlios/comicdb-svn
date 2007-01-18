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
import javax.swing.JMenu;
import javax.swing.JMenuItem;
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
import org.openide.util.actions.NodeAction;
import org.openide.util.actions.SystemAction;
import org.openide.util.datatransfer.NewType;
import org.openide.util.datatransfer.PasteType;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author dm
 */
public class SerieNode extends AbstractNode implements PropertyChangeListener {
    
    private static ResourceBundle bundle = NbBundle.getBundle(SerieNode.class);
    private Serie serie;
    
    /** Creates a new instance of ComicDBNode */
    public SerieNode(Serie serie) {
        super( new ComicChildren(serie), Lookups.singleton(serie) );
        setDisplayName(serie.getName());
        setIconBaseWithExtension("de/comicdb/comicdbcore/cdb_16x16.png");
        setSerie(serie);
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
    
    public NewType[] getNewTypes() {
        
        return new NewType[] { new NewType() {
            public String getName() {
                return bundle.getString("LBL_NewComic");
            }
            public HelpCtx getHelpCtx() {
                return new HelpCtx("org.myorg.systemproperties");
            }
            public void create() throws IOException {
                String title = bundle.getString("LBL_NewComic_dialog");
                String msg = bundle.getString("MSG_NewComic_dialog");
                String name = serie.getName();
                NotifyDescriptor.InputLine desc = new NotifyDescriptor.InputLine(msg, title);
                desc.setInputText(name);
                DialogDisplayer.getDefault().notify(desc);
                name = desc.getInputText();
                
                if (name.length() == 0) return;
                
                Comic comic = new Comic();
                comic.setName(name);
                comic.setNr(new Integer(getChildren().getNodesCount() + 1));
                ComicChildren children = (ComicChildren)getChildren();
                children.getSerie().getComics().add(comic);
//                boolean added = children.add(new Node[]{
//                    new ComicNode(comic)
//                });
//                if (added) {
                children.addNotify();
                    ComicTopComponent.getDefault().setComic(comic);
                    if (!ComicTopComponent.getDefault().isOpened())
                        ComicTopComponent.getDefault().open();
                    ComicTopComponent.getDefault().requestActive();
                    fireShortDescriptionChange(null, getShortDescription());
//                }
            }
        }, new NewType() {
            public String getName() {
                return bundle.getString("LBL_NewComics");
            }
            public HelpCtx getHelpCtx() {
                return new HelpCtx("org.myorg.systemproperties");
            }
            public void create() throws IOException {
                String title = bundle.getString("LBL_NewComic_dialog");
                String msg = bundle.getString("MSG_NewComic_dialog");
                
                String name = serie.getName();
                NotifyDescriptor.InputLine desc = new NotifyDescriptor.InputLine(msg, title);
                desc.setInputText(name);
                DialogDisplayer.getDefault().notify(desc);
                name = desc.getInputText();
                
                if (name.length() == 0) return;
                
                title = bundle.getString("LBL_NewComicCount_dialog");
                msg = bundle.getString("MSG_NewComicCount_dialog");
                desc = new NotifyDescriptor.InputLine(msg, title);
                desc.setInputText("1");
                DialogDisplayer.getDefault().notify(desc);
                int count = 0;
                try {
                    count = Integer.parseInt(desc.getInputText());
                } catch(NumberFormatException nfe) {
                    return;
                }
                
                int nr = getChildren().getNodesCount();
                for (int i = 1; i <= count; i++) {
                    Comic comic = new Comic();
                    comic.setName(name);
                    comic.setNr(new Integer( nr +  i));
                    ComicChildren children = (ComicChildren)getChildren();
                    children.getSerie().getComics().add(comic);
                    children.addNotify();
//                    boolean added = children.add(new Node[]{
//                        new ComicNode(comic)
//                    });
//                    if (added) {
                        ComicTopComponent.getDefault().setComic(comic);
//                    }
                }
                if (!ComicTopComponent.getDefault().isOpened())
                    ComicTopComponent.getDefault().open();
                ComicTopComponent.getDefault().requestActive();
                fireShortDescriptionChange(null, getShortDescription());
            }
        }
        };
    }
    
    public Action getPreferredAction() {
        return new AbstractAction(NbBundle.getMessage(SerieNode.class, "LBL_Show")) {
            public void actionPerformed(ActionEvent e) {
                SerieTopComponent.getDefault().setSerie(serie);
                if (!SerieTopComponent.getDefault().isOpened())
                    SerieTopComponent.getDefault().open();
//                SerieTopComponent.getDefault().toFront();
                SerieTopComponent.getDefault().requestActive();
            }
        };
    }
    
    public boolean canDestroy() {
        return true;
    }
    
    public void destroy() throws IOException {
        SerieChildren children = (SerieChildren)getParentNode().getChildren();
        children.getPublisher().getSeries().remove(serie);
        children.remove(new Node[] {this});
//        children.addNotify();
    }
    
    public void setSerie(Serie serie) {
        if (this.serie != null)
            this.serie.removePropertyChangeListener(this);
        this.serie = serie;
        if (serie != null)
            serie.addPropertyChangeListener(this);
    }
    
    public Serie getSerie() {
        return serie;
    }
    
    public void propertyChange(PropertyChangeEvent event) {
        setDisplayName(serie.getName());
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
        if (getSerie().getImage() != null) {
            File img = new File(getSerie().getImage().getDescription());
            if (!img.exists()) {
                img = ImageUtil.createTempImage(getSerie().getImage(), img);
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
        ret.append(NbBundle.getMessage(PublisherNode.class, "LBL_Comic") + " " + serie.getComics().size());
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
            final Comic comic = (Comic)dropNode.getLookup().lookup( Comic.class );
            int copy = action & DnDConstants.ACTION_COPY;
            if( null != comic  && (copy != 0 || !this.equals( dropNode.getParentNode()))) {
                return new PasteType() {
                    public Transferable paste() throws IOException {
                        Comic comic_1 = null;
                        if ( (action & DnDConstants.ACTION_COPY) != 0) {
                            comic_1 = CopyUtil.copyComic(comic);
                            comic_1.setName(comic.getName() + "_1");
                        } else {
                            comic_1 = comic;
                        }
                        serie.getComics().add(comic_1);
                        getChildren().add( new Node[] { new ComicNode(comic_1) } );
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
