package id.platforma.geo.application.entity.graphql.base;

import lombok.Data;

@Data
public class Schedule {
    private String id;
    private String title;
    private int price;
    private Layer layer;
    private Dataset dataset;
}
