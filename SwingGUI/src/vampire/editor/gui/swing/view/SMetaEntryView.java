package vampire.editor.gui.swing.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import vampire.editor.gui.swing.application.Initializer;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.PopupEntriesAPI;
import vampire.editor.plugin.api.domain.sheet.view.MetaEntryViewAttributesAPI;
import vampire.editor.plugin.api.view.events.MetaEntryViewListener;
import vampire.editor.plugin.api.view.sheet.MetaEntryView;

public class SMetaEntryView implements MetaEntryView, ActionListener, DocumentListener{
	
	private final DictionaryAPI dictionary;
	
	private final MetaEntryViewAttributesAPI viewAttributes;
	
	private final JTextField title = new JTextField();
	
	private /*final*/ JTextComponent content;
	
	private final FormLayout layout = new FormLayout();
	
	private final JPanel panel = new JPanel();
	
	private final List<MetaEntryViewListener> listeners = new LinkedList<>();
	
	

	public SMetaEntryView(DictionaryAPI dictionary,
			MetaEntryViewAttributesAPI viewAttributes) {
		super();
		this.dictionary = dictionary;
		this.viewAttributes = viewAttributes;
		initialize();
	}
	
	private void initialize(){
		panel.setLayout(layout);
		if (viewAttributes.getLineCount()==1){
			content = new JTextField();
			((JTextField) content).addActionListener(this);
		}
		else {
			content = new JTextArea();
			((JTextArea) content).setRows(viewAttributes.getLineCount());
			
			content.getDocument().addDocumentListener(this);
		}
		content.setFont(viewAttributes.getContentFont());
		
		title.setFont(viewAttributes.getTitleFont());
		Initializer.initialize(panel);
		Initializer.initialize(title);
		Initializer.initialize(content);
		
		title.setEditable(false);
		title.setFocusable(false);
		content.setEditable(true);
		
		layout.appendColumn(ColumnSpec.decode("pref"));
		layout.appendColumn(ColumnSpec.decode("10px"));
		layout.appendColumn(ColumnSpec.decode("pref:GROW"));
		layout.appendRow(RowSpec.decode("pref"));
		
		
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight	=	1;
		constraints.gridWidth	=	1;
		constraints.gridX		=	1;
		constraints.gridY		=	1;
		constraints.hAlign		=	CellConstraints.LEFT;
		
		panel.add(title, constraints);
		
		constraints.gridX		=	3;
		constraints.hAlign		=	CellConstraints.FILL;
		
		panel.add(content, constraints);
	}
	

	@Override
	public void setTitle(String title) {
		this.title.setText(dictionary.getValue(title));
		
	}

	@Override
	public void setContent(String content) {
		this.content.setText(dictionary.getValue(content));		
	}
	
	

	@Override
	public void setPopup(PopupEntriesAPI entries) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String title = dictionary.getKey(this.title.getText());
		String content = dictionary.getKey(this.content.getText());
		SMetaEntryViewEvent event = new SMetaEntryViewEvent(title, content);
		for (MetaEntryViewListener l : listeners){
			l.contentChanged(event);
		}
		
	}

	@Override
	public void addListener(MetaEntryViewListener listener) {
		listeners.add(listener);
	}

	@Override
	public void remove(MetaEntryViewListener listener) {
		listeners.remove(listener);
	}
	
	public JPanel getPanel(){
		return panel;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		actionPerformed(null);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		actionPerformed(null);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public MetaEntryViewAttributesAPI getViewAtts(){
		return viewAttributes;
	}

	
}
