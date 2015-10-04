package com.github.lutzblox.easyxml;

import java.io.IOException;

import com.github.lutzblox.easyxml.formatters.XMLFormatter;
import com.github.lutzblox.easyxml.readers.DefaultXMLReader;
import com.github.lutzblox.easyxml.trees.XMLTree;

import junit.framework.TestCase;
import junit.framework.TestSuite;


public class XMLFormatTest extends TestCase {
	
	public XMLFormatTest(String name) {
		
		super(name);
	}
	
	public static TestSuite suite() {
		
		return new TestSuite(XMLFormatTest.class);
	}
	
	public void testFormat() {
		
		assertNotNull("Test.xml missing!", getClass().getResourceAsStream("/test.xml"));
		
		try {
			
			XMLTree tree = new DefaultXMLReader().read(getClass().getResourceAsStream("/test.xml"));
			
			XMLFormatter.printXMLTreeAsStringTree(tree);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
			fail("An error occurred with test.xml!");
		}
	}
}
