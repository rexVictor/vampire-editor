package vampire.editor.gui.swing.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
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
	
	

	public SCategoryView(ICategoryViewAttributes viewAtts,
			DictionaryAPI dictionary) {
		super();
		this.viewAtts = viewAtts;
		this.dictionary = dictionary;
		initialize();
	}
	
	private void initialize(){
		panel.setLayout(layout);
		layout.appendColumn(ColumnSpec.decode("pref:GROW"));
		if (viewAtts.isShowLine()){
			JLabel title = new JLabel();
			title.setText(dictionary.getValue(viewAtts.getTitle()));
			layout.appendRow(RowSpec.decode("pref"));
			
			
			CellConstraints constraints = new CellConstraints();
			constraints.gridX		=	1;
			constraints.gridY		=	1;
			constraints.gridHeight	=	1;
			constraints.gridWidth	=	1;
			constraints.hAlign		=	CellConstraints.FILL;
			
			panel.add(title, constraints);
			
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
		
		layout.appendColumn(ColumnSpec.decode("10px"));
		layout.appendColumn(ColumnSpec.decode("pref:GROW"));
		
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight	=	1;
		constraints.gridWidth	=	1;
		constraints.gridX		=	layout.getColumnCount();
		constraints.gridY		=	layout.getRowCount();
		constraints.hAlign		=	CellConstraints.FILL;
		
		panel.add(view.getPanel(), constraints);
		
	}

	@Override
	public void remove(SubCategoryView entry) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
		
	}

	@Override
	public void insert(int pos, SubCategoryView entry) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
		
	}

	@Override
	public void addListener(DataViewListener<SubCategoryView> listener) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
		
	}

	@Override
	public void removeListener(DataViewListener<SubCategoryView> listener) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
		
	}

	public JPanel getPanel() {
		return panel;
	}
	
	

}
