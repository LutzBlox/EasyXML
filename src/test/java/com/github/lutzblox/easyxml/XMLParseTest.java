package com.github.lutzblox.easyxml;

import com.github.lutzblox.easyxml.formatters.XMLFormatter;
import com.github.lutzblox.easyxml.readers.EasyXMLReader;
import com.github.lutzblox.easyxml.trees.XMLTree;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;


public class XMLParseTest extends TestCase {

    public XMLParseTest(String name) {

        super(name);
    }

    public static TestSuite suite() {

        return new TestSuite(XMLParseTest.class);
    }

    public void testParse() {

        assertNotNull("'test-parse.xml' missing!", getClass().getResourceAsStream("/test-parse.xml"));

        try {

            XMLTree tree = new EasyXMLReader().read(getClass().getResourceAsStream("/test-parse.xml"));

            XMLFormatter.printXMLTreeAsStringTree(tree);

        } catch (IOException e) {

            e.printStackTrace();

            fail("An error occurred with 'test-parse.xml'!");
        }
    }
}
