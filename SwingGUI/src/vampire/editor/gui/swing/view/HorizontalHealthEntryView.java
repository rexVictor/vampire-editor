package vampire.editor.gui.swing.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.DamageType;
import vampire.editor.plugin.api.domain.sheet.view.HealthEntryViewAttributesAPI;
import vampire.editor.plugin.api.view.events.HealthEntryViewListener;
import vampire.editor.plugin.api.view.sheet.HealthEntryView;

public class HorizontalHealthEntryView implements HealthEntryView, MouseListener{
	
	private final JLabel box = new JLabel();
	
	private final JTextField penaltyField = new JTextField();
	
	private final List<HealthEntryViewListener> listeners = new LinkedList<>();
	
	private final Lock lock = new ReentrantLock();
	
	private int penalty;
	
	private DamageType damageType;
	
	private JPanel panel = new JPanel();
	
	public HorizontalHealthEntryView(DictionaryAPI dictionary, HealthEntryViewAttributesAPI viewAtts) {
		super();
		penaltyField.setBorder(null);
		
		box.addMouseListener(this);
		box.setFont(new Font("Sans Serif", 0, viewAtts.getFont().getSize()));
		penaltyField.setFont(viewAtts.getFont());
		
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(2, 1));
		panel.add(box);
		panel.add(penaltyField);
	}

	@Override
	public void addListener(HealthEntryViewListener listener) {
		lock.lock();
		try{
			listeners.add(listener);
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void setDamageType(DamageType damageType) {
		if (damageType == null){
			box.setText("\u2610");
		}
		else switch(damageType){
		case LETHAL:
		case BASHING: box.setText("\u2341"); break;
		case AGGREVATED: box.setText("\u2612");
		}
		this.damageType = damageType;
	}
	
	private void setDamageType0(DamageType damageType){
		setDamageType(damageType);
		SHeathEntryViewEvent event = makeEvent();
		lock.lock();
		try{
			for (HealthEntryViewListener l : listeners){
				l.damageTypeChanged(event);
			}
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void setPenalty(int penalty) {
		this.penaltyField.setText("-"+penalty);
		this.penalty = penalty;
	}
	
	/*private void setPenalty0(int penalty){
		setPenalty(penalty);
		SHeathEntryViewEvent event = makeEvent();
		lock.lock();
		try{
			for (HealthViewEntryListener l : listeners){
				l.penaltyChanged(event);
			}
		}
		finally{
			lock.unlock();
		}
	}*/
	
	private SHeathEntryViewEvent makeEvent(){
		return new SHeathEntryViewEvent(null, penalty, damageType);
	}

	@Override
	public void setDescription(String description) {
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (damageType == null){
			setDamageType0(DamageType.LETHAL);
		}
		else switch (damageType) {
		case BASHING:
		case LETHAL: setDamageType0(DamageType.AGGREVATED); break;
		case AGGREVATED: setDamageType0(null);
		}
	}
	
	public JPanel getPanel(){
		return panel;
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}