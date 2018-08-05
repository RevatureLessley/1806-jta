package com.revature.services;

import java.util.List;

import javax.jws.WebService;

import com.revature.beans.Book;
import com.revature.exceptions.InvalidYearException;

/*
 * We are using Jax-ws here. (jws)
 * Jax-ws is the API we use for SOAP web services.
 * In the REST example, we used jax-rs (Resful services)
 * A RESTful services API
 */

@WebService
public interface Library {

	List<Book> getAllBooks();
	String addBook(Book book) throws InvalidYearException;
}
