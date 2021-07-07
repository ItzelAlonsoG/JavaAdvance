package model;

import model.*;
import util.*;
import makereport.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * <h1>Amazon Viewer</h1>
 * Amazon Viewer es un programa que permite visualizar Movies, Series con sus respectivos Chapters,
 * Books y Magazines. Te permite generar reportes generales y con fecha del dóa.
 * <p>
 * Existen algunas reglas como que todos los elementos pueden ser visualizados o leídos a excepción
 * de las Magazines, estás solo pueden ser vistas a modo de exposición sin ser leídos.
 *
 * @author Itzel Alonso
 * @version 1.1
 * @since 2021
 *
 *
 * */


public class Main {

	public static void main(String[] args) throws SQLException, ParseException {
		// TODO Auto-generated method stub
		//Movie movie = new Movie("","","",1,(short)3);
		showMenu();

		//Chapter chapter = new Chapter("","","",1,(short)1997,2,new Serie("","","",1,1));

	}
	
	public static void showMenu() throws SQLException, ParseException {
		int exit = 0;
		do {
			
			System.out.println("BIENVENIDOS AMAZON VIEWER");
			System.out.println("");
			System.out.println("Selecciona el número de la opción deseada");
			System.out.println("1. Movies");
			System.out.println("2. Series");
			System.out.println("3. Books");
			System.out.println("4. Magazines");
			System.out.println("5. Report");
			System.out.println("6. Report Today");
			System.out.println("0. Exit");
			
			//Leer la respuesta del usuario
			int response = AmazonUtil.validateUserResponseMenu(0, 6);
			Scanner sc = new Scanner(System.in);

			switch (response) {
				case 0:
					//salir
					exit = 0;
					break;
				case 1:
					showMovies();
					break;
				case 2:
					showSeries();
					break;
				case 3:
					showBooks();
					break;
				case 4:
					showMagazines();
					break;
				case 5:
					makeReport();
					exit = 1;
					break;
				case 6:
					//Date date = new Date();
					try{
						System.out.println("Digite la fecha del reporte a enerar en formato yyyy-MM-dd: ");
						Date dateE = new SimpleDateFormat("yyyy-MM-dd").parse(sc.nextLine());
						makeReport(dateE);
					}catch(ParseException e){
						e.printStackTrace();
					}
					makeReport(new Date());
					exit = 1;
					break;
	
				default:
					System.out.println();
					System.out.println("....¡¡Selecciona una opción!!....");
					System.out.println();
					exit = 1;
					break;
			}
			
			
		}while(exit != 0);
	}
	
	static ArrayList<Movie> movies = new ArrayList<>();

