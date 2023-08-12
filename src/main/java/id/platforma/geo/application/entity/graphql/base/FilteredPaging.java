package id.platforma.geo.application.entity.graphql.base;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter(onMethod = @__(@Override))
@SuperBuilder
public class FilteredPaging<T> extends WhereVariables<T> implements PagingVariables {
    private Integer offset;
    private Integer limit;
}
