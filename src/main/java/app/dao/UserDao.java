package app.dao;


import app.entity.User;
import app.entity.UserSession;

public interface UserDao {
    void register(User user);
    void login(User user);
    void logout(String authtoken);

    User getUserByUsername(String username);

    void addSession(UserSession session);
}
