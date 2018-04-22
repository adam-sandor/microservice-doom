package doom.doomclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.io.IOException;

import static java.util.Collections.singletonList;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@SpringBootApplication
@Controller
public class DoomClient {

    private static Logger logger = LoggerFactory.getLogger(DoomClient.class);

    @Value("${DOOM_STATE_SERVICE_URL}")
    private String doomStateServiceUrl;

    @Value("${DOOM_STATE_SERVICE_PASSWORD}")
    private String doomStateServicePassword;

    private RestTemplate restTemplate;

    @GetMapping("/")
    public ModelAndView doom() throws IOException {
        DoomState state = getState();
        return new ModelAndView("doom", "state", state);
    }

    private DoomState getState() throws IOException {
        return restTemplate.getForObject(doomStateServiceUrl + "/state", DoomState.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(DoomClient.class, args);
    }

    @PostConstruct
    public void initRestTemplate() {
        restTemplate = new RestTemplate();
        restTemplate.setInterceptors(singletonList(new BasicAuthorizationInterceptor("doom-client", doomStateServicePassword)));
    }
}
