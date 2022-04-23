package Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

public class ConnectionClass {

    public Connection connection;
    public Connection getConnection() {
        String dbName = "users";
        String userName = "postgres";
        String password = "pass";

        
        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection("jdbc:postgresql://localhost/" + dbName, userName, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
