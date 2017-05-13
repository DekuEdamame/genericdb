package connection.impl.mysql;

import connection.impl.ISqlConnection;
import connection.impl.mysql.runner.MySQL;

/**
 * Created by volkan on 13.05.17.
 */
public class MySqlExec implements ISqlConnection {
    public void CreateTable(String jsonContent) {
        MySQL mySQL = new MySQL();
        mySQL.setConnection(jsonContent);
        mySQL.CreateTable();
    }

    public void SelectTable(String jsonContent) {
        MySQL mySQL = new MySQL();
        mySQL.setConnection(jsonContent);
        mySQL.SelectTable();
    }

    public void InsertTable(String jsonContent) {

    }

    public void DeleteTable(String jsonContent) {

    }

}

