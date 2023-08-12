package software.xdev.vaadin.maps.leaflet.flow.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LTileLayerWMS extends LTileLayer {

    public static final LTileLayerWMS DEFAULT_OPENSTREETMAP_WMS_LAYER =
            new LTileLayerWMS("http://ows.mundialis.de/services/service?",
                    Options.builder()
                            .layers(new String[]{"TOPO-WMS", "OSM-Overlay-WMS"})
                            .maxZoom(18)
                            .attribution("TOPO-WMS,OSM-Overlay-WMS")
                            .build());
    private Options options;

    public LTileLayerWMS(String url, Options options) {
        super(url, options.getAttribution(), options.getMaxZoom());
        this.setOptions(options);
    }

    public LTileLayerWMS(String url, String attribution, int maxZoom) {
        super(url, attribution, maxZoom);
    }
}
