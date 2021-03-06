package cz.cuni.mff.kyjovsm.lawsuite;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * The interface representing the Court services.
 */
@WebService
public interface Court {

    /**
     * The following method stores the data from the request to the database via JPA as a {@link Client} entity.
     */
    @WebMethod
    Client registerClient(String clientID, String name, Integer age);

    /**
     * @param clientID
     * @return
     */
    @WebMethod
    boolean archiveClient(String clientID);

}
