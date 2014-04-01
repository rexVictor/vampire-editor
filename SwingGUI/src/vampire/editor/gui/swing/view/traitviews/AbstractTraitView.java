package vampire.editor.gui.swing.view.traitviews;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import vampire.editor.gui.swing.view.Helper;
import vampire.editor.gui.swing.view.events.STraitViewEvent;
import vampire.editor.gui.swing.view.valueviews.AbstractValueView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.Orientation;
import vampire.editor.plugin.api.domain.sheet.view.TraitViewAttributes;
import vampire.editor.plugin.api.view.events.TraitViewListener;
import vampire.editor.plugin.api.view.sheet.TraitView;
import vampire.editor.plugin.api.view.sheet.ValueView;

public abstract class AbstractTraitView implements TraitView, DocumentListener{
	
	private static final Map<Orientation, AbstractTraitView> traitViews = new HashMap<>();
	
	static{
		traitViews.put(Orientation.HORIZONTAL, new HorizontalTraitView());
		traitViews.put(Orientation.VERTICAL, new VerticalTraitView());
	}
	
	public static AbstractTraitView buildTraitView(ValueView valueView,
													TraitViewAttributes viewAtts,
													DictionaryAPI dictionary){
		AbstractValueView vv = (AbstractValueView) valueView;
		return traitViews.get(viewAtts.getOrientation()).createNewInstance(vv, viewAtts, dictionary);
	}
	
	protected final JPanel panel = Helper.createPanel();
	
	protected final AbstractValueView valueView;
	
	protected final JTextField textField = Helper.createLimitedTextField(20);
	
	private final DictionaryAPI dictionary;
	
	private final TraitViewAttributes viewAtts;
	
	private final List<TraitViewListener> listeners = new LinkedList<>();
	
	protected AbstractTraitView(AbstractValueView valueView, TraitViewAttributes viewAtts, DictionaryAPI dictionary){
		this.viewAtts = viewAtts;
		this.valueView = valueView;
		this.dictionary = dictionary;
		initializeTextField();
	}
	
	protected AbstractTraitView(){
		viewAtts = null;
		valueView = null;
		dictionary = null;
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
		return traitView;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JTextField getField() {
		return textField;
	}

	public void setPopupMenu(Object menu){
		if (menu instanceof JPopupMenu)
			textField.setComponentPopupMenu((JPopupMenu) menu);
	}

	protected abstract AbstractTraitView createNewInstance(AbstractValueView vv, TraitViewAttributes atts, DictionaryAPI dictionary);

}
