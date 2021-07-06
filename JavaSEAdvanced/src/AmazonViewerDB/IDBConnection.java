package AmazonViewerDB;

import java.sql.Connection;
import java.sql.DriverManager;
import static AmazonViewerDB.DataBase.*;

/**
 * <h1>Connection to DB</h1>
 * La conexión a la base de datos prepara el Driver Manager.
 * @author Itzel Alonso
 * @version 1.0
 * @since 2021
 *
 * @see AmazonViewerDAO.MovieDAO
 * */
public interface IDBConnection {
    default Connection connectoToDB(){
        Connection connection = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL+DB,USER,PASSWORD);
            System.out.println(connection);
            if (connection != null){
                System.out.println("Se estableció la conexión :)");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return connection;
        }
    }
}
