package test;

import java.io.IOException;

import model.Library;

import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import parser.LibraryParser;

public class LibraryParserTest {

	@Test
	public void test() {
		try {
			Library l = new Library();
			
			System.out.println(".... Writing out information while parsing ...");
			XMLReader reader = XMLReaderFactory.createXMLReader();
			LibraryParser handler = new LibraryParser(l); 
			reader.setContentHandler(handler);
			reader.parse("./library.xml");
			
			System.out.println("\n");
			System.out.println("..... In the library ....");
			l.printBooks();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
