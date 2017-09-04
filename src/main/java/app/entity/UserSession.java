package app.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_session")
public class UserSession {



    @Column(name = "username")
    private String userName;
    @Id
    @Column(name = "authtoken", unique = true)
    private String authtoken;

    @Column(name = "time")
    private long time;

    public UserSession() {
    }

    public UserSession(String userName, String authtoken, long time) {
        this.userName = userName;
        this.authtoken = authtoken;
        this.time = time;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
