package id.platforma.geo.application.service;

import id.platforma.geo.application.entity.graphql.base.BaseRequest;
import id.platforma.geo.application.entity.graphql.base.Response;
import id.platforma.geo.application.entity.graphql.response.TokenResponse;

public interface BackendService {
    TokenResponse refreshToken(String refreshToken, String token);

    TokenResponse getToken(String username, String password);

    <R extends BaseRequest, A extends Response> A get(String token, R request, Class<A> cls);

    <R extends BaseRequest, A extends Response> A get(String url, String token, R request, Class<A> cls);

    void logout(String username);
}
