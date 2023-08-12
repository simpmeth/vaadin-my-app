package id.platforma.geo.application.service.jwt;

public interface JwtParser {
    JwtTokenPayload getPayload(String token);
}
