package vampire.editor.plugin.freebiecalculator.application.controller;

import static org.junit.Assert.*;
import static vampire.editor.plugin.freebiecalculator.application.controller.Helper.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Before;
import org.junit.Test;

import vampire.editor.plugin.api.application.sheet.controller.ValueControllerAPI;
import vampire.editor.plugin.freebiecalculator.application.controllers.FSingleSubCatController;
import vampire.editor.plugin.freebiecalculator.application.events.FreebieChangeEvent;
import vampire.editor.plugin.freebiecalculator.application.events.FreebieChangeListener;
import vampire.editor.plugin.freebiecalculator.domain.FreebieSubCategoryConfig;

public class FSingleSubCategoryControllerTest {
	
	private FreebieSubCategoryConfig config;
	
	@Before
	public void setup() throws IOException{
		config = new FreebieSubCategoryConfig();
		config.setFactor(7);
		config.setFreeAmount(3);
	}
	
	@Test
	public void testInitilizing1() {
		List<ValueControllerAPI> valueControllers = buildValueControllers(2);
		FSingleSubCatController fController = new FSingleSubCatController(valueControllers, config);
		assertEquals(0, fController.getFreebies());
	}
	
	@Test
	public void testInitializing2() {
		List<ValueControllerAPI> valueControllers = buildValueControllers(2, 5);
		FSingleSubCatController fController = new FSingleSubCatController(valueControllers, config);
		assertEquals(28, fController.getFreebies());
	}
	
	@Test
	public void testChanging1() {
		List<ValueControllerAPI> valueControllers = buildValueControllers(2,5);
		FSingleSubCatController fController = new FSingleSubCatController(valueControllers, config);
		assertEquals(28, fController.getFreebies());
		valueControllers.get(0).setValue(0);
		assertEquals(14, fController.getFreebies());
	}
	
	@Test
	public void testChanging2() {
		List<ValueControllerAPI> valueControllers = buildValueControllers(2,5);
		FSingleSubCatController fController = new FSingleSubCatController(valueControllers, config);
		assertEquals(28, fController.getFreebies());
		valueControllers.get(1).setValue(0);
		assertEquals(0, fController.getFreebies());
	}
	
	@Test
	public void testListening2() {
		List<ValueControllerAPI> valueControllers = buildValueControllers(2,5);
		FSingleSubCatController fController = new FSingleSubCatController(valueControllers, config);
		assertEquals(28, fController.getFreebies());
		final AtomicBoolean executed = new AtomicBoolean(false);
		fController.addListener(new FreebieChangeListener() {
			
			@Override
			public void freebieChanged(FreebieChangeEvent event) {
				executed.set(true);
				assertEquals(28, event.getFormerFreebies());
				assertEquals(14, event.getNewFreebies());
			}
		});
		valueControllers.get(0).setValue(0);
		assertTrue(executed.get());
	}
	
	@Test
	public void testListening1() {
		List<ValueControllerAPI> valueControllers = buildValueControllers(2,5);
		FSingleSubCatController fController = new FSingleSubCatController(valueControllers, config);
		assertEquals(28, fController.getFreebies());
		final AtomicBoolean executed = new AtomicBoolean(false);
		fController.addListener(new FreebieChangeListener() {
			
			@Override
			public void freebieChanged(FreebieChangeEvent event) {
				executed.set(true);
				assertEquals(28, event.getFormerFreebies());
				assertEquals(0, event.getNewFreebies());
			}
		});
		valueControllers.get(1).setValue(0);
		assertTrue(executed.get());
	}

}
