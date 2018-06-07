package doom.doomstate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@SpringBootApplication
@RestController
public class DoomState {

    private Player player;
    private Map<Integer, Demon> demons = new ConcurrentHashMap<>();

    private static Logger logger = LoggerFactory.getLogger(DoomState.class);

    @PostConstruct
    public void initialiseNewState() {
        logger.info("Initializing new game");
        player = new Player(100, 50);

        for (int i = 0; i < 20; i++) {
            demons.put(i, new Demon(i, 13, DemonType.Imp));
        }
    }

    @RequestMapping("/state")
    public DoomStateResponse getState() {
        return new DoomStateResponse(player, demons);
    }

    @RequestMapping(value = "/demon", method = POST)
    public void setDemon(@RequestBody DemonRequest request) {
        logger.info("Updating demon {} to health {}", request.getId(), request.getSetHealth());
        if (!demons.containsKey(request.getId())) {
            throw new IllegalArgumentException("No demon with ID " + request.getId());
        }

        Demon demon = demons.get(request.getId());
        demons.put(request.getId(), new Demon(request.getId(), request.getSetHealth(), demon.getType()));
    }

    @RequestMapping(value = "/player", method = POST)
    public void setPlayer(@RequestBody PlayerRequest request) {
        logger.info("Player updated:\n\thealth={}\n\tammo={}", request.getSetHealth(), request.getSetShotgunAmmo());
        Player oldPlayer = player;
        player = new Player(request.getSetHealth() == null ? oldPlayer.getHealth() : request.getSetHealth(),
                request.getSetShotgunAmmo() == null ? oldPlayer.getShotgunAmmo() : request.getSetShotgunAmmo());
    }

    public static void main(String[] args) {
        SpringApplication.run(DoomState.class, args);
    }

}
