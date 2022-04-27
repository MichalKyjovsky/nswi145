package kyjovsm.mff.cuni.cz.commons;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@XmlRootElement(name = "legalcase")
@XmlAccessorType(XmlAccessType.FIELD)
public class LegalCase implements Serializable {

    @XmlElement(name = "id")
    protected int ID;

    @XmlElement(name = "name")
    protected String legalCaseName;

    @XmlElement(name = "clients")
    protected HashMap<Integer, Client> clients;

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
    public LegalCase(int ID, String legalCaseName) {
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

    public List<Client> getClients() {
        return clients.values().stream().toList();
    }

    public int getID() {
        return ID;
    }

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
