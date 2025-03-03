package com.example.app.psk_lab1.rest;

import com.example.app.psk_lab1.dao.jpa.ConferenceDao;
import com.example.app.psk_lab1.dao.jpa.PresentationDao;
import com.example.app.psk_lab1.entity.Conference;
import com.example.app.psk_lab1.entity.Presentation;
import com.example.app.psk_lab1.entity.dtos.request.CreatePresentationDTO;
import com.example.app.psk_lab1.entity.dtos.response.PresentationResponseDTO;
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

    @Inject
    private ConferenceDao conferenceDao;

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
    public Response createPresentation(CreatePresentationDTO dto) {
        if (dto.conferenceId == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Conference ID must be provided.")
                    .build();
        }

        Conference existingConference = conferenceDao.findById(dto.conferenceId);
        if (existingConference == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Conference not found.")
                    .build();
        }

        Presentation presentation = new Presentation();
        presentation.setTitle(dto.title);
        presentation.setDescription(dto.description);
        presentation.setConference(existingConference);

        presentationDao.create(presentation);

        return Response.status(Response.Status.CREATED)
                .entity(new PresentationResponseDTO(presentation))
                .build();
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