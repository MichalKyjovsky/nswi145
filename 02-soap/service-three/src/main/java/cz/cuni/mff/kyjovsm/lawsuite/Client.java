package cz.cuni.mff.kyjovsm.lawsuite;

import java.io.Serializable;

/**
 * Class representing the client that is being used for web service of the court registration
 */
public class Client implements Serializable {

    public enum ClientState {
        NEW_REGISTRATION,
        IN_PROGRESS,
        PROCESSED;

    }

    private String name;

    private int age;

    private int id;

    public ClientState getClientState() {
        return clientState;
    }

    public void setClientState(ClientState clientState) {
        this.clientState = clientState;
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

    public int getId() {

        return id;

    }

    public void setId(int id) {

        this.id = id;

    }

    @Override

    public String toString() {

        return id + "::" + name + "::" + age + "::" + clientState.name();

    }
}
