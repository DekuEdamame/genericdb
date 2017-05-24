package connection.impl;

/**
 * Created by JnSnw on 12.05.17.
 */
public interface ISqlConnection {

    public void createTable(String jsonContent);

    public void selectTable(String jsonContent);

    public void insertTable(String jsonContent);

    public void DeleteTable(String jsonContent);

}