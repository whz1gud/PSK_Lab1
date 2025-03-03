package com.example.app.psk_lab1.rest;

import com.example.app.psk_lab1.dao.jpa.PresentationDao;
import com.example.app.psk_lab1.entity.Presentation;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/presentations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PresentationResource {

    @Inject
    private PresentationDao presentationDao;

    // GET /presentations
    @GET
    public List<Presentation> getAllPresentations() {
        return presentationDao.findAll();
    }

    // GET /presentations/{id}
    @GET
    @Path("/{id}")
    public Presentation getPresentationById(@PathParam("id") Long id) {
        return presentationDao.findById(id);
    }

    // POST /presentations
    @POST
    public Response createPresentation(Presentation presentation) {
        presentationDao.create(presentation);
        return Response.status(Response.Status.CREATED).entity(presentation).build();
    }

    // PUT /presentations/{id}
    @PUT
    @Path("/{id}")
    public Response updatePresentation(@PathParam("id") Long id, Presentation updated) {
        Presentation existing = presentationDao.findById(id);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());

        presentationDao.update(existing);
        return Response.ok(existing).build();
    }

    // DELETE /presentations/{id}
    @DELETE
    @Path("/{id}")
    public Response deletePresentation(@PathParam("id") Long id) {
        Presentation existing = presentationDao.findById(id);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        presentationDao.delete(existing.getId());
        return Response.noContent().build();
    }
}