package vampire.editor.plugin.dynamiccircleadder.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import vampire.editor.plugin.api.application.sheet.controller.CategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.SubCategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.TraitControllerAPI;
import vampire.editor.plugin.api.plugin.ManagerAPI;
import vampire.editor.plugin.api.view.sheet.ValueView;

public class View {
	
	private final JPanel panel = new JPanel();
	
	private final JButton leftButton = new JButton("\u25C1");
	
	private final JButton rightButton = new JButton("\u25B7");
	
	private final ManagerAPI manager;
	
	public View(ManagerAPI manager){
		this.manager = manager;
		panel.setLayout(new BorderLayout(5, 0));
		panel.add(leftButton, BorderLayout.WEST);
		panel.add(rightButton, BorderLayout.EAST);
		leftButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<ValueView> valueViews = getValueViews();
				for (ValueView v : valueViews){
					v.removeCircle();
				}
			}
		});
		rightButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<ValueView> valueViews = getValueViews();
				for (ValueView v : valueViews){
					v.addCircle();
				}
			}
		});
	}
	
	public List<ValueView> getValueViews(){
		SheetControllerAPI sheetController = manager.getGeneralController().getCurrentController();
		List<ValueView> valueViews = new LinkedList<>();
		if (sheetController == null)
			return valueViews;
		for (CategoryControllerAPI catController : sheetController.getCategoriesController()){
			for (SubCategoryControllerAPI subCatController : catController){
				for (TraitControllerAPI traitController : subCatController){
					valueViews.add(traitController.getValueController().getView());
				}
			}
		}
		return valueViews;
	}
	
	public JPanel getPanel(){
		return panel;
	}

}
