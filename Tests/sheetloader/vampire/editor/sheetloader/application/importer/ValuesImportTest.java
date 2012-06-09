package vampire.editor.sheetloader.application.importer;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import vampire.editor.domain.sheet.Value;
import vampire.editor.plugin.Manager;

/**
 * The Test Case for the Values class, which is responsible for the import of the values.json files.
 * @author rex
 *
 */
public class ValuesImportTest {
	/**
	 * The paths of the json files mapped by their ending number.
	 */
	private static final Map<Integer, Path> valueFiles = new HashMap<>();
	
	/**
	 * The paths of the default serialized objects mapped by their ending number.
	 */
	
	private static final Map<Integer, Path> expectedFiles = new HashMap<>();
	
	/**
	 * This Method initializes the declared fields and ensures all information for the tests is present. <br>
	 * If a test file is missing it will run a creation "assistant" in the console to create one for future tests.
	 * @throws Throwable
	 */
	@BeforeClass
	public static void checkTestCasesArePresent() throws Throwable{
		Path root = Paths.get("importtest", "valuetests");
		DirectoryStream<Path> directory = Files.newDirectoryStream(root);
		for (Path p : directory){
			String fileName = p.getFileName().toString();
			int id = 0;
			if (fileName.startsWith("values")) {
				id = Integer.parseInt(fileName.replaceAll("values", "").replaceAll(".json", ""));
				valueFiles.put(id, p);
			}
				
			else if (fileName.startsWith("expected")) {
				id = Integer.parseInt(fileName.replaceAll("expected", "").replaceAll(".test", ""));
				expectedFiles.put(id,p);
			}
			else
				throw new IllegalArgumentException("The struture of valuetests folder is incorrect");			
		}
		
		for (int id : valueFiles.keySet()) {
			if (!expectedFiles.containsKey(id)){
				Path expected = createTestCase(valueFiles.get(id), id);
				expectedFiles.put(id, expected);
			}
				
		}
		
		
		
	}
	
	/**
	 * Creates a test file with java default serialization. It is console based. 
	 * @param valueFile
	 * @param i
	 * @return
	 * @throws Throwable
	 */
	private static Path createTestCase(Path valueFile, int i) throws Throwable{
		Objects<? extends Value> objects = new Objects<>(Value.class, valueFile, new Manager().getResourcesHolder());
		BufferedReader reader = Files.newBufferedReader(valueFile, Charset.defaultCharset());
		System.out.println("original file: ");
		int read = 0;
		while ((read = reader.read()) != -1){
			System.out.print((char) read);
		}
		System.out.println();
		System.out.println("imported: ");
		int id = 0;
		Value value = null;
		List<Value> values = new LinkedList<>();
		while ((value = objects.getObjectByID(id)) != null) {
			values.add(value);
			id++;
		}
		System.out.println(values);
		System.out.println();
		System.out.println("Was the import correct? (y/n)");
		Path result = null;
		char input = (char) System.in.read();
		switch (input){
		case 'n' : fail("Import error");
		case 'y' : 
			result = valueFile.getParent().resolve("expected"+i+".test");
			OutputStream fileStream = Files.newOutputStream(result);
			ObjectOutputStream stream = new ObjectOutputStream(fileStream);
			for (Value v : values) {
				stream.writeObject(v);
			}
			break;
		default : fail("Illegal selection");}
		
		return result;
		
	
	}
	
	/**
	 * This method tests the correctness of the import of Value Objects.
	 * To do this it needs a json file as specified in value.json of the vampire sheet and a corresponding default serialized
	 * Object. They must both be in the same folder and be named like this: <br>
	 * json: values*.json <br>
	 * test: expected*.test <br>
	 * * means any non-negative integer, where the corresponding files must have the same integer.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@Test
	public void testValueImporter() throws Throwable{
		for (int id : valueFiles.keySet()) {
			testValueImport(valueFiles.get(id), expectedFiles.get(id));
		}
		
		
	}
	

	
	private void testValueImport(Path valuesJson, Path expected) throws Throwable {
	
		Objects<? extends Value> values = new Objects<>(Value.class, valuesJson, new Manager().getResourcesHolder());
		List<Value> valueList = new LinkedList<>();
		{
			int i = 0;
			Value value;			
			while ((value = values.getObjectByID(i)) != null){
				valueList.add(value);
				i++;
			}
		}
		List<Value> expectedValues = new LinkedList<>();
		InputStream fileStream = Files.newInputStream(expected);
		ObjectInputStream stream = new ObjectInputStream(fileStream);
		{
			int i = 0;
			while (i < valueList.size()) {
				expectedValues.add((Value) stream.readObject());
				i++;
			}
		}
		
		assertEquals(expectedValues.size(), valueList.size());
		{
			int i = 0;
			while (i < expectedValues.size()) {
				assertEquals(expectedValues.get(i),valueList.get(i));
				i++;
			}
			
		}
		
		
		
	}

}
