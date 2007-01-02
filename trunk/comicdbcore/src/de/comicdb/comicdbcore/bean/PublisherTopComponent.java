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
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.Trigger;
import de.comicdb.comicdbcore.options.ComicDBOption;
import de.comicdb.comicdbcore.options.ComicDBOptionUtil;
import de.comicdb.comicdbcore.util.ImageFileFilter;
import de.comicdb.comicdbcore.util.ImagePanel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public final class PublisherTopComponent extends TopComponent implements PropertyChangeListener {
    
    private static final long serialVersionUID = 1L;
    
    private static PublisherTopComponent instance;
    /** path to the icon used by the component and its open action */
    static final String ICON_PATH = "de/comicdb/comicdbcore/cdb_16x16.png";
    
    private static final String PREFERRED_ID = "PublisherTopComponent";
    
    private Publisher publisher;
    final Trigger trigger = new Trigger();
    
    private PresentationModel adapter = new PresentationModel(publisher, trigger);
    
    private PublisherTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(PublisherTopComponent.class, "CTL_PublisherTopComponent"));
        setToolTipText(NbBundle.getMessage(PublisherTopComponent.class, "HINT_PublisherTopComponent"));
        setIcon(Utilities.loadImage(ICON_PATH, true));
        adapter.addPropertyChangeListener(this);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabelName = new javax.swing.JLabel();
        jTextFieldName = BasicComponentFactory.createTextField(adapter.getBufferedModel("name"));
        jPanelImage = new ImagePanel();
        jButtonChooseImage = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableSeries = new javax.swing.JTable();
        jTableSeries.setDefaultRenderer(Object.class,new SerieTableCellRenderer());
        jLabelModified = new javax.swing.JLabel();
        jTextFieldModified = BasicComponentFactory.createFormattedTextField(adapter.getBufferedModel("modified"), new SimpleDateFormat(java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("format.date")));
        jButtonAccept = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(jLabelName, java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("LBL_PUBLISHER_NAME"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 4);
        add(jLabelName, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 4);
        add(jTextFieldName, gridBagConstraints);

        jPanelImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        org.jdesktop.layout.GroupLayout jPanelImageLayout = new org.jdesktop.layout.GroupLayout(jPanelImage);
        jPanelImage.setLayout(jPanelImageLayout);
        jPanelImageLayout.setHorizontalGroup(
            jPanelImageLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 238, Short.MAX_VALUE)
        );
        jPanelImageLayout.setVerticalGroup(
            jPanelImageLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 98, Short.MAX_VALUE)
        );
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 4);
        add(jPanelImage, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonChooseImage, java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("button.choose"));
        jButtonChooseImage.setEnabled(false);
        jButtonChooseImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChooseImageActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 4);
        add(jButtonChooseImage, gridBagConstraints);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("LBL_SERIE")));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        jTableSeries.setBackground(javax.swing.UIManager.getDefaults().getColor("Panel.background"));
        jTableSeries.setModel(new SerieTableModel(new SelectionInList(adapter.getBufferedModel("series"))));
        jTableSeries.setShowHorizontalLines(false);
        jTableSeries.setShowVerticalLines(false);
        jScrollPane1.setViewportView(jTableSeries);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        add(jPanel2, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(jLabelModified, java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("LBL_MODIFIED"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jLabelModified, gridBagConstraints);

        jTextFieldModified.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        add(jTextFieldModified, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonAccept, java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("button.accept"));
        jButtonAccept.setEnabled(false);
        jButtonAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAcceptActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 4);
        add(jButtonAccept, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonCancel, java.util.ResourceBundle.getBundle("de/comicdb/comicdbcore/bean/Bundle").getString("button.cancel"));
        jButtonCancel.setEnabled(false);
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 4);
        add(jButtonCancel, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents
    
    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        trigger.triggerFlush();
    }//GEN-LAST:event_jButtonCancelActionPerformed
    
    private void jButtonAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAcceptActionPerformed
        publisher.setImage(((ImagePanel)jPanelImage).getImageIcon());
        publisher.setModified(new Date());
        trigger.triggerCommit();
    }//GEN-LAST:event_jButtonAcceptActionPerformed
    
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

//        setSaveChooserPath(chooser.getSelectedFile().getParentFile());
    }//GEN-LAST:event_jButtonChooseImageActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAccept;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonChooseImage;
    private javax.swing.JLabel jLabelModified;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelImage;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableSeries;
    private javax.swing.JFormattedTextField jTextFieldModified;
    private javax.swing.JTextField jTextFieldName;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link findInstance}.
     */
    public static synchronized PublisherTopComponent getDefault() {
        if (instance == null) {
            instance = new PublisherTopComponent();
        }
        return instance;
    }
    
    /**
     * Obtain the PublisherTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized PublisherTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            ErrorManager.getDefault().log(ErrorManager.WARNING, "Cannot find Publisher component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof PublisherTopComponent) {
            return (PublisherTopComponent)win;
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
            return PublisherTopComponent.getDefault();
        }
    }
    
    public Publisher getPublisher() {
        return publisher;
    }
    
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
        adapter.setBean(publisher);
        
        ((ImagePanel)jPanelImage).setImageIcon(publisher.getImage());
        setName(NbBundle.getMessage(PublisherTopComponent.class, "CTL_PublisherTopComponent") + ":" + publisher.getName());
        jButtonChooseImage.setEnabled(publisher != null);

    }    

    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        boolean enabled = adapter.isBuffering();
        jButtonAccept.setEnabled(enabled);
        jButtonCancel.setEnabled(enabled);
        
        if (enabled) {
            setName("*" + NbBundle.getMessage(PublisherTopComponent.class, "CTL_PublisherTopComponent") + ":" + publisher.getName());
        } else {
            setName(NbBundle.getMessage(PublisherTopComponent.class, "CTL_PublisherTopComponent") + ":" + publisher.getName());
        }
    }
}