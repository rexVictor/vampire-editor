package vampire.editor.plugin.api.domain.sheet;


public interface SheetAPI {

	public SheetAPI clone();

	public DataAPI<? extends MetaEntryAPI> getMeta();

	public DataAPI<? extends CategoryAPI> getCategories();
	
	public BloodPoolAPI getBloodPool();
	
	public HealthAPI	getHealth();
	
	public MeritsAPI	getMerits();
	
	public MeritsAPI	getFlaws();

}