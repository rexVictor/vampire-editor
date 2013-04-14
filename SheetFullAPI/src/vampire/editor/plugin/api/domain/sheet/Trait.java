package vampire.editor.plugin.api.domain.sheet;

public interface Trait extends TraitAPI{

	public String getName();

	public void setName(String name);

	public Value getValue();

	public Trait clone();

	public void setValue(Value value);

}