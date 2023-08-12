package id.platforma.geo.application.service;

import id.platforma.geo.application.component.Resolver;
import id.platforma.geo.application.config.EndpointConfig;
import id.platforma.geo.application.entity.graphql.base.BaseRequest;
import id.platforma.geo.application.entity.graphql.base.Response;
import id.platforma.geo.application.entity.graphql.request.AccessRequest;
import id.platforma.geo.application.entity.graphql.request.RefreshRequest;
import id.platforma.geo.application.entity.graphql.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CmsBackendService implements BackendService {
    private final Resolver resolver;
    private final EndpointConfig endpointConfig;

    @Override
    public TokenResponse refreshToken(String refreshToken, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.COOKIE, String.format("J_REFRESH=%s; J_SESSION=%s;", refreshToken, token));

        RefreshRequest request = RefreshRequest.builder().refreshToken(refreshToken).build();

        return this.resolve(
                this.endpointConfig.getRefreshUrl(),
                request,
                headers,
                TokenResponse.class);
    }

    @Override
    public TokenResponse getToken(String username, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        AccessRequest request = AccessRequest
                .builder()
                .rememberMe(true)
                .username(username)
                .password(password)
                .build();

        return this.resolve(this.endpointConfig.getAuthUrl(), request, headers, TokenResponse.class);
    }

    @Override
    public <R extends BaseRequest, A extends Response> A get(String token, R request, Class<A> cls) {
        return this.get(this.endpointConfig.getServiceUrl(), token, request, cls);
    }

    @Override
    public <R extends BaseRequest, A extends Response> A get(String url, String token, R request, Class<A> cls) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth", String.format("Bearer %s", token));
        return this.resolve(this.endpointConfig.getServiceUrl(), request, headers, cls);
    }

    private <R, A> A resolve(String url, R request, HttpHeaders headers, Class<A> cls) {
        return resolver.postForEntity(url, request, headers, cls).getBody();
    }

    @Override
    public void logout(String username) {

    }
}
