package id.platforma.geo.application.entity.graphql.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.platforma.geo.application.entity.graphql.base.GetQueryResult;
import id.platforma.geo.application.entity.graphql.base.ProductCard;
import id.platforma.geo.application.entity.graphql.base.Response;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class GPProductGetResponse extends Response<GPProductGetResponse.GPProductWrapper<ProductCard>> {
    @Data
    @NoArgsConstructor
    public static class GPProductWrapper<T> {
        @JsonProperty("GPProduct")
        private GetQueryResult<T> gpProductGet;
    }
}
