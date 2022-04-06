package cz.cuni.mff.kyjovsm.lawsuite;

import javax.jws.WebService;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Random;

@WebService(endpointInterface = "cz.cuni.mff.kyjovsm.lawsuite.LawSuite")
public class LawSuiteImpl implements LawSuite {

    private static final Random rand = new Random();

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void processClient(Integer clientID) {
        Objects.requireNonNull(clientID, "Client's ID is required");

        LOGGER.info("Processing the client: {}", clientID);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            LOGGER.error("Failed to process the client: {}", clientID);
        }
        LOGGER.info("Processing the client: {} was successful", clientID);
    }


    @Override
    public boolean registerNewClient(String name) {
        try {
            saveClient(name);
            return true;
        } catch (InterruptedException e) {
            LOGGER.error(String.format("Failed to register client: %s", name));
        }
        return false;
    }

    @Override
    public boolean reviewClientsData(Report report) {
        switch (report.getStatus().randomReport()) {
        case TODO:
        case DATA_MISSING:
            return false;
        case QUALIFIED:
            return true;
        }
        return false;
    }

    @Override
    public boolean archiveClient(Integer clientID) {
        // Using same semantics as the referring implementation
        return true;
    }

    @Override
    public boolean rollbackToClient(Report report) {
        LOGGER.info("SENDING REPORT TO THE CLIENT: {}.", report.getClient());
        LOGGER.info("DISCREPANCIES WERE OBSERVED");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            LOGGER.error("ERROR DURING REPORTING TO CLIENT: {}", report.getClient());
            return false;
        }
        return true;
    }

    @Override
    public Report createReport(String clientID) {
        return new Report(Client.getClientByID(clientID));
    }

    private Client saveClient(String name) throws InterruptedException {
        Client c = new Client();
        c.setId(RandomStringUtils.random(20, true, false));
        c.setName(name);
        c.setAge(rand.nextInt(99));
        LOGGER.info("Saving the client: {}", c);
        LOGGER.info("Processing the client: {}", c);
        Thread.sleep(2000);
        return c;
    }
}
