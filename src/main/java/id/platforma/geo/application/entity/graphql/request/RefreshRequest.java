package id.platforma.geo.application.entity.graphql.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class RefreshRequest {
    private String refreshToken;
}
