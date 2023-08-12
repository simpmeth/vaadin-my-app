package id.platforma.geo.application.entity.graphql.request;

import id.platforma.geo.application.entity.graphql.base.FilteredPaging;
import id.platforma.geo.application.entity.graphql.base.ProductWhereConditionWrapper;
import id.platforma.geo.application.entity.graphql.base.VariablesGenericRequest;
import id.platforma.geo.application.entity.graphql.base.filter.WhereIn;
import id.platforma.geo.application.provider.Filter;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
public class GPProductList implements VariablesGenericRequest<FilteredPaging<GPProductList.ProductWhereIn>> {
    private FilteredPaging<ProductWhereIn> variables;

    private GPProductList(FilteredPaging<ProductWhereIn> variables) {
        this.variables = variables;
    }

    public static GPProductList paging(Filter filter, int limit, int offset) {
        ProductWhereConditionWrapper productWhereConditionWrapper =
                filter.getSearchTerm().get("title") != null
                        ? ProductWhereConditionWrapper.byTitle(filter.getSearchTerm().get("title"))
                        : null;

        WhereIn<ProductWhereIn> productWhereIn = ProductWhereIn.builder()
                .product(productWhereConditionWrapper)
                .build();

        FilteredPaging<ProductWhereIn> variables = FilteredPaging
                .<ProductWhereIn>builder()
                .limit(limit)
                .offset(offset)
                .where(productWhereIn)
                .build();

        return new GPProductList(variables);
    }

    @Override
    public String getOperationName() {
        return "productsListQuery";
    }

    @Override
    public String getQuery() {
        return "query productsListQuery($where: GPProductWhereIn, $limit: Int, $offset: Int, $order: [InputOrder]) {\n" +
                "  GPProduct {\n" +
                "    list(where: $where, limit: $limit, offset: $offset, order: $order) {\n" +
                "      data {\n" +
                "        id\n" +
                "        productType {\n" +
                "          id\n" +
                "          name\n" +
                "        }\n" +
                "        product {\n" +
                "          ... on GPLayer {\n" +
                "            id\n" +
                "            title\n" +
                "            annotation\n" +
                "            territory {\n" +
                "              id\n" +
                "              name\n" +
                "            }\n" +
                "            price\n" +
                "            schedules {\n" +
                "              id\n" +
                "              title\n" +
                "              price\n" +
                "              layer {\n" +
                "                id\n" +
                "                uName\n" +
                "                title\n" +
                "              }\n" +
                "            }\n" +
                "          }\n" +
                "          ... on GPGeoModel {\n" +
                "            id\n" +
                "            title\n" +
                "            annotation\n" +
                "            territory {\n" +
                "              id\n" +
                "              name\n" +
                "            }\n" +
                "            price\n" +
                "          }\n" +
                "          ... on GPCommercialService {\n" +
                "            id\n" +
                "            title\n" +
                "            annotation\n" +
                "            territory {\n" +
                "              id\n" +
                "              name\n" +
                "            }\n" +
                "            price\n" +
                "            serviceValues {\n" +
                "              id\n" +
                "              title\n" +
                "              wording\n" +
                "            }\n" +
                "          }\n" +
                "          ... on GPStatDataset {\n" +
                "            id\n" +
                "            title\n" +
                "            annotation\n" +
                "            price\n" +
                "            territory {\n" +
                "              id\n" +
                "              name\n" +
                "            }\n" +
                "            schedules {\n" +
                "              id\n" +
                "              title\n" +
                "              price\n" +
                "              dataset {\n" +
                "                id\n" +
                "                uName\n" +
                "                title\n" +
                "              }\n" +
                "            }\n" +
                "          }\n" +
                "          ... on GPRecipe {\n" +
                "            id\n" +
                "            title\n" +
                "            annotation\n" +
                "            duration\n" +
                "            recipeValues {\n" +
                "              id\n" +
                "              title\n" +
                "              wording\n" +
                "            }\n" +
                "            __typename\n" +
                "          }\n" +
                "          ... on GPBIRecipe {\n" +
                "            id\n" +
                "            title\n" +
                "            annotation\n" +
                "            duration\n" +
                "            recipeValues {\n" +
                "              id\n" +
                "              title\n" +
                "              wording\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "      hasMore\n" +
                "      totalCount\n" +
                "    }\n" +
                "  }\n" +
                "}";
    }

    @Override
    public FilteredPaging<ProductWhereIn> getVariables() {
        return this.variables;
    }

    @Override
    public void setVariables(FilteredPaging<ProductWhereIn> variables) {
        this.variables = variables;
    }

    @Data
    @SuperBuilder
    public static class ProductWhereIn extends WhereIn<ProductWhereIn> {
        public ProductWhereConditionWrapper product;
    }
}
