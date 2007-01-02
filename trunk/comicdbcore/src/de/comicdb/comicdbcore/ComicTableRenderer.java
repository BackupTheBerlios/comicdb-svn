/*
 * ComicTableRenderer.java
 *
 * Created on 5. Dezember 2006, 23:02
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.comicdb.comicdbcore;

import de.comicdb.comicdbcore.bean.Comic;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author dm
 */
public class ComicTableRenderer extends JLabel implements TableCellRenderer {
    
    public ComicTableRenderer() {
        super();
        setOpaque(true);
        setHorizontalAlignment(JLabel.CENTER);
    }
    
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus,
            int row, int column) {
        
        if(isSelected) {
            setBackground(UIManager.getColor("Table.selectionBackground"));
        } else {
            setBackground(Color.BLACK);
        }
        
        if (hasFocus) {
            setBorder( UIManager.getBorder("Table.focusCellHighlightBorder") );
            
            if (table.isCellEditable(row, column)) {
                super.setForeground( UIManager.getColor("Table.focusCellForeground") );
                super.setBackground( UIManager.getColor("Table.focusCellBackground") );
            }
        } else {
            setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        }
        
        Comic comic = (Comic) value;
        
        ImageIcon image = new ImageIcon();
        image.getImage().getGraphics().drawImage(comic.getImage().getImage(), 0, 0, 50, 70, null);
        setIcon(image);
        
        //check if the preferred height of this component is not equal to
        //the row height, then set the preferred height of this component as
        //the row height, else dont do anything.
        if(getPreferredSize().height != table.getRowHeight(row)) {
            table.setRowHeight(row, getPreferredSize().height);
        }
        
        return this;
    }
}
