package hr_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// import java.sql.Statement; // Not needed if always using PreparedStatement

public class Connector {

    // IMPORTANT: Make sure you have the UCanAccess JAR files in your project libraries.
    // This typically includes:
    // - UCanAccess.jar
    // - commons-lang3-x.x.x.jar
    // - commons-logging-x.x.jar
    // - hsqldb-x.x.x.jar
    // - jackcess-x.x.x.jar

    // This path should point to your Access database file.
    // Ensure "Employee.accdb" is in the root of your project or provide a full path.
    private static final String DB_LOC = "jdbc:ucanaccess://Employee.accdb";

    // We'll manage connection per-operation in the methods,
    // or provide a getter for a singleton-like connection.
    // For now, let's keep it simple and ensure methods get/close their own resources.

    // Constructor - you can remove connection initialization from here
    // if you want getConnection() to always return a new connection.
    // Or, you can initialize a single connection here and ensure it's closed on app exit.
    // Let's modify it to be a more direct connection getter.
    public Connector() {
        // No direct connection here. The getConnection() method will handle it.
        // This avoids issues if the constructor is called multiple times.
    }

    // New method to get a database connection
    public Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            // UCanAccess doesn't usually need Class.forName(), but it doesn't hurt.
            // Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); // Optional for UCanAccess
            connection = DriverManager.getConnection(DB_LOC);
            System.out.println("UCanAccess Connection Successful.");
        } catch (SQLException e) {
            System.err.println("Problem while connecting to UCanAccess database: " + e.getMessage());
            e.printStackTrace(); // Crucial for debugging
            throw e; // Re-throw to inform calling method (User.java)
        }
        return connection;
    }

    // Corrected runSelect method using PreparedStatement
    public ResultSet runSelect(String query, Object... params) {
        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet rs = null; // rs will be returned, so don't close here

        try {
            conn = getConnection(); // Get a new connection
            prep = conn.prepareStatement(query);

            // Set parameters if provided (for WHERE clauses, etc.)
            for (int i = 0; i < params.length; i++) {
                prep.setObject(i + 1, params[i]);
            }

            rs = prep.executeQuery();
            System.out.println("Select query executed successfully.");
            return rs;
        } catch (SQLException e) {
            System.err.println("Problem while executing SELECT query: " + e.getMessage());
            e.printStackTrace();
            // In runSelect, we return ResultSet, so closing is tricky.
            // Best practice is for the caller (e.g., User.check) to close ResultSet and PreparedStatement.
            // We should still try to close connection if an error occurs here.
            try {
                if (prep != null) prep.close();
                if (conn != null) conn.close(); // Close connection on error
            } catch (SQLException ex) {
                System.err.println("Error closing resources in runSelect catch: " + ex.getMessage());
            }
            return null;
        }
    }

    // Corrected runDML method using PreparedStatement (this is what User.insert will now use implicitly)
    // This method will NOT be called by User.insert directly anymore,
    // as User.insert now handles PreparedStatement itself.
    // However, if you have other parts of your code that use runDML, make sure they use placeholders.
    public void runDML(String query) {
        Connection conn = null;
        PreparedStatement prep = null;
        try {
            conn = getConnection(); // Get a new connection
            prep = conn.prepareStatement(query);
            // If this method were to take parameters, it would look like runSelect's parameters.
            
            int rowsAffected = prep.executeUpdate();
            System.out.println("DML query executed. Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.err.println("Problem while executing DML query (INSERT/UPDATE/DELETE): " + e.getMessage());
            e.printStackTrace(); // Crucial for debugging
        } finally {
            // Always close resources
            try {
                if (prep != null) prep.close();
                if (conn != null) conn.close(); // Close connection after use
            } catch (SQLException e) {
                System.err.println("Error closing resources after DML: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    // A placeholder for closing the connection if it were a singleton/persistent one.
    // With the current `getConnection()` creating a new one each time, this is less critical
    // but good practice if you ever change your connection strategy.
    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}