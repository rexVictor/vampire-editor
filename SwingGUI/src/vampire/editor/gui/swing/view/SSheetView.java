package vampire.editor.gui.swing.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import vampire.editor.plugin.api.view.sheet.CategoryView;
import vampire.editor.plugin.api.view.sheet.MetaView;
import vampire.editor.plugin.api.view.sheet.SheetView;

public class SSheetView implements SheetView{
	
	private final JPanel panel = new JPanel();
	
	private final FormLayout layout = new FormLayout();
	
	private final List<SCategoryView> categoryViews = new ArrayList<>();
	
	public SSheetView(){
		initialize();
	}
	
	private void initialize(){
		panel.setLayout(layout);
		layout.appendColumn(ColumnSpec.decode("pref:GROW"));
		
	}

	@Override
	public void add(CategoryView categoryView) {
		SCategoryView view = (SCategoryView) categoryView;
		categoryViews.add(view);
		layout.appendRow(RowSpec.decode("pref:GROW"));
		
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight	=	1;
		constraints.gridWidth	=	1;
		constraints.gridX		=	1;
		constraints.gridY		=	layout.getRowCount();
		constraints.hAlign		=	CellConstraints.FILL;
		
		panel.add(view.getPanel(), constraints);
		
		
	}

	public JPanel getPanel() {
		return panel;
	}

	@Override
	public List<SCategoryView> getCategoryViews() {
		return new ArrayList<>(categoryViews);
	}
	
	public void addMetaView(MetaView metaView){
		SMetaView view = (SMetaView) metaView;
		layout.appendRow(RowSpec.decode("pref"));
		
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight	=	1;
		constraints.gridWidth	=	1;
		constraints.gridX		=	1;
		constraints.gridY		=	1;
		constraints.hAlign		=	CellConstraints.FILL;
		
		panel.add(view.getPanel(), constraints);
	}
	
	
	
	


}
