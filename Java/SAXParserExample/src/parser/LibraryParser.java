package parser;

import model.Book;
import model.Library;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

// An example of a simple SAX parser to parse a library XML file

public class LibraryParser extends DefaultHandler {

	// Remember information being parsed
	StringBuffer accumulator;
	String title;
	String author;
	Library library;
	
	/**
	 * Constructor
	 */
	public LibraryParser(Library l) {
		library = l;
	}

	/**
	 * Called at the start of the document (as the name suggests)
	 */
	@Override
	public void startDocument() {
		// Print out a message to let you know something is happening. This is
		// just to help you trace the program executing.
		System.out.println("Start Document!");

		// Use accumulator to remember information parsed. Just initialize for
		// now.
		accumulator = new StringBuffer();
		title = null;
		author = null;
	}

	/**
	 * Called when the parsing of an element starts. (e.g., <book>)
	 * 
	 * Lookup documentation to learn meanings of parameters.
	 */
	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) {
		System.out.println("StartElement: " + qName);
		// What are we parsing?
		if (qName.toLowerCase().equals("book")) {
			// Its a book! write out the isbn
			System.out.println("isbn = " + atts.getValue("isbn"));
			// Forget information about any previous book parsed
			title = null;
			author = null;
		} 
		// Let's start the accumulator
		// to remember the value that gets parsed
		accumulator.setLength(0);
	}

	/**
	 * Called for values of elements
	 * 
	 * Lookup documentation to learn meanings of parameters.
	 */
	public void characters(char[] temp, int start, int length) {
		// Remember the value parsed
		accumulator.append(temp, start, length);
	}

	/**
	 * Called when the end of an element is seen. (e.g., </title>)
	 * 
	 * Lookup documentation to learn meanings of parameters.
	 */
	public void endElement(String uri, String localName, String qName) {
		// Print out that we have seen the end of an element
		System.out.println("EndElement: " + qName + " value: " + accumulator.toString().trim());
		
		// Remember details about the book being parsed
		if (qName.toLowerCase().equals("title")) 
			title = accumulator.toString();
		else if (qName.toLowerCase().equals("author"))
			author = accumulator.toString();

		if (qName.toLowerCase().equals("book")) {
			Book b = new Book(title, author);
			library.addBook(b);
		}
		// Reset the accumulator because we have seen the value
		accumulator.setLength(0);
	}

	/**
	 * Called when the end of the document is reached
	 */
	public void endDocument() {
		// Just let the user know as something to do
		System.out.println("End Document!");
	}

}
