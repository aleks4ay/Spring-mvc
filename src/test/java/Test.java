import ua.aleks4ay.dao.UserDAO;
import ua.aleks4ay.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test {

    private UserDAO userDAO = new UserDAO();

    private static List<User> users = new ArrayList<User>();

    public static void main(String[] args) throws SQLException {
        new Test().getUsers();
    }

    public void getUsers() throws SQLException {
        users = userDAO.getAll();
        for (User u : users) {
            System.out.println(u.getName() + " " + u.getSurname() + " " + u.getEmail());
        }
    }

}
