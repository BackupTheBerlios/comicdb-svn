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

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.adapter.ComboBoxAdapter;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.Trigger;
import de.comicdb.comicdbcore.options.ComicDBOption;
import de.comicdb.comicdbcore.options.ComicDBOptionUtil;
import de.comicdb.comicdbcore.util.ImageFileFilter;
import de.comicdb.comicdbcore.util.ImagePanel;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import org.openide.ErrorManager;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * Top component which displays something.
 */
public final class ComicTopComponent extends TopComponent implements PropertyChangeListener {
    
    private static final long serialVersionUID = 1L;
    
    private static ComicTopComponent instance;
    /** path to the icon used by the component and its open action */
    static final String ICON_PATH = "de/comicdb/comicdbcore/cdb_16x16.png";
    
    private static final String PREFERRED_ID = "ComicTopComponent";
    
    private Comic comic = new Comic();
    final Trigger trigger = new Trigger();
    
    private PresentationModel adapter = new PresentationModel(comic, trigger);
    
    ArrayList comicTypes = new ArrayList();
    ArrayList coverTypes = new ArrayList();
    
    private ComicTopComponent() {
        initComicTypes();
        initCoverTypes();
        initComponents();
        setName(NbBundle.getMessage(ComicTopComponent.class, "CTL_ComicTopComponent"));
        setToolTipText(NbBundle.getMessage(ComicTopComponent.class, "HINT_ComicTopComponent"));
        setIcon(Utilities.loadImage(ICON_PATH, true));
        adapter.addPropertyChangeListener(this);
    }

    private void initComicTypes() {
//        Heft/Album/Magazin/Buch/Piccolo    
        comicTypes.add(NbBundle.getMessage(ComicTopComponent.class, "LBL_COMIC_COMICTYPE_BOOKLET"));
        comicTypes.add(NbBundle.getMessage(ComicTopComponent.class, "LBL_COMIC_COMICTYPE_ALBUM"));
        comicTypes.add(NbBundle.getMessage(ComicTopComponent.class, "LBL_COMIC_COMICTYPE_MAGAZINE"));
        comicTypes.add(NbBundle.getMessage(ComicTopComponent.class, "LBL_COMIC_COMICTYPE_BOOK"));
        comicTypes.add(NbBundle.getMessage(ComicTopComponent.class, "LBL_COMIC_COMICTYPE_PICCOLO"));
    }
    
