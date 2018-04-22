package doom.doomstate;

public class Player {

    private Integer health;

    private Integer shotgunAmmo;

    public Player(Integer health, Integer shotgunAmmo) {
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
