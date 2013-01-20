package vampire.editor.plugin.api.domain.sheet.view;

import java.io.Serializable;

public interface ValueViewAttributesAPI extends PublicCloneable, Serializable{
	
	public boolean isShowSpace();
	
	public boolean isDynamic();
	
	public int getCircles();
	
	public boolean isTempSquared();
	
	public int getSize();
	
	@Override
	public ValueViewAttributesAPI clone();

}
