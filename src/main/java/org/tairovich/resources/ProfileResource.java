package org.tairovich.resources;

import org.tairovich.model.Message;
import org.tairovich.model.Profile;
import org.tairovich.service.ProfileService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {

    private ProfileService service = new ProfileService();

    @GET
    public List<Profile> getProfiles() {
        return service.getProfiles();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{name}")
    public Profile getProfile( @PathParam("id") String name){
        return service.getProfile(name);
    }

    @POST
    public Profile addProfile(Profile profile){
        return service.addProfile(profile);
    }


    @PUT
    @Path("/{name}")
    public Profile updateProfile(@PathParam("name") String name, Profile profile){
        profile.setProfileName(name);
        return service.updateProfile(profile);
    }

    @DELETE
    @Path("/{id}")
    public void deleteProfile(@PathParam("profile") String profile){
        service.removeProfile(profile);
    }

}
