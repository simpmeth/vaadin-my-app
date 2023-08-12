package software.xdev.vaadin.maps.leaflet.flow.data;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import software.xdev.vaadin.maps.leaflet.flow.LMap;

import static org.apache.commons.text.StringEscapeUtils.escapeEcmaScript;

@NpmPackage(value = "leaflet", version = "1.9.4")
@Tag("leaflet-map")
@JsModule("leaflet/dist/leaflet.js")
@CssImport("leaflet/dist/leaflet.css")
@CssImport("./leaflet/leaflet-custom.css")
public class WmsLMap extends LMap {
    private static final String CLIENT_MAP = "this.map";
    private static final String CLIENT_COMPONENTS = "this.components";
    private static final String CLIENT_TILE_LAYER = "this.wmstilelayer";

    public WmsLMap(double lat, double lon, int zoom) {
        super(lat, lon, zoom);
    }

    public void setWMSTileLayer(LTileLayerWMS tl) {
        final String removeTileLayerIfPresent = "if (" + CLIENT_TILE_LAYER + ") {"
                + CLIENT_MAP + ".removeLayer(" + CLIENT_TILE_LAYER + ");"
                + "}";
        final String addTileLayer = CLIENT_TILE_LAYER + " = L.tileLayer.wms("
                + "'" + escapeEcmaScript(tl.getLink()) + "'"
                + ",{"
                + "attribution: '" + escapeEcmaScript(tl.getAttribution()) + "'"
                + ", maxZoom: " + tl.getMaxZoom()
                + ", layers: '" + String.join(",", tl.getOptions().getLayers()) + "'"
                + (tl.getId() != null ? ", id: '" + escapeEcmaScript(tl.getId()) + "'" : "")
                + "}"
                + ").addTo(" + CLIENT_MAP + ");";
        this.getElement().executeJs(removeTileLayerIfPresent + "\n" + addTileLayer);
    }

    @Override
    public void setTileLayer(LTileLayer tl) {
        if (tl instanceof LTileLayerWMS) setWMSTileLayer((LTileLayerWMS) tl);
        super.setTileLayer(tl);
    }
}
