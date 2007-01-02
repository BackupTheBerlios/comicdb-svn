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
package de.comicdb.comicdbcore;

import de.comicdb.comicdbcore.bean.ComicDB;
import de.comicdb.comicdbcore.options.ComicDBOption;
import de.comicdb.comicdbcore.options.ComicDBOptionUtil;
import de.comicdb.comicdbcore.bean.ComicDBChildren;
import de.comicdb.comicdbcore.bean.RootNode;
import de.comicdb.comicdbcore.util.ComicFileFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JFileChooser;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;

public final class OpenComicDBAction extends CallableSystemAction {
    
    public void performAction() {
        ComicDBExplorerTopComponent comp = ComicDBExplorerTopComponent.getDefault();

        ComicDBOptionUtil util = new ComicDBOptionUtil();
        ComicDBOption options = util.retrieveSetting();

        JFileChooser chooser = new JFileChooser(options.getLoadPath());
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        ComicFileFilter fileFilter = new ComicFileFilter();
        fileFilter.addExtension("cdb");
        fileFilter.setDescription("ComicDB-File");
        chooser.setFileFilter(fileFilter);
        int ret = chooser.showOpenDialog(null);
        if (ret == JFileChooser.CANCEL_OPTION)
            return;
        
        options.setLoadPath(chooser.getSelectedFile().getParent());
        util.storeSetting(options);
        
        File file = chooser.getSelectedFile();
        try {
            ObjectInputStream objectInStr = new ObjectInputStream(new FileInputStream(file));
            ComicDB comicDB = (ComicDB) objectInStr.readObject();
            comp.getExplorerManager().setRootContext(new RootNode(new ComicDBChildren(comicDB)));
            comp.getExplorerManager().getRootContext().setDisplayName(file.getName());
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }
    
    public String getName() {
        return NbBundle.getMessage(OpenComicDBAction.class, "CTL_OpenComicDBAction");
    }
    
    protected String iconResource() {
        return "de/comicdb/comicdbcore/cdb_16x16.png";
    }
    
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }
    
    protected boolean asynchronous() {
        return false;
    }
    
}
