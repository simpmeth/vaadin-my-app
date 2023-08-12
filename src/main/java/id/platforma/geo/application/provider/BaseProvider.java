package id.platforma.geo.application.provider;

import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;

import java.util.stream.Stream;

public class BaseProvider<E, F> extends AbstractBackEndDataProvider<E, F> {


    @Override
    protected Stream<E> fetchFromBackEnd(Query<E, F> query) {
        return null;
    }

    @Override
    protected int sizeInBackEnd(Query<E, F> query) {
        return 0;
    }
}