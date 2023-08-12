package id.platforma.geo.application.entity.graphql.base;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Location {
    private Integer line;
    private Integer column;
    private String sourceName;
}
