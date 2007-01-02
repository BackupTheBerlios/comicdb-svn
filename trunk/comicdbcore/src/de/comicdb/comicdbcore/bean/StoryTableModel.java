/*
 * StoryTableModel.java
 *
 * Created on 17. Dezember 2006, 19:09
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.comicdb.comicdbcore.bean;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.list.SelectionInList;
import javax.swing.ListModel;
import org.openide.util.NbBundle;

/**
 *
 * @author dm
 */
public class StoryTableModel extends AbstractTableAdapter{
    public ListModel model;

    public StoryTableModel(ListModel model) {
        super(model);
        this.model = model;
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        Story story = (Story) getRow(rowIndex);
        if (columnIndex == 0) {
            return story.getName();
        } else if (columnIndex == 1) {
            return story.getAuthor();
        } else if (columnIndex == 2) {
            return story.getPainter();
        } else {
            return story.getDescription();
        }
    }

    public String getColumnName(int columnIndex) {
        
        if (columnIndex == 0) {
            return NbBundle.getMessage(StoryTableModel.class, "LBL_STORY_NAME");
        } else if (columnIndex == 1) {
            return NbBundle.getMessage(StoryTableModel.class, "LBL_STORY_AUTHOR");
        } else if (columnIndex == 2) {
            return NbBundle.getMessage(StoryTableModel.class, "LBL_STORY_PAINTER");
        } else {
            return NbBundle.getMessage(StoryTableModel.class, "LBL_STORY_DESCRIPTION");
        }
    }

    public int getColumnCount() {
        return 4;
    }

    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
}
