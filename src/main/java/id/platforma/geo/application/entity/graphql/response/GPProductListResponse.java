package id.platforma.geo.application.entity.graphql.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import id.platforma.geo.application.entity.graphql.base.ListQueryResult;
import id.platforma.geo.application.entity.graphql.base.ProductCard;
import id.platforma.geo.application.entity.graphql.base.Response;
import lombok.Data;
import lombok.NoArgsConstructor;

public class GPProductListResponse extends Response<GPProductListResponse.GPProductWrapper<ProductCard>> {
    @Data
    @NoArgsConstructor
    public static class GPProductWrapper<T> {
        @JsonProperty("GPProduct")
        private ListQueryResult<T> gpProductList;
    }
}
