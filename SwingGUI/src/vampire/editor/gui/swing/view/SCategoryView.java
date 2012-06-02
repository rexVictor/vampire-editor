package vampire.editor.gui.swing.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import vampire.editor.gui.swing.domain.Line;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.view.events.DataViewListener;
import vampire.editor.plugin.api.view.sheet.CategoryView;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;
import vampire.editor.plugin.fullapi.sheet.view.ICategoryViewAttributes;

public class SCategoryView implements CategoryView{
	
	private final JPanel panel = new JPanel();
	
	private final ICategoryViewAttributes viewAtts;
	
	private final DictionaryAPI dictionary;
	
	private final FormLayout layout = new FormLayout();
	
	private final List<SSubCategoryView> subCategoryViews = new ArrayList<>();
	
	private LineImage title;
	
	

	public SCategoryView(ICategoryViewAttributes viewAtts,
			DictionaryAPI dictionary, String title) {
		super();
		this.viewAtts = viewAtts;
		this.dictionary = dictionary;
		initialize(title);
	}
	
	private void initialize(String titleName){
		panel.setLayout(layout);
		panel.setBackground(Color.WHITE);
		layout.appendColumn(ColumnSpec.decode("10px"));
		if (viewAtts.isShowLine()){
			Line line = Line.getDefault();
			title = new LineImage(line.getImage());
			title.setTitle(dictionary.getValue(titleName));
			title.setTitleFont(viewAtts.getFont());
			layout.appendRow(RowSpec.decode("50px"));
			
			
			CellConstraints constraints = new CellConstraints();
			constraints.gridX		=	1;
			constraints.gridY		=	1;
			constraints.gridHeight	=	1;
			constraints.gridWidth	=	1;
			
			panel.add(title.getPanel(), constraints);			
			
		}
		layout.appendRow(RowSpec.decode("pref"));
	}
	
	private void refreshTitle(){
		if (true) {
			JPanel panel = title.getPanel();
			CellConstraints constraints = layout.getConstraints(panel);
			this.panel.remove(panel);
			constraints.gridWidth = layout.getColumnCount();
			layout.removeRow(constraints.gridY);
			layout.insertRow(constraints.gridY, RowSpec.decode(panel.getHeight()+"px"));
			this.panel.add(panel, constraints);
			this.panel.invalidate();
			this.panel.revalidate();
			title.getPanel().repaint();
			
		}
	}

	@Override
	public List<? extends SubCategoryView> getEntries() {
		return new ArrayList<>(subCategoryViews);
	}

	@Override
	public void add(SubCategoryView entry) {
		SSubCategoryView view = (SSubCategoryView) entry;
		subCategoryViews.add(view);
		
		layout.appendColumn(ColumnSpec.decode("pref:GROW"));
		
		layout.addGroupedColumn(layout.getColumnCount());
		
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight	=	1;
		constraints.gridWidth	=	1;
		constraints.gridX		=	layout.getColumnCount();
		constraints.gridY		=	layout.getRowCount();
		constraints.hAlign		=	CellConstraints.FILL;
		
		panel.add(view.getPanel(), constraints);
		layout.appendColumn(ColumnSpec.decode("10px"));
		
		refreshTitle();
		
	}

	@Override
	public void remove(SubCategoryView entry) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public void insert(int pos, SubCategoryView entry) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addListener(DataViewListener<SubCategoryView> listener) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public void removeListener(DataViewListener<SubCategoryView> listener) {
		throw new UnsupportedOperationException();
		
	}

	public JPanel getPanel() {
		return panel;
	}
	
	

}
