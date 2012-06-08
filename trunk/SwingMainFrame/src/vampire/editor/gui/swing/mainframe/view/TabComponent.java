package vampire.editor.gui.swing.mainframe.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vampire.editor.plugin.api.application.sheet.events.MetaEntryEventAPI;
import vampire.editor.plugin.api.application.sheet.events.MetaEntryListener;

public class TabComponent implements MetaEntryListener{
	
	private final JLabel title = new JLabel();
	
	private final JButton closeButton = new JButton();
	
	private final JPanel tabComponent = new JPanel();
	
	public TabComponent(){
		tabComponent.setLayout(new BorderLayout());
		tabComponent.add(title, BorderLayout.CENTER);
		tabComponent.add(closeButton, BorderLayout.WEST);
	}

	@Override
	public void valueChanged(MetaEntryEventAPI event) {
		String name = event.getNewValue();
		if (!name.trim().isEmpty())
			title.setText(name);
		else
			title.setText("unnamed");
		
	}
	
	public JPanel getComponent(){
		return tabComponent;
	}
	
	public void addCloseListener(ActionListener listener){
		closeButton.addActionListener(listener);
	}
	
	

}
