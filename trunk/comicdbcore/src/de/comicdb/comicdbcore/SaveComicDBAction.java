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
import de.comicdb.comicdbcore.util.ComicFileFilter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;

public final class SaveComicDBAction extends CallableSystemAction {
    
    public void performAction() {
        
        ComicDBExplorerTopComponent comp = ComicDBExplorerTopComponent.getDefault();
        ComicDBChildren children = (ComicDBChildren)comp.getExplorerManager().getRootContext().getChildren();
        ComicDB comicDB = children.getComicDB();
        
        ComicDBOptionUtil util = new ComicDBOptionUtil();
        ComicDBOption options = util.retrieveSetting();
        
        JFileChooser chooser = new JFileChooser();
        if (options.getSavePath() != null)
            chooser = new JFileChooser(options.getSavePath());
        
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        ComicFileFilter fileFilter = new ComicFileFilter();
        fileFilter.addExtension("cdb");
        fileFilter.setDescription("ComicDB-File");
        chooser.setFileFilter(fileFilter);
        int ret = chooser.showSaveDialog(null);
        if (ret == JFileChooser.CANCEL_OPTION)
            return;
        
        options.setSavePath(chooser.getSelectedFile().getParent());
        util.storeSetting(options);
        
        File file = chooser.getSelectedFile();
        try {
            ObjectOutputStream objectOutStr = new ObjectOutputStream(new FileOutputStream(file));
            objectOutStr.writeObject(comicDB);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    public String getName() {
        return NbBundle.getMessage(SaveComicDBAction.class, "CTL_SaveComicDBAction");
    }
    
    protected String iconResource() {
        return "de/comicdb/comicdbcore/save_16x16.png";
    }
    
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }
    
    protected boolean asynchronous() {
        return false;
    }
    
}
