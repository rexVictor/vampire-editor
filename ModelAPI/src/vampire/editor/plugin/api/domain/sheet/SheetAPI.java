package vampire.editor.plugin.api.domain.sheet;


public interface SheetAPI {

	public SheetAPI clone();

	public MetaAPI getMeta();

	public DataAPI<? extends CategoryAPI> getCategories();
	
	public BloodPoolAPI getBloodPool();
	
	public HealthAPI	getHealth();
	
	public MeritsAPI	getMerits();
	
	public MeritsAPI	getFlaws();
	
	public String getBorderKey();

}