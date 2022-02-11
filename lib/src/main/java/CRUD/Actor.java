package CRUD;

public class Actor {

    public int actor_id;
    public String first_name, last_name, last_update;

    public Actor() {

    }
    public Actor(int actor_id, String first_name,String last_name,String last_update) {
        this.actor_id = actor_id;
        this.first_name=first_name;
        this.last_name=last_name;
        this.last_update=last_update;
    }
}
