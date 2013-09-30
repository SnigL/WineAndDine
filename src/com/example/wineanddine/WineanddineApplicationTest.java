package com.example.wineanddine;

import java.util.ArrayList;
import java.util.Collection;
import junit.framework.Assert;
import junit.framework.TestCase;

public class WineanddineApplicationTest extends TestCase {
	
	private Collection<Object> collection;
	public void testMessage() {
		String message = "Hello World!";
		Assert.assertEquals(12, message.length());
	}
	
	/**@Before */
	public void setUp() {
		collection = new ArrayList<Object>();
	}
	
	/**@Test */
	public void testEmptyCollection() {
		assertTrue(collection.isEmpty());
	}
	
	/**@Test */
	public void testOneItemCollection() {
		collection.add("ItemA");
		collection.add("ItemB");
		collection.add("ItemC");
		assertEquals(3, collection.size());
	}

}
