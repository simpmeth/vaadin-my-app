package id.platforma.geo.application.service.jwt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class JwtTokenPayload {

    @JsonProperty("exp")
    private Long exp;

    @JsonProperty("iat")
    private Long iat;

    @JsonProperty("auth_time")
    private Long authTime;

    @JsonProperty("jti")
    private String jti;

    @JsonProperty("iss")
    private String iss;

    @JsonProperty("aud")
    private List<String> aud;

    @JsonProperty("sub")
    private String sub;

    @JsonProperty("typ")
    private String typ;

    @JsonProperty("azp")
    private String azp;

    @JsonProperty("nonce")
    private String nonce;

    @JsonProperty("session_state")
    private String sessionState;

    @JsonProperty("acr")
    private String acr;

    @JsonProperty("allowed-origins")
    private List<String> allowedOrigins;

    @JsonProperty("realm_access")
    private AccessWrapper realmAccess;

    @JsonProperty("resource_access")
    private Map<String, AccessWrapper> resourceAccess;

    @JsonProperty("roles")
    private List<String> roles;

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("sid")
    private String sid;

    @JsonProperty("email_verified")
    private Boolean emailVerified;

    @JsonProperty("name")
    private String name;

    @JsonProperty("groups")
    private List<String> groups;

    @JsonProperty("preferred_username")
    private String preferredUsername;

    @JsonProperty("given_name")
    private String givenName;

    @JsonProperty("family_name")
    private String familyName;

    @JsonProperty("profile-id")
    private String profileId;


    public String getPreferredRoles() {
        List<String> roles = new ArrayList<>();

        for (Map.Entry<String, AccessWrapper> entry : resourceAccess.entrySet()) {
            roles.addAll(entry.getValue().getRoles());
        }

        return String.join(",", roles);
    }
}
