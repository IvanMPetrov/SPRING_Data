import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _04_AddMinion {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db",properties);

        String[] minionInfo = scanner.nextLine().split(" ");
        String minionNameInput = minionInfo[1].trim();
        int minionAgeInput = Integer.parseInt(minionInfo[2]);
        String minionCityInput = minionInfo[3].trim();
        String villainNameInput = scanner.nextLine().split(" ")[1];

        PreparedStatement getVillainName = connection.prepareStatement(
                "SELECT v.name FROM villains as v WHERE v.name = ?;");

        getVillainName.setString(1,villainNameInput);
        ResultSet villainName = getVillainName.executeQuery();

        if (!villainName.next()){// insert villain if missing

            PreparedStatement addVillain = connection.prepareStatement(
                    "INSERT INTO villains(name,evilness_factor) VALUES (?,'evil');");
            addVillain.setString(1,villainNameInput);
            addVillain.executeUpdate();

            System.out.printf("Villain %s was added to the database.%n",villainNameInput);
        }

        PreparedStatement getCityName = connection.prepareStatement(
                "SELECT t.name FROM towns as t WHERE t.name = ?;");

        getCityName.setString(1,minionCityInput);
        ResultSet minionsTowns = getCityName.executeQuery();

        if (!minionsTowns.next()){ // insert town if missing

            PreparedStatement insertTown = connection.prepareStatement(
                    "INSERT INTO towns (name) VALUES (?)");
            insertTown.setString(1,minionCityInput);
            insertTown.executeUpdate();
            System.out.printf("Town %s was added to the database.%n",minionCityInput);
        }
        //id na grada i id na zlodeq

        PreparedStatement townIDstatement = connection.prepareStatement(
                "SELECT t.id FROM towns as t WHERE t.name = ?;");
        townIDstatement.setString(1,minionCityInput);
        ResultSet townId = townIDstatement.executeQuery();

        PreparedStatement villainIdStatement = connection.prepareStatement(
                "SELECT v.id FROM villains as v WHERE v.name = ?;");
        villainIdStatement.setString(1,villainNameInput);
        ResultSet villainId = villainIdStatement.executeQuery();
        villainId.next();
        townId.next();

        int villainID = villainId.getInt("id");
        int townID = townId.getInt("id");

        PreparedStatement assignMinionToVillain = connection.prepareStatement(
                "INSERT INTO minions (name,age,town_id) VALUES (?,?,?)");
        assignMinionToVillain.setString(1,minionNameInput);
        assignMinionToVillain.setInt(2,minionAgeInput);
        assignMinionToVillain.setInt(3,townID);
        assignMinionToVillain.executeUpdate();

        PreparedStatement minionGetLastIdStatement = connection.prepareStatement(
                "SELECT id FROM minions ORDER BY id DESC LIMIT 1");
        ResultSet minionGetLastIdSet = minionGetLastIdStatement.executeQuery();
        minionGetLastIdSet.next();
        int minionID = minionGetLastIdSet.getInt("id");

        PreparedStatement minionsVillainsMAP = connection.prepareStatement(
                "INSERT INTO minions_villains (minion_id,villain_id) VALUES (?,?);");
        minionsVillainsMAP.setInt(1,minionID);
        minionsVillainsMAP.setInt(2,villainID);
        minionsVillainsMAP.executeUpdate();

        System.out.printf("Successfully added %s to be minion of %s%n",minionNameInput,villainNameInput);
    }
}
