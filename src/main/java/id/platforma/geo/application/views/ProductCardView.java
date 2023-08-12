package id.platforma.geo.application.views;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import id.platforma.geo.application.entity.graphql.base.Product;
import id.platforma.geo.application.entity.graphql.base.ProductCard;
import id.platforma.geo.application.entity.graphql.base.ProductFeature;
import id.platforma.geo.application.entity.graphql.base.RecipeValue;
import id.platforma.geo.application.entity.graphql.base.Schedule;
import id.platforma.geo.application.entity.graphql.wrapper.GPProduct;
import id.platforma.geo.application.views.layout.MainLayout;
import jakarta.annotation.security.PermitAll;

@PageTitle("Карточка продукта")
@Route(value = "/productCard/:productCardId", layout = MainLayout.class)
@PermitAll
public class ProductCardView extends VerticalLayout implements HasUrlParameter<String> {
    private String productCardId;
    private ProductCard productCard;

    public ProductCardView() {

    }

    private static void getIconLayout(VerticalLayout iconBox, VaadinIcon icon, String label, String value) {
        if (value == null || value.isEmpty()) return;
        VerticalLayout iconLayout = new VerticalLayout();
        Div searchDiv = new Div(new Icon(icon));
        iconLayout.add(searchDiv);

        VerticalLayout textLayout = new VerticalLayout();
        textLayout.add(
                new HorizontalLayout(new Text(label)),
                new HorizontalLayout(new Text(value))
        );

        iconBox.add(new HorizontalLayout(iconLayout, textLayout));
    }

    @Override
    public void setParameter(BeforeEvent event,
                             @OptionalParameter String parameter) {

        final RouteParameters urlParameters = event.getRouteParameters();

        this.productCardId = urlParameters.get("productCardId").get();
        this.productCard = GPProduct.GET(this.productCardId).getData().getGpProductGet().getGet();

        VerticalLayout iconBox = new VerticalLayout();
        iconBox.setWidth(200, Unit.PIXELS);
        Product product = productCard.getProduct().get(0);

        getIconLayout(iconBox, VaadinIcon.MAP_MARKER, "Город",
                product.getTerritory() == null ? null : product.getTerritory().getName());

        getIconLayout(iconBox, VaadinIcon.CALENDAR, "Обновление",
                product.getUpdatePeriod() == null ? null : product.getUpdatePeriod().getTitle());

        getIconLayout(iconBox, VaadinIcon.BRIEFCASE, "Поставщик", product.getSupplier());
        getIconLayout(iconBox, VaadinIcon.STOPWATCH, "Результат за",
                product.getDuration() == null
                        ? null : String.format("%s минут", product.getDuration()));

        getIconLayout(iconBox, VaadinIcon.PACKAGE, "Рецепт включает",
                product.getLayers() == null
                        ? null : String.format("%s слоёв", product.getLayers().size()));


        VerticalLayout infoBox = new VerticalLayout();
        H2 header = new H2(product.getTitle());
        infoBox.add(header);
        if (product.getSchedules() != null && product.getSchedules().size() == 1) {
            H6 schedules = new H6(product.getSchedules().get(0).getTitle());
            infoBox.add(schedules);
        } else if (product.getLayers() != null) {
            H6 schedules = new H6(String.format("Рецепт включает %s слоёв", product.getLayers().size()));
            infoBox.add(schedules);
        }

        Div description = new Div(new Html("<span>" + product.getDescription() + "<span/>"));
        infoBox.add(description);

        if (product.getRecipeValues() != null && product.getRecipeValues().size() > 0) {

            infoBox.add(new H6("Использование рецепта поможет"));

            VerticalLayout table = new VerticalLayout();

            int rowCount = 0;
            HorizontalLayout row = new HorizontalLayout();
            for (RecipeValue recipeValue : product.getRecipeValues()) {

                Div featureDiv = new Div();
                featureDiv.getStyle().set("border", "6px dotted DarkOrange");
                featureDiv.setHeight(300, Unit.PIXELS);
                featureDiv.setWidth(330, Unit.PIXELS);

                featureDiv.add(new H6(recipeValue.getTitle()));
                featureDiv.add(new Html("<span>" + recipeValue.getWording() + "<span/>"));
                if (rowCount <= 2) {
                    row.add(featureDiv);
                    rowCount++;
                } else {

                    table.add(row);
                    row = new HorizontalLayout();
                    row.add(featureDiv);
                    rowCount = 0;
                }
            }
            if (rowCount > 0) table.add(row);
            infoBox.add(table);
        }

        if (product.getProductFeatures() != null && product.getProductFeatures().size() > 0) {

            infoBox.add(new H5("Данные позволяют анализировать"));

            HorizontalLayout layout = new HorizontalLayout();
            for (ProductFeature feature : product.getProductFeatures()) {
                Div featureDiv = new Div();
                featureDiv.getStyle().set("border", "6px dotted DarkOrange");
                featureDiv.setHeight(300, Unit.PIXELS);
                featureDiv.setWidth(330, Unit.PIXELS);

                featureDiv.add(new H6(feature.getTitle()));
                featureDiv.add(new Html("<span>" + feature.getDescription() + "<span/>"));
                layout.add(featureDiv);
            }
            infoBox.add(layout);
        }


        if (product.getSchedules() != null && product.getSchedules().size() > 0) {

            StringBuilder titleStringBuilder = new StringBuilder();
            if (product.get__typename() == null) product.set__typename("");
            switch (product.get__typename()) {
                case "GPLayer":
                    titleStringBuilder.append("Слой");
                    break;
                case "GPStatDataset":
                    titleStringBuilder.append("Набор данный\"");
                    break;
                default:
                    titleStringBuilder.append("DEF ");
                    break;
            }
            titleStringBuilder.append(" %s представлен в нескольких периодах");

            infoBox.add(new H5(String.format(titleStringBuilder.toString(), product.getTitle())));

            VerticalLayout layout = new VerticalLayout();
            for (Schedule schedule : product.getSchedules()) {
                if (schedule.getDataset() != null) {
                    HorizontalLayout row = new HorizontalLayout();
                    VerticalLayout descriptionSchedule = new VerticalLayout();

                    descriptionSchedule.add(
                            new HorizontalLayout(new Text(schedule.getTitle())),
                            new HorizontalLayout(new Text(schedule.getDataset().getUName()))
                    );

                    VerticalLayout passportLink = new VerticalLayout(new Text("Паспорт качества: "));
                    Anchor link = new Anchor(schedule.getDataset().getPassport(), "Скачать");

                    passportLink.add(link);

                    row.add(descriptionSchedule, passportLink);
                    layout.add(row);
                } else {

                    HorizontalLayout row = new HorizontalLayout();
                    VerticalLayout descriptionSchedule = new VerticalLayout();

                    descriptionSchedule.add(
                            new HorizontalLayout(new Text(schedule.getTitle())),
                            new HorizontalLayout(new Text(schedule.getLayer().getUName()))
                    );

                    VerticalLayout passportLink = new VerticalLayout(new Text("Паспорт качества: "));
                    Anchor link = new Anchor(schedule.getLayer().getUri(), "Скачать");

                    passportLink.add(link);

                    row.add(descriptionSchedule, passportLink);
                    layout.add(row);

                }
            }
            infoBox.add(layout);
        }

        VerticalLayout container = new VerticalLayout(new HorizontalLayout(iconBox, infoBox));
        container.setSizeFull();
        add(container);
        setSpacing(false);
        this.setSizeFull();
    }

}
