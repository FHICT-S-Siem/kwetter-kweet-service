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
import java.util.Optional;

@AllArgsConstructor
@Path("/api/v1/kweet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class KweetController {
    @Inject
    KweetService kweetService;

    @GET
    public Response getKweets(
    ) {
        List<Kweet> kweets = Kweet.listAll();
        return Response.ok(kweets).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") long id) {
        return Kweet.findByIdOptional(id)
                .map(kweet -> Response.ok(kweet).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Transactional
    public Response create(Kweet kweet) {

        System.out.println("kweet" + kweet.toString());
        Kweet.persist(kweet);
        if (kweet.isPersistent()){
            return Response.created(URI.create("/kweet" + kweet.id)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("/{id}")
    public Response replace(@PathParam("id") long id, Kweet kweet) {
        try {
            Optional<Kweet> foundKweet = Kweet.findByIdOptional(id);
            if (foundKweet.isPresent()) {
                Kweet r = foundKweet.get();
                r.setMessage(kweet.message);
                Kweet updatedKweet = kweetService.update(r);
                return Response.ok(updatedKweet).build();
            }

            return Response.status(Status.NOT_FOUND).build();

        } catch (Exception e) {
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
    }

    @DELETE
    @Transactional
    public Response deleteAll() {
        long deleted = Kweet.deleteAll();
        System.out.println(deleted);
        if(deleted > 0){
            return Response.noContent().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
