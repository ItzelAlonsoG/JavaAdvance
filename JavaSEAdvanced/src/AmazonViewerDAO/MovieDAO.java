package AmazonViewerDAO;

import AmazonViewerDB.IDBConnection;
import model.Movie;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static AmazonViewerDB.DataBase.*;

/**
 * <h1>Movie DAO</h1>
 *  La inferfaz consume a {@link IDBConnection} y sirve para consultar información de tablas específicas de la base de datos, para ello emplementamos querys.
 *  como por ejemplo:
 *  {@code String query = "SELECT * FROM " + TVIEWED +
 *   " WHERE " + TVIEWED_ID_MATERIAL + " = ?" +
 *   " AND " + TVIEWED_ID_ELEMENT + " = ?" +
 *   " AND " + TVIEWED_ID_USER + " = ?" ; }
 * @author  Itzel Alonso
 * @version 1.0
 * @since 2021
 *
 *
 * @see AmazonViewerDB.DataBase
 *
 * */



public interface MovieDAO extends IDBConnection {

    /**
     * Es utilizado para insertar dentro de la tabla viewed de la bd si un objeto movie ya fue visto.
     * */

    default Movie setMovieViewed(Movie movie) throws SQLException {

    try (Connection connection = connectoToDB()){
        //Statement es utilizado para insertar y hacer update en las bd de datos.
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");


        Statement statement = connection.createStatement();
        System.out.println("ID MOVIE : " + movie.getId()) ;
        String query = "INSERT INTO " + TVIEWED +
                " ("+TVIEWED_ID_MATERIAL+", "+TVIEWED_ID_ELEMENT+", "+TVIEWED_ID_USER+", "+TVIEWED_DATE+")" +
                " VALUES('"+ID_TMATERIAL[0]+"', '"+ movie.getId()+"', '"+TUSER_IDUSUARIO+"', '"  + dateformat.format(new Date()) + "' )";
        System.out.println(query);
        // Valida la cantidad de rows afectados
        if(statement.executeUpdate(query) > 0){
            System.out.println("Se marcó en Visto");
        }

    }catch(SQLException e){
        e.printStackTrace();
    }
        return movie;
    }

    default ArrayList<Movie> read() throws SQLException {
        ArrayList<Movie> movies = new ArrayList<>();
        try (Connection conection = connectoToDB()){
            String query = "SELECT * FROM " + TMOVIE;

            //PrepareStatement es utilizado para consultar las bd de datos.
            PreparedStatement preparedStatement = conection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Movie movie = new Movie(
                        rs.getString(TMOVIE_TITLE),
                        rs.getString(TMOVIE_GENRE),
                        rs.getString(TMOVIE_CREATOR),
                       Integer.valueOf(rs.getString(TMOVIE_DURATION)) ,
                        Short.valueOf(rs.getString(TMOVIE_YEAR)));
                movie.setId(Integer.valueOf(rs.getString(TMOVIE_ID)));
                movie.setViewed(getMoviewViewed(
                        preparedStatement,
                        conection,
                        Integer.valueOf(rs.getString(TMOVIE_ID))
                ));
                movies.add(movie);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return movies;
    }

    private boolean getMoviewViewed(PreparedStatement preparedStatement, Connection connection, int id_movie) throws SQLException {
        System.out.println(id_movie + " IDENTIFICADOR MOVIE");

        boolean viewed = false;

        String query = "SELECT * FROM " + TVIEWED +
                " WHERE " + TVIEWED_ID_MATERIAL + " = ?" +
                " AND " + TVIEWED_ID_ELEMENT + " = ?" +
                " AND " + TVIEWED_ID_USER + " = ?" ;


        ResultSet rs = null;
        try
        {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,ID_TMATERIAL[0]);
            preparedStatement.setInt(2,id_movie);
            preparedStatement.setInt(3,TUSER_IDUSUARIO);
            rs = preparedStatement.executeQuery();
            viewed = rs.next();




        } catch(Exception e){

            e.printStackTrace();

        }



        return viewed;
    }

    default ArrayList<Movie> getMoviesViewedByDate(Date date) throws SQLException {
        ArrayList<Movie> movies = new ArrayList<>();

        String dateformat = new SimpleDateFormat("yyyy-MM-dd").format(date);

        try(Connection connection = connectoToDB()){
            String query = "SELECT * FROM " + TMOVIE + " AS m INNER JOIN " + TVIEWED +
                    " AS v ON m." + TMOVIE_ID + " = v." + TVIEWED_ID_ELEMENT +
                    " WHERE v." + TVIEWED_ID_MATERIAL + " = " + ID_TMATERIAL[0] +
                    " AND v." + TVIEWED_ID_USER + " = " + TUSER_IDUSUARIO +
                    " AND " + TVIEWED_DATE + " = '" + dateformat + "';";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                Movie movie = new Movie(
                rs.getString("m."+TMOVIE_TITLE),
                rs.getString("m."+TMOVIE_GENRE),
                rs.getString("m."+ TMOVIE_CREATOR),
                Integer.valueOf(rs.getString("m."+ TMOVIE_DURATION)),
                Short.valueOf(rs.getString("m." + TMOVIE_YEAR)));

                movie.setId(rs.getInt("m."+TMOVIE_ID));
                movie.setViewed(true);
                movies.add(movie);
            }
            System.out.println(movies.size());

            preparedStatement.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return movies;
    }

}
