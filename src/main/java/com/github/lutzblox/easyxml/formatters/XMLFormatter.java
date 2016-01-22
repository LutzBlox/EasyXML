package com.github.lutzblox.easyxml.formatters;

import com.github.lutzblox.easyxml.trees.XMLTag;
import com.github.lutzblox.easyxml.trees.XMLTree;


public class XMLFormatter {

    public static void printXMLTreeAsStringTree(XMLTree tree) {

        System.out.println(getXMLTreeAsStringTree(tree));
    }

    public static String getXMLTreeAsStringTree(XMLTree tree) {

        return format("", tree.getRoot(), "").trim();
    }

    private static String format(String str, XMLTag tag, String indentStr) {

        str += indentStr + tag.toString() + "\n";

        indentStr += "   ";

        for (XMLTag child : tag.getChildren()) {

            str = format(str, child, indentStr);
        }

        return str;
    }
}
