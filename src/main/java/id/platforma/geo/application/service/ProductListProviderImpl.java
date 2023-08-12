package id.platforma.geo.application.service;

import com.vaadin.flow.data.provider.Query;
import id.platforma.geo.application.entity.graphql.base.ProductCard;
import id.platforma.geo.application.entity.graphql.wrapper.GPProduct;
import id.platforma.geo.application.provider.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductListProviderImpl implements ProductListProvider {

    @Override
    public List<ProductCard> fetchProduct(Filter filter, Query<ProductCard, Void> query) {

        return GPProduct
                .LIST
                .PAGING(filter, query.getLimit(), query.getOffset())
                .getData()
                .getGpProductList()
                .getList()
                .getData();
    }
}
