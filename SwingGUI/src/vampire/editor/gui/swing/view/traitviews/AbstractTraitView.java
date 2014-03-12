package vampire.editor.gui.swing.view.traitviews;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import vampire.editor.gui.swing.view.STraitViewEvent;
import vampire.editor.gui.swing.view.valueviews.AbstractValueView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.TraitViewAttributes;
import vampire.editor.plugin.api.view.events.TraitViewListener;
import vampire.editor.plugin.api.view.sheet.TraitView;

public class AbstractTraitView implements TraitView, DocumentListener, ActionListener{
	
	public static AbstractTraitView buildTraitView(AbstractValueView valueView,
													TraitViewAttributes viewAtts,
													DictionaryAPI dictionary){
		switch (viewAtts.getOrientation()){
		case HORIZONTAL: return new HorizontalTraitView(valueView, viewAtts, dictionary);
		case VERTICAL: return new VerticalTraitView(valueView, viewAtts, dictionary);
		}
		return null;
	}
	
	protected final JPanel panel = new JPanel();
	
	protected final AbstractValueView valueView;
	
	private final DictionaryAPI dictionary;
	
	private final TraitViewAttributes viewAtts;
	
	protected final JTextField textField = new JTextField();
	
	protected final JPopupMenu popupMenu = new JPopupMenu();
	
	private final List<TraitViewListener> listeners = new LinkedList<>();
	
	public AbstractTraitView(AbstractValueView valueView, TraitViewAttributes viewAtts, DictionaryAPI dictionary){
		this.viewAtts = viewAtts;
		this.valueView = valueView;
		this.dictionary = dictionary;
		initializeTextField();
	}
	
	protected void initializeTextField(){
		Document document = new LimitedDocument(20);
		textField.setDocument(document);
		textField.setEditable(viewAtts.isEditable());
		textField.setFocusable(viewAtts.isEditable());
		textField.setBorder(null);
		textField.setBackground(Color.WHITE);
		textField.getDocument().addDocumentListener(this);
		textField.setComponentPopupMenu(popupMenu);
		textField.setFont(viewAtts.getFont());
	}
	
	protected void initializePanel(){
		panel.setBackground(Color.WHITE);
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
		String translated = dictionary.getValue(entry);
		JMenuItem menuItem = new JMenuItem(translated);
		menuItem.setActionCommand(translated);
		menuItem.addActionListener(this);
		popupMenu.add(menuItem);
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
		return traitView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		textField.setText(e.getActionCommand());
	}

	public Component getPanel() {
		return panel;
	}

	public JTextField getField() {
		return textField;
	}

}
