package vampire.editor.gui.swing.view;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

import javax.swing.JLabel;
import javax.swing.JPanel;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.HealthViewAttibutesAPI;
import vampire.editor.plugin.api.view.events.HealthViewListener;
import vampire.editor.plugin.api.view.sheet.HealthEntryView;
import vampire.editor.plugin.api.view.sheet.HealthView;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class SHealthView implements HealthView{
	
	private final JPanel panel = new JPanel();
	
	private final FormLayout layout = new FormLayout("5px, pref:GROW, 5px, pref:GROW, 5px, pref:GROW, 5px", "pref");
	
	private final List<HealthViewListener> listeners = new LinkedList<>();
	
	private final List<HealthEntryView> healthEntryViews = new LinkedList<>();
	
	private final Lock lock = new ReentrantLock();
	
	private final DictionaryAPI dictionary;
	
	public SHealthView(DictionaryAPI dictionaryAPI, HealthViewAttibutesAPI viewAtts){
		this.dictionary = dictionaryAPI;
		panel.setLayout(layout);
		panel.setBackground(Color.WHITE);
		JLabel label = new JLabel();
		label.setFont(viewAtts.getFont());
		label.setText(dictionary.getValue("health"));
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight = 1;
		constraints.gridWidth = 5;
		constraints.gridX = 2;
		constraints.gridY = 1;
		
		panel.add(label, constraints);
	}

	@Override
	public List<HealthEntryView> getEntries() {
		return healthEntryViews;
	}

	@Override
	public void addHealthEntryView(HealthEntryView healthEntryView) {
		healthEntryViews.add(healthEntryView);
		SHealthEntryView view = (SHealthEntryView) healthEntryView;
		layout.appendRow(RowSpec.decode("pref"));
		
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight = 1;
		constraints.gridWidth = 1;
		constraints.gridX = 2;
		constraints.gridY = layout.getRowCount();
		constraints.hAlign = CellConstraints.LEFT;
		
		panel.add(view.getDescription(), constraints);
		
		constraints.gridX = 4;
		constraints.hAlign = CellConstraints.RIGHT;
		
		panel.add(view.getPenalty(), constraints);
		
		constraints.gridX = 6;
		constraints.hAlign = CellConstraints.CENTER;
		
		panel.add(view.getBox(), constraints);
	}

	@Override
	public void removeHealthEntryView(HealthEntryView healthEntryView) {
		healthEntryViews.remove(healthEntryView);
		throw new UnsupportedOperationException();
	}

	@Override
	public void addListener(HealthViewListener listener) {
		lock.lock();
		try{
			listeners.add(listener);
		}
		finally{
			lock.unlock();
		}
	}
	
	public JPanel getPanel(){
		return panel;
	}
	
	public FormLayout getLayout(){
		return layout;
	}

}
