package AmazonViewerDAO;

import AmazonViewerDB.IDBConnection;
import model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    default Movie seMovieViewed(Movie movie){
        return movie;
    }

    default ArrayList<Movie> read() throws SQLException {
        ArrayList<Movie> movies = new ArrayList<>();
        try (Connection conection = connectoToDB()){
            String query = "SELECT * FROM " + TMOVIE;
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

        System.out.println(query);
        ResultSet rs = null;
        try
        {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,ID_TMATERIAL[0]);
            preparedStatement.setInt(2,id_movie);
            preparedStatement.setInt(3,TUSER_IDUSUARIO);
            rs = preparedStatement.executeQuery();
            viewed = rs.next();
            System.out.println("resultado: " + viewed);

        } catch(Exception e){

            e.printStackTrace();

        }



        return viewed;
    }

}
