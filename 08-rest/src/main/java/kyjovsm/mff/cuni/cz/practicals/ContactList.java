package kyjovsm.mff.cuni.cz.practicals;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.xml.bind.JAXBElement;
import kyjovsm.mff.cuni.cz.commons.Client;

import java.util.HashMap;
import java.util.Map;

@Path("/contact")
public class ContactList {

    @Context
    UriInfo uriInfo;

    private static Map<Integer, Client> contactList = new HashMap<>();

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("{contact}")
    public Client getContact(@PathParam("contact") Integer contactID) {
        return contactList.get(contactID);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("{contact}")
    public Response putContact(@PathParam("contact") Integer contactID, JAXBElement<Client> contact) {
        Client c = contact.getValue();

        Response res;

        if (!contactList.containsKey(contactID)) {
            res = Response.status(Response.Status.CONFLICT)
                    .entity("Given Contact ID is not present.")
                    .build();
        } else {
            contactList.replace(contactID, c);
            res = Response.status(Response.Status.NO_CONTENT)
                    .entity("Contact was updated.")
                    .build();
        }

        return Response.created(uriInfo.getAbsolutePath())
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response postContact(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName, @FormParam("email") String email) {

        int contactID = contactList.keySet()
                .size();

        Response res;

        if (contactList.containsKey(contactID)) {
            res = Response.status(Response.Status.CONFLICT)
                    .entity("Given Contact ID is present.")
                    .build();
        } else {
            contactList.put(contactID, new Client(contactID, firstName, lastName, email));
            res = Response.status(Response.Status.CREATED)
                    .entity("Contact was created.")
                    .build();
        }

        return Response.created(uriInfo.getAbsolutePath())
                .build();

    }
}
