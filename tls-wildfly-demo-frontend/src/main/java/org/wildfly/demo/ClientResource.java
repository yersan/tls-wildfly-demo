package org.wildfly.demo;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.rest.client.inject.RestClient;


@Path("/greetings")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class ClientResource {
    @Inject
    @RestClient
    private ServerServiceClient serverServiceClient;

    @Inject
    private HttpServletRequest httpRequest;

    @GET
    public Response getClientGreeting() {
        Greeting greeting = new Greeting("Hello From WildFly Frontend Microservice!", getHostName(), httpRequest.getLocalPort(), httpRequest.getLocalAddr());
        return Response.ok(greeting)
                .build();
    }

    @GET()
    @Path("/backend")
    public Response getServerGreeting() {
        return serverServiceClient.getServerGreeting();
    }

    private String getHostName() {
        return System.getenv("KUBERNETES_NAMESPACE") != null ? System.getenv("HOSTNAME") : httpRequest.getLocalName();
    }
}
