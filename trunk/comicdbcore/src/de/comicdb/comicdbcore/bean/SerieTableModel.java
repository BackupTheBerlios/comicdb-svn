/*
 * StoryTableModel.java
 *
 * Created on 17. Dezember 2006, 19:09
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.comicdb.comicdbcore.bean;

import javax.swing.ImageIcon;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dm
 */
public class SerieTableModel extends AbstractTableModel implements ListDataListener{
    
    private static int max_column = 4;
    
    public ListModel model;
    
    public SerieTableModel(ListModel model) {
        super();
        this.model = model;
        this.model.addListDataListener(this);
    }
    
    public int getColumnCount() {
        return max_column;
    }
    
    public int getRowCount() {
        int rows = model.getSize() / max_column;
        if (model.getSize() > 0 && rows == 0)
            rows = 1;
        return rows;
    }
    
    public String getColumnName(int col) {
        return "";
    }
    
    private Serie getElementAt(int row, int col) {
        int element = (row * max_column) + col;
        if (element < model.getSize())
            return (Serie)model.getElementAt(element);
        return null;
    }
    
    public Object getValueAt(int row, int col) {
        Serie serie = (Serie) getElementAt(row, col);
        if (serie != null) {
            if (serie.getImage() != null)
                return serie.getImage();
            return serie.getName();
        }
        return null;
    }
    
    public void intervalRemoved(ListDataEvent event) {
        fireTableRowsDeleted(event.getIndex0(), event.getIndex1());
        fireTableStructureChanged();
    }
    
    public void intervalAdded(ListDataEvent event) {
        fireTableRowsInserted(event.getIndex0(), event.getIndex1());
        fireTableStructureChanged();
    }
    
    public void contentsChanged(ListDataEvent event) {
        fireTableRowsUpdated(event.getIndex0(), event.getIndex1());
        fireTableStructureChanged();
    }
}