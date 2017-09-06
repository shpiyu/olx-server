package app.service;

import app.entity.User;

public interface UserService {
    void register(User user);
    String login(User user);
    void logout(String authtoken);

    User getUserInfo(int userId, String authtoken);
}
