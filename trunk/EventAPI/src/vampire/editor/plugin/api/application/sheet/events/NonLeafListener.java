package vampire.editor.plugin.api.application.sheet.events;

public interface NonLeafListener<E> {
	
	public void added(E e);
	
	public void removed(E e);

}
