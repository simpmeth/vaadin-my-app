package id.platforma.geo.application.entity.graphql.request;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class AccessRequest {
    private String username;
    private String password;
    private Boolean rememberMe;
}
