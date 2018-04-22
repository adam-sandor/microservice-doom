package doom.doomengine;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerRequest {

    private Integer setHealth;

    private Integer setShotgunAmmo;

    @JsonCreator
    public PlayerRequest(@JsonProperty("setHealth") Integer setHealth, @JsonProperty("setShotgunAmmo") Integer setShotgunAmmo) {
        this.setHealth = setHealth;
        this.setShotgunAmmo = setShotgunAmmo;
    }

    public Integer getSetHealth() {
        return setHealth;
    }

    public Integer getSetShotgunAmmo() {
        return setShotgunAmmo;
    }
}
