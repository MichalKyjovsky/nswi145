package cz.cuni.mff.kyjovsm.court;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

/**
 * The interface representing the Court services.
 */
@WebService
public interface Court {

    /**
     * The following method stores the data from the request to the database via JPA as a {@link Client} entity.
     */
    @WebMethod
    Client registerClient(Integer clientID, String name, Integer age);

}
