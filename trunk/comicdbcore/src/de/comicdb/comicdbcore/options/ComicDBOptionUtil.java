/*
 * MailOption.java
 *
 * Created on 14. Oktober 2006, 17:29
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.comicdb.comicdbcore.options;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.openide.filesystems.FileLock;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.Repository;

/**
 *
 * @author dm
 */
public class ComicDBOptionUtil {
    private final static String fileName = "ComicDB";
    private final static String fileExt = "cfg";
    
    
    FileObject folderObject;
    FileObject settingFile;
    FileLock lock;
    ComicDBOption option = new ComicDBOption();
    
    /** Creates a new instance of ComicDBOption */
    public ComicDBOptionUtil() {
        folderObject = Repository.getDefault().getDefaultFileSystem().getRoot().getFileObject("Settings");
        if (folderObject == null){
            try {
                folderObject=Repository.getDefault().getDefaultFileSystem().getRoot().createFolder("Settings");
                storeSetting(option);
            } catch (IOException ex) {
                ex.printStackTrace();
                // TODO file can not be created , do something about it
            }
        } else {
            settingFile= folderObject.getFileObject(fileName, fileExt);
            if (settingFile == null)
                storeSetting(option);
        }
    }
    
    public boolean storeSetting(ComicDBOption settings) {
        try {
            if (folderObject.getFileObject(fileName, fileExt) == null){
                settingFile = folderObject.createData(fileName, fileExt);
            }
            settingFile= folderObject.getFileObject(fileName, fileExt);
            lock = settingFile.lock();
            ObjectOutputStream objectOutStr = new ObjectOutputStream(settingFile.getOutputStream(lock));
            objectOutStr.writeObject(settings);
            objectOutStr.close();
            lock.releaseLock();
        } catch (IOException ex) {
            // TODO file can not be created , do something about it
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public ComicDBOption retrieveSetting() {
        settingFile = folderObject.getFileObject(fileName, fileExt);
        try {
            ObjectInputStream objectInStr = new ObjectInputStream(settingFile.getInputStream());
            option = (ComicDBOption) objectInStr.readObject();
            objectInStr.close();
        } catch (Exception e) {
            option = new ComicDBOption();
        }
        return option;
    }
}
