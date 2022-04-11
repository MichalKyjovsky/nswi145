package kyjovsm.mff.cuni.cz.lawoffice;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.xml.bind.JAXBElement;
import kyjovsm.mff.cuni.cz.commons.Client;
import kyjovsm.mff.cuni.cz.commons.LegalCase;

import java.util.HashMap;

@Path("/litigation")
public class LawOfficeClientsResource {

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

    @DELETE
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/cases/{caseID}")
    public Response deleteLegalCaseById(@PathParam("caseID") Integer caseID) {

        legalCases.remove(caseID);

        return Response.created(uriInfo.getAbsolutePath())
                .status(Response.Status.OK)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/cases/{caseID}/clients")
    public Response postClient(@PathParam("caseID") Integer caseID, JAXBElement<Client> client) {

        Client c = client.getValue();

        Response res;

        if (legalCases.get(caseID)
                .getClients()
                .stream()
                .anyMatch(cl -> cl.equals(c))) {
            res = Response.created(uriInfo.getAbsolutePath())
                    .status(Response.Status.CONFLICT)
                    .entity("Given Contact ID is present.")
                    .build();
        } else {
            legalCases.get(caseID)
                    .addClient(c);
            res = Response.created(uriInfo.getAbsolutePath())
                    .status(Response.Status.CREATED)
                    .entity("Contact was created.")
                    .build();
        }
        return res;
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/cases")
    public Response createLegalCase(JAXBElement<LegalCase> legalCase) {

        LegalCase legalCaseValue = legalCase.getValue();

        Response res;

        if (legalCases.values().stream().anyMatch(lc -> lc.equals(legalCaseValue))) {
            res = Response.created(uriInfo.getAbsolutePath())
                    .status(Response.Status.CONFLICT)
                    .entity("Given Legal Case already exists.")
                    .build();
        } else {
            legalCaseValue.setID(legalCases.keySet().size());
            legalCases.put(legalCaseValue.getID(),  legalCaseValue);
            res = Response.created(uriInfo.getAbsolutePath())
                    .status(Response.Status.CREATED)
                    .entity("Legal Case was created.")
                    .build();
        }
        return res;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/cases/{caseID}/clients/{clientID}")
    public Response putClient(@PathParam("caseID") Integer caseID, @PathParam("clientID") Integer clientID,
            JAXBElement<Client> client) {

        Client c = client.getValue();

        Response res;

        if (!legalCases.containsKey(caseID) || (legalCases.containsKey(caseID) && legalCases.get(caseID)
                .getClientById(clientID) != null)) {
            res = Response.created(uriInfo.getAbsolutePath())
                    .status(Response.Status.CONFLICT)
                    .entity("Given Contact ID is not present.")
                    .build();
        } else {
            legalCases.get(caseID)
                    .updateClient(clientID, c);
            res = Response.created(uriInfo.getAbsolutePath())
                    .status(Response.Status.NO_CONTENT)
                    .entity("Contact was updated.")
                    .build();
        }

        return res;
    }

}
