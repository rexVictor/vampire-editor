package vampire.editor.plugin.api.domain.sheet;

public interface Merit extends MeritAPI{

	public int getCost();

	public void setCost(int cost);

	public String getName();

	public void setName(String name);

	public Merit clone();

}