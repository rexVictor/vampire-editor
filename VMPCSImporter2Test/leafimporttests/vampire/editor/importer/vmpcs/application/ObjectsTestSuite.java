package vampire.editor.importer.vmpcs.application;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BloodpoolViewImportTest.class,
		CategoryViewAttsImportTest.class, FontImportTest.class,
		HealthEntryImportTest.class, HealthViewAttsImportTest.class,
		MeritEntryViewAttsImportTest.class, MeritViewAttsImportTest.class,
		MetaEntryViewAttsImportTest.class, SubCategoryViewAttsImportTest.class,
		TraitViewAttsImportTest.class, ValueImportTest.class,
		ValueViewAttsImporterTest.class })
public class ObjectsTestSuite {

}
