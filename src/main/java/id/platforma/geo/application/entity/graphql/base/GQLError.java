package id.platforma.geo.application.entity.graphql.base;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GQLError {
    private String cause;
    private List<Object> stackTrace;
    private String message;
    private List<Location> locations;
    private Object extensions;
    private String errorType;
    private Object path;
    private List<String> suppressed;
    private String localizedMessage;
    private String description;
    private String validationErrorType;
    private List<String> queryPath;
}
