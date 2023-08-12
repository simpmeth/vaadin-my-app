package id.platforma.geo.application.entity.graphql.base;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Territory {
    private String id;
    private String name;
    private String geometryWKT;
    private String fcode;
}