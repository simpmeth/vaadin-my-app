package id.platforma.geo.application.views;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import id.platforma.geo.application.component.vaadin.EmbedDashboard;
import id.platforma.geo.application.component.vaadin.Superset;
import id.platforma.geo.application.views.layout.MainLayout;

@PageTitle("Добрый день! ")
@Route(value = "superset", layout = MainLayout.class)
@AnonymousAllowed
public class SupersetView extends HorizontalLayout {
    public SupersetView() {
        Superset superset = Superset
                .embedDashboard();
        EmbedDashboard dashboard = EmbedDashboard
                .builder()
                .id("fb4c1daa-7a21-4a24-8f27-be9479f55c00")
                .supersetDomain("https://bi.platforma.id")
                .fetchGuestToken(SupersetView::getToken)
                .dashboardUiConfig(null)
                .build();
        superset.setEmbedDashboard(dashboard);

        superset.setSizeFull();


        VerticalLayout container = new VerticalLayout(superset);
        add(container);
        setSizeFull();
    }

    private static String getToken() {
        return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ1c2VyIjp7InVzZXJuYW1lIjoici5rdWxtaXRvdiIsImxhc3RfbmFtZSI6IiIsImZpcnN0X25hbWUiOiIifSwicmVzb3VyY2VzIjpbeyJ0eXBlIjoiZGFzaGJvYXJkIiwiaWQiOiJmYjRjMWRhYS03YTIxLTRhMjQtOGYyNy1iZTk0NzlmNTVjMDAifV0sInJsc19ydWxlcyI6W10sImlhdCI6MTY5MDc5MTE0OS40MjgzODg4LCJleHAiOjE2OTA3OTE0NDkuNDI4Mzg4OCwiYXVkIjoiaHR0cDovLzAuMC4wLjA6ODA4MC8iLCJ0eXBlIjoiZ3Vlc3QifQ.y5SnFRzuaql-qpdLnFUco_ON32CUekE3MMVZiQU9qHMw4e229VM2q1Eb_9x9sa0FhqjUwCP6dWKdm9WBwkw06Q";
    }
}
