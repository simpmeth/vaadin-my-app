package id.platforma.geo.application.entity.graphql.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dataset {
    public String id;
    @JsonProperty("uName")
    public String uName;
    public String title;
    /**
     * for get
     */
    public String passport;
}
