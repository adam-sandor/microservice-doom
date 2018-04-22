package doom.doomclient;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class DoomState {

    private Player player;

    private Map<Integer, Demon> demons;

    @JsonCreator
    public DoomState(@JsonProperty("player") Player player, @JsonProperty("demons") Map<Integer, Demon> demons) {
        this.player = player;
        this.demons = demons;
    }

    public Player getPlayer() {
        return player;
    }

    public Map<Integer, Demon> getDemons() {
        return demons;
    }

    public List<Demon> getDemonList() {
        ArrayList<Demon> list = new ArrayList<>(demons.values());
        list.sort(Comparator.comparingInt(Demon::getId));
        return list;
    }
}
