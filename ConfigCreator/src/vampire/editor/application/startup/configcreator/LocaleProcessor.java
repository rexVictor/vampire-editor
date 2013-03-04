package vampire.editor.application.startup.configcreator;

import java.util.Locale;

import org.jdom2.Element;

class LocaleProcessor implements ElementProcessor{

	@Override
	public void process(Element element, ConfigCreator configCreator) {
		String language = element.getAttributeValue(ConfigStrings.LANGUAGE);
		String country = element.getAttributeValue(ConfigStrings.COUNTRY);
		String variant = element.getAttributeValue(ConfigStrings.VARIANT);
		Locale locale = new Locale(language, country, variant);
		Locale.setDefault(locale);
	}

	@Override
	public String getName() {
		return ConfigStrings.LOCALE;
	}

}
