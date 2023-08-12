package id.platforma.geo.application.views;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.AnchorTarget;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import id.platforma.geo.application.views.layout.MainLayout;
import software.xdev.vaadin.maps.leaflet.flow.data.LCenter;
import software.xdev.vaadin.maps.leaflet.flow.data.LDivIcon;
import software.xdev.vaadin.maps.leaflet.flow.data.LMarker;
import software.xdev.vaadin.maps.leaflet.flow.data.LPoint;
import software.xdev.vaadin.maps.leaflet.flow.data.LPolygon;
import software.xdev.vaadin.maps.leaflet.flow.data.LTileLayer;
import software.xdev.vaadin.maps.leaflet.flow.data.LTileLayerWMS;
import software.xdev.vaadin.maps.leaflet.flow.data.WmsLMap;

import java.util.Arrays;

@PageTitle("Добрый день! ")
@Route(value = "leaflet", layout = MainLayout.class)
@AnonymousAllowed
public class LeafletView extends HorizontalLayout {
    /**
     * UI-Components
     */
    private final Button btnLunch1 = new Button("1");
    private final Button btnLunch2 = new Button("2");
    LTileLayer defaultOpenstreetmapTile = LTileLayer.DEFAULT_OPENSTREETMAP_TILE;
    LTileLayerWMS defaultOpenstreetmapWmsLayer = LTileLayerWMS.DEFAULT_OPENSTREETMAP_WMS_LAYER;
    private boolean viewLunch = false;
    private WmsLMap map1;
    private WmsLMap map2;
    private LTileLayer tileLayer;

    public LeafletView() {
        this.map1 = initMapComponents(defaultOpenstreetmapTile, defaultOpenstreetmapWmsLayer);
        this.map2 = initMapComponents(defaultOpenstreetmapTile, defaultOpenstreetmapWmsLayer);

        btnLunch1.getElement().setAttribute("map", "map1");
        btnLunch2.getElement().setAttribute("map", "map2");

        this.btnLunch1.addClickListener(this::btnLunchClick);

        this.btnLunch2.addClickListener(this::btnLunchClick);

        final HorizontalLayout hlButtonContainer = new HorizontalLayout();
        hlButtonContainer.setWidthFull();
        hlButtonContainer.setJustifyContentMode(JustifyContentMode.BETWEEN);
        hlButtonContainer.setPadding(false);
        hlButtonContainer.setSpacing(false);

        addButton(btnLunch1, this.map1, hlButtonContainer);
        addButton(btnLunch2, this.map2, hlButtonContainer);

        VerticalLayout container = new VerticalLayout(this.map1, this.map2, hlButtonContainer);
        container.setSizeFull();
        add(container);
        setSizeFull();
    }

    private void addButton(Button btnLunch, WmsLMap map, HorizontalLayout hlButtonContainer) {
        hlButtonContainer.add(
                btnLunch,
                new Button("Center on Caribbean", e -> map.centerAndZoom(
                        new LPoint(14.467727, -61.69703),
                        new LPoint(16.33426, -60.921676))),
                new Button("Open dialog over map", ev ->
                {
                    final Icon icoClose = VaadinIcon.CLOSE.create();

                    final Dialog dialog = new Dialog(icoClose);
                    dialog.setWidth("50vw");
                    dialog.setHeight("50vh");
                    dialog.open();

                    icoClose.addClickListener(iev -> dialog.close());
                })
        );
    }

    private <L extends LTileLayer> WmsLMap initMapComponents(L... tileLayers) {
        WmsLMap map;

        final LMarker markerXDev = new LMarker(49.675806677512824, 12.160990185846394);
        markerXDev.setPopup(
                "<a href='https://xdev.software/en' target='" + AnchorTarget.BLANK.getValue() + "'>XDEV Software "
                        + "GmbH</a>");

        final LMarker markerInfo = new LMarker(49.674095, 12.162257);

        markerInfo.setDivIcon(new LDivIcon("""
                <p><center><b>Welcome to Weiden in der Oberpfalz!</b></center></p>
                <p>This demo shows you different markers,<br> popups, polygons and other stuff</p>
                """));

        final LPolygon polygonNoc = new LPolygon(
                Arrays.asList(
                        new LPoint(49.674883, 12.159098),
                        new LPoint(49.675719, 12.160248),
                        new LPoint(49.676080, 12.159985),
                        new LPoint(49.675750, 12.158008),
                        new LPoint(49.675306, 12.158499)));
        polygonNoc.setFill(true);
        polygonNoc.setFillColor("#3366ff");
        polygonNoc.setFillOpacity(0.8);
        polygonNoc.setStroke(false);
        polygonNoc.setPopup("NOC-Nordoberpfalz Center");


        map = new WmsLMap(49.675126, 12.160733, 17);


        int pos = 0;
        for (LTileLayer layer : tileLayers) {
            layer.setAttribution("WMS -- " + pos);
            map.setTileLayer(layer);
            pos++;
        };

        map.setSizeFull();
        // add some logic here for called Markers (token)
        map.addMarkerClickListener(ev -> System.out.println(ev.getTag()));

        map.addLComponents(
                markerXDev,
                markerInfo,
                polygonNoc);
        return map;
    }

    private void btnLunchClick(final ClickEvent<Button> event) {
        this.viewLunch = !this.viewLunch;


        String map = event.getSource().getElement().getAttribute("map");
        Component component1 = UI.getCurrent().getChildren().filter(component -> component.getId().get().equals(map)).findFirst().get();
        if (component1 instanceof WmsLMap)
            ((WmsLMap) component1).setViewPoint(new LCenter(49.675126, 12.160733, this.viewLunch ? 16 : 17));
        event.getSource().getElement().setText(this.viewLunch ? "Go back to the normal view" : "Where do XDEV employees go for lunch?");
    }
}
