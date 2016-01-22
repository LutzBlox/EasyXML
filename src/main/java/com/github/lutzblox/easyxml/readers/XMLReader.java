package com.github.lutzblox.easyxml.readers;

import com.github.lutzblox.easyxml.trees.XMLTree;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;


public abstract class XMLReader {

    private ReaderParameters params;

    private DocumentBuilderFactory factory;

    public XMLReader() {

        this(new ReaderParameters());
    }

    public XMLReader(ReaderParameters params) {

        this.params = params;

        factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringComments(params.getIgnoreComments());
        factory.setIgnoringElementContentWhitespace(params.getIgnoreWhitespace());
        factory.setValidating(params.getValidate());
        factory.setNamespaceAware(params.getNamespaceAware());
    }

    public XMLTree read(File file) throws IOException {

        try {

            return read(factory.newDocumentBuilder().parse(file));

        } catch (SAXException e) {

            e.printStackTrace();

            return new XMLTree();

        } catch (ParserConfigurationException e) {

            e.printStackTrace();

            return new XMLTree();
        }
    }

    public XMLTree read(InputStream stream) throws IOException {

        try {

            return read(factory.newDocumentBuilder().parse(stream));

        } catch (SAXException e) {

            e.printStackTrace();

            return new XMLTree();

        } catch (ParserConfigurationException e) {

            e.printStackTrace();

            return new XMLTree();
        }
    }

    public XMLTree read(String str) throws IOException {

        try {

            InputSource source = new InputSource();
            source.setCharacterStream(new StringReader(str));

            return read(factory.newDocumentBuilder().parse(source));

        } catch (SAXException e) {

            e.printStackTrace();

            return new XMLTree();

        } catch (ParserConfigurationException e) {

            e.printStackTrace();

            return new XMLTree();
        }
    }

    public abstract XMLTree read(Document d);

    public ReaderParameters getReaderParameters() {

        return params;
    }
}
