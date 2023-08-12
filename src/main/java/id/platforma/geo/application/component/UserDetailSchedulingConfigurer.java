package id.platforma.geo.application.component;

import id.platforma.geo.application.config.EndpointConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
@Slf4j
@EnableAsync
@RequiredArgsConstructor
public class UserDetailSchedulingConfigurer implements SchedulingConfigurer {
    private final EndpointConfig endpointConfig;
    private Map<Integer, BackendUserDetails> userDetailsMap = new HashMap<>();

    @Async
    private void checkTocken() {
        if (this.userDetailsMap.size() > 0) {
            for (Map.Entry<Integer, BackendUserDetails> userDetailsEntry : userDetailsMap.entrySet()) {
                BackendUserDetails userDetails = userDetailsEntry.getValue();
                if (!userDetails.isSessionValid()) {
                    userDetailsMap.remove(userDetailsEntry.getKey());
                    continue;
                }
                if (new Date().getTime() - endpointConfig.getTokenRefreshTime() > userDetails.getTokenRefreshTime()) {
                    userDetails.refreshToken();
                    log.debug(String.format("update token for user %s",
                            userDetails.getUsername() + "_" + userDetails.hashCode()));
                    userDetails.setTokenRefreshTime(new Date().getTime());
                }
            }
        }
    }

    @Bean
    public Executor taskExecutor() {
        return Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());

        taskRegistrar.addTriggerTask(() -> checkTocken(), context -> {
            Optional<Date> lastCompletionTime = Optional.ofNullable(context.lastCompletionTime());
            Instant nextExecutionTime = lastCompletionTime.orElseGet(Date::new).toInstant().plusMillis(1000);
            return Date.from(nextExecutionTime).toInstant();
        });


    }


    public void addUserDetails(BackendUserDetails userDetails) {
        this.userDetailsMap.put(userDetails.hashCode(), userDetails);
    }

    public void removeUserDetails(BackendUserDetails userDetails) {
        if (this.userDetailsMap.containsKey(userDetails.hashCode()))
            this.userDetailsMap.remove(userDetails.hashCode());
    }

}