package siem.kwetter.kweet;

import lombok.AllArgsConstructor;

import javax.inject.Inject;
import javax.naming.directory.InvalidAttributesException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.net.URI;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Path("/api/v1/kweet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class KweetController {
    @Inject
    KweetService kweetService;

    @GET
    public Response getKweets(
//            @QueryParam("message") @DefaultValue("") String message
    ) {
//        try {
//            return Response.ok(kweetService.getKweets(message)).build();
//        } catch (Exception e) {
//            return Response.status(Status.BAD_REQUEST).build();
//        }
        List<Kweet> kweets = Kweet.listAll();
        return Response.ok(kweets).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") long id) {
//        try {
//            final var kweetByIdOpt = kweetService.findKweetById(id);
//            if (kweetByIdOpt.isEmpty()) {
//                return Response.status(Status.NOT_FOUND).build();
//            }
//
//            return Response.ok(kweetByIdOpt.get()).build();
//        } catch (Exception e) {
//            return Response.status(Status.BAD_REQUEST).build();
//        }
        return Kweet.findByIdOptional(id)
                .map(kweet -> Response.ok(kweet).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Transactional
    public Response create(Kweet kweet) {
        Kweet.persist(kweet);
        if (kweet.isPersistent()){
            return Response.created(URI.create("/kweet" + kweet.id)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();

//        try {
//            kweetService.create(kweet);
//            return Response.noContent().build();
//        } catch (Exception e) {
//            if (e instanceof InvalidAttributesException) {
//                return Response.status(Status.CONFLICT).entity(Map.of("message", e.getMessage())).build();
//            }
//
//            return Response.status(Status.BAD_REQUEST).build();
//        }
    }

    @PUT
    @Path("/{id}")
    public Response replace(@PathParam("id") long id, Kweet kweet) {
        try {
            return Response.ok(kweetService.replace(id, kweet)).build();
        } catch (Exception e) {
            if (e instanceof InvalidParameterException) {
                return Response.status(Status.NOT_FOUND).entity(Map.of("message", e.getMessage())).build();
            }

            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response update(@PathParam("id") long id) {
        boolean deleted = Kweet.deleteById(id);
        if(deleted){
            return Response.noContent().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
//        var isDeleted = kweetService.delete(id);
//        if (!isDeleted) {
//            return Response.notModified().build();
//        }
//        return Response.noContent().build();
    }
}
