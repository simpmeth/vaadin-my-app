package id.platforma.geo.application.provider;

import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;
import id.platforma.geo.application.entity.graphql.base.VariablesGenericRequest;
import id.platforma.geo.application.entity.graphql.response.GPProductListResponse;
import id.platforma.geo.application.service.BackendService;

import java.util.stream.Stream;

public class CmsProvider<T extends VariablesGenericRequest, E, F> extends AbstractBackEndDataProvider<E, F> {
    private static BackendService backendService;
    private Class<T> queryClass;
    private Class<E> entityClass;
    private Class<F> filterClass;

    public CmsProvider(BackendService backendService,
                       Class<T> queryClass,
                       Class<E> entityClass,
                       Class<F> filterClass) {
        this.backendService = backendService;
        this.queryClass = queryClass;
        this.entityClass = entityClass;
        this.filterClass = filterClass;
    }

    @Override
    protected Stream<E> fetchFromBackEnd(Query<E, F> query) {
        try {
            T request = queryClass.getDeclaredConstructor().newInstance(query.getOffset(), query.getOffset());
            Object params = new Object();
            request.setVariables(params);

            // Response<E> response = backendService.get(request, new HttpHeaders(), request.getResponseClass());

            //E data = response.getData();
            GPProductListResponse GPProductListResponse = new GPProductListResponse();

            return Stream.<E>builder().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }  //return PersonService.fetchPersons(query.getOffset(), query.getLimit(), query.getFilter(), query.getSortOrders()).stream();
    }

    @Override
    protected int sizeInBackEnd(Query<E, F> query) {
        return 10;
    }
}