	static {
		try {
			movies = Movie.makeMoviesList();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	public static void showMovies() throws SQLException, ParseException {
		movies = Movie.makeMoviesList();
		int exit = 1;
		
		do {
			System.out.println();
			System.out.println(":: MOVIES ::");
			System.out.println();

			AtomicInteger atomicInteger = new AtomicInteger(1);
			//no podemos cambiar valores de variables e meramente declaratico
			movies.forEach(m -> System.out.println(atomicInteger.getAndIncrement() +". "+ m.getTitle() + " Visto: " + m.isViewed()));
			//movies.forEach(System.out::print);

			/*for (int i = 0; i < movies.size(); i++) { //1. Movie 1
				System.out.println(i+1 + ". " + movies.get(i).getTitle() + " Visto: " + movies.get(i).isViewed());
			}
			*/
			System.out.println("0. Regresar al Menu");
			System.out.println();
			
			//Leer Respuesta usuario
			int response = AmazonUtil.validateUserResponseMenu(0, movies.size());
			
			if(response == 0) {
				exit = 0;
				showMenu();
				break;
			}
			if (response > 0) {
				Movie movieSelected = movies.get(response-1);
				movieSelected.view();

			}
			
			
		}while(exit !=0);
		
	}
	static ArrayList<Serie> series = Serie.makeSeriesList();
	public static void showSeries() throws SQLException, ParseException {
		int exit = 1;
		
		do {
			System.out.println();
			System.out.println(":: SERIES ::");
			System.out.println();
			
			for (int i = 0; i < series.size(); i++) { //1. Serie 1
				System.out.println(i+1 + ". " + series.get(i).getTitle() + " Visto: " + series.get(i).isViewed());
			}
			
			System.out.println("0. Regresar al Menu");
			System.out.println();
			
			//Leer Respuesta usuario
			int response = AmazonUtil.validateUserResponseMenu(0, series.size());
			
			if(response == 0) {
				exit = 0;
				showMenu();
			}
			
			if(response > 0) {
				showChapters(series.get(response-1).getChapters());
			}
			
			
		}while(exit !=0);
	}
	
	public static void showChapters(ArrayList<Chapter> chaptersOfSerieSelected) throws SQLException {
		int exit = 1;
		
		do {
			System.out.println();
			System.out.println(":: CHAPTERS ::");
			System.out.println();
			
			
			for (int i = 0; i < chaptersOfSerieSelected.size(); i++) { //1. Chapter 1
				System.out.println(i+1 + ". " + chaptersOfSerieSelected.get(i).getTitle() + " Visto: " + chaptersOfSerieSelected.get(i).isViewed());
			}
			
			System.out.println("0. Regresar al Menu");
			System.out.println();
			
			//Leer Respuesta usuario
			int response = AmazonUtil.validateUserResponseMenu(0, chaptersOfSerieSelected.size());
			
			if(response == 0) {
				exit = 0;
			}
			
			
			if(response > 0) {
				Chapter chapterSelected = chaptersOfSerieSelected.get(response-1);
				chapterSelected.view();
			}
		}while(exit !=0);
	}
	
	static ArrayList<Book> books= Book.makeBookList();
	public static void showBooks() throws SQLException, ParseException {
		int exit = 1;
		
		do {
			System.out.println();
			System.out.println(":: BOOKS ::");
			System.out.println();
			
			for (int i = 0; i < books.size(); i++) { //1. Book 1
				System.out.println(i+1 + ". " + books.get(i).getTitle() + " Leído: " + books.get(i).isReaded());
			}
			
			System.out.println("0. Regresar al Menu");
			System.out.println();
			
			//Leer Respuesta usuario
			int response = AmazonUtil.validateUserResponseMenu(0, books.size());
			
			if(response == 0) {
				exit = 0;
				showMenu();
			}
			
			if(response > 0) {
				Book bookSelected = books.get(response-1);
	            bookSelected.view();
			}
			
		}while(exit !=0);
	}
	
	public static void showMagazines() throws SQLException, ParseException {
		 ArrayList<Magazine> magazines = Magazine.makeMagazineList();
		int exit = 0;
		do {
			System.out.println();
			System.out.println(":: MAGAZINES ::");
			System.out.println();
			
			for (int i = 0; i < magazines.size(); i++) { //1. Book 1
				System.out.println(i+1 + ". " + magazines.get(i).getTitle());
			}
			
			System.out.println("0. Regresar al Menu");
			System.out.println();
			
			//Leer Respuesta usuario
			int response = AmazonUtil.validateUserResponseMenu(0, 0);
			
			if(response == 0) {
				exit = 0;
				showMenu();
			}
			
			
		}while(exit !=0);
	}
	
	public static void makeReport() {
		
		Report report = new Report();
		report.setNameFile("reporte");
		report.setExtension("txt");
		report.setTitle(":: VISTOS ::");
		StringBuilder contentReport = new StringBuilder();
		StringBuilder finalContentReport = contentReport;

		movies.stream().filter(m -> m.getIsViewed()).forEach(m -> finalContentReport.append(m.toString() + "\n"));

		//Consumer<Serie> seriesEach = s ->finalContentReport.append(s.toString() + "\n");

		Consumer<Serie> seriesEach = s ->{
			ArrayList<Chapter> chapters = s.getChapters();
			chapters.stream().filter(c -> c.getIsViewed()).forEach(c -> finalContentReport.append(c.toString() + "\n"));
		};
		series.stream().forEach(seriesEach);


		books.stream().filter(b -> b.getIsReaded()).forEach(b -> finalContentReport.append(b.toString() + "\n"));

		report.setContent(contentReport.toString());
		report.makeReport();
		System.out.println("Reporte Generado");
		System.out.println();
	}
	
	public static void makeReport(Date date) throws SQLException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-h-m-s-S");
		String dateString = df.format(date);
		Report report = new Report();
		
		report.setNameFile("reporte" + dateString);
		report.setExtension("txt");
		report.setTitle(":: VISTOS ::");
		
		
		SimpleDateFormat dfNameDays = new SimpleDateFormat("E, W MMM Y");
		dateString = dfNameDays.format(date);
		String contentReport = "Date: " + dateString + "\n\n\n";

		ArrayList<Movie> movies_Date = new ArrayList<>();
		movies_Date = Movie.makeMoviesListDate(date);

		for (Movie movie : movies_Date) {
			if (movie.getIsViewed()) {
				contentReport += movie.toString() + "\n";
				
			}
		}
		
		for (Serie serie : series) {
			ArrayList<Chapter> chapters = serie.getChapters();
			for (Chapter chapter : chapters) {
				if (chapter.getIsViewed()) {
					contentReport += chapter.toString() + "\n";
					
				}
			}
		}
		
		for (Book book : books) {
			if (book.getIsReaded()) {
				contentReport += book.toString() + "\n";
				
			}
		}
		report.setContent(contentReport);
		report.makeReport();
		
		System.out.println("Reporte Generado cool");
		System.out.println();
	}
	
}















