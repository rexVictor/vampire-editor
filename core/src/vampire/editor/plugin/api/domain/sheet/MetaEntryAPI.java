package vampire.editor.plugin.api.domain.sheet;

public interface MetaEntryAPI extends Nameable{

	public String getName();

	public String getValue();

	public MetaEntryAPI clone();

}