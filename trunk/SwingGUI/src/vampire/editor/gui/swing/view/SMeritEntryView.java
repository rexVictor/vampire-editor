package vampire.editor.gui.swing.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JTextField;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.MeritEntryViewAttibutesAPI;
import vampire.editor.plugin.api.view.events.MeritEntryViewListener;
import vampire.editor.plugin.api.view.sheet.MeritEntryView;

public class SMeritEntryView implements MeritEntryView, ActionListener{
	
	private final JTextField textField = new JTextField();
	
	private final JTextField costField = new JTextField();
	
	private final DictionaryAPI dictionary;
	
	private final List<MeritEntryViewListener> listeners = new LinkedList<>();
	
	private final Lock lock = new ReentrantLock();

	public SMeritEntryView(DictionaryAPI dictionary, MeritEntryViewAttibutesAPI viewAtts) {
		super();
		this.dictionary = dictionary;
		textField.setFont(viewAtts.getFont());
		costField.setFont(viewAtts.getFont());
		textField.setBorder(null);
		costField.setBorder(null);
		textField.addActionListener(this);
		costField.addActionListener(this);
	}

	@Override
	public void setCost(int cost) {
		costField.setText(cost+"");
	}

	@Override
	public void setText(String text) {
		textField.setText(dictionary.getValue(text));
	}

	@Override
	public void addListener(MeritEntryViewListener listener) {
		lock.lock();
		try{
			listeners.add(listener);
		}
		finally{
			lock.unlock();
		}
	}

	public JTextField getTextField() {
		return textField;
	}

	public JTextField getCostField() {
		return costField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = dictionary.getKey(textField.getText());
		String costString = costField.getText();
		try{
			int cost = Integer.parseInt(costString);
			SMeritEntryViewEvent event = new SMeritEntryViewEvent(cost, name);
			if (e.getSource() == costField){
				for (MeritEntryViewListener l : listeners){
					l.costChanged(event);
				}
			}
			else if (e.getSource() == textField){
				for (MeritEntryViewListener l : listeners){
					l.nameChanged(event);
				}
			}
		}
		catch(NumberFormatException exception){
			
		}
	}
	
	

}
