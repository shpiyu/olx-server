package app.entity;

import org.omg.CORBA.PERSIST_STORE;

import javax.persistence.*;

@Entity
@Table(name = "user_session")
public class UserSession {


    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "username")
    private User user;

    @Id
    @Column(name = "authtoken", unique = true)
    private String authtoken;

    @Column(name = "time")
    private long time;



    public UserSession() {
    }



    public UserSession(User user, String authtoken, long time) {
        this.user = user;
        this.authtoken = authtoken;
        this.time = time;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User userName) {
        this.user = user;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
