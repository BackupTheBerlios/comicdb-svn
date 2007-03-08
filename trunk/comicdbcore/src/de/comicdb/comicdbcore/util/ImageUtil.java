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

package de.comicdb.comicdbcore.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
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
    
    public static ImageIcon getThumbImage(ImageIcon myLoadedImageIcon, int thumbHeight, int thumbWidth) {
        
        Image myImage = myLoadedImageIcon.getImage();
//        int scaledX = (int) (scaleFactor * myImage.getWidth(null));
//        int scaledY = (int) (scaleFactor * myImage.getHeight(null));
        BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = thumbImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(myImage, 0, 0, thumbWidth, thumbHeight, null);
        graphics2D.dispose();
//        Image scaledImage  = myImage.getScaledInstance(thumbWidth, thumbHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(thumbImage);
    }
    
}
