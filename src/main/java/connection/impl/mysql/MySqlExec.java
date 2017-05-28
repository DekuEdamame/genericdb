package connection.impl.mysql;

import com.google.gson.Gson;
import connection.impl.ISqlConnection;
import connection.impl.mysql.runner.MySQL;

/**
 * Created by JnSnw on 13.05.17.
 */
public class MySqlExec implements ISqlConnection {

    private MySQL mySQL;

    public MySqlExec (String jsonDbInfo) {
        mySQL = new MySQL(jsonDbInfo);
    }

    public void createTable(String jsonContent) {
        mySQL.createTable(jsonContent);
        mySQL.closeConnection();
    }

    public String selectTable(String jsonContent) {
        String selectResult = mySQL.selectTable(jsonContent);
        mySQL.closeConnection();
        return selectResult;
    }

    public void insertTable(String jsonContent) {

    }

    public void DeleteTable(String jsonContent) {

    }

}

