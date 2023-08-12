package id.platforma.geo.application.entity.graphql.response;

import id.platforma.geo.application.entity.graphql.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse extends BaseResponse {
    private String token;
    private String refreshToken;
}
