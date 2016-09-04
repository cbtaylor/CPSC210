package model;

import java.util.ArrayList;
import java.util.List;

public class Library {
	
	private List<Book> books;
	
	public Library() {
		books = new ArrayList<Book>();
	}
	
	public void addBook(Book b) {
		books.add(b);
	}
	
	public void printBooks() {
		for (Book b: books) {
			System.out.println(b);
		}
	}

}
