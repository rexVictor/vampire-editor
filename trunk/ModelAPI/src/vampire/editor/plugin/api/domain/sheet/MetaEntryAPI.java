package vampire.editor.plugin.api.domain.sheet;

public interface MetaEntryAPI extends Nameable{

	@Override
	public String getName();

	public String getValue();

	@Override
	public MetaEntryAPI clone();	

}