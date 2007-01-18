package de.comicdb.comicdbcore.sort;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.NodeAction;


public final class SortAction extends NodeAction {
    String sort;
    
    public SortAction() {
        super();
    }
    
    protected boolean enable(Node[] activatedNodes) {
        return true;
    }
    
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }
    
    public JMenuItem getPopupPresenter() {
        JMenu menu = new JMenu(NbBundle.getBundle(SortAction.class).getString("Sort.By"));
        if (getActivatedNodes().length > 0) {
            Children children = getActivatedNodes()[0].getChildren();
            if (children instanceof Sortable) {
                for (final SortableItem sortableItem: ((Sortable)children).getSortableItems()) {
                    JMenuItem item = new JMenuItem(sortableItem.getMenuName());
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent actionEvent) {
                            for (Node node : getActivatedNodes()) {
                                Children children = node.getChildren();
                                if (children instanceof Sortable) {
                                    ((Sortable)children).setSort(new Sort(sortableItem.getProperty(), sortableItem.getOrder() == Sort.ASC ? Sort.DESC : Sort.ASC));
                                    ((Sortable)children).updateChildren();
                                }
                            }
                        }
                    });
                    if (sortableItem.getOrder() == Sort.ASC)
                        item.setIcon(new ImageIcon(getClass().getResource("/de/comicdb/comicdbcore/sort/arrow_down.png")));
                    else if (sortableItem.getOrder() == Sort.DESC)
                        item.setIcon(new ImageIcon(getClass().getResource("/de/comicdb/comicdbcore/sort/arrow_up.png")));
                    else
                        item.setIcon(new ImageIcon(getClass().getResource("/de/comicdb/comicdbcore/sort/arrow_off.png")));
                    menu.add(item);
                }
            }
        }
        
        return menu;
    }
    
    public String getName() {
        return NbBundle.getBundle(SortAction.class).getString("Sort.By");
    }
    
//    protected void performAction(Node[] activatedNodes) {
//        System.out.println("performAction");
//        for(Node node : activatedNodes) {
//            if (node instanceof Sortable) {
//                ((Sortable)node).setSort()
//            }
//        }
//    }
    
//        class RadioMenuItemActionListener implements ActionListener {
//            public void actionPerformed(ActionEvent e) {
//                Controller controller = MonitorAction.getController();
//                TransactionView transView = TransactionView.getInstance();
//                Object source = e.getSource();
//                if (source == descSortMenuItem) {
//                    if (!transView.isDescButtonSelected()) {
//                        transView.setDescButtonSelected();
//                        controller.setComparator(controller.new CompTime(true));
//                    }
//                } else if (source == ascSortMenuItem) {
//                    if (!transView.isAscButtonSelected()) {
//                        transView.setAscButtonSelected();
//                        controller.setComparator(controller.new CompTime(false));
//                    }
//                } else if (source == alphSortMenuItem) {
//                    if (!transView.isAlphButtonSelected()) {
//                        transView.setAlphButtonSelected();
//                        controller.setComparator(controller.new CompAlpha());
//                    }
//                }
//            }
//        }
    
    protected void performAction(Node[] node) {
    }
}