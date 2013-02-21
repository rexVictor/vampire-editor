package vampire.editor.gui.swing.optiondialog.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public class OptionDialog implements TreeSelectionListener, ActionListener{
	
	private static class ComponentInfos{
		
		private static int id = 0;
		
		private final JComponent component;
		
		private final String key;

		private ComponentInfos(JComponent component) {
			super();
			this.component = component;
			key = id+"";
			id++;
		}
		
		
	}
	
	private final JDialog dialog = new JDialog();
	
	private final JTree tree;
	
	private final Map<MutableTreeNode, ComponentInfos> pages = new HashMap<>();
	
	private final JPanel contentPage = new JPanel();
	
	private final JPanel buttonLine = new JPanel();
	
	private final DefaultMutableTreeNode root = new DefaultMutableTreeNode("preferences");
	
	private final CardLayout layout = new CardLayout();
	
	public OptionDialog(){
		tree = new JTree(root);
		tree.addTreeSelectionListener(this);
		tree.setToggleClickCount(2);
		ComponentInfos rootInfo = new ComponentInfos(new JPanel());
		pages.put(root, rootInfo);
		contentPage.setLayout(layout);
		contentPage.add(rootInfo.component, rootInfo.key);
		dialog.setLayout(new BorderLayout(10,0));
		tree.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		dialog.add(tree, BorderLayout.LINE_START);
		dialog.add(contentPage, BorderLayout.CENTER);
		JButton button = new JButton("close");
		button.addActionListener(this);
		buttonLine.setLayout(new BorderLayout(10,3));
		buttonLine.add(button, BorderLayout.LINE_END);
		dialog.add(buttonLine, BorderLayout.PAGE_END);
	}
	
	public void createDialog(){
		layout.show(contentPage, "0");
		dialog.setSize(400, 500);
		dialog.setVisible(true);
		dialog.setSize(400, 500);
	}
	
	public void addTreeNode(MutableTreeNode node, JComponent component){
		ComponentInfos infos = new ComponentInfos(component);
		pages.put(node, infos);
		contentPage.add(infos.component, infos.key);
	}
	
	public void addNodeToRoot(MutableTreeNode node){
		root.add(node);
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		MutableTreeNode selected = (MutableTreeNode) tree.getLastSelectedPathComponent();
		ComponentInfos toShow = pages.get(selected);
		layout.show(contentPage, toShow.key);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dialog.dispose();
	}
	
	public boolean isVisible(){
		return dialog.isVisible();
	}

}
