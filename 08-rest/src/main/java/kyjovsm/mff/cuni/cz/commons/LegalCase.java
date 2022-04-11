package kyjovsm.mff.cuni.cz.commons;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@XmlRootElement(name = "legalcase")
public class LegalCase implements Serializable {

    private int ID;

    private String legalCaseName;

    private HashMap<Integer, Client> clients;

    /**
     * Default constructor for JAXB
     */
    public LegalCase() {
        // NOP
    }

    /**
     * Constructor method.
     *
     * @param ID ID of the legal case.
     * @param legalCaseName Name of the legal case.
     */
    public LegalCase(int ID,  String legalCaseName) {
        this.ID = ID;
        this.legalCaseName = legalCaseName;
        this.clients = new HashMap<>();

    }


    public Client getClientById(Integer clientID) {
        return this.clients.get(clientID);
    }


    public void updateClient(Integer clientID, Client updatedClient) {
        this.clients.replace(clientID, updatedClient);
    }

    public void addClient(Client newClient) {
        this.clients.put(this.clients.keySet().size(), newClient);
    }

    @XmlElement(name = "clients")
    public List<Client> getClients() {
        return clients.values().stream().toList();
    }

    @XmlElement(name = "id")
    public int getID() {
        return ID;
    }

    @XmlElement(name = "name")
    public String getLegalCaseName() {
        return legalCaseName;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setLegalCaseName(String legalCaseName) {
        this.legalCaseName = legalCaseName;
    }

    public void setClients(HashMap<Integer, Client> clients) {
        this.clients = clients;
    }
}
