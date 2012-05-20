package vampire.editor.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.VersionAPI;

public class Version implements VersionAPI{
	
	private final byte[] version = new byte[]{1,0,0,0};
	
	private final String description = "Default model";

	@Override
	public byte[] getVersion() {
		return version;
	}

	@Override
	public String getDescription() {
		return description;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(description + " - ");
		for (byte b : version){
			sb.append(b+".");
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();		
	}

}
