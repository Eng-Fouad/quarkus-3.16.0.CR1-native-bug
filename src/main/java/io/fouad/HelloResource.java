package io.fouad;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/")
public class HelloResource {

    @Path("hello")
    @GET
    public String hello() {
        return "Hello World!";
    }
}