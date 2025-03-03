package com.example.app.psk_lab1.rest;

import com.example.app.psk_lab1.dao.jpa.ConferenceDao;
import com.example.app.psk_lab1.entity.Conference;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/conferences")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConferenceResource {

    @Inject
    private ConferenceDao conferenceDao;

    // GET /conferences
    @GET
    public List<Conference> getAllConferences() {
        return conferenceDao.findAll();
    }

    // POST /conferences
    @POST
    public Response createConference(Conference conference) {
        conferenceDao.create(conference);
        return Response.status(Response.Status.CREATED).entity(conference).build();
    }

    // GET /conferences/{id}
    @GET
    @Path("/{id}")
    public Conference getConference(@PathParam("id") Long id) {
        return conferenceDao.findById(id);
    }

    // PUT /conferences/{id}
    @PUT
    @Path("/{id}")
    public Response updateConference(@PathParam("id") Long id, Conference confUpdate) {
        Conference existing = conferenceDao.findById(id);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        existing.setTitle(confUpdate.getTitle());
        existing.setLocation(confUpdate.getLocation());
        existing.setStartDate(confUpdate.getStartDate());
        existing.setEndDate(confUpdate.getEndDate());

        conferenceDao.update(existing);
        return Response.ok(existing).build();
    }

    // DELETE /conferences/{id}
    @DELETE
    @Path("/{id}")
    public Response deleteConference(@PathParam("id") Long id) {
        Conference existing = conferenceDao.findById(id);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        conferenceDao.delete(existing.getId());
        return Response.noContent().build();
    }
}
