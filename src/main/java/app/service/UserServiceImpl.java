package app.service;

import app.dao.UserDao;
import app.entity.User;
import app.entity.UserSession;
import org.springframework.beans.factory.annotation.Autowired;

import javax.net.ssl.SSLContext;
import java.sql.Time;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    public void register(User user) {
        userDao.register(user);
    }

    public String login(User user) {
        String username = user.getUserName();
        String password = user.getPassword();
        User storedUser = userDao.getUserByUsername(username);
        if (password.equals(storedUser.getPassword())){
            String authtoken = getNewAuthtoken();
            UserSession session = new UserSession(username,authtoken,System.currentTimeMillis());
            userDao.addSession(session);
            return authtoken;
        } else {
            return null;
        }

    }

    private String getNewAuthtoken() {
        return UUID.randomUUID().toString();
    }

    public void logout(String authtoken) {
        userDao.logout(authtoken);
    }
}
