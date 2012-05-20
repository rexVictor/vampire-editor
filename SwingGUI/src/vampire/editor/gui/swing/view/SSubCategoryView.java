package vampire.editor.gui.swing.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.TraitAPI;
import vampire.editor.plugin.api.view.events.DataViewListener;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;
import vampire.editor.plugin.api.view.sheet.TraitView;
import vampire.editor.plugin.fullapi.sheet.view.ISubCategoryViewAttributes;
import vampire.editor.plugin.fullapi.sheet.view.ITraitViewAttributes;
import vampire.editor.plugin.fullapi.sheet.view.IValueViewAttributes;

public class SSubCategoryView implements SubCategoryView{
	
	private final JPanel panel = new JPanel();
	
	private final List<TraitView> traitViews = new ArrayList<>();
	
	private final ISubCategoryViewAttributes atts;
	
	private final DictionaryAPI dictionary;
	
	private final FormLayout layout = new FormLayout();
	
	public SSubCategoryView(ISubCategoryViewAttributes atts, DictionaryAPI dictionary, String title) {
		super();
		this.atts = atts;
		this.dictionary = dictionary;
		initialize(title);
	}

	private void initialize(String title){
		layout.appendColumn(ColumnSpec.decode("pref:GROW"));
		if (atts.isShowTitle()){
			layout.appendRow(RowSpec.decode("pref"));
			JTextField textField = new JTextField();
			textField.setEditable(false);
			textField.setText(dictionary.getValue(title));
			
			CellConstraints constraints = new CellConstraints();
			constraints.gridHeight	=	1;
			constraints.gridWidth	=	1;
			constraints.gridX		=	1;
			constraints.gridY		=	1;
			constraints.hAlign		=	CellConstraints.CENTER;
			
			panel.add(textField, constraints);
		}
	}

	@Override
	public List<TraitView> getEntries() {
		return new ArrayList<>(traitViews);
	}

	@Override
	public void add(TraitView entry) {
		STraitView view = (STraitView) entry;
		layout.appendRow(RowSpec.decode("pref"));
		
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight	=	1;
		constraints.gridWidth	=	1;
		constraints.gridX		=	1;
		constraints.gridY		=	layout.getRowCount();
		constraints.hAlign		=	CellConstraints.LEFT;
		
		panel.add(view.getPanel(), constraints);
		traitViews.add(view);
	}

	@Override
	public void remove(TraitView entry) {
		if (entry instanceof STraitView){
			STraitView view = (STraitView) entry;
			CellConstraints constraints = layout.getConstraints(view.getPanel());
			panel.remove(view.getPanel());
			layout.removeRow(constraints.gridY);
			traitViews.remove(view);
		}		
	}

	@Override
	public void insert(int pos, TraitView entry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListener(DataViewListener<?> listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(DataViewListener<?> listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TraitView add(TraitAPI entry) {
		SValueView valueView = new SValueView((IValueViewAttributes) entry.getValue().getViewAtts());
		STraitView traitView = new STraitView(valueView, dictionary, (ITraitViewAttributes) entry.getViewAtts());
		traitView.setName(entry.getName());
		add(traitView);
		return traitView;
	}

}
