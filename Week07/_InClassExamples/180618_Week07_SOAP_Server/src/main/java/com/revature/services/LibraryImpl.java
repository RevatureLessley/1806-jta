package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Book;
import com.revature.exceptions.InvalidYearException;

public class LibraryImpl implements Library {
	public static List<Book> bookList = new ArrayList<>();
	
	@Override
	public List<Book> getAllBooks() {
		System.out.println("========GET ALL BOOKS========");
		
		bookList.add(new Book("Lord of the Bobberts", "Bobbert", 1990));
		bookList.add(new Book("Bobbert and the Philosphers Stone", "Ryan", 2002));
		bookList.add(new Book("The Hitchhikers Guide to the Gala-Bobbert", "Obama", 2020));
		
		return bookList;
	}

	@Override
	public String addBook(Book book) throws InvalidYearException {
		// TODO Auto-generated method stub
		System.out.println("=======ADDING A BOOK: " + book.getTitle() + "============");
		bookList.add(book);
		if(book.getYear()>2018){
			throw new InvalidYearException();
		}
		return "=======SUCCESSFUL BOOK ADD: " + book.getTitle() + "==============";
	}

}
