package de.comicdb.comicdbcore;

import de.comicdb.comicdbcore.bean.Comic;
import de.comicdb.comicdbcore.bean.ComicChildren;
import de.comicdb.comicdbcore.bean.ComicTopComponent;
import java.awt.Component;
import java.awt.Dialog;
import java.text.MessageFormat;
import java.util.List;
import javax.swing.JComponent;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.NodeAction;

// An example action demonstrating how the wizard could be called from within
// your code. You can copy-paste the code below wherever you need.
public final class ComicWizardAction extends NodeAction {
    
    private WizardDescriptor.Panel[] panels;
    
    public void performAction(Node[] nodes) {
        Children children = getActivatedNodes()[0].getChildren();
        ComicChildren serie = (ComicChildren) children;
        WizardDescriptor wizardDescriptor = new WizardDescriptor(getPanels());
        // {0} will be replaced by WizardDesriptor.Panel.getComponent().getName()
        wizardDescriptor.setTitleFormat(new MessageFormat("{0}"));
        wizardDescriptor.setTitle(NbBundle.getMessage(getClass(), "LBL_NewComic"));
        wizardDescriptor.putProperty("name", serie.getSerie().getName());
        Dialog dialog = DialogDisplayer.getDefault().createDialog(wizardDescriptor);
        dialog.setVisible(true);
        dialog.toFront();
        
        boolean cancelled = wizardDescriptor.getValue() != WizardDescriptor.FINISH_OPTION;
        if (!cancelled) {
            if (children instanceof ComicChildren) {
//                ComicChildren serie = (ComicChildren) children;
                List<Comic> comics = serie.getSerie().getComics();
                Boolean isOneSelected = (Boolean)wizardDescriptor.getProperty("oneSelected");
                String name = (String)wizardDescriptor.getProperty("name");
                if (isOneSelected.booleanValue()) {
                    Comic ncomic = new Comic();
                    ncomic.setName(name);
                    ncomic.setNr(new Integer(((Number)wizardDescriptor.getProperty("nr")).intValue()));
                    comics.add(ncomic);
                } else { // more comics added
                    int from = ((Number)wizardDescriptor.getProperty("from")).intValue();
                    int to = ((Number)wizardDescriptor.getProperty("to")).intValue();
                    for (int i = from; i <= to; i++ ) {
                        Comic ncomic = new Comic();
                        ncomic.setName(name);
                        ncomic.setNr(new Integer(i));
                        comics.add(ncomic);
                    }
                }
                serie.addNotify();
                ComicTopComponent.getDefault().setComic(comics.get(comics.size() - 1));
                if (!ComicTopComponent.getDefault().isOpened())
                    ComicTopComponent.getDefault().open();
                ComicTopComponent.getDefault().requestActive();
            }
        }
    }
    
    public boolean enable(Node[] node) {
        return true;
    }
    /**
     * Initialize panels representing individual wizard's steps and sets
     * various properties for them influencing wizard appearance.
     */
    private WizardDescriptor.Panel[] getPanels() {
        if (panels == null) {
            panels = new WizardDescriptor.Panel[] {
                new ComicWizardPanel()
            };
            String[] steps = new String[panels.length];
            for (int i = 0; i < panels.length; i++) {
                Component c = panels[i].getComponent();
                // Default step name to component name of panel. Mainly useful
                // for getting the name of the target chooser to appear in the
                // list of steps.
                steps[i] = c.getName();
                if (c instanceof JComponent) { // assume Swing components
                    JComponent jc = (JComponent) c;
                    // Sets step number of a component
                    jc.putClientProperty("WizardPanel_contentSelectedIndex", new Integer(i));
                    // Sets steps names for a panel
                    jc.putClientProperty("WizardPanel_contentData", steps);
                    // Turn on subtitle creation on each step
                    jc.putClientProperty("WizardPanel_autoWizardStyle", Boolean.TRUE);
                    // Show steps on the left side with the image on the background
                    jc.putClientProperty("WizardPanel_contentDisplayed", Boolean.TRUE);
                    // Turn on numbering of all steps
                    jc.putClientProperty("WizardPanel_contentNumbered", Boolean.FALSE);
                    //
//                    jc.putClientProperty("WizardPanel_image", new Image)
                }
            }
        }
        return panels;
    }
    
    public String getName() {
        return NbBundle.getMessage(getClass(), "LBL_NewComic");
    }
    
    public String iconResource() {
        return null;
    }
    
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }
    
    protected boolean asynchronous() {
        return false;
    }
    
}

