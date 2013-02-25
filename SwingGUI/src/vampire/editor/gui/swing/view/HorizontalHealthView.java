package vampire.editor.gui.swing.view;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JLabel;
import javax.swing.JPanel;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.HealthViewAttibutesAPI;
import vampire.editor.plugin.api.view.events.HealthViewListener;
import vampire.editor.plugin.api.view.sheet.HealthEntryView;
import vampire.editor.plugin.api.view.sheet.HealthView;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;

public class HorizontalHealthView implements HealthView{
	
private final JPanel panel = new JPanel();
	
	private final FormLayout layout = new FormLayout("5px", "pref,pref");
	
	private final List<HealthViewListener> listeners = new LinkedList<>();
	
	private final List<HealthEntryView> healthEntryViews = new LinkedList<>();
	
	private final Lock lock = new ReentrantLock();
	
	private final DictionaryAPI dictionary;
	
	public HorizontalHealthView(DictionaryAPI dictionaryAPI, HealthViewAttibutesAPI viewAtts){
		this.dictionary = dictionaryAPI;
		for (int i = 0; i < 10; i++){
			layout.appendColumn(ColumnSpec.decode("pref:GROW"));
			layout.appendColumn(ColumnSpec.decode("5px"));
		}
		panel.setLayout(layout);
		panel.setBackground(Color.WHITE);
		JLabel label = new JLabel();
		label.setFont(viewAtts.getFont());
		label.setText(dictionary.getValue("health"));
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight = 1;
		constraints.gridWidth = 19;
		constraints.gridX = 2;
		constraints.gridY = 1;
		constraints.hAlign = CellConstraints.CENTER;
		
		panel.add(label, constraints);
	}

	@Override
	public List<HealthEntryView> getEntries() {
		return healthEntryViews;
	}

	@Override
	public void addHealthEntryView(HealthEntryView healthEntryView) {
		healthEntryViews.add(healthEntryView);
		HorizontalHealthEntryView view = (HorizontalHealthEntryView) healthEntryView;
		
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight = 1;
		constraints.gridWidth = 1;
		constraints.gridX = healthEntryViews.size()*2;
		constraints.gridY = layout.getRowCount();
		constraints.hAlign = CellConstraints.FILL;
		
		panel.add(view.getPanel(), constraints);
		
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
