package connection.impl.mysql.parser;

import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by volkan on 13.05.17.
 */
public class JsonSqlParser {


    private enum TableAction{ CREATE, SELECT, INSERT, DELETE }
    private String jsonContent;
    private String dbNode;
    private String dbName;
    private String dbUser;
    private String dbPassword;
    private String tableName;
    private String tableAction;
    private String tableColumn ="";
    private String sqlQuery = "";


    private void InitialSqlProperties(){

        JSONObject jsonObject = new JSONObject(this.jsonContent);
        JSONObject dbInfo = jsonObject.getJSONObject("dbTarget");
        JSONObject tableInfo = dbInfo.getJSONObject("dbTable");
        JSONObject tableColumn = tableInfo.getJSONObject("tableColumn");

        this.dbNode = dbInfo
                .getString("dbNode");
        this.dbName = dbInfo
                .getString("dbName");
        this.dbUser = dbInfo
                .getString("dbUser");
        this.dbPassword = dbInfo
                .getString("dbPassword");

        this.tableName = tableInfo
                .getString("tableName");
        this.tableAction = tableInfo
                .getString("tableAction");


        TableAction enumAction = TableAction.valueOf(tableAction);

        switch (enumAction){
            case CREATE:
                Iterator columnIterator = tableColumn.keys();
                while (columnIterator.hasNext()) {
                    String columnName = (String) columnIterator.next();
                    String columnValue = tableColumn.getJSONObject(columnName).getString("dataType");
                    Boolean primaryKey = false;

                    if (tableColumn.getJSONObject(columnName).has("primaryKey")){
                        primaryKey = tableColumn.getJSONObject(columnName).getBoolean("primaryKey");
                    }

                    if (primaryKey && columnIterator.hasNext()){
                        this.tableColumn += columnName + " " + columnValue + " PRIMARY KEY, ";
                    }else if (columnIterator.hasNext()){
                        this.tableColumn += columnName + " " + columnValue + ", ";
                    }else {
                        this.tableColumn += columnName + " " + columnValue;
                    }

                }
                sqlQuery = String.format("%s TABLE %s ( %s )", enumAction, this.tableName, this.tableColumn);

            case SELECT:
                return;

            case INSERT:
                return;

            case DELETE:
                return;
            }

    }

    public void setJsonContent(String jsonContent) {
        this.jsonContent = jsonContent;
        InitialSqlProperties();
    }

    public String getDbNode() {
        return dbNode;
    }

    public String getDbName() {
       return dbName;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getTableName() {
        return tableName;
    }

    public String getTableColumn() {
        return tableColumn;
    }

    public String getSqlQuery() {
        return sqlQuery;
    }

}


