package vampire.editor.sheetloader.application.exporter;

import static org.junit.Assert.*;

import java.awt.Font;
import java.util.Map;

import org.junit.Test;

import vampire.editor.domain.sheet.MetaEntry;
import vampire.editor.domain.sheet.Value;
import vampire.editor.domain.sheet.view.CategoryViewAttributes;
import vampire.editor.domain.sheet.view.SubCategoryViewAttributes;
import vampire.editor.domain.sheet.view.TraitViewAttributes;
import vampire.editor.domain.sheet.view.ValueViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.Orientation;

public class RawExportTest {
	
	@Test
	public void testConvertMetaEntry1(){
		testMetaEntryConversionWith("name", "value");
	}
	
	@Test
	public void testConvertMetaEntry2(){
		testMetaEntryConversionWith("title", "content");
	}
	
	@Test
	public void testConvertCategoryViewAttributes1(){
		testCategoryViewAttsConversionWith(false, "Kuckuck", "vtmdefault", null);
	}
	
	@Test
	public void testConvertCategoryViewAttributes2(){
		Font font = new Font("Sans Serif", 2, 50);
		testCategoryViewAttsConversionWith(true, "Test", "iwas", font);
	}
	
	@Test
	public void testConvertCategoryViewAttributes3(){
		Font font = new Font("Sans Serif", 0, 20);
		testCategoryViewAttsConversionWith(false, "test", "testing", font);
	}
	
	@Test
	public void testConvertSubCategoryViewAttributes1(){
		testSubCategoryViewAttsConversionWith(false, true, null);
	}
	
	@Test
	public void testConvertSubCategoryViewAttributes2(){
		Font font = new Font("Sans Serif", 0, 10);
		testSubCategoryViewAttsConversionWith(true, false, font);
	}
	
	@Test
	public void testConvertSubCategoryViewAttributes3(){
		Font font = new Font("Sans Serif", 0, 10);
		testSubCategoryViewAttsConversionWith(true, true, font);
	}
	
	
	@Test
	public void testConvertTraitViewAttribures1() {
		Font font = new Font("Linux Libertine", 0, 10);
		testTraitViewAttsConversionWith(true, Orientation.HORIZONTAL, false, font);
	}
	
	@Test
	public void testConvertTraitViewAttribures2() {
		Font font = null;
		testTraitViewAttsConversionWith(false, Orientation.HORIZONTAL, true, font);
	}
	
	@Test
	public void testConvertTraitViewAttribures3() {
		Font font = new Font("Sans Serif", 1, 46);
		testTraitViewAttsConversionWith(false, Orientation.VERTICAL, false, font);
	}

	@Test
	public void testConvertValueViewAttributes1() {
		testValueViewAttributesConversionWith(6, false, true, 10, false);
	}
	
	@Test
	public void testConvertValueViewAttributes2() {
		testValueViewAttributesConversionWith(7, true, true, 15, true);
	}
	
	@Test
	public void testConvertValueViewAttributes3() {
		testValueViewAttributesConversionWith(4, false, false, 8, false);
	}
	
	@Test
	public void testConvertValue1() {
		testValueConversionWith(0, 3, 5, 10);
	}
	
	@Test
	public void testConvertValue2() {
		testValueConversionWith(4, 5, 6, 8);
	}
	
	@Test
	public void testConvertValue3() {
		testValueConversionWith(0, 0, -1, 5);
	}
	
	
	private void testValueViewAttributesConversionWith(int circles, boolean dynamic, boolean showSpace, int size,
			boolean tempSquared){
		ValueViewAttributes atts = new ValueViewAttributes(showSpace, dynamic, circles, tempSquared, size);
		RawExporter exporter = new RawExporter();
		Map<String, Object> map = exporter.export(atts);

		int exportedCircles = (int) map.get("circles");
		boolean exportedDynamic = (boolean) map.get("dynamic");
		boolean exportedShowSpace = (boolean) map.get("showSpace");
		boolean exportedTempSquared = (boolean) map.get("tempSquared");
		int exportedSize = (int) map.get("size");
		
		assertEquals(circles, exportedCircles);
		assertEquals(dynamic, exportedDynamic);
		assertEquals(showSpace, exportedShowSpace);
		assertEquals(tempSquared, exportedTempSquared);
		assertEquals(size, exportedSize);
	}
	
	private void testValueConversionWith(int minValue, int value, int tempValue, int maxValue){
		Value valueObject = new Value(value, minValue, maxValue);
		valueObject.setTempValue(tempValue);
		RawExporter exporter = new RawExporter();
		Map<String, Object> map = exporter.export(valueObject);

		int exportedMinValue = (int) map.get("minValue");
		int exportedValue = (int) map.get("value");
		int exportedMaxValue = (int) map.get("maxValue");
		int exportedTempValue = (int) map.get("tempValue");
		
		assertEquals(minValue, exportedMinValue);
		assertEquals(value, exportedValue);
		assertEquals(maxValue, exportedMaxValue);
		assertEquals(tempValue, exportedTempValue);
	}
	
	private void testTraitViewAttsConversionWith(boolean editable, Orientation orientation, boolean squares, Font font){
		TraitViewAttributes viewAtts = new TraitViewAttributes(editable, orientation, squares, font);
		RawExporter exporter = new RawExporter();
		Map<String, Object> map = exporter.export(viewAtts);

		boolean exportedEditable = (boolean) map.get("editable");
		String exportedOrientation = (String) map.get("orientation");
		boolean exportedSquares = (boolean) map.get("squares");
		
		assertEquals(null, map.get("font"));
		assertEquals(editable, exportedEditable);
		assertEquals(orientation.toString(), exportedOrientation);
		assertEquals(squares, exportedSquares);
	}
	
	private void testSubCategoryViewAttsConversionWith(boolean showTitle, boolean expandable, Font font){
		SubCategoryViewAttributes viewAtts = new SubCategoryViewAttributes(expandable, showTitle);
		viewAtts.setFont(font);
		RawExporter exporter = new RawExporter();
		Map<String, Object> map = exporter.export(viewAtts);

		boolean exportedShowTitle = (boolean) map.get("showTitle");
		boolean exportedExpandable = (boolean) map.get("expandable");
		
		assertEquals(null, map.get("font"));
		assertEquals(showTitle, exportedShowTitle);
		assertEquals(expandable, exportedExpandable);
	}
	
	private void testCategoryViewAttsConversionWith(boolean showLine, String title, String image, Font font){
		CategoryViewAttributes viewAtts = new CategoryViewAttributes();
		viewAtts.setFont(font);
		viewAtts.setImage(image);
		viewAtts.setShowLine(showLine);
		viewAtts.setTitle(title);
		RawExporter exporter = new RawExporter();
		Map<String, Object> map = exporter.export(viewAtts);

		String exportedTitle = (String) map.get("title");
		boolean exportedShowLine = (boolean) map.get("showLine");
		String exportedImage = (String) map.get("image");
		
		assertEquals(null, map.get("font"));
		assertEquals(showLine, exportedShowLine);
		assertEquals(title, exportedTitle);
		assertEquals(image, exportedImage);
	}
	
	private void testMetaEntryConversionWith(String name, String value){
		MetaEntry metaEntry = new MetaEntry();
		metaEntry.setName(name);
		metaEntry.setValue(value);
		
		RawExporter exporter = new RawExporter();
		Map<String, Object> map = exporter.export(metaEntry);
		
		String exportedName = (String) map.get("name");
		String exportedValue =  (String) map.get("value");
		
		assertEquals(name, exportedName);
		assertEquals(value, exportedValue);
	}


}
