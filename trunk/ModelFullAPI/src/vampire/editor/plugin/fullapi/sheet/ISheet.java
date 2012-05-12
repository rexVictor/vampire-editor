package vampire.editor.plugin.fullapi.sheet;

public interface ISheet{

	public ISheet clone();

	public IData<IMetaEntry> getMeta();

	public IData<ICategory> getCategories();

}