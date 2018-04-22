package doom.doomclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClientActionsController {

    private static Logger logger = LoggerFactory.getLogger(ClientActionsController.class);

    @Value("${DOOM_ENGINE_SERVICE_URL}")
    private String doomEngineServiceUrl;

    @RequestMapping(path = "/shootDemon", method = RequestMethod.POST)
    public String shootDemon(@RequestBody ShootRequest request) {
        logger.info("Shooting demon " + request.getDemonId() + " with " + request.getWeapon());
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(doomEngineServiceUrl + "/shootDemon", request, String.class);
    }
}