    private void initCoverTypes() {
        coverTypes.add(NbBundle.getMessage(ComicTopComponent.class, "LBL_COMIC_COVERTYPE_VARIANT"));
        coverTypes.add(NbBundle.getMessage(ComicTopComponent.class, "LBL_COMIC_COVERTYPE_HARD"));
        coverTypes.add(NbBundle.getMessage(ComicTopComponent.class, "LBL_COMIC_COVERTYPE_SOFT"));
        coverTypes.add(NbBundle.getMessage(ComicTopComponent.class, "LBL_COMIC_COVERTYPE_MUSEUM"));
        coverTypes.add(NbBundle.getMessage(ComicTopComponent.class, "LBL_COMIC_COVERTYPE_SILVER"));
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        popupMenu = new javax.swing.JPopupMenu();
        jLabelName = new javax.swing.JLabel();
        jTextFieldName = BasicComponentFactory.createTextField(adapter.getBufferedModel("name"));
        jLabelNr = new javax.swing.JLabel();
        jLabelComicType = new javax.swing.JLabel();
        jComboBoxComicType = new javax.swing.JComboBox();
        jLabelCoverPrice = new javax.swing.JLabel();
        jTextFieldCoverPrice = BasicComponentFactory.createFormattedTextField(adapter.getBufferedModel("coverprice"),new DecimalFormat("##0.00"));
        jLabelPrice = new javax.swing.JLabel();
        jTextFieldPrice = BasicComponentFactory.createFormattedTextField(adapter.getBufferedModel("price"),new DecimalFormat("##0.00"));
        jLabelCoverDate = new javax.swing.JLabel();
        jTextFieldCoverDate = BasicComponentFactory.createFormattedTextField(adapter.getBufferedModel("coverdate"), new SimpleDateFormat(java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("format.date")));
        jLabelQuantity = new javax.swing.JLabel();
        jButtonCoverDate = new javax.swing.JButton();
        jLabelCost = new javax.swing.JLabel();
        jTextFieldCost = BasicComponentFactory.createFormattedTextField(adapter.getBufferedModel("cost"), new DecimalFormat("##0.00"));
        jLabelPayDate = new javax.swing.JLabel();
        jTextFieldPayDate = BasicComponentFactory.createFormattedTextField(adapter.getBufferedModel("paydate"), new SimpleDateFormat(java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("format.date")));
        jButtonPayDate = new javax.swing.JButton();
        jLabelModified = new javax.swing.JLabel();
        jTextFieldModified = BasicComponentFactory.createFormattedTextField(adapter.getBufferedModel("modified"), new SimpleDateFormat(java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("format.date")));
        jPanel2 = new javax.swing.JPanel();
        jButtonChooseImage = new javax.swing.JButton();
        jLabelCondition = new javax.swing.JLabel();
        jPanelNotes = new javax.swing.JPanel();
        jScrollPaneNotes = new javax.swing.JScrollPane();
        jTextAreaNotes = BasicComponentFactory.createTextArea(adapter.getBufferedModel("notes"));
        jPanelStories = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableStorys = new javax.swing.JTable();
        jTextFieldNr = BasicComponentFactory.createIntegerField(adapter.getBufferedModel("nr"));
        jTextFieldCondition = jTextFieldCondition = BasicComponentFactory.createIntegerField(adapter.getBufferedModel("condition"));
        jTextFieldQuantity = BasicComponentFactory.createIntegerField(adapter.getBufferedModel("quantity"));
        jLabelPageCount = new javax.swing.JLabel();
        jTextFieldPageCount = BasicComponentFactory.createIntegerField(adapter.getBufferedModel("pagecount"));
        jLabelCoverType = new javax.swing.JLabel();
        jComboBoxCoverType = new javax.swing.JComboBox();
        jLabelState = new javax.swing.JLabel();
        jComboBoxState = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanelImage = new ImagePanel();
        jPanel1 = new javax.swing.JPanel();
        jButtonAccept = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(jLabelName, java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("LBL_COMIC_NAME"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 0);
        add(jLabelName, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 0);
        add(jTextFieldName, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(jLabelNr, java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("LBL_COMIC_NR"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jLabelNr, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(jLabelComicType, java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("LBL_COMIC_TYPE"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jLabelComicType, gridBagConstraints);

        jComboBoxComicType.setModel(new ComboBoxAdapter(comicTypes, adapter.getBufferedModel("comictype")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jComboBoxComicType, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(jLabelCoverPrice, java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("LBL_COMIC_COVER_PRICE"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jLabelCoverPrice, gridBagConstraints);

        jTextFieldCoverPrice.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jTextFieldCoverPrice, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(jLabelPrice, java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("LBL_COMIC_PRICE"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jLabelPrice, gridBagConstraints);

        jTextFieldPrice.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jTextFieldPrice, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(jLabelCoverDate, java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("LBL_COMIC_COVER_DATE"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jLabelCoverDate, gridBagConstraints);

        jTextFieldCoverDate.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jTextFieldCoverDate, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(jLabelQuantity, java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("LBL_COMIC_QUANTITY"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jLabelQuantity, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonCoverDate, "...");
        jButtonCoverDate.setEnabled(false);
        jButtonCoverDate.setMaximumSize(new java.awt.Dimension(18, 18));
        jButtonCoverDate.setMinimumSize(new java.awt.Dimension(18, 18));
        jButtonCoverDate.setPreferredSize(new java.awt.Dimension(18, 18));
        jButtonCoverDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCoverDateActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        add(jButtonCoverDate, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(jLabelCost, java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("LBL_COMIC_COST"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jLabelCost, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jTextFieldCost, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(jLabelPayDate, java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("LBL_COMIC_PAYDATE"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jLabelPayDate, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jTextFieldPayDate, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonPayDate, "...");
        jButtonPayDate.setEnabled(false);
        jButtonPayDate.setMaximumSize(new java.awt.Dimension(18, 18));
        jButtonPayDate.setMinimumSize(new java.awt.Dimension(18, 18));
        jButtonPayDate.setPreferredSize(new java.awt.Dimension(18, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        add(jButtonPayDate, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(jLabelModified, java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("LBL_MODIFIED"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jLabelModified, gridBagConstraints);

        jTextFieldModified.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jTextFieldModified, gridBagConstraints);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 798, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 24, Short.MAX_VALUE)
        );
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanel2, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonChooseImage, java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("button.choose"));
        jButtonChooseImage.setEnabled(false);
        jButtonChooseImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChooseImageActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 4);
        add(jButtonChooseImage, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(jLabelCondition, java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("LBL_COMIC_CONDITION"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jLabelCondition, gridBagConstraints);

        jPanelNotes.setLayout(new java.awt.BorderLayout());

        jPanelNotes.setBorder(javax.swing.BorderFactory.createTitledBorder(java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("LBL_COMIC_NOTES")));
        jTextAreaNotes.setColumns(20);
        jTextAreaNotes.setRows(5);
        jScrollPaneNotes.setViewportView(jTextAreaNotes);

        jPanelNotes.add(jScrollPaneNotes, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        add(jPanelNotes, gridBagConstraints);

        jPanelStories.setLayout(new java.awt.BorderLayout());

        jPanelStories.setBorder(javax.swing.BorderFactory.createTitledBorder(java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("LBL_COMIC_STORIES")));
        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jScrollPane1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseReleased(evt);
            }
        });

        jTableStorys.setModel(new StoryTableModel( new SelectionInList(adapter.getBufferedModel("storys"))));
        jTableStorys.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableStorysMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableStorysMouseReleased(evt);
            }
        });

        jScrollPane1.setViewportView(jTableStorys);

        jPanelStories.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        add(jPanelStories, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jTextFieldNr, gridBagConstraints);

        jTextFieldCondition.setColumns(4);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jTextFieldCondition, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jTextFieldQuantity, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(jLabelPageCount, java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("LBL_COMIC_PAGECOUNT"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jLabelPageCount, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jTextFieldPageCount, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(jLabelCoverType, java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("LBL_COMIC_COVERTYPE"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jLabelCoverType, gridBagConstraints);

        jComboBoxCoverType.setModel(new ComboBoxAdapter(coverTypes, adapter.getBufferedModel("covertype")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jComboBoxCoverType, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(jLabelState, java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("LBL_COMIC_STATE"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jLabelState, gridBagConstraints);

        jComboBoxState.setModel(new ComboBoxAdapter(State.getStates(), adapter.getBufferedModel("state")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jComboBoxState, gridBagConstraints);

        jPanelImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        org.jdesktop.layout.GroupLayout jPanelImageLayout = new org.jdesktop.layout.GroupLayout(jPanelImage);
        jPanelImage.setLayout(jPanelImageLayout);
        jPanelImageLayout.setHorizontalGroup(
            jPanelImageLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 353, Short.MAX_VALUE)
        );
        jPanelImageLayout.setVerticalGroup(
            jPanelImageLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 282, Short.MAX_VALUE)
        );
        jScrollPane2.setViewportView(jPanelImage);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jScrollPane2, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonAccept, java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("button.accept"));
        jButtonAccept.setEnabled(false);
        jButtonAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAcceptActionPerformed(evt);
            }
        });

        jPanel1.add(jButtonAccept);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonCancel, java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("button.cancel"));
        jButtonCancel.setEnabled(false);
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jPanel1.add(jButtonCancel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        add(jPanel1, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCoverDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCoverDateActionPerformed
        
    }//GEN-LAST:event_jButtonCoverDateActionPerformed
        
    private void jScrollPane1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseReleased
        if (evt.isPopupTrigger()) {
            popupMenu.removeAll();
            popupMenu.add(new AddStoryAction());
            popupMenu.add(new DeleteStoryAction());
            popupMenu.show(jTableStorys, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jScrollPane1MouseReleased
    
    private void jScrollPane1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MousePressed
        if (evt.isPopupTrigger()) {
            popupMenu.removeAll();
            popupMenu.add(new AddStoryAction());
            popupMenu.add(new DeleteStoryAction());
            popupMenu.show(jTableStorys, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jScrollPane1MousePressed
    
    private void jTableStorysMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableStorysMouseReleased
        if (evt.isPopupTrigger()) {
            popupMenu.removeAll();
            popupMenu.add(new AddStoryAction());
            popupMenu.add(new DeleteStoryAction());
            popupMenu.show(jTableStorys, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jTableStorysMouseReleased
    
    private void jTableStorysMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableStorysMousePressed
        if (evt.isPopupTrigger()) {
            popupMenu.removeAll();
            popupMenu.add(new AddStoryAction());
            popupMenu.add(new DeleteStoryAction());
            popupMenu.show(jTableStorys, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jTableStorysMousePressed
    
    private void jButtonChooseImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChooseImageActionPerformed
        ComicDBOptionUtil util = new ComicDBOptionUtil();
        ComicDBOption options = util.retrieveSetting();

        JFileChooser chooser = new JFileChooser(options.getImagePath());
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        chooser.setFileFilter(ImageFileFilter.getInstance());
        int ret = chooser.showOpenDialog(null);
        if (ret == JFileChooser.CANCEL_OPTION)
            return;
        
        options.setImagePath(chooser.getSelectedFile().getParent());
        util.storeSetting(options);
        
        ((ImagePanel)jPanelImage).setImageIcon(new ImageIcon(chooser.getSelectedFile().getAbsolutePath()));

        adapter.setBufferedValue("image", new ImageIcon(chooser.getSelectedFile().getAbsolutePath()));
        
    }//GEN-LAST:event_jButtonChooseImageActionPerformed
    
    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        trigger.triggerFlush();
    }//GEN-LAST:event_jButtonCancelActionPerformed
    
    private void jButtonAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAcceptActionPerformed
        comic.setImage(((ImagePanel)jPanelImage).getImageIcon());
        comic.setModified(new Date());
        trigger.triggerCommit();
    }//GEN-LAST:event_jButtonAcceptActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAccept;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonChooseImage;
    private javax.swing.JButton jButtonCoverDate;
    private javax.swing.JButton jButtonPayDate;
    private javax.swing.JComboBox jComboBoxComicType;
    private javax.swing.JComboBox jComboBoxCoverType;
    private javax.swing.JComboBox jComboBoxState;
    private javax.swing.JLabel jLabelComicType;
    private javax.swing.JLabel jLabelCondition;
    private javax.swing.JLabel jLabelCost;
    private javax.swing.JLabel jLabelCoverDate;
    private javax.swing.JLabel jLabelCoverPrice;
    private javax.swing.JLabel jLabelCoverType;
    private javax.swing.JLabel jLabelModified;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelNr;
    private javax.swing.JLabel jLabelPageCount;
    private javax.swing.JLabel jLabelPayDate;
    private javax.swing.JLabel jLabelPrice;
    private javax.swing.JLabel jLabelQuantity;
    private javax.swing.JLabel jLabelState;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelImage;
    private javax.swing.JPanel jPanelNotes;
    private javax.swing.JPanel jPanelStories;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPaneNotes;
    private javax.swing.JTable jTableStorys;
    private javax.swing.JTextArea jTextAreaNotes;
    private javax.swing.JFormattedTextField jTextFieldCondition;
    private javax.swing.JFormattedTextField jTextFieldCost;
    private javax.swing.JFormattedTextField jTextFieldCoverDate;
    private javax.swing.JFormattedTextField jTextFieldCoverPrice;
    private javax.swing.JFormattedTextField jTextFieldModified;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JFormattedTextField jTextFieldNr;
    private javax.swing.JFormattedTextField jTextFieldPageCount;
    private javax.swing.JFormattedTextField jTextFieldPayDate;
    private javax.swing.JFormattedTextField jTextFieldPrice;
    private javax.swing.JFormattedTextField jTextFieldQuantity;
    private javax.swing.JPopupMenu popupMenu;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link findInstance}.
     */
    public static synchronized ComicTopComponent getDefault() {
        if (instance == null) {
            instance = new ComicTopComponent();
        }
        return instance;
    }
    
    /**
     * Obtain the ComicTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized ComicTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            ErrorManager.getDefault().log(ErrorManager.WARNING, "Cannot find Comic component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof ComicTopComponent) {
            return (ComicTopComponent)win;
        }
        ErrorManager.getDefault().log(ErrorManager.WARNING, "There seem to be multiple components with the '" + PREFERRED_ID + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }
    
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }
    
    public void componentOpened() {
        // TODO add custom code on component opening
    }
    
    public void componentClosed() {
        // TODO add custom code on component closing
    }
    
    /** replaces this in object stream */
    public Object writeReplace() {
        return new ResolvableHelper();
    }
    
    protected String preferredID() {
        return PREFERRED_ID;
    }
    
    final static class ResolvableHelper implements Serializable {
        private static final long serialVersionUID = 1L;
        public Object readResolve() {
            return ComicTopComponent.getDefault();
        }
    }
    
    public Comic getComic() {
        return comic;
    }
    
    public void setComic(Comic comic) {
        this.comic = comic;
        adapter.setBean(comic);
        ((ImagePanel)jPanelImage).setImageIcon(comic.getImage());

        adapter.triggerFlush();        

        jButtonChooseImage.setEnabled(comic != null);
        jButtonCoverDate.setEnabled(comic != null);
        jButtonPayDate.setEnabled(comic != null);
        update();
    }
    
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        update();
    }

    private void update() {
        boolean enabled = adapter.isBuffering();
        jButtonAccept.setEnabled(enabled);
        jButtonCancel.setEnabled(enabled);
        
        if (enabled) {
            setName("*" + NbBundle.getMessage(ComicTopComponent.class, "CTL_ComicTopComponent") + ":" + comic.getName() + " " + comic.getNr());
        } else {
            setName(NbBundle.getMessage(ComicTopComponent.class, "CTL_ComicTopComponent") + ":" + comic.getName() + " " + comic.getNr());
        }

    }
    private class DeleteStoryAction extends AbstractAction {
        public DeleteStoryAction() {
            putValue(Action.NAME, java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("LBL_STORY_DELETE"));
            int row = jTableStorys.getSelectedRow();
            if (row >= 0 && row <= ((List)adapter.getBufferedModel("storys").getValue()).size()) {
                setEnabled(true);
            } else {
                setEnabled(false);
            }
        }
        
        public void actionPerformed(ActionEvent evt) {
            int row = jTableStorys.getSelectedRow();
            StoryList oldValue = (StoryList)adapter.getBufferedModel("storys").getValue();
            StoryList newValue = (StoryList)oldValue.clone();
            newValue.remove(row);
            adapter.setBufferedValue("storys", newValue);
//            adapter.getBufferedModel("storys").fireValueChange(oldValue, newValue, false);
//            jTableStorys.updateUI();
        }
    }
    
    private class AddStoryAction extends AbstractAction {
        public AddStoryAction() {
            putValue(Action.NAME, java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("LBL_STORY_ADD"));
        }
        public void actionPerformed(ActionEvent evt) {
            Story story = StoryDialog.showDialog(null, true);
            if (story == null)
                return;
            
            StoryList oldValue = (StoryList)adapter.getBufferedModel("storys").getValue();
            StoryList newValue = (StoryList)oldValue.clone();
            newValue.add(story);
            adapter.setBufferedValue("storys", newValue);
//            adapter.getBufferedModel("storys").fireValueChange(oldValue, newValue, false);
//            jTableStorys.updateUI();
        }
    }
}

