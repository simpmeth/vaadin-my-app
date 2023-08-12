package id.platforma.geo.application.service;

import id.platforma.geo.application.component.BackendAuthenticationContext;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CmsBackendServiceProvider {
    @Autowired
    public static CmsBackendServiceProvider INSTANCE;
    @Autowired
    @Getter
    private final BackendService backendService;
    @Autowired
    @Getter
    private final BackendAuthenticationContext authContext;

    @PostConstruct
    public void postConstruct() {
        this.INSTANCE = new CmsBackendServiceProvider(this.backendService, this.authContext);
    }
}
