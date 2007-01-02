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

import de.comicdb.comicdbcore.util.CopyUtil;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.Action;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
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
public class ComicDBNode extends AbstractNode {
    
    private static ResourceBundle bundle = NbBundle.getBundle(ComicDBNode.class);
    
    /** Creates a new instance of ComicDBNode */
    public ComicDBNode(ComicDB comicDB) {
        super( new PublisherChildren(comicDB), Lookups.singleton(comicDB) );
        setDisplayName(comicDB.getFile());
        setIconBaseWithExtension("de/comicdb/comicdbcore/cdb_16x16.png");
    }
    
    public Action[] getActions(boolean popup) {
        return new Action[] {
            SystemAction.get( NewAction.class ),
            null,
            SystemAction.get( PasteAction.class)
        };
    }
    
    public NewType[] getNewTypes() {
        return new NewType[] { new NewType() {
            
            public String getName() {
                return bundle.getString("LBL_NewPublisher");
            }
            
            public HelpCtx getHelpCtx() {
                return new HelpCtx("org.myorg.systemproperties");
            }
            
            public void create() throws IOException {
                String title = bundle.getString("LBL_NewPublisher_dialog");
                String msg = bundle.getString("MSG_NewPublisher_dialog");
                NotifyDescriptor.InputLine desc = new NotifyDescriptor.InputLine(msg, title);
                DialogDisplayer.getDefault().notify(desc);
                String name = desc.getInputText();
                
                if (name.equalsIgnoreCase("")) return;
                
                Publisher pub = new Publisher();
                pub.setName(name);
                boolean added = getChildren().add(new Node[]{
                    new PublisherNode(pub)
                });
                if (added) {
                    PublisherTopComponent.getDefault().setPublisher(pub);
                    if (!PublisherTopComponent.getDefault().isOpened())
                        PublisherTopComponent.getDefault().open();
                    PublisherTopComponent.getDefault().toFront();
                }
                
            }
        }};
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
        System.out.println("ComicDBNode: " + dropNode);
        if( null != dropNode ) {
            final Publisher publisher = (Publisher)dropNode.getLookup().lookup( Publisher.class );
            int copy = action & DnDConstants.ACTION_COPY;
            if( null != publisher  && (copy != 0 || !this.equals( dropNode.getParentNode()))) {
                return new PasteType() {
                    public Transferable paste() throws IOException {
                        Publisher publisher_1 = null;
                        if ( (action & DnDConstants.ACTION_COPY) != 0) { 
                            publisher_1 = CopyUtil.copyPublisher(publisher);
                            publisher_1.setName(publisher.getName() + "_1");
                        } else {
                            publisher_1 = publisher;
                        }
                        getChildren().add( new Node[] { new PublisherNode(publisher_1) } );
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
