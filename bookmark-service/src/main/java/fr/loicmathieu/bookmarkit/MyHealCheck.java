package fr.loicmathieu.bookmarkit;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;
import org.jboss.resteasy.annotations.LinkHeaderParam;

import java.lang.annotation.Annotation;

@Readiness
public class MyHealCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.builder().name("custom").withData("key", "value").up().build();
    }
}
