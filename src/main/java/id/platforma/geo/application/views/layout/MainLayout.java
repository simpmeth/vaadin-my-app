package id.platforma.geo.application.views.layout;

import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.sidenav.SideNavItem;
import id.platforma.geo.application.component.BackendAuthenticationContext;
import id.platforma.geo.application.views.AboutView;
import id.platforma.geo.application.views.HelloWorldView;
import id.platforma.geo.application.views.LeafletView;
import id.platforma.geo.application.views.SupersetView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Collection;

@Slf4j
public class MainLayout extends UnauthorisedMainLayout {

    public MainLayout(BackendAuthenticationContext authContext) {
        super(authContext);
    }

    @Override
    public void fillSideNav() {
        this.addSideNavItem(new SideNavItem("Hello World", HelloWorldView.class, LineAwesomeIcon.GLOBE_SOLID.create()));
        this.addSideNavItem(new SideNavItem("О продукте", AboutView.class, LineAwesomeIcon.USER.create()));
        this.addSideNavItem(new SideNavItem("Leaflet", LeafletView.class, LineAwesomeIcon.MAP.create()));
        this.addSideNavItem(new SideNavItem("Superset", SupersetView.class, LineAwesomeIcon.DASHCUBE.create()));
        getAuthContext().getAuthenticatedUser(UserDetails.class)
                .map(user -> {
                    Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
                    if (!authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) return null;
                    SideNavItem adminSection = new SideNavItem("Admin");
                    adminSection.setPrefixComponent(VaadinIcon.COG.create());
                    adminSection.addItem(new SideNavItem("Users", AboutView.class, VaadinIcon.GROUP.create()));
                    adminSection.addItem(new SideNavItem("Permissions", AboutView.class, VaadinIcon.KEY.create()));

                    this.addSideNavItem(adminSection);
                    return null;
                });


    }
}
