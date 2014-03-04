package vampire.editor.plugin.freebiecalculator.application.controller;

import static org.junit.Assert.*;
import static vampire.editor.plugin.freebiecalculator.application.controller.Helper.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Test;

import vampire.editor.plugin.api.application.sheet.controller.ValueControllerAPI;
import vampire.editor.plugin.freebiecalculator.application.controllers.FSubCatController;
import vampire.editor.plugin.freebiecalculator.application.events.SumChangeEvent;
import vampire.editor.plugin.freebiecalculator.application.events.SumChangeListener;

public class FSubCatControllerTest {

	@Test
	public void testInitializing1() {
		List<ValueControllerAPI> valueControllers = buildValueControllers(2);
		FSubCatController fController = new FSubCatController(valueControllers);
		assertEquals(2, fController.getSum());
	}
	
	@Test
	public void testInitializing2() {
		List<ValueControllerAPI> valueControllers = buildValueControllers(2, 5);
		FSubCatController fController = new FSubCatController(valueControllers);
		assertEquals(7, fController.getSum());
	}
	
	@Test
	public void testChanging1() {
		List<ValueControllerAPI> valueControllers = buildValueControllers(2,5);
		FSubCatController fController = new FSubCatController(valueControllers);
		assertEquals(7, fController.getSum());
		valueControllers.get(0).setValue(0);
		assertEquals(5, fController.getSum());
	}
	
	@Test
	public void testChanging2() {
		List<ValueControllerAPI> valueControllers = buildValueControllers(2,5);
		FSubCatController fController = new FSubCatController(valueControllers);
		assertEquals(7, fController.getSum());
		valueControllers.get(1).setValue(0);
		assertEquals(2, fController.getSum());
	}
	
	@Test
	public void testListening2() {
		List<ValueControllerAPI> valueControllers = buildValueControllers(2,5);
		FSubCatController fController = new FSubCatController(valueControllers);
		assertEquals(7, fController.getSum());
		final AtomicBoolean executed = new AtomicBoolean(false);
		fController.addSumChangeListener(new SumChangeListener() {
			
			@Override
			public void sumChanged(SumChangeEvent event) {
				executed.set(true);
				assertEquals(7, event.getFormerSum());
				assertEquals(5, event.getNewSum());
			}
		});
		valueControllers.get(0).setValue(0);
		assertTrue(executed.get());
	}
	
	@Test
	public void testListening1() {
		List<ValueControllerAPI> valueControllers = buildValueControllers(2,5);
		FSubCatController fController = new FSubCatController(valueControllers);
		assertEquals(7, fController.getSum());
		final AtomicBoolean executed = new AtomicBoolean(false);
		fController.addSumChangeListener(new SumChangeListener() {
			
			@Override
			public void sumChanged(SumChangeEvent event) {
				executed.set(true);
				assertEquals(7, event.getFormerSum());
				assertEquals(2, event.getNewSum());
			}
		});
		valueControllers.get(1).setValue(0);
		assertTrue(executed.get());
	}

}
