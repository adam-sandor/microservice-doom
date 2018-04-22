package doom.doomstate;

import java.util.Map;

public class DoomStateResponse {

    private Player player;

    private Map<Integer, Demon> demons;

    public DoomStateResponse(Player player, Map<Integer, Demon> demons) {
        this.player = player;
        this.demons = demons;
    }

    public Player getPlayer() {
        return player;
    }

    public Map<Integer, Demon> getDemons() {
        return demons;
    }
}
