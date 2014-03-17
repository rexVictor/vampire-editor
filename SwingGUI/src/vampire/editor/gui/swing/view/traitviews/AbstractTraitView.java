package vampire.editor.gui.swing.view.traitviews;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import vampire.editor.gui.swing.view.Helper;
import vampire.editor.gui.swing.view.STraitViewEvent;
import vampire.editor.gui.swing.view.valueviews.AbstractValueView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.TraitViewAttributes;
import vampire.editor.plugin.api.view.events.TraitMouseViewListener;
import vampire.editor.plugin.api.view.events.TraitViewListener;
import vampire.editor.plugin.api.view.sheet.TraitView;
import vampire.editor.plugin.api.view.sheet.ValueView;

public class AbstractTraitView implements TraitView, DocumentListener, ActionListener, MouseListener{
	
	public static AbstractTraitView buildTraitView(ValueView valueView,
													TraitViewAttributes viewAtts,
													DictionaryAPI dictionary){
		AbstractValueView vv = (AbstractValueView) valueView;
		switch (viewAtts.getOrientation()){
		case HORIZONTAL: return new HorizontalTraitView(vv, viewAtts, dictionary);
		case VERTICAL: return new VerticalTraitView(vv, viewAtts, dictionary);
		}
		return null;
	}
	
	protected final JPanel panel = Helper.createPanel();
	
	protected final AbstractValueView valueView;
	
	private final DictionaryAPI dictionary;
	
	private final TraitViewAttributes viewAtts;
	
	protected final JTextField textField = Helper.createLimitedTextField(20);
	
	private final List<TraitViewListener> listeners = new LinkedList<>();
	
	private final List<TraitMouseViewListener> mouseListeners = new LinkedList<>();
	
	public AbstractTraitView(AbstractValueView valueView, TraitViewAttributes viewAtts, DictionaryAPI dictionary){
		this.viewAtts = viewAtts;
		this.valueView = valueView;
		this.dictionary = dictionary;
		initializeTextField();
	}
	
	protected void initializeTextField(){
		textField.setEditable(viewAtts.isEditable());
		textField.setFocusable(viewAtts.isEditable());
		textField.getDocument().addDocumentListener(this);
		textField.setFont(viewAtts.getFont());
	}
	
	@Override
	public void setName(String name) {
		String newName = dictionary.getValue(name);
		textField.setText(newName);
	}

	@Override
	public void addListener(TraitViewListener listener) {
		listeners.add(listener);
	}

	@Override
	public AbstractValueView getValueView() {
		return valueView;
	}

	@Override
	public TraitViewAttributes getViewAttributes() {
		return viewAtts;
	}

	@Override
	public void setTooltip(String text) {
		textField.setToolTipText(text);
	}

	@Override
	public void addPopupEntry(String entry) {
		/*String translated = dictionary.getValue(entry);
		JMenuItem menuItem = new JMenuItem(translated);
		menuItem.setActionCommand(translated);
		menuItem.addActionListener(this);
		popupMenu.add(menuItem);*/
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		String input = textField.getText();
		String translated = dictionary.getKey(input);
		STraitViewEvent event = new STraitViewEvent(translated);
		for (TraitViewListener l : listeners){
			l.traitNameChanged(event);
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		insertUpdate(null);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		insertUpdate(null);
	}
	
	public AbstractTraitView clone(){
		AbstractValueView clonedValueView = valueView.clone();
		TraitViewAttributes clonedTraitViewAttributes = viewAtts.clone();
		AbstractTraitView traitView = AbstractTraitView.buildTraitView(
				clonedValueView, clonedTraitViewAttributes, dictionary);
		traitView.setPopupMenu(textField.getComponentPopupMenu());
		for (MouseListener l : textField.getMouseListeners()){
			traitView.textField.addMouseListener(l);
		}
		return traitView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		textField.setText(e.getActionCommand());
	}

	public JPanel getPanel() {
		return panel;
	}

	public JTextField getField() {
		return textField;
	}

	@Override
	public void addMouseListener(TraitMouseViewListener l) {
		mouseListeners.add(l);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		STraitMouseViewEvent event = new STraitMouseViewEvent(e.getClickCount(), e.getButton());
		for (TraitMouseViewListener l : mouseListeners){
			l.mouseViewEventFired(event);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	public void setPopupMenu(JPopupMenu menu){
		textField.setComponentPopupMenu(menu);
	}

}
