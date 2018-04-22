package doom.doomengine;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DemonRequest {

    private int id;

    private int setHealth;


    @JsonCreator
    public DemonRequest(@JsonProperty("id") int id, @JsonProperty("setHealth") int setHealth) {
        this.id = id;
        this.setHealth = setHealth;
    }

    public int getId() {
        return id;
    }

    public int getSetHealth() {
        return setHealth;
    }

}
