package id.platforma.geo.application.entity.graphql.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import id.platforma.geo.application.entity.graphql.base.filter.WhereIn;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
public class WhereVariables<T> {
    private WhereIn<T> where;
}
