package connection;

/**
 * Created by JnSnw on 08.05.17.
 */

import com.google.gson.Gson;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MySQL {

    Connection connect;
    private String target;
    private String dbuser;
    private String dbpassword;
    List<Map<String, Object>> listofMaps;

    public MySQL(String host, String database, String dbuser, String dbpassword) {
        this.target = String.format("jdbc:mysql://%s/%s", host, database);
        this.dbuser = dbuser;
        this.dbpassword = dbpassword;
    }


    public String customQuery(String query){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(target, dbuser, dbpassword);
        } catch (Exception e) {
            System.out.println("Error in establishing the connection " + e);
        }

        try {
            QueryRunner queryRunner = new QueryRunner();
            listofMaps = queryRunner.query(connect, query, new MapListHandler());
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't query the database.", e);
        } finally {
            DbUtils.closeQuietly(connect);
        }
        return new Gson().toJson(listofMaps);

    }

    public String autoQuery(String table, String statement, String column, String value){
        String query = String.format("%s * from %s where %s = '%s'", statement, table, column, value);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(target, dbuser, dbpassword);
        } catch (Exception e) {
            System.out.println("Error in establishing the connection " + e);
        }

        try {
            QueryRunner queryRunner = new QueryRunner();
            listofMaps = queryRunner.query(connect, query, new MapListHandler());
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't query the database.", e);
        } finally {
            DbUtils.closeQuietly(connect);
        }
        return new Gson().toJson(listofMaps);

    }

}