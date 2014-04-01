package vampire.editor.application.startup.configcreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Element;

import vampire.editor.plugin.api.domain.sheet.ModelConstructors;
import vampire.editor.plugin.api.domain.sheet.VersionModelImplementation;
import vampire.editor.plugin.api.domain.sheet.VersionModelSpecification;

public class ModelProcessor implements ElementProcessor{

	private final NullProcessor nullProcessor = new NullProcessor();
	private final PathClassProcessor pathClassProcessor = new PathClassProcessor();

	@Override
	public void process(Element element, ConfigCreator configCreator) {
		Attribute path = element.getAttribute(ConfigStrings.PATH);
		Attribute file = element.getAttribute(ConfigStrings.FILE);
		ClassLoader loader = null;
		if(path == null){
			loader = nullProcessor.process(null, null, null, null);
		}
		else {
			loader = pathClassProcessor.process(null, configCreator, path, file);
		}
		String clazzname = element.getAttributeValue(ConfigStrings.CLASS);
		try {
			@SuppressWarnings("unchecked")
			Class<ModelConstructors> clazz = (Class<ModelConstructors>) loader.loadClass(clazzname);
			VersionModelSpecification versionSpec 
				= ModelConstructors.class.getAnnotation(VersionModelSpecification.class);
			VersionModelImplementation versionImpl = clazz.getAnnotation(VersionModelImplementation.class);
			List<String> compatibleSpecs = new ArrayList<>(Arrays.asList(versionSpec.compatibleVersions()));
			compatibleSpecs.add(versionSpec.version());
			List<String> compatibleImpls = new ArrayList<>(Arrays.asList(versionImpl.compatibleWithSpecifications()));
			if (Collections.disjoint(compatibleSpecs, compatibleImpls)){
				throw new ConfigImportException("The Model implementation does not support the current specification."); //$NON-NLS-1$
			}
			configCreator.setModelConstructors(clazz.newInstance());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new ConfigImportException(e);
		}
	}

	@Override
	public String getName() {
		return "model"; //$NON-NLS-1$
	}

}
