package id.platforma.geo.application.config;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import id.platforma.geo.application.component.BackendAuthenticationContext;
import id.platforma.geo.application.views.LoginView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Objects;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration
        extends VaadinWebSecurity {

    private LogoutConfigurer<HttpSecurity> logoutConfigurer;
    @Autowired
    private BackendAuthenticationContext authenticationContext;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                .requestMatchers(
                        new AntPathRequestMatcher("/public/**"),
                        new AntPathRequestMatcher("/images/**"),
                        new AntPathRequestMatcher("/icon/**"),
                        /**
                         * иначе ад и израиль с редиректами при авторизации
                         * */
                        new AntPathRequestMatcher("/line-awesome/**")
                )
                .permitAll());

        super.configure(http);
        setLoginView(http, LoginView.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        this.configure(http);
        this.logoutConfigurer = http.logout();
        this.logoutConfigurer.invalidateHttpSession(true);
        LogoutConfigurer var10001 = this.logoutConfigurer;
        Objects.requireNonNull(var10001);
        this.addLogoutHandlers(var10001::addLogoutHandler);
        DefaultSecurityFilterChain securityFilterChain = (DefaultSecurityFilterChain) http.build();
        this.authenticationContext.setLogoutHandlers(this.logoutConfigurer.getLogoutSuccessHandler(), this.logoutConfigurer.getLogoutHandlers());
        return securityFilterChain;//super.filterChain(http);
    }
}