package model;

import java.util.ArrayList;
import java.util.Date;
/**
 *<h1>Book</h1>
 * Es una clase pública que tiene como propósito contruir los objetos de tipo Book e implementa métodos de
 * la interfaz {@link IVisualizable}.
 * <br>
 * Con el cual podemos márcar que ya hemos leído un libro con el método {@code view}.
 *
 * @author Itzel Alonso
 * @since  2021
 * @version 1.1
 * */
public class Book extends Publication implements IVisualizable {
	private int id;
	private String isbn;
	private boolean readed;
	private int timeReaded;
	
	
	public Book(String title, Date edititionDate, String editorial, String[] authors) {
		super(title, edititionDate, editorial);
		// TODO Auto-generated constructor stub
		setAuthors(authors);
	}


	public int getId() {
		return id;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public String isReaded() {
		String leido = "";
		if(readed == true) {
			leido = "Sí";
		}else {
			leido = "No";
		}
		
		return leido;
	}


	public void setReaded(boolean readed) {
		this.readed = readed;
	}
	
	public boolean getIsReaded() {
		return readed;
	}


	public int getTimeReaded() {
		return timeReaded;
	}


	public void setTimeReaded(int timeReaded) {
		this.timeReaded = timeReaded;
	}

	/**
	 * {@inheritDoc}
	 * */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String detailBook = "\n :: BOOK ::" + 
							"\n Title: " + getTitle() +
							"\n Editorial: " + getEditorial() + 
							"\n Edition Date: " + getEdititionDate() +
							"\n Authors: ";
		for (int i = 0; i < getAuthors().length; i++) {
			detailBook += "\t" + getAuthors()[i] + " ";
		}
		return  detailBook;
	}

	/**
	 * {@inheritDoc}
	 * */

	@Override
	public Date startToSee(Date dateI) {
		// TODO Auto-generated method stub
		return dateI;
	}

	/**
	 * {@inheritDoc}
	 * */

	@Override
	public void stopToSee(Date dateI, Date dateF) {
		// TODO Auto-generated method stub
		if (dateF.getTime() > dateI.getTime()) {
			setTimeReaded((int)(dateF.getTime() - dateI.getTime()));
		}else {
			setTimeReaded(0);
		}
	}

	public void view(){
		setReaded(true);
		Date dateI = startToSee(new Date());

		for (int i = 0; i < 100000; i++) {
			System.out.println("..........");
		}

		//Termine de verla
		stopToSee(dateI, new Date());
		System.out.println();
		System.out.println("Leíste: " + toString());
		System.out.println("Por: " + getTimeReaded() + " milisegundos");
	}
	
	public static ArrayList<Book> makeBookList() {
		ArrayList<Book> books = new ArrayList();
		String[] authors = new String[3];
		for (int i = 0; i < 3; i++) {
			authors[i] = "author "+i;
		}
		for (int i = 1; i <= 5; i++) {
			books.add(new Book("Book " + i, new Date(), "editorial " + i, authors));
		}
		
		return books;
	}
	
}
