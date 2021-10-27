package markus.wieland.tmrestapi.trackmaniarestapi.fuel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class FuelStationRestController {

    private RestTemplate restTemplate;
    private static final String BASE_URL = "https://creativecommons.tankerkoenig.de/json/";


    public FuelStationRestController(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/stations")
    public String getStations(@NonNull @RequestParam double lat, @NonNull @RequestParam double lng, @NonNull @RequestParam double rad, @NonNull @RequestParam String sort) {
        return restTemplate.getForObject(BASE_URL+"list.php?lat="
                + lat + "&lng="
                + lng + "&rad="
                + rad + "&sort="
                + sort + "&type=all&apikey=55636559-d8f2-dd7f-1500-7f9c460d59e7", String.class);
    }
}
