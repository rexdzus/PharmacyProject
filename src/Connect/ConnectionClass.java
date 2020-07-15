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
        String userName = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }


}
