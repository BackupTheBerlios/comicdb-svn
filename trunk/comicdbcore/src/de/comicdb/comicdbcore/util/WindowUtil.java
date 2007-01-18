/*
 * WindowUtil.java
 *
 * Created on 14. Januar 2007, 16:00
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.comicdb.comicdbcore.util;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

/**
 *
 * @author dm
 */
public class WindowUtil {
    
    /** Creates a new instance of WindowUtil */
    public WindowUtil() {
    }
    
    public static void centerWindowOnScreen(Window window) {
        if (window == null)
            throw new IllegalArgumentException("argument window must not be null!");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation((screenSize.width - window.getWidth()) / 2, (screenSize.height - window.getHeight()) / 2);
    }
    
    public static void centerWindowOnScreenAndShow(Window window) {
        if (window == null)
            throw new IllegalArgumentException("argument window must not be null!");

        centerWindowOnScreen(window);
        window.setVisible(true);
    }
}
