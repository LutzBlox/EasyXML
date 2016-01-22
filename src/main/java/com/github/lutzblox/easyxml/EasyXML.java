package com.github.lutzblox.easyxml;

import com.github.lutzblox.easyxml.readers.DefaultXMLReader;
import com.github.lutzblox.easyxml.readers.ReaderParameters;
import com.github.lutzblox.easyxml.readers.XMLReader;
import com.github.lutzblox.easyxml.writers.DefaultXMLWriter;
import com.github.lutzblox.easyxml.writers.WriterParameters;
import com.github.lutzblox.easyxml.writers.XMLWriter;


public class EasyXML {

    public static XMLReader newXMLReader() {

        return new DefaultXMLReader();
    }

    public static XMLReader newXMLReader(ReaderParameters params) {

        return new DefaultXMLReader(params);
    }

    public static XMLWriter newXMLWriter(){

        return new DefaultXMLWriter();
    }

    public static XMLWriter newXMLWriter(WriterParameters params){

        return new DefaultXMLWriter(params);
    }
}
