/*
 * ImageUtil.java
 *
 * Created on 6. Januar 2007, 12:34
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.comicdb.comicdbcore.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author dm
 */
public class ImageUtil {
    
    /** Creates a new instance of ImageUtil */
    public ImageUtil() {
    }
    
    public static File createTempImage(ImageIcon icon, File oldFile) {
        String extension = icon.getDescription();
        extension = extension.substring(extension.lastIndexOf(".") + 1);
        try {
            File ret = File.createTempFile("cdb", "." + extension);
            ret.deleteOnExit();
            BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB );
            Graphics2D g2d = bi.createGraphics();
            g2d.drawImage(icon.getImage(), 0, 0, null);
            g2d.dispose(); //??
            ImageIO.write(bi, extension, ret);
            return ret;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }
}
