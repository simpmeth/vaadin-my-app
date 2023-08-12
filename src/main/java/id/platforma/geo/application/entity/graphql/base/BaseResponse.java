package id.platforma.geo.application.entity.graphql.base;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BaseResponse {
    private List<GQLError> errors;
}
