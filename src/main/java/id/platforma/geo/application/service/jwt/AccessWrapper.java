package id.platforma.geo.application.service.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AccessWrapper {

    @JsonProperty("roles")
    private List<String> roles;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
