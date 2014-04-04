package vampire.editor.gui.swing.view.popupmenus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vampire.editor.gui.swing.view.properties.SubCategoryViewProperties;

public class PropertiesTrigger implements ActionListener{
	
	private final SubCategoryViewProperties properties;
	
	

	public PropertiesTrigger(SubCategoryViewProperties properties) {
		super();
		this.properties = properties;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		properties.showDialog();
	}

}
