import java.sql.*;
import java.util.Properties;

public class _02_GetVillainNames {
    public static void main(String[] args) throws SQLException {


        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db",properties);



        PreparedStatement statement = connection.prepareStatement("SELECT v.name, COUNT(DISTINCT m.id) as 'output' FROM villains as v " +
                " JOIN minions_villains as mv ON mv.villain_id = v.id" +
                " JOIN minions as m ON m.id = mv.minion_id" +
                " GROUP BY v.id " +
                " HAVING output > 15 " +
                " ORDER BY output DESC ;");

        ResultSet resultset = statement.executeQuery();

        while (resultset.next()){
            String villainName = resultset.getString("v.name");
            int minionsCount = resultset.getInt("output");
            System.out.println(villainName + " " + minionsCount);
        }
    }
}
