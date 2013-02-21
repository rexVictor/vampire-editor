package vampire.editor.gui.swing.mainframe.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TabComponent{
	
	private final JLabel title = new JLabel();
	
	private final JButton closeButton = new JButton("x");
	
	private final JPanel tabComponent = new JPanel();
	
	public TabComponent(){
		tabComponent.setLayout(new BorderLayout());
		tabComponent.add(title, BorderLayout.CENTER);
		tabComponent.add(closeButton, BorderLayout.WEST);
	}

	public JPanel getComponent(){
		return tabComponent;
	}
	
	public void addCloseListener(ActionListener listener){
		closeButton.addActionListener(listener);
	}
	
	public void setTitle(String title) {
		this.title.setText(title);
	}


}
