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

    @XmlElement(name = "firstName")
    protected String firstName;

    @XmlElement(name = "lastName")
    protected String lastName;

    @XmlElement(name = "email")
    protected String email;

    /**
     * Default constructor required JAXB
     */
    public Client() {
        // NOP
    }

    public Client(int ID, String firstName, String lastName, String email) {
        this.ID = ID;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getID() {
        return this.ID;
    }


    public String getEmail() {
        return this.email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Client that)) {
            return false;
        }

        return this.email.equals(that.email) && this.firstName.equals(that.firstName) && this.lastName.equals(
                that.lastName);
    }
}
