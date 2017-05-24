package connection.impl.mysql;

import connection.impl.ISqlConnection;
import connection.impl.mysql.runner.MySQL;

/**
 * Created by JnSnw on 13.05.17.
 */
public class MySqlExec implements ISqlConnection {
    public void createTable(String jsonContent) {
        MySQL mySQL = new MySQL(jsonContent);
        mySQL.createTable();
        mySQL.closeConnection();
    }

    public void selectTable(String jsonContent) {
        MySQL mySQL = new MySQL(jsonContent);
        mySQL.selectTable();
    }

    public void insertTable(String jsonContent) {

    }

    public void DeleteTable(String jsonContent) {

    }

}

