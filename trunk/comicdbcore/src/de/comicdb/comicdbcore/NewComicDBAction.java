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

import de.comicdb.comicdbcore.bean.Comic;
import de.comicdb.comicdbcore.bean.ComicDB;
import de.comicdb.comicdbcore.bean.ComicTopComponent;
import de.comicdb.comicdbcore.bean.Serie;
import de.comicdb.comicdbcore.bean.SerieTopComponent;
import de.comicdb.comicdbcore.bean.ComicDBChildren;
import de.comicdb.comicdbcore.bean.RootNode;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;

public final class NewComicDBAction extends CallableSystemAction {
    
    public void performAction() {
        ComicDBExplorerTopComponent comp = ComicDBExplorerTopComponent.getDefault();
        comp.getExplorerManager().setRootContext(new RootNode(new ComicDBChildren(new ComicDB())));
        comp.getExplorerManager().getRootContext().setDisplayName("new ComicDB");
        
        if (ComicTopComponent.getDefault().isOpened())
            ComicTopComponent.getDefault().setComic(new Comic());
        
        if (SerieTopComponent.getDefault().isOpened())
            SerieTopComponent.getDefault().setSerie(new Serie());
    }
    
    public String getName() {
        return NbBundle.getMessage(NewComicDBAction.class, "CTL_NewComicDBAction");
    }
    
    protected String iconResource() {
        return "de/comicdb/comicdbcore/new_16x16.png";
    }
    
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }
    
    protected boolean asynchronous() {
        return false;
    }
    
}
