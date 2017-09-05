package app.entity;

import javax.persistence.*;

@Entity
@Table(name = "actions")
public class AdvertisementAction {
    @Id
    @Column(name = "action_id")
    @GeneratedValue
    private int id;
    @Column(name = "action_name")
    private String actionName;

    public AdvertisementAction() {
    }

    public AdvertisementAction(String actionName) {
        this.actionName = actionName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
