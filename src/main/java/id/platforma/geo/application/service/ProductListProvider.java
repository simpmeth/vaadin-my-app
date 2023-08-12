package id.platforma.geo.application.service;

import com.vaadin.flow.data.provider.Query;
import id.platforma.geo.application.entity.graphql.base.ProductCard;
import id.platforma.geo.application.provider.Filter;

import java.util.List;

public interface ProductListProvider {
    List<ProductCard> fetchProduct(Filter filter, Query<ProductCard, Void> query);
}