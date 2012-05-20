package vampire.editor.plugin.api.domain.sheet;


public interface HealthEntryAPI extends Nameable{

	public int getPenalty();

	@Override
	public String getName();

	public DamageType getDamageType();

	@Override
	public HealthEntryAPI clone();

}