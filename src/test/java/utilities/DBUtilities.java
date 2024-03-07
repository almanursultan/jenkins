package utilities;

import java.sql.*;

public class DBUtilities {

    private static final String url = Config.getProperty("cashwiseDbUrl");
    private static final String username = Config.getProperty("cashwiseDbUsername");
    private static final String password = Config.getProperty("cashwiseDbPassword");


    /**
     * sets up connection with the Cashwise database
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * closes open SQL connection
     *
     * @param connection
     */
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * executes Select statement
     *
     * @param connection to Cashwise database
     * @param query      to be executed
     * @return ResultSet with data
     * @throws SQLException
     */
    public static ResultSet executeQuery(Connection connection, String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeQuery();
    }

    /**
     * executes INSERT, UPDATE, DELETE queries
     *
     * @param connection to the database
     * @param query      to be executed
     * @return the number of rows affected
     * @throws SQLException
     */
    public static int executeUpdate(Connection connection, String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeUpdate();
    }

    /**
     * closes ResultSet
     *
     * @param rs
     */
    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * closes statement
     *
     * @param stmt
     */
    public static void closeStatement(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

























