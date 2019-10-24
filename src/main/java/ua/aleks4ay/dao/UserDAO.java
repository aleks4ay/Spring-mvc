package ua.aleks4ay.dao;

import org.springframework.stereotype.Component;
import ua.aleks4ay.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by aser on 24.10.2019.
 */
@Component
public class UserDAO {
    private static Connection conn;

    static {
        String url = null;
        String username = null;
        String password = null;

        //load DB properties
        try (InputStream in = UserDAO.class.getClassLoader().getResourceAsStream("persistence.properties")){
            Properties properties = new Properties();
            properties.load(in);
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //acquire DB connection
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM users;");
        while (rs.next()) {
            users.add(new User(rs.getString(1), rs.getString(2), rs.getString(3)));
        }
        return users;
    }

}
