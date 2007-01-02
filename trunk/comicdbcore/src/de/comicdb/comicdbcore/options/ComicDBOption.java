/*
 * ComicDBOption.java
 *
 * Created on 28. Dezember 2006, 12:23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.comicdb.comicdbcore.options;

import java.io.Serializable;

/**
 *
 * @author dm
 */
public class ComicDBOption implements Serializable{
    
    private String savePath = null;
    private String loadPath = null;
    private String imagePath = null;
    
    /** Creates a new instance of ComicDBOption */
    public ComicDBOption() {
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getLoadPath() {
        return loadPath;
    }

    public void setLoadPath(String loadPath) {
        this.loadPath = loadPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
}
