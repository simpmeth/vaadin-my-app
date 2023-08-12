package id.platforma.geo.application.entity.graphql.base.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WhereIn<T> {
    private List<T> _and;
    private List<T> _or;
    private List<T> _not;
    private List<T> _eq;
}
