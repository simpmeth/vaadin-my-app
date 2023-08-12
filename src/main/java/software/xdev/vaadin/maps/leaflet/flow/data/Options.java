package software.xdev.vaadin.maps.leaflet.flow.data;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.MediaType;

@Data
@SuperBuilder
class Options {
    private String link;
    private String attribution;
    private int maxZoom;
    private String id;

    private String crs;
    private String[] layers;
    private String service;
    private String request;
    private String styles;
    private MediaType format;
    private Boolean transparent;
    private String version;
}
