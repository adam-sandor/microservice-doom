package doom.doomstate;

public class Demon {

    private int id;

    private Integer health;

    private DemonType type;

    public Demon(int id, Integer health, DemonType type) {
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
