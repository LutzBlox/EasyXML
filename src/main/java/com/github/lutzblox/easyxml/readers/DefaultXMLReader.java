package com.github.lutzblox.easyxml.readers;

import com.github.lutzblox.easyxml.trees.PITag;
import com.github.lutzblox.easyxml.trees.XMLTag;
import com.github.lutzblox.easyxml.trees.XMLTree;
import org.w3c.dom.*;


public class DefaultXMLReader extends XMLReader {

    public DefaultXMLReader() {

        super();
    }

    public DefaultXMLReader(ReaderParameters params) {

        super(params);
    }

    @Override
    public XMLTree read(Document d) {

        Element eRoot = d.getDocumentElement();

        XMLTag root = new XMLTag(eRoot.getNodeName());

        parseElementAttributes(eRoot, root);

        parseChildren(eRoot, root);

        return new XMLTree(root);
    }

    private void parseElementAttributes(Element e, XMLTag tag) {

        NamedNodeMap attrs = e.getAttributes();

        for (int i = 0; i < attrs.getLength(); i++) {

            Node item = attrs.item(i);

            if (item.getNodeType() == Node.ATTRIBUTE_NODE) {

                tag.addAttribute(item.getNodeName(), item.getNodeValue());
            }
        }
    }

    private void parseChildren(Element e, XMLTag tag) {

        NodeList children = e.getChildNodes();

        for (int i = 0; i < children.getLength(); i++) {

            Node nChild = children.item(i);

            if (nChild.getNodeType() == Node.ELEMENT_NODE) {

                Element eChild = (Element) nChild;

                XMLTag child = new XMLTag(eChild.getNodeName());

                child.setParent(tag);

                parseElementAttributes(eChild, child);

                parseChildren(eChild, child);

                tag.addChild(child);

            } else if (nChild.getNodeType() == Node.TEXT_NODE) {

                String value = nChild.getTextContent();

                if (value != null && !value.trim().contentEquals("")) {

                    tag.setValue(value);
                }

            } else if (nChild.getNodeType() == Node.PROCESSING_INSTRUCTION_NODE) {

                PITag child = new PITag(nChild.getNodeName());

                child.setValue(nChild.getNodeValue());

                child.setParent(tag);

                tag.addChild(child);
            }
        }
    }
}
