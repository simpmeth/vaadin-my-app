package id.platforma.geo.application.component;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class Resolver {
    private final RestTemplate restTemplate;

    public <R, O> ResponseEntity<O> postForEntity(String url, R request, HttpHeaders headers, Class<O> cls) {
        HttpEntity<R> httpEntity = new HttpEntity<>(request, headers);
        return restTemplate.exchange(url, HttpMethod.POST, httpEntity, cls);
    }
}
