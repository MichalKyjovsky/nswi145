package kyjovsm.mff.cuni.cz.lawoffice;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import kyjovsm.mff.cuni.cz.commons.Client;
import kyjovsm.mff.cuni.cz.commons.LegalCase;

import java.util.HashMap;

/**
 * REST-API endpoints for Litigation Financing domain.
 *
 * @author michalkyjovsky
 */
@Path("/litigation")
public class LawOfficeClientsResource {

    /**
     * Map of the {@link LegalCase Legal Cases} and their {@link Client clients}.
     */
    private static HashMap<Integer, LegalCase> legalCases = new HashMap<>();

    @Context
    UriInfo uriInfo;

    /**
     * Gets the {@link Client} client of the particular {@link LegalCase} legal case and ID given by the path
     * attributes.
     *
     * @param caseID ID of the legal case the client is associated with.
     * @param clientID ID of the queried client.
     * @return The {@link Client client} if found by the given key else {@code null}.
     */
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/cases/{caseID}/clients/{clientID}")
    public Client getClientOfCase(@PathParam("caseID") Integer caseID, @PathParam("clientID") Integer clientID) {
        return legalCases.get(caseID)
                .getClientById(clientID);
    }

    /**
     * Gets all the client of the given {@link LegalCase} legal case.
     *
     * @param caseID ID of the legal case the client is associated with.
     *
     * @return The {@link Client client} if found by the given key else {@code null}.
     */
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/cases/{caseID}/clients")
    public Response getAllClientsPerCase(@PathParam("caseID") Integer caseID) {
        return Response.created(uriInfo.getAbsolutePath())
                .status(Response.Status.OK)
                .entity(legalCases.get(caseID)
                        .getClients())
                .build();
    }

    /**
     * Deletes the {@link Client client} by its ID.
     *
     * @param caseID ID of the legal case the client is associated with.
     * @param clientID ID of the queried client.
     *
     * @return Either {@link Response.Status OK} if client was deleted successfully or internal server error is thrown.
     */
    @DELETE
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/cases/{caseID}/clients/{clientID}")
    public Response deleteClientById(@PathParam("caseID") Integer caseID, @PathParam("clientID") Integer clientID) {

        legalCases.get(caseID)
                .getClients()
                .removeIf(c -> c.getID() == clientID);

        return Response.created(uriInfo.getAbsolutePath())
                .status(Response.Status.OK)
                .build();
    }

    /**
     * Deletes the {@link LegalCase Legal Case} by the given ID.
     *
     * @param caseID ID of the legal case the client is associated with.
     *
     * @return Either {@link Response.Status OK} if legal case was deleted successfully or internal server error is
     * thrown.
     */
    @DELETE
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/cases/{caseID}")
    public Response deleteLegalCaseById(@PathParam("caseID") Integer caseID) {

        legalCases.remove(caseID);

        return Response.created(uriInfo.getAbsolutePath())
                .status(Response.Status.OK)
                .build();
    }

    /**
     * Creates a new {@link Client client}.
     *
     * @param caseID ID of the legal case the client is associated with.
     * @param client A {@link Client client} instance with name and email attributes.
     *
     * @return A newly created {@link Client client} scheme.
     */
    @POST
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/cases/{caseID}/clients")
    public Response createClient(@PathParam("caseID") Integer caseID, Client client) {

        Response res;

        if (legalCases.get(caseID)
                .getClients()
                .stream()
                .anyMatch(cl -> cl.equals(client))) {
            res = Response.created(uriInfo.getAbsolutePath())
                    .status(Response.Status.CONFLICT)
                    .entity("Given Contact ID is present.")
                    .build();
        } else {
            legalCases.get(caseID)
                    .addClient(client);
            res = Response.created(uriInfo.getAbsolutePath())
                    .status(Response.Status.CREATED)
                    .entity(client)
                    .build();
        }
        return res;
    }

    /**
     * Creates a new {@link LegalCase Legal Case}.
     *
     * @param legalCaseValue A {@link LegalCase Legal Case} instance to be created.
     *
     * @return A newly created {@link LegalCase Legal Case} scheme.
     */
    @POST
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/cases")
    public Response createLegalCase(LegalCase legalCaseValue) {

        Response res;

        if (legalCases.values()
                .stream()
                .anyMatch(lc -> lc.equals(legalCaseValue))) {
            res = Response.created(uriInfo.getAbsolutePath())
                    .status(Response.Status.CONFLICT)
                    .entity("Given Legal Case already exists.")
                    .build();
        } else {
            legalCaseValue.setID(legalCases.keySet()
                    .size());
            legalCases.put(legalCaseValue.getID(), legalCaseValue);
            res = Response.created(uriInfo.getAbsolutePath())
                    .status(Response.Status.CREATED)
                    .entity(legalCaseValue)
                    .build();
        }
        return res;
    }

    /**
     * Updates the data of the particular Client denoted by its Legal Case ID and Client's ID
     *
     * @param caseID ID of the legal case the client is associated with.
     * @param clientID ID of the queried client.
     * @param client {@link Client Client} scheme to be providing updated data
     *
     * @return An updated {@link Client client} scheme.
     */
    @PUT
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/cases/{caseID}/clients/{clientID}")
    public Response putClient(@PathParam("caseID") Integer caseID, @PathParam("clientID") Integer clientID,
            Client client) {

        Response res;

        if (!legalCases.containsKey(caseID) || (legalCases.containsKey(caseID) && legalCases.get(caseID)
                .getClientById(clientID) != null)) {
            res = Response.created(uriInfo.getAbsolutePath())
                    .status(Response.Status.CONFLICT)
                    .entity("Given Contact ID is not present.")
                    .build();
        } else {
            legalCases.get(caseID)
                    .updateClient(clientID, client);
            res = Response.created(uriInfo.getAbsolutePath())
                    .status(Response.Status.NO_CONTENT)
                    .entity(client)
                    .build();
        }

        return res;
    }

}
