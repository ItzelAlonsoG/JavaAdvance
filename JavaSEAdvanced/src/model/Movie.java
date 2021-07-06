package model;

import AmazonViewerDAO.MovieDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
/**
 * <h1>Movie</h1>
 * Hereda los métodos de la super clase {@link Film}
 * Implementa de {@link IVisualizable}
 *
 *  @author Itzel Alonso
 *  @since  2021
 *  @version 1.1
 * */
public class Movie extends Film implements IVisualizable, MovieDAO {
	
	private int id;
	private int timeViewed;

	public Movie(){

	}
	
	public Movie(String title, String genre, String creator, int duration, short year) {
		super(title, genre, creator, duration);
		setYear(year);
	}

	
	public int getId() {
		return id;
	}

	public void setId(Integer integer) {
		this.id = id;
	}
	
	
	public int getTimeViewed() {
		return timeViewed;
	}
	public void setTimeViewed(int timeViewed) {
		this.timeViewed = timeViewed;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  "\n :: MOVIE ::" + 
				"\n Title: " + getTitle() +
				"\n Genero: " + getGenre() + 
				"\n Year: " + getYear() + 
				"\n Creator: " + getCreator() +
				"\n Duration: " + getDuration();
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
			setTimeViewed((int)(dateF.getTime() - dateI.getTime()));
		}else {
			setTimeViewed(0);
		}
		
		
	}
	
	public static ArrayList<Movie> makeMoviesList() throws SQLException {
		Movie movie = new Movie();

		
		return movie.read();
	}

	/**
	 * {@inheritDoc}
	 * */
	@Override
	public void view() {
		setViewed(true);
		Date dateI = startToSee(new Date());

		for (int i = 0; i < 100000; i++) {
			System.out.println("..........");
		}

		//Termine de verla
		stopToSee(dateI, new Date());
		System.out.println();
		System.out.println("Viste: " + toString());
		System.out.println("Por: " + getTimeViewed() + " milisegundos");
	}
}







