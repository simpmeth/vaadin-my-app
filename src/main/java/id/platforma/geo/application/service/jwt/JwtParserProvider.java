package id.platforma.geo.application.service.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtParserProvider {
    @Autowired
    public static JwtParserImpl INSTANCE;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void postConstruct() {
        this.INSTANCE = new JwtParserImpl(objectMapper);
    }
}
