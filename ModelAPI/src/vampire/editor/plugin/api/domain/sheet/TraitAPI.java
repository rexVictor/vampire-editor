package vampire.editor.plugin.api.domain.sheet;

public interface TraitAPI extends Nameable{

	@Override
	public String getName();

	public void setName(String name);

	public ValueAPI getValue();
}