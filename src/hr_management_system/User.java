package hr_management_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane; // To display error messages to the user

/**
 *
 * @author ABS
 */
public class User {
    private String username;
    private String password;
    private String email;
    private String contact;

    // Instantiate the Connector. Each User object will have its own Connector instance.
    // This is okay for now, but for larger applications, consider a singleton Connector
    // or passing a shared Connection object.
    private Connector c = new Connector();

    // Constructor for signup (all details)
    public User(String username, String password, String email, String contact) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.contact = contact;
    }

    // Constructor for login (username and password) - Corrected assignment
    public User(String name, String pass) {
        this.username = name; // Corrected: assign parameter 'name' to 'this.username'
        this.password = pass; // Corrected: assign parameter 'pass' to 'this.password'
    }

    // Method to insert a new user into the database
    public void insert() {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            // Get the database connection from the Connector instance
            // The updated Connector.java now has a public getConnection() method.
            conn = c.getConnection();

            // SQL INSERT statement with placeholders (?) for security and correctness
            // Ensure 'user' is the correct table name in your Access database.
            String query = "INSERT INTO user (username, password, email, contact) VALUES (?, ?, ?, ?)";
            pst = conn.prepareStatement(query); // Prepare the SQL statement

            // Set the values for the placeholders
            pst.setString(1, this.username);
            pst.setString(2, this.password);
            pst.setString(3, this.email);
            pst.setString(4, this.contact);

            // Execute the DML (Data Manipulation Language) statement
            int rowCount = pst.executeUpdate();

            if (rowCount > 0) {
                System.out.println("User '" + this.username + "' added successfully to the database.");
                // The SignupForm will display "SignUp completed, Proceed to login."
            } else {
                System.out.println("Failed to add user '" + this.username + "' to the database (0 rows affected).");
                JOptionPane.showMessageDialog(null, "Signup failed. No rows affected.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            // Catch specific SQL exceptions for database errors
            System.err.println("Database error during user insertion: " + e.getMessage());
            e.printStackTrace(); // Print full stack trace for detailed debugging in NetBeans Output window
            JOptionPane.showMessageDialog(null, "Error inserting user: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            System.err.println("An unexpected error occurred during user insertion: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Ensure resources are closed in a finally block to prevent resource leaks
            try {
                if (pst != null) {
                    pst.close();
                }
                // IMPORTANT: Since Connector.getConnection() now provides a new Connection
                // for each call, it's crucial to close it here to prevent leaks, especially
                // with file-based databases like Access.
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing resources after user insertion: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    // Method to check user credentials for login
    public Boolean check(String user, String pass) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        // SQL SELECT statement with placeholders
        // Ensure 'user' is the correct table name in your Access database.
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";

        try {
            conn = c.getConnection(); // Get a new connection from Connector
            
            // It's good practice to ensure connection is valid, though getConnection()
            // should throw an exception if it fails to establish.
            if (conn == null || conn.isClosed()) {
                throw new SQLException("Failed to get a valid database connection.");
            }
            
            pst = conn.prepareStatement(query);
            pst.setString(1, user);
            pst.setString(2, pass);

            rs = pst.executeQuery(); // Execute the query

            if (rs.next()) { // If a row is found, credentials are valid
                return true;
            } else {
                return false; // No matching user found
            }
        } catch (SQLException e) {
            System.err.println("Database error while verifying login: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error verifying login: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while verifying login: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Always close resources in a finally block
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                // Close the connection here as it was opened specifically for this operation.
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing resources after login check: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return false; // Default return if an error occurs
    }
}