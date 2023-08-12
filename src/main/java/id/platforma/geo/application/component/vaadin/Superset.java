package id.platforma.geo.application.component.vaadin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.IFrame;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Tag("superset-container")
@JsModule("./superset/embedded-sdk@0.1.0-alpha.9/index.js")
@CssImport("./superset/superset-custom.css")
public class Superset extends Component implements HasSize, HasStyle, HasComponents {
    public static final String STANDALONE_PARAM_NAME = "standalone";
    public static final String DEFAULT_SUPERSET_CLASS_NAME = "App";
    private static final String CLIENT_SUPERSET = "this.map";
    StringBuilder jsSb = new StringBuilder();
    private boolean standalone;

    public Superset(String url, boolean standalone) {
        this.standalone = standalone;
        UUID uuid = UUID.randomUUID();
        String uuidStr = getNonSlashedString(uuid.toString());

        IFrame iFrame = new IFrame();
        iFrame.setSizeFull();
        if (url != null && !url.isEmpty()) {
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
            builder.queryParam(STANDALONE_PARAM_NAME, standalone);
            iFrame.setSrc(builder.toUriString());
        } else {

        }

        Div div = new Div();
        div.setId(uuidStr + "_div");
        div.setClassName(DEFAULT_SUPERSET_CLASS_NAME);
        div.setSizeFull();
        div.getStyle().set("font-family", "sans-serif");
        div.getStyle().set("text-align", "center");

        this.setId(uuidStr);

        div.add(iFrame);
        this.add(div);
    }

    public static Superset standalone(String url) {
        return new Superset(url, true);
    }


    public static Superset embedDashboard() {
        return new Superset("", true);
    }

    public void setEmbedDashboard(EmbedDashboard embedDashboard) {
        if (embedDashboard == null) return;
        ObjectMapper mapper = new ObjectMapper();

        jsSb.append(
                "\nconst getToken" + getIdStr() + " = () => {\n" +
                        "  return \"" + embedDashboard.getFetchGuestToken().get() + "\";\n" +
                        "};\n");

        String getDashboardUiConfigStr = null;
        try {
            getDashboardUiConfigStr = mapper.writeValueAsString(embedDashboard.getDashboardUiConfig());
        } catch (Exception e) {

        }

        jsSb.append("\n supersetEmbeddedSdk.embedDashboard({\n" +
                "  id: \"" + embedDashboard.getId() + "\", \n" +
                "  supersetDomain: \"" + embedDashboard.getSupersetDomain() + "\", \n" +
                "  mountPoint: document.getElementById(\"" + getIdStr() + "_div\"),\n" +
                "  fetchGuestToken: () => getToken" + getIdStr() + "(),\n" +
                (getDashboardUiConfigStr != null && !getDashboardUiConfigStr.isEmpty()
                        ? "  dashboardUiConfig: " + getDashboardUiConfigStr + "\n"
                        : "") +
                "});\n");
        this.getElement().executeJs(jsSb.toString());
    }

    private String getIdStr() {
        return getNonSlashedString(this.getId().get());
    }

    private String getNonSlashedString(String str) {
        return str.replace("-", "");
    }
}
