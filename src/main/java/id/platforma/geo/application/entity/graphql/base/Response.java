package id.platforma.geo.application.entity.graphql.base;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class Response<T> extends BaseResponse {
    private T data;
    private boolean dataPresent;
    private Map<String, String> extensions;
}
