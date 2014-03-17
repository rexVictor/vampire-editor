package vampire.editor.gui.swing.application;

import java.awt.Image;
import java.util.LinkedList;
import java.util.List;

import vampire.editor.gui.swing.view.LineImage;
import vampire.editor.gui.swing.view.SCategoryView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.plugin.api.domain.sheet.CategoryAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.view.CategoryViewAttributes;
import vampire.editor.plugin.api.plugin.CategoryViewFactory;
import vampire.editor.plugin.api.plugin.CategoryViewFactoryModule;
import vampire.editor.plugin.api.plugin.SubCategoryViewFactory;
import vampire.editor.plugin.api.view.sheet.CategoryView;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;

public class SCategoryViewFactory implements CategoryViewFactory{
	
	private final List<CategoryViewFactoryModule> modules = new LinkedList<>();
	
	private final ResourcesHolderAPI resources;
	
	private final DictionaryAPI dictionary;
	
	private final SubCategoryViewFactory subCategoryViewFactory;
	
	

	public SCategoryViewFactory(ResourcesHolderAPI resources,
			DictionaryAPI dictionary,
			SubCategoryViewFactory subCategoryViewFactory) {
		super();
		this.resources = resources;
		this.dictionary = dictionary;
		this.subCategoryViewFactory = subCategoryViewFactory;
	}

	@Override
	public void add(CategoryViewFactoryModule module) {
		modules.add(module);
	}

	@Override
	public CategoryView build(CategoryAPI category,
			ModelToViewModelMapperAPI mapper) {
		CategoryViewAttributes viewAtts = (CategoryViewAttributes) mapper.getViewAttributes(category); 
		SCategoryView categoryView = new SCategoryView();
		List<SubCategoryView> subCategoryViews = subCategoryViewFactory.build(category, mapper);
		for (SubCategoryView subCategoryView : subCategoryViews){
			categoryView.add(subCategoryView);
		}
		if (viewAtts.isShowLine()){
			Image line = resources.getLine(viewAtts.getImage());
			line = line.getScaledInstance(SizeConverter.millimetersToPixel(180), -1, Image.SCALE_SMOOTH);
			LineImage lineImage = new LineImage(line, dictionary.getValue(category.getName()), viewAtts.getFont());
			categoryView.addLine(lineImage);
		}
		return categoryView;
	}

}
