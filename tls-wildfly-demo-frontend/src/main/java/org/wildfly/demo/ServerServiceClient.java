package org.wildfly.demo;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/greetings")
@RegisterRestClient(configKey = "backend-service")
public interface ServerServiceClient {
    @GET
    Response getServerGreeting();
}
