package com.projectone.core.base;

import com.projectone.core.dao.Restaurant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Llegeix la com.projectone.core.base de dades que està ubicada a la IP indicada "35.205.41.45:1521"
 */
public class ReadDB {
    //  Definición de Strings que se usan para la conexión con la base de datos. Driver, URL, usuario y contraseña
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String THIN_URL = "jdbc:oracle:thin:@35.205.41.45:1521:XE";
    private static final String USER = "usuari";
    private static final String PASSWORD = "usuari";

    //  Objeto que ejecuta la Query. Le definimos que le tiene que llegar un String "query", que contiene la consulta a la BD.
//  Se le define una función, que realiza la conexión a la base de datos, ejecuta la query que se le pasa,
//  convierte el resultado en un objeto y nos lo devuelve.
    private Object executeQuery(String query, Function<ResultSet, Object> f) {
        Connection con;
        Statement stmt;
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(THIN_URL, USER, PASSWORD);
            stmt = con.createStatement();
            // Aquí feim una Query directament a la com.projectone.core.base de dades:
            ResultSet rs = stmt.executeQuery(query);
            // Convierte el resultado de query a cualquier objeto o coleccion valida (beans, list..)
            Object o = f.apply(rs);
            stmt.close();
            con.close();
            // Devuelve el objeto
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    // Crea una lista con el resultado de la BD. Define dos maneras de realizar la Query.
    public List readRestaurantAPI(boolean isTraditionalSearch) {
        // ArrayList de Restaurantes
        List<Restaurant> arrayRestaurants = new ArrayList<>();
        // Mapping con el Bean (Restaurant)
        ResultSetMapper<Restaurant> mapper = new ResultSetMapper<>();
        try {
            //Definición de la Query
            final String query = "SELECT R.RES_CODI,R.RES_NOM,R.RES_ADRECA,R.RES_WEB,R.RES_TELEFON,R.RES_URL_IMG,R.RES_MITJANA, TR.TRS_DESCRIPCIO FROM " +
                    "RESTAURANTS R,TRESTAURANTS TR WHERE  R.RES_TRS_CODI = TR.TRS_CODI";
            // Obtiene los datos por el "método tradicional". Repite el código innecesariamente.
            if (isTraditionalSearch) {
                //VERSION GENERICS
                Class.forName(DRIVER);
                Connection con = DriverManager.getConnection(THIN_URL, USER, PASSWORD);
                Statement stmt = con.createStatement();
                // Aquí feim una Query directament a la com.projectone.core.base de dades:
                ResultSet rs = stmt.executeQuery(query);
                // I aquí indicam que mentres hi hagi mes restaurants, segueixi impriment-los.
                arrayRestaurants = mapper.mapResultSetToObject(rs, Restaurant.class);
                stmt.close();
                con.close();
            }
            // Obtiene los datos desde la función definida más arriba.
            else {
                //VERSION FUNCTIONAL
                // ArrayList de Restaurantes
                arrayRestaurants = new ArrayList<>();
                // Define la función
                Function<ResultSet, Object> func = new Function<ResultSet, Object>() {
                    // Mapea el resultado con el Bean
                    public Object apply(ResultSet rs) {
                        return mapper.mapResultSetToObject(rs, Restaurant.class);
                    }
                };
                // Devuelve restaurantes a partir de la función que ejecuta la query. Le pasamos la query, la función lo ejecuta y nos devuelve el resultado
                arrayRestaurants = (ArrayList) executeQuery(query, func);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        // Devuelve la arrayList
        return arrayRestaurants;
    }

    /*

    private Object executePreparedStatement(String query
            , Function<PreparedStatement, PreparedStatement> psFunction
            , Function<ResultSet, Object> f) {
        Connection con;
        PreparedStatement ps;
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(THIN_URL, USER, PASSWORD);
            ps = con.prepareStatement(query);
            ps = psFunction.apply(ps);
            ResultSet rs = ps.executeQuery(query);
            Object o = f.apply(rs);
            ps.close();
            con.close();
            return o;
        } catch (Exception e) {
            return null;
        }
    }


    public List<Restaurant> readRestaurant(String search) {
        List<Restaurant> arrayRestaurants = new ArrayList<>();
        try {
            String query = StringUtils.EMPTY;
            if (StringUtils.isEmpty(search)) {
                query = "SELECT R.RES_CODI,R.RES_NOM,R.RES_ADRECA,R.RES_WEB,R.RES_TELEFON,R.RES_URL_IMG,R.RES_MITJANA, TR.TRS_DESCRIPCIO FROM " +
                        "RESTAURANTS R,TRESTAURANTS TR WHERE ROWNUM <= 5 AND R.RES_TRS_CODI = TR.TRS_CODI ORDER BY RES_MITJANA DESC";
            } else {
                query = "SELECT R.RES_CODI,R.RES_NOM,R.RES_ADRECA,R.RES_WEB,R.RES_TELEFON,R.RES_URL_IMG,R.RES_MITJANA, TR.TRS_DESCRIPCIO " +
                        "FROM RESTAURANTS R,TRESTAURANTS TR WHERE R.RES_TRS_CODI = TR.TRS_CODI" +
                        " AND LOWER (RES_NOM) LIKE '%" + search.toLowerCase() + "%'";
            }
            ResultSetMapper<Restaurant> a = new ResultSetMapper<>();
            Function<ResultSet, Object> func = new Function<ResultSet, Object>() {
                public Object apply(ResultSet rs) {
                    return a.mapResultSetToObject(rs, Restaurant.class);
                }
            };
            arrayRestaurants = (ArrayList) executeQuery(query, func);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return arrayRestaurants;
    }

        private Object searchPreparedDB(String query
            , Function<PreparedStatement, PreparedStatement> fps // Modifica la query
            , Function<ResultSet, Object> f) { // Convierte a datos java
        try {

            Class.forName(DRIVER); //Driver DB (Como nos conectamos)
            Connection con = DriverManager.getConnection(THIN_URL, USER, PASSWORD); // Se le dan los "valores" de la conexión
            PreparedStatement ps = con.prepareStatement(query); // Se prepara la query deseada
            ps = fps.apply(ps); // Modificamos el PS con los valores necesarios para la query correcta
            ResultSet rs = ps.executeQuery(); // Ejecuta la query
            Object o = f.apply(rs); // Convertimos resultado de query a cualquier objeto o coleccion valida (beans, list..)
            con.close(); // Cierra conexión
            return o; // Devuelve el objeto
        } catch (Exception e) {
            return null;
        }

    }


    public static Restaurant readRestaurantInfo(String id) {
        Restaurant restaurant = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@35.205.41.45:1521:XE", "usuari", "usuari");
            PreparedStatement stmt = con.prepareStatement("SELECT R.RES_CODI,R.RES_NOM,R.RES_ADRECA,R.RES_WEB,R.RES_TELEFON,R.RES_URL_IMG,R.RES_MITJANA, TR.TRS_DESCRIPCIO " +
                    "FROM RESTAURANTS R,TRESTAURANTS TR WHERE TR.TRS_CODI=R.RES_TRS_CODI AND R.RES_CODI='" + id + "'");
            // Aquí feim una Query directament a la com.projectone.core.base de dades:
            ResultSet rs = stmt.executeQuery();

            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return restaurant;
    }

    public ArrayList readOpinions(String id) {

        ArrayList arrayOpinions = new ArrayList();

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@35.205.41.45:1521:XE", "usuari", "usuari");

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT O.OPI_OBSERVACIO, O.OPI_PUNTUACIO, U.USU_NOM FROM OPINIONS O\n" +
                            "JOIN USUARIS U ON U.USU_CODI = O.OPI_USU_CODI\n" +
                            "JOIN RESTAURANTS R ON O.OPI_RES_CODI = R.RES_CODI\n" +
                            "WHERE R.RES_CODI = '" + id + "'"

            );

            while (rs.next()) {

                String opinio = rs.getString("OPI_OBSERVACIO");
                String user = rs.getString("USU_NOM");
                String puntuacio = rs.getString("OPI_PUNTUACIO");


                Opinio opn = new Opinio();

                opn.setOpinio(opinio);
                opn.setUser(user);
                opn.setPuntuacio(puntuacio);


                arrayOpinions.add(opn);

            }
            stmt.close();
            con.close();


        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return arrayOpinions;
    }


    public List<Restaurant> getRestaurantWithPS(String searchName) {
        List<Restaurant> arrayRestaurants = new ArrayList<>();
        try {
            executePreparedStatement("SELECT * FROM (SELECT R.RES_NOM, R.RES_ADRECA, R.RES_WEB, R.RES_TELEFON, R.RES_URL_IMG, R.RES_CODI, T.TRS_DESCRIPCIO FROM RESTAURANTS R, TRESTAURANTS T WHERE RES_TRS_CODI = TRS_CODI AND LOWER(R.RES_NOM) LIKE ? ORDER BY RES_MITJANA ASC) WHERE ROWNUM <= 5"
                    , rs -> {
                        try {
                            rs.setString(1, "%" + searchName.toLowerCase() + "%");
                        } catch (SQLException e) {
                            System.out.println("mek");
                        }
                        return rs;
                    }
                    , rs -> {
                        ResultSetMapper<Restaurant> mapper = new ResultSetMapper<>();
                        return mapper.mapResultSetToObject(rs, Restaurant.class);
                    });
        } catch (Exception e) {
            System.out.println("error");
        }
        return arrayRestaurants;
    }


    public List getAll(String query, Class classname){
        arrayRestaurants = new ArrayList<>();
        Function<ResultSet, Object> func = new Function<ResultSet, Object>() {
            public Object apply(ResultSet rs) {
                return mapper.mapResultSetToObject(rs, Restaurant.class);
            }
        };
        arrayRestaurants = (ArrayList) executeQuery(query, func);
    }*/
}






