package hu.unideb.inf.DAO;

import hu.unideb.inf.Modell.User;
import java.util.List;

public interface UserDAO extends AutoCloseable {

    public void saveUser(User user);
    public void deleteUser(User user);
    public void updateUser(User user);
    public boolean validate(String userName, String password);
    public List<User> getUsers();
}
