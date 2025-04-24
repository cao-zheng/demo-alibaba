package springboot3.demoorder.cofig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigurationConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
