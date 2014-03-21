package vampire.editor.gui.swing.application.factories;

import java.awt.Image;

import vampire.editor.gui.swing.application.SizeConverter;
import vampire.editor.gui.swing.view.LineImage;
import vampire.editor.gui.swing.view.SCategoryView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.plugin.api.domain.sheet.CategoryAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.view.CategoryViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.LineAttributes;
import vampire.editor.plugin.api.plugin.CategoryViewFactoryModule;
import vampire.editor.plugin.api.view.sheet.CategoryView;

public class LineAdder implements CategoryViewFactoryModule{
	
	private final ResourcesHolderAPI resources;
	
	private final DictionaryAPI dictionary;
	
	public LineAdder(ResourcesHolderAPI resources, DictionaryAPI dictionary) {
		super();
		this.resources = resources;
		this.dictionary = dictionary;
	}

	@Override
	public void process(CategoryAPI category, ModelToViewModelMapperAPI mapper,
			CategoryView categoryView) {
		CategoryViewAttributes viewAtts = (CategoryViewAttributes) mapper.getViewAttributes(category);
		if (viewAtts.isShowLine()){
			SCategoryView cv = (SCategoryView) categoryView;
			LineAttributes lineAtts = viewAtts.getLine();
			Image line = resources.getLine(lineAtts.getKey());
			line = line.getScaledInstance(SizeConverter.millimetersToPixel(lineAtts.getWidth()),
											lineAtts.getHeight(), Image.SCALE_SMOOTH);
			LineImage lineImage = new LineImage(line, dictionary.getValue(category.getName()), viewAtts.getFont());
			cv.addLine(lineImage);
		}
	}

}
