package vampire.editor.plugin.api.domain.sheet.view;

public interface ValueViewAttributesAPI extends PublicCloneable{
	
	public boolean isShowSpace();
	
	public boolean isDynamic();
	
	public int getCircles();
	
	public boolean isTempSquared();
	
	public int getSize();
	
	@Override
	public ValueViewAttributesAPI clone();

}
