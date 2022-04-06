package cz.cuni.mff.kyjovsm.lawsuite;


import javax.jws.WebService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

@WebService(endpointInterface = "cz.cuni.mff.kyjovsm.lawsuite.Court")
public class CourtImpl implements Court {

    private static final Logger LOGGER = LogManager.getLogger();


    @Override
    public Client registerClient(String clientID, String name, Integer age) {
        Objects.requireNonNull(clientID, "Client's ID is required");
        Objects.requireNonNull(name, "Client's name is required");
        Objects.requireNonNull(age, "Client's age is required");

        try {
            return saveClient(clientID, name, age);
        } catch (InterruptedException e) {
            throw new IllegalStateException("Problem with client data processing encountered.");
        }
    }

    @Override
    public boolean archiveClient(String clientID) {
        Objects.requireNonNull(clientID, "Client's ID is required");
        LOGGER.info(String.format("Archiving Client with ID: %s...", clientID));

        boolean status = true;

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            status = false;
        }

        if (status) {
            LOGGER.info(String.format("Client with ID: %s was successfully archived.", clientID));
            return true;
        } else {
            LOGGER.error(String.format("An error occurred during archiving of Client with ID: %s. Please contact the support service", clientID));
            return false;
        }

    }


    private Client saveClient(String clientID, String name, Integer age) throws InterruptedException {
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
