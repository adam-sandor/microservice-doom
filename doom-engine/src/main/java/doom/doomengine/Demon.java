package doom.doomengine;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Demon {

    private int id;

    private Integer health;

    private DemonType type;

    @JsonCreator
    public Demon(@JsonProperty("id") int id, @JsonProperty("health") Integer health, @JsonProperty("type") DemonType type) {
        this.id = id;
        this.health = health;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public Integer getHealth() {
        return health;
    }

    public DemonType getType() {
        return type;
    }
}
