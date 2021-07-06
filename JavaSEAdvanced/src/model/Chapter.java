

package model;

import java.util.ArrayList;

/**
 *<h1>Chapter</h1>
 * Es una clase pública que tiene como propósito contruir los capitulos de la clase {@link Serie} y hereda de {@link Movie}
 *
 *
 *
 * <br>
 * Con el cual podemos validar con el método {@code view()} si  la serie ha sido completada si ya hemos visto
 * todos los capítulos de la serie.
 *
 * @author Itzel Alonso
 * @since  2021
 * @version 1.1
 *
 * @see Film
 * */

public class Chapter extends Movie {
	
	
	private int id;
	private int sessionNumber;
	private Serie serie;

	public Chapter(String title, String genre, String creator, int duration, short year, int sessionNumber, Serie serie) {
		super(title, genre, creator, duration, year);
		// TODO Auto-generated constructor stub
		this.setSessionNumber(sessionNumber);
		this.setSerie(serie);
	}
	/**
	 * {@inheritDoc}
	 * */
	
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public int getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(int sessionNumber) {
		this.sessionNumber = sessionNumber;
	}
	
	
	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}
	/**
	 * {@inheritDoc}
	 * */

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  "\n :: SERIE ::" + 
				"\n Title: " + getSerie().getTitle() +
				"\n :: CHAPTER ::" + 
				"\n Title: " + getTitle() +
				"\n Year: " + getYear() + 
				"\n Creator: " + getCreator() +
				"\n Duration: " + getDuration();
	}
	
	
	public static ArrayList<Chapter> makeChaptersList(Serie serie) {
		ArrayList<Chapter> chapters = new ArrayList();
		
		for (int i = 1; i <= 5; i++) {
			chapters.add(new Chapter("Capituo "+i, "genero "+i, "creator" +i, 45, (short)(2017+i), i, serie));
		}
		
		return chapters;
	}
	/**
	 * {@inheritDoc}
	 * */
	@Override
	public void view() {
		super.view();
		ArrayList<Chapter> chapters = getSerie().getChapters();
		int chapterViewedCounter = 0;
		for ( Chapter chapter:chapters) {
			if(chapter.getIsViewed()){
				chapterViewedCounter++;
			}
		}

		if(chapterViewedCounter == chapters.size()){
			getSerie().view();
		}
	}
}
