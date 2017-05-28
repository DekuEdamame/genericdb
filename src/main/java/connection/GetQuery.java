package connection;

import connection.impl.mysql.MySqlExec;


/**intellij json
 * Created by JnSnw on 08.05.17.
 */
public class GetQuery {

    public static void main(String[] args) {
        String jsonDbInfo ="{\n"+
                "  \"dbTarget\": {\n"+
                "    \"dbNode\"    : \"localhost\",\n"+
                "    \"dbName\"    : \"userinfo\",\n"+
                "    \"dbUser\"    : \"root\",\n"+
                "    \"dbPassword\": \"admin\",\n"+
                "      }\n"+
                "}";


        String jsonCreateString ="{\n"+
                "    \"dbTable\": {\n"+
                "      \"tableName\"   : \"testtable\",\n"+
                "      \"tableAction\" : \"CREATE\",\n"+
                "      \"tableColumn\": {\n"+
                "        \"PersonID\": {\n"+
                "          \"dataType\"    : \"INT\",\n"+
                "          \"primaryKey\"  : true\n"+
                "        },\n"+
                "        \"FirstName\": {\n"+
                "          \"dataType\"    : \"VARCHAR(255)\"\n"+
                "        },\n"+
                "        \"LastName\": {\n"+
                "          \"dataType\"    : \"VARCHAR(255)\"\n"+
                "        },\n"+
                "        \"Address\": {\n"+
                "          \"dataType\"    : \"VARCHAR(255)\"\n"+
                "        },\n"+
                "        \"PostCode\": {\n"+
                "          \"dataType\"    : \"INT\"\n"+
                "        },\n"+
                "        \"City\": {\n"+
                "          \"dataType\"    : \"VARCHAR(255)\"\n"+
                "        }\n"+
                "    }\n"+
                "  }\n"+
                "}";

        String jsonSelectString ="{\n"+
                "    \"dbTable\": {\n"+
                "      \"tableName\"   : \"Person\",\n"+
                "      \"tableAction\" : \"SELECT\",\n"+
                "      \"tableColumn\": {\n"+
                "        \"columnName\": {\n"+
                "          \"value\"    : \"PersonID\",\n"+
                "        },\n"+
                "        \"selectValue\": {\n"+
                "          \"value\"    : \"1\",\n"+
                "        },\n"+
                "    }\n"+
                "  }\n"+
                "}";

        MySqlExec mySqlExec = new MySqlExec(jsonDbInfo);
        System.out.println(mySqlExec.selectTable(jsonSelectString));



    }
}
