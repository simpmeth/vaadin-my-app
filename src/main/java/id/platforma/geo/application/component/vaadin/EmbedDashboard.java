package id.platforma.geo.application.component.vaadin;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.function.Supplier;

@Data
@Builder
public class EmbedDashboard {
    private String id;
    private String supersetDomain;
    private Supplier<String> fetchGuestToken;
    private DashboardUiConfig dashboardUiConfig;

    @Data
    @Builder
    public static class DashboardUiConfig {
        private boolean hideTitle;
        private boolean hideTab;
        private boolean hideChartControls;
    }
}
