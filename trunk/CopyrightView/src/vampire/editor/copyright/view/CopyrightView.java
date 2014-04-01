package vampire.editor.copyright.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import vampire.editor.copyright.domain.Project;

public class CopyrightView implements TreeSelectionListener, ActionListener{
	
	private final JDialog dialog = new JDialog();
	
	private final JPanel panel = new JPanel();
	
	private final JTree tree;
	
	private final JLabel nameLabel = new JLabel();
	
	private final JLabel copyrightLabel = new JLabel();
	
	private final JLabel licenseLabel = new JLabel();
	
	private final JTextPane license = new JTextPane();
	
	private final JLabel link = new JLabel();
	
	private final Map<MutableTreeNode, Project> projects = new HashMap<>();
	
	private final DefaultMutableTreeNode root = new DefaultMutableTreeNode("copyright"); //$NON-NLS-1$
	
	private final JButton button = new JButton("close"); //$NON-NLS-1$
	
	public CopyrightView(List<Project> projects){
		tree = new JTree(root);
		tree.addTreeSelectionListener(this);
		license.setContentType("text/html"); //$NON-NLS-1$
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(nameLabel);
		copyrightLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(copyrightLabel);
		link.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(link);
		licenseLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(licenseLabel);
		panel.add(new JScrollPane(license));
		panel.add(Box.createGlue());
		dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		
		button.addActionListener(this);
		
		for (Project p : projects){
			add0(p, root);
		}
		
		tree.expandRow(0);
		tree.setRootVisible(false);
		tree.setShowsRootHandles(true);
		
		Container c = dialog.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(new JScrollPane(tree), BorderLayout.WEST);
		c.add(panel, BorderLayout.CENTER);
		c.add(button, BorderLayout.SOUTH);
		
	}
	
	private void add0(Project project, DefaultMutableTreeNode treeNode){
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(project.getName());
		treeNode.add(node);
		projects.put(node, project);
		for (Project p : project.getLibs()){
			add0(p, node);
		}
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		MutableTreeNode selected = (MutableTreeNode) tree.getLastSelectedPathComponent();
		Project project = projects.get(selected);
		if (project == null){
			return;
		}
		copyrightLabel.setText("\u00a9 " + project.getCopyright()); //$NON-NLS-1$
		nameLabel.setText("Project name: " + project.getName()); //$NON-NLS-1$
		license.setText(project.getLicensetext());
		link.setText(project.getLink());
		licenseLabel.setText(project.getLicense());
	}
	
	public void showDialog(){
		dialog.setSize(1000,500);
		dialog.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dialog.dispose();
	}

}
