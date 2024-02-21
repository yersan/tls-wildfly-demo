package org.wildfly.demo;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/greetings")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class ServerResource {

    @Inject
    private HttpServletRequest httpRequest;

    @GET
    public Response getServerGreeting() {
        Greeting greeting = new Greeting("Hello from WildFly Backend Microservice!", getHostName(), httpRequest.getLocalPort(), httpRequest.getLocalAddr());

        return Response
                .ok(greeting)
                .build();
    }

    private String getHostName() {
        return System.getenv("KUBERNETES_NAMESPACE") != null ? System.getenv("HOSTNAME") : httpRequest.getLocalName();
    }
}
