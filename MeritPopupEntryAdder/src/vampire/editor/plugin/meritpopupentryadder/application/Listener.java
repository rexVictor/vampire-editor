package vampire.editor.plugin.meritpopupentryadder.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

public class Listener implements ActionListener, MouseListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem item = (JMenuItem) e.getSource();
		JPopupMenu menu1 = (JPopupMenu) item.getParent();
		JMenu menu = (JMenu) menu1.getInvoker();
		String cost = menu.getText();
		JPopupMenu menu2 = (JPopupMenu) menu.getParent();
		JTextField invoker = (JTextField) menu2.getInvoker();
		invoker.setText(cost+item.getText());
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {}

}
