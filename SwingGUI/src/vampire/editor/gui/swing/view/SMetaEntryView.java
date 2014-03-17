/*******************************************************************************
 * The Sheetview of the vampire editor implemented using Swing.
 * Copyright (C) 2013 Matthias Reimchen
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *    
 * For further information see https://code.google.com/p/vampire-editor/ or write a
 * mail to development.rex@gmail.com
 ******************************************************************************/
package vampire.editor.gui.swing.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

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
	
	private final JPanel panel = new JPanel();
	
	private final List<MetaEntryViewListener> listeners = new LinkedList<>();
	
	private final JPopupMenu popupMenu = new JPopupMenu();
	

	public SMetaEntryView(DictionaryAPI dictionary,
			MetaEntryViewAttributesAPI viewAttributes) {
		super();
		this.dictionary = dictionary;
		this.viewAttributes = viewAttributes;
		initialize();
	}
	
	private void initialize(){
		panel.setLayout(new BorderLayout(5, 0));
		if (viewAttributes.getLineCount()==1){
			content = new JTextField();
		}
		else {
			content = new JTextArea();
			JTextArea area = (JTextArea) content;
			area.setRows(viewAttributes.getLineCount());
			area.setLineWrap(true);
			area.setWrapStyleWord(true);
		}
		content.getDocument().addDocumentListener(this);
		content.setComponentPopupMenu(popupMenu);
		//content.setFont(viewAttributes.getContentFont());
		content.setBorder(null);
		title.setFont(viewAttributes.getTitleFont());
		title.setBorder(null);
		Initializer.initialize(panel);
		Initializer.initialize(title);
		Initializer.initialize(content);
		
		title.setEditable(false);
		title.setFocusable(false);
		content.setEditable(true);
		
		panel.add(title, BorderLayout.WEST);
		
		panel.add(content, BorderLayout.CENTER);
	}
	

	@Override
	public void setTitle(String title) {
		String translation = dictionary.getValue(title);
		this.title.setText(translation);
		
	}

	@Override
	public void setContent(String content) {
		String translation = dictionary.getValue(content);
		this.content.setText(translation);
	}
	
	

	@Override
	public void setPopup(PopupEntriesAPI entries) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		content.setText(e.getActionCommand());
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
		String title = dictionary.getKey(this.title.getText());
		String content = dictionary.getKey(this.content.getText());
		SMetaEntryViewEvent event = new SMetaEntryViewEvent(title, content);
		for (MetaEntryViewListener l : listeners){
			l.contentChanged(event);
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		insertUpdate(e);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		insertUpdate(e);
	}
	
	public MetaEntryViewAttributesAPI getViewAtts(){
		return viewAttributes;
	}

	@Override
	public void addPopupEntry(String entry){
		String translated = dictionary.getValue(entry);
		JMenuItem menuItem = new JMenuItem(translated);
		menuItem.setActionCommand(translated);
		menuItem.addActionListener(this);
		popupMenu.add(menuItem);
	}

	
}
