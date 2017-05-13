package connection.impl;

/**
 * Created by volkan on 12.05.17.
 */
public interface ISqlConnection {

    public void CreateTable(String jsonContent);

    public void SelectTable(String jsonContent);

    public void InsertTable(String jsonContent);

    public void DeleteTable(String jsonContent);

}