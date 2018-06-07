package doom.doomengine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.log;
import static java.lang.Math.max;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.IntStream.range;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@SpringBootApplication
@RestController
public class DoomEngine {

    private static Logger logger = LoggerFactory.getLogger(DoomEngine.class);

    @Value("${DOOM_STATE_SERVICE_URL}")
    private String doomStateServiceUrl;

    @Value("${DOOM_STATE_SERVICE_PASSWORD}")
    private String doomStateServicePassword;

    private RestTemplate stateService;

    //@Scheduled(fixedRate = 5000)
    public void moveDemons() throws IOException {
        DoomState state = getState();
        List<Demon> demons = new ArrayList<>(state.getDemons().values());

        Random random = new Random();
        Integer attackerCount = random.nextInt(5);

        int totalDamage = (int) range(0, attackerCount)
                .mapToObj(i -> demons.get(random.nextInt(demons.size())))
                .collect(summarizingInt(value -> value.getType().damage))
                .getSum();

        int health = max(state.getPlayer().getHealth() - totalDamage, 0);

        logger.info("{} demons attack the player for {} damage. Player health = {}", attackerCount, totalDamage, health);
        stateService.postForObject(doomStateServiceUrl + "/player", new PlayerRequest(health, null), Void.class);
    }

    @RequestMapping(value = "/shootDemon", method = POST)
    public String shootDemon(@RequestBody ShootRequest request) throws IOException {
        switch (request.getWeapon()) {
            case "shotgun": {
                shootDemon(request.getDemonId(), 10, 1);
                return "Shot demon " + request.getDemonId() + " with shotgun for 10 damage";
            }
            case "doublebarelled": {
                shootDemon(request.getDemonId(), 20, 2);
                return "Shot demon " + request.getDemonId() + " with doublebarelled for 20 damage";
            }
            default:
                throw new IllegalArgumentException("Unknown weapon: '" + request.getWeapon() + "'");
        }
    }

    private void shootDemon(int id, int damage, int ammo) throws IOException {
        DoomState state = getState();
        Demon demon = state.getDemons().get(id);
        Player player = state.getPlayer();
        if (demon.getHealth() > 0) {
            DemonRequest request = new DemonRequest(id, demon.getHealth() - damage);
            stateService.postForObject(doomStateServiceUrl + "/demon", request, Void.class);
        }
        if (player.getShotgunAmmo() > 0) {
            PlayerRequest request = new PlayerRequest(null, max(player.getShotgunAmmo() - ammo, 0));
            stateService.postForObject(doomStateServiceUrl + "/player", request, Void.class);
        }
    }

    private DoomState getState() throws IOException {
        return stateService.getForObject(doomStateServiceUrl + "/state", DoomState.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(DoomEngine.class, args);
    }

    @PostConstruct
    public void initRestTemplate() {
        stateService = new RestTemplate();
        stateService.setInterceptors(singletonList(new BasicAuthorizationInterceptor("doom-engine", doomStateServicePassword)));
    }
}
