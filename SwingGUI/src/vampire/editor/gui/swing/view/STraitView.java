package vampire.editor.gui.swing.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextField;

import vampire.editor.plugin.api.view.events.TraitViewListener;
import vampire.editor.plugin.api.view.sheet.TraitView;

public class STraitView implements TraitView, ActionListener{
	
	private final List<TraitViewListener> listeners = new LinkedList<>();
	
	private final JPanel panel = new JPanel();
	
	private final JTextField textField = new JTextField();
	
	private final SValueView valueView;
	
	
	public STraitView(SValueView valueView) {
		super();
		this.valueView = valueView;
		textField.addActionListener(this);
		panel.setLayout(new GridLayout(1, 2));
		panel.add(textField);
		panel.add(valueView.getPanel());
	}

	
	@Override
	public void setName(String name) {
		textField.setText(name);		
	}

	@Override
	public void addListener(TraitViewListener listener) {
		listeners.add(listener);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String text = textField.getText();
		STraitViewEvent event = new STraitViewEvent(text);
		for (TraitViewListener listener : listeners){
			listener.traitNameChanged(event);
		}
	}


	public JPanel getPanel() {
		return panel;
	}


	public JTextField getTextField() {
		return textField;
	}


	public SValueView getValueView() {
		return valueView;
	}
	
	

}
