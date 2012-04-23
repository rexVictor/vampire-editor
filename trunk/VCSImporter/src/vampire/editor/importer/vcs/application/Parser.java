package vampire.editor.importer.vcs.application;

import java.nio.charset.Charset;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import vampire.editor.importer.vcs.domain.Strings;
import vampire.editor.plugin.api.domain.sheet.MetaEntryAPI;
import vampire.editor.plugin.api.domain.sheet.Nameable;
import vampire.editor.plugin.fullapi.sheet.IData;
import vampire.editor.plugin.fullapi.sheet.IMetaEntry;
import vampire.editor.plugin.fullapi.sheet.ISheet;
import vampire.editor.plugin.fullapi.sheet.SheetConstructors;

public class Parser {
	
	private final Strings strings = new Strings();
	
	private final List<String> meta = new ArrayList<>();
	
	private final List<Byte> source;
	
	private final Charset charset = Charset.forName("ISO-8859-1");
	
	private final ISheet sheet;	
	
	private final SheetConstructors constructors;
	
	public Parser(List<Byte> source, ISheet sheet, SheetConstructors constructors) {
		super();
		this.source = source;
		this.sheet = sheet;
		this.constructors = constructors;
	}
	
	public void parse(){
		List<Byte> stringChars = source.subList(0x45, source.size()-1);
		parseStrings(stringChars);
		stringChars.clear();
		splitStrings();
		parseMeta(source);
	}
	
	private void parseStrings(List<Byte> list){
		if (list.size()==0) return; //check for recursion stop
									//it seems surprising that equality is demanded, but a valid document
									//must hold this condition.
		byte length = list.get(0);
		List<Byte> subList = list.subList(1, length+1);
		Byte[] tempArray = subList.toArray(new Byte[subList.size()]);
		byte[] array = new byte[tempArray.length];
		for (int i = 0; i< array.length; i++){
			array[i] = tempArray[i];
		}
		String parsed = new String(array, charset);
		strings.add(parsed);
		list.subList(0, length+1).clear();
		parseStrings(list);
	}
	
	private void splitStrings() {
		for (int i = 0; i <= 11; i++){
			if (i == 5 || i == 9) continue;
			meta.add(strings.get(i));
		}				
	}
	
	private List<String> getOrder(IData<? extends Nameable, ?> data){
		Iterator<? extends Nameable> iterator = data.getIterator();
		List<String> order = new ArrayList<>();
		while (iterator.hasNext()){
			order.add(iterator.next().getName());
		}
		return order;
	}
	
	private void parseMeta(List<Byte> list){
		IData<MetaEntryAPI, IMetaEntry> meta = sheet.getMeta();
		List<String> order = getOrder(meta);
		meta.sort(new MetaSorter());
		Iterator<IMetaEntry> iterator = meta.getIterator();
		
		int i = 0;
		while (iterator.hasNext()) {
			IMetaEntry entry = iterator.next();
			
			if (entry.getName().equals("generation")) {
				entry.setValue(list.get(0x1)+"");
				continue;
			}
			
			entry.setValue(this.meta.get(i));
			i++;
		}
		
		meta.sort(new MetaSorter(order));		
	}

}
