package connection;

/**
 * Created by volkan on 08.05.17.
 */
public class GetQuery {

    public static void main(String[] args) {
        MySQL connection = new MySQL("localhost", "userinfo", "root", "admin");
        String idOne = connection.autoQuery("Person", "select", "PersonID", "1");
        String firstName = connection.autoQuery("Person", "select", "FirstName", "Iris");

        System.out.println(idOne);
        System.out.println(firstName);

    }
}
