package vampire.editor.sheetloader.domain;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import vampire.editor.sheetloader.domain.ProtoValue;
import vampire.editor.sheetloader.domain.ProtoValueViewAttribute;

public class HashCodeCalculator {

	@Test
	public void testInjectivityOfProtoValueViewAttributesHashCode(){
		Set<Integer> set = new HashSet<>();
		ProtoValueViewAttribute att = null;
		for (int i = -100; i<100; i++){
			att = new ProtoValueViewAttribute(false, false, i);					
			assertTrue(set.add(att.hashCode()));
		}
		for (int i = -100; i<100; i++){
			att = new ProtoValueViewAttribute(false, true, i);					
			assertTrue(set.add(att.hashCode()));
		}
		for (int i = -100; i<100; i++){
			att = new ProtoValueViewAttribute(true, false, i);					
			assertTrue(set.add(att.hashCode()));
		}
		for (int i = -100; i<100; i++){
			att = new ProtoValueViewAttribute(true, true, i);					
			assertTrue(set.add(att.hashCode()));
		}
		
	}
	
	@Test
	public void testInjectivityOfProtoValueHashCode(){
		Set<Long> set = new HashSet<>();
		ProtoValue value = null;
		Random random = new Random();
		for (int min = 0; min < 20; min++) {
			for (int max = 0; max < 20; max++) {
				for (int val = 0; val < 20; val++) {
					for (int temp = 0; temp < 20; temp++) {
						int viewID = random.nextInt(1000);
						value = new ProtoValue(min, max, val, temp, viewID);
						assertTrue(set.add(value.getId()));
					}
				}
			}
		}
		System.out.println(new ProtoValue(0,5,1,1,20).getId());
	}

}
