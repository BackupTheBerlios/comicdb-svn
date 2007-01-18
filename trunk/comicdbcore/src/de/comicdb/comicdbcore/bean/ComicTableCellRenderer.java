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

import de.comicdb.comicdbcore.util.ImageUtil;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author dm
 */
public class ComicTableCellRenderer extends JLabel implements TableCellRenderer{
    
    /** Creates a new instance of SerieTableCellRenderer */
    public ComicTableCellRenderer() {
        super();
        setOpaque(true);
        setHorizontalAlignment(JLabel.CENTER);
    }
    
    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        
        table.setRowHeight(row, 140);
        
//        if(isSelected) {
//            setBackground(UIManager.getColor("Table.selectionBackground"));
//        } else {
//            setBackground(Color.BLACK);
//        }
        
        if (hasFocus && value != null) {
            setBorder( UIManager.getBorder("Table.focusCellHighlightBorder") );
            setBackground(UIManager.getColor("Table.selectionBackground"));
            if (table.isCellEditable(row, column)) {
                super.setForeground( UIManager.getColor("Table.focusCellForeground") );
                super.setBackground( UIManager.getColor("Table.focusCellBackground") );
            }
        } else {
            setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
            setBackground(UIManager.getColor("Panel.background"));
        }
        
        if (value == null) {
            setIcon(null);
            setText(null);
            
        } else if (value instanceof Comic) {
            Comic comic = (Comic)value;
            if (comic.getImage() != null) {
                setText(null);
                int thumbWidth = 160;
                int thumbHeight = 120;
                double thumbRatio = (double)thumbWidth / (double)thumbHeight;
                int imageWidth = comic.getImage().getImage().getWidth(null);
                int imageHeight = comic.getImage().getImage().getHeight(null);
                double imageRatio = (double)imageWidth / (double)imageHeight;
                if (thumbRatio < imageRatio) {
                    thumbHeight = (int)(thumbWidth / imageRatio);
                } else {
                    thumbWidth = (int)(thumbHeight * imageRatio);
                }
                setIcon(ImageUtil.getThumbImage(comic.getImage(), thumbHeight, thumbWidth));
                
            } else {
                setIcon(null);
                setText(comic.getName() + " " + comic.getNr());
            }
            setToolTipText(comic.getName() + " " + comic.getNr());
        }
        
        return this;
    }
    
}
