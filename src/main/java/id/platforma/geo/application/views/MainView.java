package id.platforma.geo.application.views;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import id.platforma.geo.application.views.layout.MainLayout;
import jakarta.annotation.security.PermitAll;

@PageTitle("Главная страница")
@Route(value = "", layout = MainLayout.class)
@PermitAll
@AnonymousAllowed
public class MainView extends VerticalLayout {
    public MainView() {
        setSpacing(false);

        Image img = new Image("images/empty-plant.png", "placeholder plant");
        img.setWidth("200px");
        add();


        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        this.setSizeFull();
        add(layout, img);


        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }
}
