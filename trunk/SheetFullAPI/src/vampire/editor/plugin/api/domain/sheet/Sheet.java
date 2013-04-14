package vampire.editor.plugin.api.domain.sheet;

public interface Sheet extends SheetAPI{

	public String getBorderKey();

	public void setBorderKey(String borderKey);

	public Sheet clone();

	public void setCategories(Categories categories);

	public void setMeta(Meta meta);

	public void setBloodPool(BloodPool bloodPool);

	public void setHealth(Health health);

	public void setMerits(Merits merits);

	public void setFlaws(Merits merits);

	public Meta getMeta();

	public Categories getCategories();

	public Merits getMerits();

	public Merits getFlaws();

	public BloodPool getBloodPool();

	public Health getHealth();

}