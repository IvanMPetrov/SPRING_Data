import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _06_RemoveVillain {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db",properties);

        int idToRemove = Integer.parseInt(scanner.nextLine());

        PreparedStatement getNameStatement = connection.prepareStatement(
                "SELECT name FROM villains WHERE id = ?;");
        getNameStatement.setInt(1,idToRemove);
        ResultSet getNameSet = getNameStatement.executeQuery();
        String name = "";
        if (getNameSet.next()){
            name = getNameSet.getString("name");
        }else {
            System.out.println("Villain does not exist in the database");
            return;
        }
        int countMinions = 0;

        try {
            PreparedStatement functionStatement = connection.prepareStatement(
                    "SELECT udf_RemoveVillainReleaseMinionsReturnCount(?);");
            functionStatement.setInt(1,idToRemove);

            ResultSet countSet = functionStatement.executeQuery();
            countSet.next();
            countMinions = countSet.getInt(1);
        }catch (SQLException e){
            System.out.println(e);
        }

        System.out.printf("%s was deleted%n",name);
        System.out.printf("%d minions released%n",countMinions);



    }
}
