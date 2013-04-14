package vampire.editor.importer.vmpcs.application;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ProtoBloodPoolImportTest.class, ProtoCategoryImportTest.class,
		ProtoHealthImportTest.class, ProtoMeritsImportTest.class,
		ProtoMetaImportTest.class, ProtoSheetImportTest.class,
		ProtoSubCategoryImportTest.class, ProtoTraitImportTest.class,
		ProtoValueImportTest.class })
public class ProtoImportTests {

}
