package id.platforma.geo.application.entity.graphql.base;

import lombok.Data;

import java.util.List;

@Data
public class ProductCard {
    private String id;
    private ProductType productType;
    private List<Product> product;
}
