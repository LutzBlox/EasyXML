package com.github.lutzblox.easyxml;

import com.github.lutzblox.easyxml.formatters.XMLFormatter;
import com.github.lutzblox.easyxml.readers.DefaultXMLReader;
import com.github.lutzblox.easyxml.trees.XMLTree;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;


public class XMLFormatTest extends TestCase {

    public XMLFormatTest(String name) {

        super(name);
    }

    public static TestSuite suite() {

        return new TestSuite(XMLFormatTest.class);
    }

    public void testFormat() {

        assertNotNull("'test-format.xml' missing!", getClass().getResourceAsStream("/test-format.xml"));

        try {

            XMLTree tree = new DefaultXMLReader().read(getClass().getResourceAsStream("/test-format.xml"));

            XMLFormatter.printXMLTreeAsStringTree(tree);

        } catch (IOException e) {

            e.printStackTrace();

            fail("An error occurred with 'test-format.xml'!");
        }
    }
}
