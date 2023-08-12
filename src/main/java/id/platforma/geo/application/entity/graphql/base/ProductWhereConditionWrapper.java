package id.platforma.geo.application.entity.graphql.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ProductWhereConditionWrapper {
    @JsonProperty("on_GPStatDataset")
    private On on_GPStatDataset;
    @JsonProperty("on_GPCommercialService")
    private On on_GPCommercialService;
    @JsonProperty("on_GPGeoModel")
    private On on_GPGeoModel;
    @JsonProperty("on_GPLayer")
    private On on_GPLayer;
    @JsonProperty("on_GPRecipe")
    private On on_GPRecipe;
    @JsonProperty("on_GPBIRecipe")
    private On on_GPBIRecipe;

    public static ProductWhereConditionWrapper byTitle(String value) {
        On onCondition = On.builder()
                .title(ILike.builder()
                        ._ilike(value)
                        .build())
                .build();
        return ProductWhereConditionWrapper
                .builder()
                .on_GPStatDataset(onCondition)
                .on_GPCommercialService(onCondition)
                .on_GPGeoModel(onCondition)
                .on_GPLayer(onCondition)
                .on_GPRecipe(onCondition)
                .on_GPBIRecipe(onCondition)
                .build();
    }

    @Data
    @Builder
    public static class On {
        private ILike title;
    }

    @Data
    @Builder
    private static class ILike {

        @JsonProperty("_ilike")
        private String _ilike;
    }
}
