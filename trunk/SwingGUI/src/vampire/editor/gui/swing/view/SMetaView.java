package vampire.editor.gui.swing.view;

import java.util.List;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import vampire.editor.plugin.api.view.events.DataViewListener;
import vampire.editor.plugin.api.view.sheet.MetaEntryView;
import vampire.editor.plugin.api.view.sheet.MetaView;

public class SMetaView implements MetaView{
	
	private final JPanel panel = new JPanel();
	
	private final FormLayout layout = new FormLayout();
	
	private int added = 0;
	
	public SMetaView(){
		initialize();
	}
	
	private void initialize(){
		panel.setLayout(layout);
		layout.appendRow(RowSpec.decode("pref"));
		layout.appendRow(RowSpec.decode("pref"));
		layout.appendRow(RowSpec.decode("pref"));
		layout.appendRow(RowSpec.decode("pref"));
		layout.appendColumn(ColumnSpec.decode("pref:GROW"));
		layout.appendColumn(ColumnSpec.decode("pref:GROW"));
		layout.appendColumn(ColumnSpec.decode("pref:GROW"));
		layout.setColumnGroups(new int[][]{{1,2,3}});
		
	}

	@Override
	public List<? extends MetaEntryView> getEntries() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(MetaEntryView entry) {
		SMetaEntryView view = (SMetaEntryView) entry;
		int lineCount = view.getViewAtts().getLineCount();
		

		int x = added/4+1;
		int y = added%4+1;
		
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight	=	lineCount;
		constraints.gridWidth	=	1;
		constraints.gridX		=	x;
		constraints.gridY		=	y;
		constraints.hAlign		=	CellConstraints.FILL;
		
		panel.add(view.getPanel(), constraints);
		
		added+=lineCount;
		
	}

	@Override
	public void remove(MetaEntryView entry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(int pos, MetaEntryView entry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListener(DataViewListener<MetaEntryView> listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(DataViewListener<MetaEntryView> listener) {
		// TODO Auto-generated method stub
		
	}
	
	public JPanel getPanel(){
		return panel;
	}

}