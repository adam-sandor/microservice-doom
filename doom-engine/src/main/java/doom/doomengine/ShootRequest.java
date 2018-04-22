package doom.doomengine;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ShootRequest {

    private int demonId;

    private String weapon;

    @JsonCreator
    public ShootRequest(@JsonProperty("demonId") int demonId, @JsonProperty("weapon") String weapon) {
        this.demonId = demonId;
        this.weapon = weapon;
    }

    public int getDemonId() {
        return demonId;
    }

    public String getWeapon() {
        return weapon;
    }
}
