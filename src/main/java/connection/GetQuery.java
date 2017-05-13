package connection;

import connection.impl.mysql.MySqlExec;
import connection.impl.mysql.parser.JsonSqlParser;
import connection.impl.mysql.runner.MySQL;

/**
 * Created by JnSnw on 08.05.17.
 */
public class GetQuery {

    public static void main(String[] args) {
        String jsonCreateString = "{\n" +
                "  \"dbTarget\": {\n" +
                "    \"dbNode\"    : \"localhost\",\n" +
                "    \"dbName\"    : \"userinfo\",\n" +
                "    \"dbUser\"    : \"root\",\n" +
                "    \"dbPassword\": \"admin\",\n" +
                "    \"dbTable\": {\n" +
                "      \"tableName\"   : \"testtable\",\n" +
                "      \"tableAction\" : \"CREATE\",\n" +
                "      \"tableColumn\": {\n" +
                "        \"PersonID\": {\n" +
                "          \"dataType\"    : \"INT\",\n" +
                "          \"primaryKey\"  : true\n" +
                "        },\n" +
                "        \"FirstName\": {\n" +
                "          \"dataType\"    : \"VARCHAR(255)\"\n" +
                "        },\n" +
                "        \"LastName\": {\n" +
                "          \"dataType\"    : \"VARCHAR(255)\"\n" +
                "        },\n" +
                "        \"Address\": {\n" +
                "          \"dataType\"    : \"VARCHAR(255)\"\n" +
                "        },\n" +
                "        \"PostCode\": {\n" +
                "          \"dataType\"    : \"INT\"\n" +
                "        },\n" +
                "        \"City\": {\n" +
                "          \"dataType\"    : \"VARCHAR(255)\"\n" +
                "        }\n" +
                "      }\n" +
                "\n" +
                "    }\n" +
                "  }\n" +
                "\n" +
                "}";


        MySqlExec mySqlExec = new MySqlExec();
        mySqlExec.CreateTable(jsonCreateString);

//        System.out.println(jsonSqlParser.getDbName())
//        System.out.println(jsonSqlParser.getDbUser());
        //System.out.println(jsonSqlParser.getTableName());
        //System.out.println(jsonSqlParser.getTableColumn());
        //System.out.println(jsonSqlParser.getSqlQuery());




    }
}
