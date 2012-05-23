package vampire.editor.plugin.api.domain.sheet;


public interface SheetAPI {

	public SheetAPI clone();

	public DataAPI<? extends MetaEntryAPI> getMeta();

	public DataAPI<? extends CategoryAPI> getCategories();

}