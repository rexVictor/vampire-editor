package vampire.editor.gui.swing.optiondialog.view;

import static org.junit.Assert.*;

import javax.swing.JLabel;
import javax.swing.tree.DefaultMutableTreeNode;

import org.junit.Test;

@SuppressWarnings({"static-method", "nls"})
public class OptionDialogTest {

	@Test
	public void test() throws Throwable {
		DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("test1");
		DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("test2");
		DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("test3");
		node1.add(node2);
		JLabel label1 = new JLabel("test1");
		JLabel label2 = new JLabel("test2");
		JLabel label3 = new JLabel("test3");
		OptionDialog dialog = new OptionDialog();
		dialog.addNodeToRoot(node1);
		dialog.addNodeToRoot(node3);
		dialog.addTreeNode(node1, label1);
		dialog.addTreeNode(node2, label2);
		dialog.addTreeNode(node3, label3);
		dialog.createDialog();
		
		while (dialog.isVisible()){
			Thread.sleep(10);
		}
		
		
		fail("Not yet implemented");
	}

}
