package vampire.editor.plugin.sheetsummary.view;

import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CatSummaryView {
	
	private final JPanel panel = new JPanel();
	
	private final List<SubCatSummaryView> subCatSummaryViews;
	
	public CatSummaryView(SubCatSummaryView... subCatSummaryViews){
		this.subCatSummaryViews = Arrays.asList(subCatSummaryViews);
		panel.setLayout(new GridLayout());
		JLabel paranthesisLeft = new JLabel("("); //$NON-NLS-1$
		panel.add(paranthesisLeft);
		SubCatSummaryView first = subCatSummaryViews[0];
		panel.add(first.getComponent());
		for (int i = 1; i < subCatSummaryViews.length; i++){
			SubCatSummaryView subCatSummaryView = subCatSummaryViews[i];
			JLabel line = new JLabel("|"); //$NON-NLS-1$
			panel.add(line);
			panel.add(subCatSummaryView.getComponent());
		}
		JLabel paranthesisRight = new JLabel(")"); //$NON-NLS-1$
		panel.add(paranthesisRight);
	}

	public JPanel getPanel() {
		return panel;
	}

	public List<SubCatSummaryView> getSubCatSummaryViews() {
		return subCatSummaryViews;
	}
	
}
