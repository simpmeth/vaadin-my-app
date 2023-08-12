package id.platforma.geo.application.component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinServletRequest;
import com.vaadin.flow.server.VaadinServletResponse;
import com.vaadin.flow.spring.security.AuthenticationContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.CompositeLogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@Data
@Slf4j
public class BackendAuthenticationContext extends AuthenticationContext {
    private LogoutSuccessHandler logoutSuccessHandler;
    private CompositeLogoutHandler logoutHandler;

    @Override
    public <U> Optional<U> getAuthenticatedUser(Class<U> userType) {
        return super.getAuthenticatedUser(userType);
    }

    public <U extends BackendUserDetails> void logout(Class<U> cmsUserDetailsClass) {
        Optional<U> authenticatedUser = super.getAuthenticatedUser(cmsUserDetailsClass);
        authenticatedUser.get().logout();
        this.logout();
    }

    @Override
    public void logout() {
        HttpServletRequest request = VaadinServletRequest.getCurrent().getHttpServletRequest();
        HttpServletResponse response = VaadinServletResponse.getCurrent().getHttpServletResponse();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UI ui = UI.getCurrent();
        this.logoutHandler.logout(request, response, auth);
        ui.accessSynchronously(() -> {
            try {
                this.logoutSuccessHandler.onLogoutSuccess(request, response, auth);
            } catch (ServletException | IOException var5) {
                log.warn("There was an error notifying the logout handler about the user logout", var5);
            }

        });
    }

    public void setLogoutHandlers(LogoutSuccessHandler logoutSuccessHandler, List<LogoutHandler> logoutHandlers) {
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.logoutHandler = new CompositeLogoutHandler(logoutHandlers);
    }
}
