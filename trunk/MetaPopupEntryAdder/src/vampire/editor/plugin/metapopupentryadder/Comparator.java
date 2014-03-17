package vampire.editor.plugin.metapopupentryadder;

import java.text.Collator;

import vampire.editor.plugin.api.domain.DictionaryAPI;

public class Comparator implements java.util.Comparator<String>{
	
	private final Collator collator = Collator.getInstance();
	
	private final DictionaryAPI dictionary;
	
	

	public Comparator(DictionaryAPI dictionary) {
		super();
		this.dictionary = dictionary;
	}



	@Override
	public int compare(String o1, String o2) {
		return collator.compare(dictionary.getValue(o1), dictionary.getValue(o2));
	}

}
