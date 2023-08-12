package id.platforma.geo.application.entity.graphql.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Layer {
    private String id;
    private String cid;
    @JsonProperty("uName")
    private String uName;
    private String title;
    private String uri;
    private LayerOptions layerOptions;
    private List<Bg> bg;
    private List<Ov> ov;
}
