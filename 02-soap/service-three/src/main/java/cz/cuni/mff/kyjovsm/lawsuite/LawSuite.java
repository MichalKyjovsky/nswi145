package cz.cuni.mff.kyjovsm.lawsuite;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * The interface representing the LawSuite services.
 */
@WebService
public interface LawSuite {

    /**
     * The following method stores the data from the request to the database via JPA as a {@link Client} entity.
     */
    @WebMethod
    void processClient(Integer clientID);

    @WebMethod
    boolean registerNewClient(String name);

    @WebMethod
    boolean reviewClientsData(Report report);

    @WebMethod
    Report createReport(String clientID);

    @WebMethod
    boolean rollbackToClient(Report report);

    /**
     * @param clientID ID of the {@link Client} to be archived
     * @retur <code>true</code> if the archivaton was successful else <code>false</code>
     */
    @WebMethod
    boolean archiveClient(Integer clientID);

}
