package id.platforma.geo.application.entity.graphql.base;

import lombok.Data;

@Data
public class GetQueryResult<T> {
    private T get;
}
