package vampire.editor.persistency.i18n;

public interface Dictionary {
	
	public String getValue(String key);
	
	public String getKey(String value);
	
	public void addPair(String key, String value);

}
