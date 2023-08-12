package id.platforma.geo.application.entity.graphql.base;

public interface VariablesGenericRequest<T> extends BaseRequest {
    T getVariables();

    void setVariables(T variables);
}
