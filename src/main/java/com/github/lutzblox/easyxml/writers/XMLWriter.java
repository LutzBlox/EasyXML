package com.github.lutzblox.easyxml.writers;

import com.github.lutzblox.easyxml.trees.XMLTree;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;


public abstract class XMLWriter {

	private WriterParameters params;

	public XMLWriter() {

		this(new WriterParameters());
	}

	public XMLWriter(WriterParameters params) {

		this.params = params;
	}

	public void write(XMLTree tree, File file) throws IOException {

		String contents = write(tree);

		if (!file.exists()) {

			file.createNewFile();
		}

		PrintStream ps = new PrintStream(file);

		ps.println(contents);

		ps.close();
	}

	public void write(XMLTree tree, OutputStream stream) {

		String contents = write(tree);
		
		PrintStream ps = new PrintStream(stream);
		
		ps.println(contents);
		
		ps.close();
	}

	public void write(XMLTree tree, PrintStream stream) {

		String contents = write(tree);
		
		stream.println(contents);
	}

	public abstract String write(XMLTree tree);
	
	public WriterParameters getWriterParameters(){
		
		return params;
	}
}
