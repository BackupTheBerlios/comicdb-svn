/*
 * SerieTableCellRenderer.java
 *
 * Created on 2. Januar 2007, 15:49
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.comicdb.comicdbcore.bean;

import java.awt.Color;
import java.awt.Component;
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
            
        } else if (value instanceof ImageIcon) {
            setIcon((ImageIcon)(value));
            if(getPreferredSize().height != table.getRowHeight(row)) {
                table.setRowHeight(row, getPreferredSize().height);
            }
            setText(null);
            
        } else {
            setIcon(null);
            setText((String)value);
        }        
        
        return this;
    }
}
