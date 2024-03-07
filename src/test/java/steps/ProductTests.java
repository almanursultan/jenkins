package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utilities.DBUtilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductTests {

    private static Connection connection;
    private static ResultSet resultSet;

    @Given("I set up connection to database")
    public void i_set_up_connection_to_database() throws SQLException {
       connection = DBUtilities.getConnection();
    }
    @Given("I retrieve all products prices")
    public void i_retrieve_all_products_prices() throws SQLException {
       String query = "select id, price, title from products";
       resultSet = DBUtilities.executeQuery(connection,query);
    }
    @Then("verify prices are between {int} and {int}")
    public void verify_prices_are_between_and(Integer lowerLimit, Integer upperLimit) throws SQLException {

           while (resultSet.next()) {
               long price = resultSet.getLong("price");
               if (price < lowerLimit || price > upperLimit) {
                   String title = resultSet.getString("title");
                   System.out.println("ID: " + price + ", Price: " + price + ", Title: " + title);
               }


       }
    }



}
