package connection.impl;

import com.google.gson.Gson;

/**
 * Created by JnSnw on 12.05.17.
 */
public interface ISqlConnection {

    public void createTable(String jsonContent);

    public String selectTable(String jsonContent);

    public void insertTable(String jsonContent);

    public void DeleteTable(String jsonContent);

}