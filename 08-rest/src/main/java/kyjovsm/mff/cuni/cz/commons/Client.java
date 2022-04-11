package kyjovsm.mff.cuni.cz.commons;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@XmlRootElement(name = "client")
public class Client implements Serializable {

    private int ID;

    private String fullName;

    private String email;

    /**
     * Default constructor required JAXB
     */
    public Client() {
        // NOP
    }

    public Client(int ID, String fullName, String email) {
        this.ID = ID;
        this.email = email;
        this.fullName = fullName;
    }

    @XmlElement(name = "id")
    public int getID() {
        return this.ID;
    }

    @XmlElement(name = "name")
    public String getFullName() {
        return this.fullName;
    }

    @XmlElement(name = "email")
    public String getEmail() {
        return this.email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Client)) {
            return false;
        }


        Client that = (Client) obj;
        return this.email.equals(that.email) && this.fullName.equals(that.fullName);
    }
}
