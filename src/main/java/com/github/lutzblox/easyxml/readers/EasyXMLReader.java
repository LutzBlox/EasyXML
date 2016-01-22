package com.github.lutzblox.easyxml.readers;

import com.github.lutzblox.easyxml.exceptions.EasyXMLParseError;
import com.github.lutzblox.easyxml.trees.PITag;
import com.github.lutzblox.easyxml.trees.XMLTag;
import com.github.lutzblox.easyxml.trees.XMLTree;
import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;


public class EasyXMLReader extends XMLReader {

    @Override
    public XMLTree read(Document d) {

        DefaultXMLReader defaultReader = new DefaultXMLReader();

        XMLTree tree = defaultReader.read(d);

        String xmlStr = getTagAsString(tree.getRoot());

        try {

            return defaultReader.read(xmlStr);

        } catch (Exception e) {

            return new XMLTree();
        }
    }

    private String getTagAsString(XMLTag tag) {

        String tagStr = "";

        if (tag instanceof PITag) {

            PITag instruction = (PITag) tag;

            if (instruction.getName().equals("easyxml")) {

                tagStr += parseEasyXML(instruction.getValue());
            }

        } else {

            tagStr = "<" + tag.getName();

            for (String attrName : tag.getAttributes().keySet()) {

                tagStr += " " + attrName + "=\"" + tag.getAttribute(attrName) + "\"";
            }

            tagStr += ">";

            if (tag.hasChildren()) {

                for (XMLTag child : tag.getChildren()) {

                    tagStr += getTagAsString(child);
                }

            } else if (tag.hasValue()) {

                tagStr += tag.getValue();
            }

            tagStr += "</" + tag.getName() + ">";
        }

        return tagStr;
    }

    private String parseEasyXML(String code) {

        code = code.trim();

        String result = "";

        List<String> lines = new ArrayList<String>();

        int lineLevel = 0;
        String lineCurrent = "";

        for (char c : code.toCharArray()) {

            if (c == '"' && lineLevel == 0) {

                lineLevel = 1;
                lineCurrent += c;

            } else if (c == '"' && lineLevel == 1) {

                lineLevel = 0;
                lineCurrent += c;

            } else if (c == ';' && lineLevel == 0) {

                lines.add(lineCurrent.trim());
                lineCurrent = "";

            } else {

                lineCurrent += c;
            }
        }

        for (String line : lines) {

            List<String> args = new ArrayList<String>();

            int argLevel = 0;
            String argCurrent = "";

            for (char c : line.toCharArray()) {

                if (c == '"' && argLevel == 0) {

                    argLevel = 1;

                } else if (c == '"' && argLevel == 1) {

                    argLevel = 0;

                } else if (c == ' ' && argLevel == 0) {

                    args.add(argCurrent);
                    argCurrent = "";

                } else {

                    argCurrent += c;
                }
            }

            args.add(argCurrent);

            if (args.size() > 0) {

                String command = args.get(0);

                switch (command) {

                    case "echo":

                        if (args.size() == 2) {

                            result += args.get(1);

                        } else {

                            EasyXMLParseError error = new EasyXMLParseError("Command 'echo' requires 1 argument and received " + (args.size() - 1) + "!");
                            error.setStackTrace(new StackTraceElement[0]);
                            error.printStackTrace();
                        }

                        break;

                    case "sysecho":

                        if (args.size() == 2) {

                            System.out.println("[SYSOUT] "+args.get(1));

                        } else {

                            EasyXMLParseError error = new EasyXMLParseError("Command 'sysecho' requires 1 argument and received " + (args.size() - 1) + "!");
                            error.setStackTrace(new StackTraceElement[0]);
                            error.printStackTrace();
                        }

                        break;

                    default:

                        new EasyXMLParseError("Unknown command: " + command + "!").printStackTrace();
                }
            }
        }

        return result;
    }
}
