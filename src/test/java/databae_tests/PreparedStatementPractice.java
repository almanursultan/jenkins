package databae_tests;

import utilities.DBUtilities;

import java.sql.*;

public class PreparedStatementPractice {


    public static void main(String[] args) throws SQLException {
        printClients("Google");
    }


    public static void printClients(String companyName) throws SQLException {
        Connection connection = DBUtilities.getConnection();
        String query = "SELECT client_name FROM clients WHERE company_name = '" + companyName + "'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
    }



    public static ResultSet executeQuery(Connection connection, String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeQuery();
    }

    public static int executeUpdate(Connection connection, String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return  preparedStatement.executeUpdate();
    }
}

