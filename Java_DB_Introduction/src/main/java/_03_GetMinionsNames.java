import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _03_GetMinionsNames {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db",properties);

        int villainId = Integer.parseInt(scanner.nextLine());

        PreparedStatement villainStatement = connection
                .prepareStatement(" SELECT name FROM villains WHERE id = ?");

        PreparedStatement minions = connection
                .prepareStatement(" SELECT v.name,m.name,m.age FROM minions as m" +
                        " JOIN minions_villains as mv ON m.id = mv.minion_id" +
                        " JOIN villains as v ON v.id = mv.villain_id" +
                        " WHERE v.id = ?;");

        villainStatement.setInt(1,villainId);

        ResultSet villainResultset = villainStatement.executeQuery();

        if (!villainResultset.next()){
            System.out.printf("No villain with ID %d exists in the database.",villainId);

        }else {
            String villainName = villainResultset.getString("name");
            minions.setInt(1,villainId);
            ResultSet minionsResultset = minions.executeQuery();
            System.out.println("Villain name: " + villainName);

            for (int i = 1; minionsResultset.next(); i++) {
//                String minionName = resultset.getString("m.name");
//                int minionAge = resultset.getInt("m.age");
                System.out.printf("%d. %s %d%n",i,minionsResultset.getString("m.name"),minionsResultset.getInt("m.age"));

            }        }



    }
}
