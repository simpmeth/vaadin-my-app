package id.platforma.geo.application.entity.graphql.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
    private String supplier;
    private String description;
    private List<Object> photos;
    private List<ProductFeature> productFeatures;
    private UpdatePeriod updatePeriod;
    private String id;
    private String title;
    private String annotation;
    private Territory territory;
    private String price;
    private List<Schedule> schedules;
    private Integer duration;
    private List<RecipeValue> recipeValues;
    private String __typename;
    private List<Layer> layers;
}
