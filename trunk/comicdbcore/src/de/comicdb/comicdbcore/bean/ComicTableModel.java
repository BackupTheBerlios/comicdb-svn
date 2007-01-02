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
public class ComicTableModel extends AbstractTableModel implements ListDataListener{
    
    private static int max_column = 4;
    
    public ListModel model;
    
    public ComicTableModel(ListModel model) {
        super();
        this.model = model;
        this.model.addListDataListener(this);
    }
    
    public int getColumnCount() {
        return max_column;
    }
    
    public int getRowCount() {
        int rows = model.getSize() / max_column;
        int remain = model.getSize() % max_column;
        if (model.getSize() > 0 && remain > 0)
            rows += 1;
        return rows;
    }
    
    public String getColumnName(int col) {
        return "";
    }
    
    private Comic getElementAt(int row, int col) {
        int element = (row * max_column) + col;
        if (element < model.getSize())
            return (Comic)model.getElementAt(element);
        return null;
    }
    
    public Object getValueAt(int row, int col) {
        Comic comic = (Comic) getElementAt(row, col);
        if (comic != null) {
            if (comic.getImage() != null)
                return comic.getImage();
            return comic.getName() + " " + comic.getNr();
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