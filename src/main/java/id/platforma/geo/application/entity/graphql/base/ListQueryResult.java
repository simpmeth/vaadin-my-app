package id.platforma.geo.application.entity.graphql.base;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListQueryResult<T> {
    private DocumentList<T> list;
}
