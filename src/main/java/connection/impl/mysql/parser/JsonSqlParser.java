package connection.impl.mysql.parser;

import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by JnSnw on 13.05.17.
 */
public class JsonSqlParser {


    private enum TableAction{ CREATE, SELECT, INSERT, DELETE }
    private String jsonDbInfo;
    private String jsonContent;
    private String dbNode;
    private String dbName;
    private String dbUser;
    private String dbPassword;
    private String tableName;
    private String tableAction;
    private String columnAction ="";
    private String sqlQuery = "";


    private void initialDbProperties(){
        JSONObject jsonObject = new JSONObject(this.jsonDbInfo);
        JSONObject dbInfo = jsonObject.getJSONObject("dbTarget");

        this.dbNode = dbInfo
                .getString("dbNode");
        this.dbName = dbInfo
                .getString("dbName");
        this.dbUser = dbInfo
                .getString("dbUser");
        this.dbPassword = dbInfo
                .getString("dbPassword");

    }

    private void initialSqlProperties(){

        JSONObject jsonObject = new JSONObject(this.jsonContent);
        JSONObject tableInfo;
        if (jsonObject.has("dbTarget")){
            tableInfo = jsonObject.getJSONObject("dbTarget").getJSONObject("dbTable");
        } else {
            tableInfo = jsonObject.getJSONObject("dbTable");
        }
        JSONObject tableColumn = tableInfo.getJSONObject("tableColumn");


        this.tableName = tableInfo
                .getString("tableName");
        this.tableAction = tableInfo
                .getString("tableAction");


        TableAction enumAction = TableAction.valueOf(tableAction);

        switch (enumAction){
            case CREATE:
                Iterator createIterator = tableColumn.keys();
                while (createIterator.hasNext()) {
                    String columnName = (String) createIterator.next();
                    String columnValue = tableColumn.getJSONObject(columnName).getString("dataType");
                    Boolean primaryKey = false;

                    if (tableColumn.getJSONObject(columnName).has("primaryKey")){
                        primaryKey = tableColumn.getJSONObject(columnName).getBoolean("primaryKey");
                    }

                    if (primaryKey && createIterator.hasNext()){
                        this.columnAction += columnName + " " + columnValue + " PRIMARY KEY, ";
                    }else if (createIterator.hasNext()){
                        this.columnAction += columnName + " " + columnValue + ", ";
                    }else {
                        this.columnAction += columnName + " " + columnValue;
                    }

                }
                sqlQuery = String.format("%s TABLE %s ( %s )", enumAction, this.tableName, this.columnAction);

            case SELECT:

                String columnName = (String) tableColumn.getJSONObject("columnName").get("value");
                String selectValue = (String) tableColumn.getJSONObject("selectValue").get("value");

                System.out.println( columnName + selectValue);

                sqlQuery = String.format("%s * from %s where %s=%s", enumAction, this.tableName, columnName, selectValue);




            case INSERT:
                return;

            case DELETE:
                return;
            }

    }

    public void setDbInfo(String jsonDbInfo) {
        this.jsonDbInfo = jsonDbInfo;
        initialDbProperties();
    }

    public void setJsonContent(String jsonContent) {
        this.jsonContent = jsonContent;
        initialSqlProperties();
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

    public String getSqlQuery() {
        return sqlQuery;
    }

}


