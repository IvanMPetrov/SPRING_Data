import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class _05_ChangeTownNamesCasing {

    public static void main(String[] args) throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db",properties);

        Scanner scanner = new Scanner(System.in);

        String countryName = scanner.nextLine();

        PreparedStatement countryNameStatement = connection.prepareStatement("SELECT name FROM towns WHERE country = ?;");
        countryNameStatement.setString(1,countryName);
        countryNameStatement.executeQuery();
        ResultSet citiesSet = countryNameStatement.getResultSet();
        PreparedStatement convertDatabaseCitiesNames = connection.prepareStatement(
                "UPDATE towns " +
                        "SET name = UPPER(name) WHERE country = ?;");
        convertDatabaseCitiesNames.setString(1,countryName);
        convertDatabaseCitiesNames.executeUpdate();

        List<String> citiesList = new ArrayList<>();
        int count =0;

            for (count = 0;citiesSet.next(); count++) {
                citiesList.add(citiesSet.getString("name").toUpperCase());
            }

            if (!citiesList.isEmpty()) {
                System.out.println(count + " town names were affected.");
                System.out.println(citiesList);
            } else {
                System.out.println("No town names were affected");
            }


    }
}
