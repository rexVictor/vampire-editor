package vampire.editor.gui.swing.view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vampire.editor.gui.swing.domain.Images;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.BloodPoolViewAttributesAPI;
import vampire.editor.plugin.api.view.events.BloodPoolViewListener;
import vampire.editor.plugin.api.view.sheet.BloodPoolView;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class SBloodPoolView implements BloodPoolView, MouseListener{
	
	private final JPanel panel = new JPanel();
	
	private final FormLayout layout = new FormLayout();
	
	private final List<BloodPoolViewListener> listeners = new LinkedList<>();
	
	private final Lock lock = new ReentrantLock();
	
	private final List<JLabel> squares = new ArrayList<>();
	
	private int maxValue = 0;
	
	private Icon emptyBox;
	
	private Icon crossedBox;
	
	public SBloodPoolView(BloodPoolViewAttributesAPI viewAtts, DictionaryAPI dictionary){
		int size = viewAtts.getSize();
		emptyBox = new ImageIcon(Images.getImage("square_empty", size, size));
		crossedBox = new ImageIcon(Images.getImage("square_crossed", size, size));
		panel.setBackground(Color.WHITE);
		panel.setLayout(layout);
		layout.appendColumn(ColumnSpec.decode("5px"));
		for (int i = 0; i < 10; i++){
			addColumn();
		}
		layout.appendRow(RowSpec.decode("pref"));
		JTextField textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setBorder(null);
		textField.setFont(viewAtts.getFont());
		textField.setEditable(false);
		textField.setText(dictionary.getValue("bloodpool"));
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight = 1;
		constraints.gridWidth = layout.getColumnCount()-2;
		constraints.gridX = 2;
		constraints.gridY = 1;
		constraints.hAlign = CellConstraints.CENTER;
		
		panel.add(textField, constraints);
		
		setMaxValue(10);
	}
	
	private void addColumn(){
		layout.appendColumn(ColumnSpec.decode("pref:GROW"));
		layout.appendColumn(ColumnSpec.decode("5px"));
		layout.addGroupedColumn(layout.getColumnCount()-1);
	}
 
	@Override
	public void addListener(BloodPoolViewListener listener) {
		lock.lock();
		try{
			listeners.add(listener);
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void setValue(int value) {
		for (int i = 0; i < value; i++){
			squares.get(i).setIcon(crossedBox);
		}
		for (int i = value; i < maxValue; i++){
			squares.get(i).setIcon(emptyBox);
		}
	}
	
	private void setValue0(int value){
		setValue(value);
		SBloodPoolViewEvent event = new SBloodPoolViewEvent(value, maxValue);
		lock.lock();
		try{
			for (BloodPoolViewListener listener : listeners){
				listener.valueChanged(event);
			}
		}
		finally{
			lock.unlock();
		}
	}
	
	private void addSquare(){
		JLabel label = new JLabel(emptyBox);
		label.addMouseListener(this);
		int rowCount = layout.getRowCount();
		int squareCount = squares.size();
		int x = squareCount % 10;
		int y = squareCount / 10;
		if (rowCount < y+2){
			layout.appendRow(RowSpec.decode("pref"));
		}
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight = 1;
		constraints.gridWidth = 1;
		constraints.gridX = 2 * x+2;
		constraints.gridY = y +2;
		squares.add(label);
		panel.add(label, constraints);
	} 
	
	private void removeSquare(){
		int size = squares.size();
		JLabel last = squares.remove(size-1);
		CellConstraints constraints = layout.getConstraints(last);
		panel.remove(last);
		if (constraints.gridX == 2){
			layout.removeRow(layout.getRowCount());
		}
	}
	
	@Override
	public void setMaxValue(int maxValue) {
		if (this.maxValue <= maxValue){
			for(int i = this.maxValue; i < maxValue; i++){
				addSquare();
			}
		}
		else{
			for (int i = maxValue; i < this.maxValue; i++){
				removeSquare();
			}
		}
		this.maxValue = maxValue;
		panel.revalidate();
		panel.repaint();
	}
	
	public JPanel getView(){
		return panel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel label = (JLabel) e.getSource();
		int value = 0;
		switch(e.getButton()){
		case MouseEvent.BUTTON1:
			value = squares.indexOf(label) + 1;
			break;
		case MouseEvent.BUTTON3:
			value = squares.indexOf(label);
		}
		setValue0(value);
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
