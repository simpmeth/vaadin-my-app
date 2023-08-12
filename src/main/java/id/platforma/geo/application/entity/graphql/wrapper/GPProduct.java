package id.platforma.geo.application.entity.graphql.wrapper;

import id.platforma.geo.application.component.BackendUserDetails;
import id.platforma.geo.application.entity.graphql.base.ProductCard;
import id.platforma.geo.application.entity.graphql.request.GPProductGet;
import id.platforma.geo.application.entity.graphql.request.GPProductList;
import id.platforma.geo.application.entity.graphql.response.GPProductGetResponse;
import id.platforma.geo.application.entity.graphql.response.GPProductListResponse;
import id.platforma.geo.application.provider.Filter;
import id.platforma.geo.application.service.CmsBackendServiceProvider;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public class GPProduct {
    private static String getToken() {
        Optional<BackendUserDetails> authenticatedUser =
                CmsBackendServiceProvider.INSTANCE.getAuthContext()
                        .getAuthenticatedUser(BackendUserDetails.class);
        if (authenticatedUser.isEmpty()) return null;
        String accessToken = authenticatedUser.get().getAccessToken();
        return accessToken;
    }

    public static GPProductGetResponse GET(String id) {
        GPProductGet get = GPProductGet.get(id);
        return CmsBackendServiceProvider.INSTANCE.getBackendService()
                .get(getToken(), get, GPProductGetResponse.class);
    }

    public class LIST {


        public static GPProductListResponse PAGING(Filter filter, int limit, int offset) {

            GPProductList list = GPProductList.paging(filter, limit, offset);
            GPProductListResponse response =
                    CmsBackendServiceProvider.INSTANCE.getBackendService()
                            .get(getToken(), list, GPProductListResponse.class);

            List<ProductCard> data = response.getData().getGpProductList().getList().getData();
            int size = response.getData().getGpProductList().getList().getData().size();
            if (size > limit) {
                ProductCard productCard0 = data.get(0);
                for (int i = size; i <= limit - 1; i++) {
                    data.add(productCard0);
                }
            }
            return response;
        }
    }
}
