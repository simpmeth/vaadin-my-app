package id.platforma.geo.application.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "graphql")
@Data
public class EndpointConfig {
    private String authUrl;
    private String refreshUrl;
    private String serviceUrl;
    private Long tokenRefreshTime;
}
