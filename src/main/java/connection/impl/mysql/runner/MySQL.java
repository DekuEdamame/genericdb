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

    private JsonSqlParser jsonSqlParser;
    private Connection connection;
    private List<Map<String, Object>> listofMaps;
    private String dbUser;
    private String dbPassword;
    private String sqlQuery;
    private String jdbcConnection;




    public MySQL(String jsonDbInfo) {

        jsonSqlParser = new JsonSqlParser();
        jsonSqlParser.setDbInfo(jsonDbInfo);

        dbUser = jsonSqlParser.getDbUser();
        dbPassword = jsonSqlParser.getDbPassword();
        jdbcConnection = String.format("jdbc:mysql://%s/%s", jsonSqlParser.getDbNode(), jsonSqlParser.getDbName());
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcConnection, dbUser, dbPassword);
        } catch (Exception e) {
            System.out.println("Error in establishing the connection " + e);
        }
    }


    public void createTable(String jsonContent) {
        try {
            jsonSqlParser.setJsonContent(jsonContent);
            sqlQuery = jsonSqlParser.getSqlQuery();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sqlQuery);
        } catch (Exception e) {
            System.out.println("Error creating Table " + e);
        }
    }

    public String selectTable() {
        try {
            QueryRunner queryRunner = new QueryRunner();
            listofMaps = queryRunner.query(connection, sqlQuery, new MapListHandler());
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't query the database.", e);
        }
        return new Gson().toJson(listofMaps);
    }

    public void closeConnection() {
        try {
            DbUtils.closeQuietly(connection);
        } catch (Exception e) {
            System.out.println("Error in closing connection " + e);
        }
    }

}