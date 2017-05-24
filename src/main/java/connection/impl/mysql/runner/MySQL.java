package connection.impl.mysql.runner;

/**
 * Created by JnSnw on 08.05.17.
 */


import com.google.gson.Gson;
import connection.impl.mysql.parser.JsonSqlParser;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class MySQL {

    private Connection connection;
    private List<Map<String, Object>> listofMaps;
    private String dbUser;
    private String dbPassword;
    private String sqlQuery;
    private String jdbcConnection;


    public MySQL(String jsonContent) {
        JsonSqlParser jsonSqlParser = new JsonSqlParser();
        jsonSqlParser.setJsonContent(jsonContent);
        this.dbUser = jsonSqlParser.getDbUser();
        this.dbPassword = jsonSqlParser.getDbPassword();
        this.sqlQuery = jsonSqlParser.getSqlQuery();
        this.jdbcConnection = String.format("jdbc:mysql://%s/%s", jsonSqlParser.getDbNode(), jsonSqlParser.getDbName());
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(jdbcConnection, dbUser, dbPassword);
        } catch (Exception e) {
            System.out.println("Error in establishing the connection " + e);
        }
    }


    public void createTable() {
        try {
            Statement stmt = this.connection.createStatement();
            stmt.executeUpdate(this.sqlQuery);
        } catch (Exception e) {
            System.out.println("Error creating Table " + e);
        }
    }

    public String selectTable() {
        try {
            QueryRunner queryRunner = new QueryRunner();
            this.listofMaps = queryRunner.query(this.connection, this.sqlQuery, new MapListHandler());
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't query the database.", e);
        }
        return new Gson().toJson(listofMaps);
    }

    public void closeConnection() {
        try {
            DbUtils.closeQuietly(this.connection);
        } catch (Exception e) {
            System.out.println("Error in closing connection " + e);
        }
    }

}