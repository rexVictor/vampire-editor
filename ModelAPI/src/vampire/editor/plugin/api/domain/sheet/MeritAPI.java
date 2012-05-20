package vampire.editor.plugin.api.domain.sheet;

public interface MeritAPI extends Nameable{

	public int getCost();

	@Override
	public String getName();
	
	@Override
	public MeritAPI clone();

}