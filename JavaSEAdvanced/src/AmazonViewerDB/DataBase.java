package AmazonViewerDB;

/**
 * <h1>DataBase Variables</h1>
 * La clase tiene como objetivo almacenar toda la información relacionada a la BD  como:
 * connexion, nombres de las tablas y las columnas de las tablas.
 *
 * @author  Itzel Alonso
 * @version 1.1
 * @since  2021
 *
 * @see IDBConnection
 * */

public class DataBase {
    
    public static final String URL = "jdbc:mysql://localhost:3306/";
    public static final String DB = "amazonviewer";
    public static final String USER = "amazonviewer";
    public static final String PASSWORD = "amazonviewer";

    public static final String TMOVIE           = "movie";
    public static final String TMOVIE_ID        = "id";
    public static final String TMOVIE_TITLE     = "title";
    public static final String TMOVIE_GENRE     = "genre";
    public static final String TMOVIE_CREATOR   = "creator";
    public static final String TMOVIE_DURATION  = "duration";
    public static final String TMOVIE_YEAR      = "year";

    public static final String TMATERIAL = "material";
    public static final String TMATERIAL_ID = "id";
    public static final String TMATERIAL_TABLE = "table";
    public static final int [] ID_TMATERIAL= {1,2,3,4,5};


    public static final String TUSER = "user";
    public static final int TUSER_IDUSUARIO =1;
    public static final String TUSER_ID = "id";
    public static final String TUSER_TABLE = "table";

    public static final String TVIEWED = "viewed";
    public static final String TVIEWED_ID = "id";
    public static final String TVIEWED_ID_MATERIAL = "id_material";
    public static final String TVIEWED_ID_ELEMENT = "id_element";
    public static final String TVIEWED_ID_USER = "id_user";


}
