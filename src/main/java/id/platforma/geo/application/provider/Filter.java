package id.platforma.geo.application.provider;

import lombok.Data;
import lombok.Singular;

import java.util.HashMap;
import java.util.Map;

@Data
public class Filter {
    @Singular
    private Map<String, String> searchTerm = new HashMap<>();
}