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
}
