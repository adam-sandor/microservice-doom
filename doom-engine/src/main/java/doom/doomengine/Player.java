package doom.doomengine;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {

    private Integer health;

    private Integer shotgunAmmo;

    @JsonCreator
    public Player(@JsonProperty("health") Integer health, @JsonProperty("shotgunAmmo") Integer shotgunAmmo) {
        this.health = health;
        this.shotgunAmmo = shotgunAmmo;
    }

    public Integer getHealth() {
        return health;
    }

    public Integer getShotgunAmmo() {
        return shotgunAmmo;
    }
}
