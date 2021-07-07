package model;

import AmazonViewerDAO.MovieDAO;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	private Date dateViewed;



	public Movie(){

	}
	
	public Movie(String title, String genre, String creator, int duration, short year) {
		super(title, genre, creator, duration);
		setYear(year);
	}




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTimeViewed() {
		return timeViewed;
	}
	public void setTimeViewed(int timeViewed) {
		this.timeViewed = timeViewed;
	}

	public Date getDateViewed() {
		return dateViewed;
	}

	public void setDateViewed(Date dateViewed) {

		this.dateViewed = dateViewed;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  "\n :: MOVIE ::" + 
				"\n Title: " + getTitle() +
				"\n Genero: " + getGenre() + 
				"\n Year: " + getYear() + 
				"\n Creator: " + getCreator() +
				"\n Duration: " + getDuration() ;
	//	        "\n Date see: " + getDateViewed();
	}
	/**
	 * {@inheritDoc}
	 * */
	@Override
	public Date startToSee(Date dateI) {
		// TODO Auto-generated method stub
		setDateViewed(dateI);
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
	public void view() throws SQLException {

		setViewed(true);
		Date dateI = startToSee(new Date());
		//setDateViewed(dateI);
		Movie movie = new Movie();
		movie.setMovieViewed(this);
		System.out.println(this);



		System.out.println("La vi en el día y hora: " + dateI);



		for (int i = 0; i < 100000; i++) {
			System.out.println("..........");
		}

		//Termine de verla
		stopToSee(dateI, new Date());
		System.out.println();
		System.out.println("Viste: " + toString());
		System.out.println("Por: " + getTimeViewed() + " milisegundos");
	}

	public static ArrayList<Movie> makeMoviesListDate (Date date) throws SQLException {
		Movie movie = new Movie();
		return movie.getMoviesViewedByDate(date);
	}
}







