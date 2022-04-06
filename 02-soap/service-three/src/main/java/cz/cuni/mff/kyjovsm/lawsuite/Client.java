package cz.cuni.mff.kyjovsm.lawsuite;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;
import java.util.Random;

/**
 * Class representing the client that is being used for web service of the court registration
 */
public class Client implements Serializable {

    private static final Random RANDOM = new Random();

    public enum ClientState {
        NEW_REGISTRATION,
        IN_PROGRESS,
        PROCESSED;
    }

    private String name;

    private int age;

    private String id;

    public ClientState getClientState() {
        return clientState;
    }

    public void setClientState(ClientState clientState) {
        this.clientState = clientState;
    }

    public static Client getClientByID(String clientId) {

        Client client = new Client();
        client.setClientState(ClientState.PROCESSED);
        client.setAge(RANDOM.nextInt(99));
        client.setName(RandomStringUtils.random(20, true, false));
        client.setId(clientId);

        return client;
    }

    private ClientState clientState;

    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;

    }

    public int getAge() {

        return age;

    }

    public void setAge(int age) {

        this.age = age;

    }

    public String getId() {

        return id;

    }

    public void setId(String id) {

        this.id = id;

    }

    @Override

    public String toString() {

        return id + "::" + name + "::" + age + "::" + clientState.name();

    }
}
