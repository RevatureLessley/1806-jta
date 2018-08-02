package com.revature.main;

import java.io.PrintWriter;
import java.util.List;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.revature.beans.Book;
import com.revature.exceptions.InvalidYearException;
import com.revature.services.Library;

public class Driver {

	/*
	 * Apache Crossfire (cxf) is our SOAP framework.
	 * Whereas Jersey was our REST framework.
	 */
	public static void main(String[] args) {
		String url = "http://localhost:8085/SOAP_Sserver/library";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		//First we set what class the response will fit into
		//Then we set the endpoint we are hitting.
		factory.setServiceClass(Library.class);
		factory.setAddress(url);
		
		//Next we set up a logging mechanism so we can actually see the data exchange.
		LoggingInInterceptor inInterceptor = new LoggingInInterceptor();
		LoggingOutInterceptor outInterceptor = new LoggingOutInterceptor();
		//After creating interceptors, add them to our factory bean.
		factory.getInInterceptors().add(inInterceptor);
		factory.getOutInterceptors().add(outInterceptor);
		//Finish interceptor configurations for encoding
		//We use System.out since we are printing to the console
		inInterceptor.setPrintWriter(new PrintWriter(System.out));
		outInterceptor.setPrintWriter(new PrintWriter(System.out));
		
		//Consuming SOAP
		Library library = (Library) factory.create();
		List <Book> bookList = library.getAllBooks();
		
		for(Book b: bookList){
			System.out.println(b);
		}
		
		//post a book
		try {
			//System.out.println(library.addBook(new Book("My new book!", "me", 2040)));
			System.out.println(library.addBook(new Book("My new book!", "me", 2000)));
		} catch (InvalidYearException e) {
			e.printStackTrace();
		}
	}

}
