package vampire.editor.plugin.api.domain.sheet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface VersionModelSpecification {
	
	public String version();
	
	public String[] compatibleVersions();

}
