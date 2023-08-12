package id.platforma.geo.application.entity.graphql.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentList<T> {
    private List<T> data;
    private Boolean hasMore;
    private Integer totalCount;
}
