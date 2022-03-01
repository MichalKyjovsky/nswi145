package cz.cuni.mff.kyjovsm.court;


import jakarta.jws.WebService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

@WebService(endpointInterface = "cz.cuni.mff.kyjovsm.court.Court")
public class CourtImpl implements Court {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Client registerClient(Integer clientID, String name, Integer age) {
        Objects.requireNonNull(clientID, "Client's ID is required");
        Objects.requireNonNull(name, "Client's name is required");
        Objects.requireNonNull(age, "Client's age is required");

        try {
            return saveClient(clientID, name, age);
        } catch (InterruptedException e) {
            throw new IllegalStateException("Problem with client data processing encountered.");
        }
    }


    private Client saveClient(Integer clientID, String name, Integer age) throws InterruptedException {
        Client c = new Client();
        c.setId(clientID);
        c.setName(name);
        c.setAge(age);
        LOGGER.info("Saving the client: {}", c);
        LOGGER.info("Processing the client: {}", c);
        Thread.sleep(2000);
        return c;
    }
}
