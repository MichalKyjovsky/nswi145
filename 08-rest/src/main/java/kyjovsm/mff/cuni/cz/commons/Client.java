package kyjovsm.mff.cuni.cz.commons;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@XmlRootElement(name = "client")

@XmlAccessorType(XmlAccessType.FIELD)
public class Client implements Serializable {

    @XmlElement(name = "id")
    protected int ID;

    @XmlElement(name = "name")
    protected String fullName;

    @XmlElement(name = "email")
    protected String email;

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


    public int getID() {
        return this.ID;
    }

    public String getFullName() {
        return this.fullName;
    }

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

        if (!(obj instanceof Client that)) {
            return false;
        }

        return this.email.equals(that.email) && this.fullName.equals(that.fullName);
    }
}
