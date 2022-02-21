package markus.wieland.tmrestapi.trackmaniarestapi;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Value("${pas")
    private String key;
}
