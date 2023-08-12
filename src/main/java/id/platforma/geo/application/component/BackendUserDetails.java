package id.platforma.geo.application.component;

import id.platforma.geo.application.entity.graphql.response.TokenResponse;
import id.platforma.geo.application.service.BackendService;
import id.platforma.geo.application.service.jwt.JwtParserProvider;
import id.platforma.geo.application.service.jwt.JwtTokenPayload;
import jakarta.servlet.http.HttpSession;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Builder
@Setter
@Getter(onMethod = @__(@Override))
@Slf4j
@Scope("session")
public class BackendUserDetails implements UserDetails {
    private Collection<? extends GrantedAuthority> authorities;
    private String username;
    private String password;
    @Getter
    private String accessToken;
    @Getter
    private String refreshToken;
    @Getter
    private Long tokenRefreshTime;
    @Getter
    private UserDetailSchedulingConfigurer endpointConfigUpdater;
    @Getter
    private HttpSession httpSession;
    @Getter
    private BackendService backendService;
    @Getter
    private JwtTokenPayload jwtTokenPayload;

    public static BackendUserDetails get(String username,
                                         String password,
                                         UserDetailSchedulingConfigurer endpointConfigUpdater,
                                         BackendService backendService, HttpSession session) {

        TokenResponse token = backendService.getToken(username, password);
        if (token.getToken() == null || token.getToken().isEmpty()) {
            throw new RuntimeException("wrong password");
        }

        JwtTokenPayload jwt = JwtParserProvider.INSTANCE.getPayload(token.getToken());
        List<GrantedAuthority> roles = new ArrayList<>();

        jwt.getRoles()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .forEach(roles::add);

        BackendUserDetails userDetails = BackendUserDetails
                .builder()
                .authorities(roles)
                .username(username)
                .accessToken(token.getToken())
                .refreshToken(token.getRefreshToken())
                .tokenRefreshTime(new Date().getTime())
                .httpSession(session)
                .endpointConfigUpdater(endpointConfigUpdater)
                .backendService(backendService)
                .jwtTokenPayload(jwt)
                .build();

        endpointConfigUpdater.addUserDetails(userDetails);
        return userDetails;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void logout() {
        this.endpointConfigUpdater.removeUserDetails(this);
        this.backendService.logout(this.username);
    }

    public void refreshToken() {
        log.debug("refreshToken for " + this.getUsername());
        TokenResponse tokenResponse = this.backendService.refreshToken(this.refreshToken, this.accessToken);

        this.setAccessToken(tokenResponse.getToken());
        this.setRefreshToken(tokenResponse.getRefreshToken());
    }

    public boolean isSessionValid() {
        //увы
        try {
            this.getHttpSession().getAttributeNames();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
