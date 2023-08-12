package id.platforma.geo.application.entity.graphql.base.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoolExpression<T> {
    private T _eq;
    private T _gt;
    private T _gte;
    private List<T> _in;
    private Boolean _isNull;
    private T _lt;
    private T _lte;
    private T _neq;
    private List<T> _nin;
    private T _ilike;
}
