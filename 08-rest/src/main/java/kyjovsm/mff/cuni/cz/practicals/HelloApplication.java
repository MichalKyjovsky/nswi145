package kyjovsm.mff.cuni.cz.practicals;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.glassfish.jersey.CommonProperties;

import java.util.HashMap;
import java.util.Map;

@ApplicationPath("/api")
public class HelloApplication extends Application {

    public HelloApplication() {


//        packages("kyjovsm.mff.cuni.cz.practicals");
    }



    @Override
    public Map<String, Object> getProperties() {

        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("jersey.config.server.provider.packages", "kyjovsm.mff.cuni.cz.practicals");
        properties.put("jersey.config.server.wadl.disableWadl", "false");
        properties.put("jersey.config.server.provider.classnames","org.glassfish.jersey.media.multipart.MultiPartFeature");
        properties.put(CommonProperties.OUTBOUND_CONTENT_LENGTH_BUFFER,"0");
        System.out.println("getProperties:-> CommonProperties.OUTBOUND_CONTENT_LENGTH_BUFFER_SERVER :" + CommonProperties.getValue(properties,CommonProperties.OUTBOUND_CONTENT_LENGTH_BUFFER,String.class));
        return properties;
    }
}