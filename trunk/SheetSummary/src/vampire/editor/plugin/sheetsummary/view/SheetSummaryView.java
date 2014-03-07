package vampire.editor.plugin.sheetsummary.view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SheetSummaryView {
	
	private final JPanel panel = new JPanel();
	
	private final CatSummaryView attributes;
	
	private final CatSummaryView abilities;
	
	private final SubCatSummaryView virtues;
	
	private final SubCatSummaryView disciplines;
	
	private final SubCatSummaryView backgrounds;

	public SheetSummaryView(CatSummaryView attributes,
			CatSummaryView abilities, SubCatSummaryView virtues,
			SubCatSummaryView disciplines, SubCatSummaryView backgrounds) {
		super();
		this.attributes = attributes;
		this.abilities = abilities;
		this.virtues = virtues;
		this.disciplines = disciplines;
		this.backgrounds = backgrounds;
		panel.setLayout(new GridLayout(0, 2));
		panel.add(new JLabel("attributes"));
		panel.add(attributes.getPanel());
		panel.add(new JLabel("abilities"));
		panel.add(abilities.getPanel());
		panel.add(new JLabel("virtues"));
		panel.add(virtues.getComponent());
		panel.add(new JLabel("disciplines"));
		panel.add(disciplines.getComponent());
		panel.add(new JLabel("backgrounds"));
		panel.add(backgrounds.getComponent());
	}

	public JPanel getPanel() {
		return panel;
	}

	public CatSummaryView getAttributes() {
		return attributes;
	}

	public CatSummaryView getAbilities() {
		return abilities;
	}

	public SubCatSummaryView getVirtues() {
		return virtues;
	}

	public SubCatSummaryView getDisciplines() {
		return disciplines;
	}

	public SubCatSummaryView getBackgrounds() {
		return backgrounds;
	}
	
	

}
