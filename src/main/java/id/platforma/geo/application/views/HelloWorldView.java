package id.platforma.geo.application.views;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.ItemDoubleClickEvent;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import id.platforma.geo.application.entity.graphql.base.Product;
import id.platforma.geo.application.entity.graphql.base.ProductCard;
import id.platforma.geo.application.entity.graphql.base.RecipeValue;
import id.platforma.geo.application.provider.Filter;
import id.platforma.geo.application.service.ProductListProvider;
import id.platforma.geo.application.views.layout.MainLayout;
import jakarta.annotation.security.PermitAll;

import java.util.Date;

@PageTitle("Добрый день!")
@Route(value = "hello", layout = MainLayout.class)
@PermitAll
public class HelloWorldView extends HorizontalLayout {
    private int productCardCount = 0;
    private ProductListProvider productListProvider;
    private Filter filter = new Filter();
    private ComponentRenderer<Component, ProductCard> personCardRenderer = new ComponentRenderer<>(
            productCard -> {
                HorizontalLayout cardLayout = new HorizontalLayout();
                cardLayout.setMargin(true);
                Avatar avatar = new Avatar(productCard.getProductType().getName() + "_2");
                avatar.setHeight("64px");
                avatar.setWidth("64px");

                VerticalLayout infoLayout = new VerticalLayout();
                infoLayout.setSpacing(false);
                infoLayout.setPadding(false);
                infoLayout.getElement().appendChild(
                        ElementFactory.createStrong(productCard.getProduct().get(0).getTitle()));
                infoLayout.add(new Div(new Text(productCard.getProductType().getId())));

                VerticalLayout contactLayout = new VerticalLayout();
                contactLayout.setSpacing(false);
                contactLayout.setPadding(false);
                productCardCount++;
                for (Product product : productCard.getProduct()) {
                    contactLayout.add(new Div(new Text(String.valueOf(productCardCount))));
                    contactLayout.add(new Div(new Text(product.getId())));
                    contactLayout.add(new Div(new Text(product.getTitle())));
                    contactLayout.add(new Div(new Text(product.getAnnotation())));
                    contactLayout.add(new Div(new Text(String.valueOf(product.getDuration()))));
                    contactLayout.add(new Div(new Text(product.getPrice())));
                    contactLayout.add(new Div(new Text("--------------------")));
                    if (product.getRecipeValues() != null)
                        for (RecipeValue recipe : product.getRecipeValues()) {
                            contactLayout.add(new Div(new Text(recipe.getId())));
                            contactLayout.add(new Div(new Text(recipe.getTitle())));
                            contactLayout.add(new Div(new Text(recipe.getWording())));
                        }
                    contactLayout.add(new Div(new Text("--------------------")));
                }
                Details contactInformation = new Details("Product", contactLayout);
                contactInformation.setOpened(true);
                infoLayout
                        .add(contactInformation);

                cardLayout.add(avatar, infoLayout);
                return cardLayout;
            });

    public HelloWorldView(ProductListProvider productListProvider) {
        this.productListProvider = productListProvider;
        productCardCount = 0;

        Grid<ProductCard> grid = new Grid<>(ProductCard.class, false);

        grid.addColumn(personCardRenderer);
        grid.setPageSize(6);
        grid.addItemDoubleClickListener(
                (ComponentEventListener<ItemDoubleClickEvent<ProductCard>>) event -> openCard(event.getItem())
        );

        Button button = new Button("Каталог");
        button.setPrefixComponent(new Icon(VaadinIcon.ALIGN_JUSTIFY));


        TextField searchField = new TextField();
        searchField.setWidth("100%");
        searchField.setPlaceholder("Поиск слоев и моделей");
        searchField.setSuffixComponent(new Icon(VaadinIcon.SEARCH));
        searchField.setValueChangeMode(ValueChangeMode.EAGER);
        searchField.setValueChangeMode(ValueChangeMode.LAZY);
        searchField.addValueChangeListener(e -> {
            filter.getSearchTerm().put("title", e.getValue());
            productCardCount = 0;
            grid.setItems(query -> productListProvider.fetchProduct(filter, query).stream());
        });

        HorizontalLayout searchLayout = new HorizontalLayout(button, searchField);
        searchLayout.setWidth("100%");

        grid.setItems(query -> productListProvider.fetchProduct(filter, query).stream());

        VerticalLayout container = new VerticalLayout(searchLayout, grid);
        container.setSizeFull();
        this.setSizeFull();
        add(container);
    }

    private static void openCard(ProductCard productCard) {
        UI.getCurrent().navigate("/productCard/" + productCard.getId());
    }

    public void startTime(ClickEvent<Button> e) {
        new Thread(() ->
                Notification.show(new Date().toGMTString())
        ).start();
    }
}
