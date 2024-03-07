package databae_tests;

import org.junit.Assert;

import java.sql.*;

public class IntroToJDBC {
    public static void main(String[] args) {
        //host: 18.159.52.24
        //port: 5434
        //database: postgres
        //username: cashwiseuser
        //password: cashwisepass

        String url = "jdbc:postgresql://18.159.52.24:5434/postgres";
        String username = "cashwiseuser";
        String password = "cashwisepass";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            //create a connection with database
            connection = DriverManager.getConnection(url, username, password);
           //option 1
            //create statement and execute query
            statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM clients ORDER BY client_id;";

            //===================    OPTION 2  ===============================

            // Step 3: Create a PreparedStatement
            String sql = "select * from clients where client_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Step 4: Set parameters
            preparedStatement.setString(1, "John Doe");

            preparedStatement.executeQuery();

            //execute query and store results
            resultSet = statement.executeQuery(sqlQuery);

            while(resultSet.next()){
                System.out.println(resultSet.getString("address"));
                Assert.assertNotNull(resultSet.getString("client_id"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}


