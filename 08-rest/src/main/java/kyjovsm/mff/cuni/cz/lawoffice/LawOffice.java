package kyjovsm.mff.cuni.cz.lawoffice;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;


@ApplicationPath("/law-office")
public class LawOffice extends ResourceConfig {
    public LawOffice() {
        packages("cz.cuni.mff.kyjovsm");
        register(LawOfficeClientsResource.class);
    }


}
