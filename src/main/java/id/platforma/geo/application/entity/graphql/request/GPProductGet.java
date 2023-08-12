package id.platforma.geo.application.entity.graphql.request;

import id.platforma.geo.application.entity.graphql.base.UniqueEntity;
import id.platforma.geo.application.entity.graphql.base.VariablesGenericRequest;
import lombok.Data;

@Data
public class GPProductGet implements VariablesGenericRequest<UniqueEntity> {
    private UniqueEntity variables;

    private GPProductGet(UniqueEntity variables) {
        this.variables = variables;
    }

    public static GPProductGet get(String id) {
        return new GPProductGet(UniqueEntity
                .builder()
                .id(id)
                .build());
    }


    @Override
    public String getOperationName() {
        return "getProduct";
    }

    @Override
    public String getQuery() {
        return "query getProduct($id: String!) {\n" +
                "  GPProduct {\n" +
                "    get(id: $id) {\n" +
                "      id\n" +
                "      productType {\n" +
                "        id\n" +
                "        name\n" +
                "      }\n" +
                "      product {\n" +
                "        ... on GPLayer {\n" +
                "          ...LayerFields\n" +
                "        }\n" +
                "        ... on GPGeoModel {\n" +
                "          ...GeoModelFields\n" +
                "        }\n" +
                "        ... on GPCommercialService {\n" +
                "          ...CommercialService\n" +
                "        }\n" +
                "        ... on GPStatDataset {\n" +
                "          ...GpStatDatasetFields\n" +
                "        }\n" +
                "        ... on GPRecipe {\n" +
                "          id\n" +
                "          title\n" +
                "          layers {\n" +
                "            ...LayersFields\n" +
                "          }\n" +
                "          steps {\n" +
                "            ...RecipeStepsFields\n" +
                "          }\n" +
                "          img\n" +
                "          duration\n" +
                "          description\n" +
                "          recipeValues {\n" +
                "            id\n" +
                "            num\n" +
                "            wording\n" +
                "            title\n" +
                "          }\n" +
                "          semanticTerms {\n" +
                "            id\n" +
                "            term\n" +
                "            definition\n" +
                "          }\n" +
                "          usefulLayers {\n" +
                "            id\n" +
                "            title\n" +
                "            description\n" +
                "            price\n" +
                "            territory {\n" +
                "              ...TerritoryFields\n" +
                "            }\n" +
                "          }\n" +
                "          description\n" +
                "          __typename\n" +
                "        }\n" +
                "        ... on GPBIRecipe {\n" +
                "          ...BiRecipeFields\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}\n" +
                "\n" +
                "fragment LayerFields on GPLayer {\n" +
                "  id\n" +
                "  title\n" +
                "  description\n" +
                "  annotation\n" +
                "  price\n" +
                "  passport\n" +
                "  supplier\n" +
                "  geoType {\n" +
                "    id\n" +
                "  }\n" +
                "  territory {\n" +
                "    ...TerritoryFields\n" +
                "  }\n" +
                "  rubrics {\n" +
                "    id\n" +
                "    title\n" +
                "    parent {\n" +
                "      id\n" +
                "      title\n" +
                "    }\n" +
                "  }\n" +
                "  updatePeriod {\n" +
                "    id\n" +
                "    num\n" +
                "    title\n" +
                "  }\n" +
                "  schedules {\n" +
                "    id\n" +
                "    title\n" +
                "    price\n" +
                "    releaseDate\n" +
                "    layer {\n" +
                "      id\n" +
                "      uName\n" +
                "      title\n" +
                "      uri\n" +
                "      passport\n" +
                "    }\n" +
                "  }\n" +
                "  similarLayers {\n" +
                "    id\n" +
                "    title\n" +
                "    description\n" +
                "    price\n" +
                "    territory {\n" +
                "      ...TerritoryFields\n" +
                "    }\n" +
                "  }\n" +
                "  productFeatures {\n" +
                "    id\n" +
                "    title\n" +
                "    description\n" +
                "  }\n" +
                "  recommendedRecipes {\n" +
                "    id\n" +
                "    description\n" +
                "    annotation\n" +
                "    title\n" +
                "    layers {\n" +
                "      ...LayersFields\n" +
                "    }\n" +
                "  }\n" +
                "  photos\n" +
                "  uNameKey\n" +
                "  __typename\n" +
                "}\n" +
                "\n" +
                "fragment TerritoryFields on GPTerritories {\n" +
                "  id\n" +
                "  name\n" +
                "  geometryWKT\n" +
                "  fcode\n" +
                "}\n" +
                "\n" +
                "fragment LayersFields on GPLayersList {\n" +
                "  id\n" +
                "  cid\n" +
                "  bg {\n" +
                "    on\n" +
                "    o\n" +
                "    g\n" +
                "    h\n" +
                "    id\n" +
                "    tmpLayerTypename\n" +
                "    n\n" +
                "    layer {\n" +
                "      ... on GPLayerPOI {\n" +
                "        id\n" +
                "        title\n" +
                "        poiList {\n" +
                "          id\n" +
                "          geometryJSON\n" +
                "        }\n" +
                "        __typename\n" +
                "      }\n" +
                "      ... on GPLayerMap {\n" +
                "        id\n" +
                "        uName\n" +
                "        title\n" +
                "        uri\n" +
                "        layerOptions\n" +
                "        __typename\n" +
                "      }\n" +
                "    }\n" +
                "    layerEm {\n" +
                "      id\n" +
                "      M\n" +
                "      O\n" +
                "      I\n" +
                "      P {\n" +
                "        id\n" +
                "        title\n" +
                "      }\n" +
                "      L {\n" +
                "        id\n" +
                "        title\n" +
                "        min\n" +
                "        max\n" +
                "        weight\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "  ov {\n" +
                "    on\n" +
                "    o\n" +
                "    g\n" +
                "    h\n" +
                "    id\n" +
                "    tmpLayerTypename\n" +
                "    n\n" +
                "    layer {\n" +
                "      ... on GPLayerPOI {\n" +
                "        id\n" +
                "        title\n" +
                "        poiList {\n" +
                "          id\n" +
                "          geometryJSON\n" +
                "        }\n" +
                "        __typename\n" +
                "      }\n" +
                "      ... on GPLayerMap {\n" +
                "        id\n" +
                "        uName\n" +
                "        title\n" +
                "        uri\n" +
                "        layerOptions\n" +
                "        distribution {\n" +
                "          id\n" +
                "          title\n" +
                "          distribution\n" +
                "        }\n" +
                "        __typename\n" +
                "      }\n" +
                "    }\n" +
                "    layerEm {\n" +
                "      id\n" +
                "      M\n" +
                "      O\n" +
                "      I\n" +
                "      P {\n" +
                "        id\n" +
                "        title\n" +
                "      }\n" +
                "      L {\n" +
                "        id\n" +
                "        title\n" +
                "        min\n" +
                "        max\n" +
                "        weight\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}\n" +
                "\n" +
                "fragment GeoModelFields on GPGeoModel {\n" +
                "  id\n" +
                "  title\n" +
                "  description\n" +
                "  annotation\n" +
                "  passport\n" +
                "  territory {\n" +
                "    ...TerritoryFields\n" +
                "  }\n" +
                "  rubrics {\n" +
                "    id\n" +
                "    title\n" +
                "    parent {\n" +
                "      id\n" +
                "      title\n" +
                "    }\n" +
                "  }\n" +
                "  price\n" +
                "  __typename\n" +
                "}\n" +
                "\n" +
                "fragment CommercialService on GPCommercialService {\n" +
                "  id\n" +
                "  title\n" +
                "  passport\n" +
                "  description\n" +
                "  annotation\n" +
                "  territory {\n" +
                "    ...TerritoryFields\n" +
                "  }\n" +
                "  rubrics {\n" +
                "    id\n" +
                "    title\n" +
                "    parent {\n" +
                "      id\n" +
                "      title\n" +
                "    }\n" +
                "  }\n" +
                "  price\n" +
                "  __typename\n" +
                "}\n" +
                "\n" +
                "fragment GpStatDatasetFields on GPStatDataset {\n" +
                "  id\n" +
                "  title\n" +
                "  annotation\n" +
                "  price\n" +
                "  supplier\n" +
                "  description\n" +
                "  photos\n" +
                "  productFeatures {\n" +
                "    id\n" +
                "    title\n" +
                "    description\n" +
                "  }\n" +
                "  territory {\n" +
                "    ...TerritoryFields\n" +
                "  }\n" +
                "  passport\n" +
                "  updatePeriod {\n" +
                "    id\n" +
                "    num\n" +
                "    title\n" +
                "  }\n" +
                "  schedules {\n" +
                "    id\n" +
                "    title\n" +
                "    price\n" +
                "    dataset {\n" +
                "      id\n" +
                "      uName\n" +
                "      title\n" +
                "      passport\n" +
                "    }\n" +
                "  }\n" +
                "}\n" +
                "\n" +
                "fragment RecipeStepsFields on GPRecipeStep {\n" +
                "  id\n" +
                "  num\n" +
                "  title\n" +
                "  description\n" +
                "  gallery\n" +
                "  subtitle\n" +
                "  cmscreationdate\n" +
                "}\n" +
                "\n" +
                "fragment BiRecipeFields on GPBIRecipe {\n" +
                "  id\n" +
                "  title\n" +
                "  steps {\n" +
                "    ...RecipeStepsFields\n" +
                "  }\n" +
                "  img\n" +
                "  duration\n" +
                "  description\n" +
                "  recipeValues {\n" +
                "    id\n" +
                "    num\n" +
                "    wording\n" +
                "    title\n" +
                "    wording\n" +
                "  }\n" +
                "  usefulDatasets {\n" +
                "    id\n" +
                "    territory {\n" +
                "      ...TerritoryFields\n" +
                "    }\n" +
                "    title\n" +
                "    description\n" +
                "    price\n" +
                "  }\n" +
                "  annotation\n" +
                "  datasetsList {\n" +
                "    id\n" +
                "    dslist {\n" +
                "      name\n" +
                "      ds {\n" +
                "        ... on GPDSScheduleDB {\n" +
                "          id\n" +
                "          uName\n" +
                "          title\n" +
                "          passport\n" +
                "          __typename\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "  biTemplate\n" +
                "}";
    }

    @Override
    public UniqueEntity getVariables() {
        return variables;
    }

    @Override
    public void setVariables(UniqueEntity variables) {
        this.variables = variables;
    }
}
