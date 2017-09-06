package app.dao;


import app.entity.User;
import app.entity.UserSession;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public class UserDaoOracleImpl extends HibernateDaoSupport implements UserDao {

    @Transactional
    public void register(User user) {
        getHibernateTemplate().save(user);
    }

    @Override
    @Transactional
    public User getUserByUsername(String username) {
        List<User> users = (List<User>) getHibernateTemplate().find("from User where userName = ?", username);
        return users.get(0);
    }


    public User getUserByAuthtoken(String authtoken){
        List<UserSession> sessions = (List<UserSession>) getHibernateTemplate().find("from UserSession where authtoken = ?", authtoken);
        return sessions.get(0).getUser();
    }


    @Override
    @Transactional
    public void addSession(UserSession session) {
        getHibernateTemplate().save(session);
    }



    public void login(User user) {

    }

    @Transactional
    public void logout(String authtoken) {
        UserSession session = getHibernateTemplate().load(UserSession.class, authtoken);
        getHibernateTemplate().delete(session);
    }

    @Override
    public boolean isLoggedIn(String authtoken) {
        List<UserSession> loggedInUsers = (List<UserSession>) getHibernateTemplate().find("from UserSession where authtoken = ?", authtoken);
        if (loggedInUsers.size()>0)
            return true;
        return false;
    }

    @Override
    public User getUserInfo(int userId) {
        return getHibernateTemplate().get(User.class, userId);
    }
}
