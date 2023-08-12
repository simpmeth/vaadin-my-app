package id.platforma.geo.application.views.layout;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import id.platforma.geo.application.component.BackendAuthenticationContext;
import id.platforma.geo.application.component.BackendUserDetails;
import lombok.Data;

@Data
public class UnauthorisedMainLayout extends AppLayout {
    private final transient BackendAuthenticationContext authContext;
    private H2 viewTitle;
    private SideNav sideNav;

    public UnauthorisedMainLayout(BackendAuthenticationContext authContext) {
        this.authContext = authContext;

        initSideNav();

        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void initSideNav() {
        this.sideNav = new SideNav();
        fillSideNav();
    }

    public void fillSideNav() {
    }

    public void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        this.viewTitle = new H2();
        this.viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        HorizontalLayout header =
                getAuthContext().getAuthenticatedUser(BackendUserDetails.class)
                        .map(user -> {
                            Button logout = new Button("Выход", click -> {
                                getAuthContext().logout(BackendUserDetails.class);
                            });
                            Span loggedUser = new Span(String.format("Добрый день, %s!", user.getJwtTokenPayload().getName()));

                            return new HorizontalLayout(toggle, viewTitle, loggedUser, logout);
                        })
                        .orElseGet(() -> {
                            Span loggedUser = new Span("Добрый день гость!");
                            Button login = new Button("Войти", click -> {
                                getUI().get().getPage().setLocation("/login");
                            });

                            return new HorizontalLayout(toggle, viewTitle, loggedUser, login);
                        });

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(viewTitle);
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");


        addToNavbar(true, header);
    }

    public void addDrawerContent() {
        H1 appName = new H1("Тестовое приложение");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(this.sideNav);

        addToDrawer(header, scroller, createFooter());
    }

    public void addSideNavItem(SideNavItem sideNavItem) {
        this.sideNav.addItem(sideNavItem);
    }

    private Footer createFooter() {
        Footer layout = new Footer();
        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }


}
