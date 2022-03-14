package cz.cuni.mff.kyjovsm;

import java.net.URL;
import javax.xml.namespace.QName;

import jakarta.servlet.annotation.WebServlet;
import jakarta.xml.soap.*;
import jakarta.servlet.http.HttpServlet;

import jakarta.xml.ws.Service;
import cz.cuni.mff.kyjovsm.court.Court;

public class CourtClient {

    public static void main(String[] args) throws Exception {
        SOAPConnectionFactory soapcf = SOAPConnectionFactory.newInstance();
        SOAPConnection soapc = soapcf.createConnection();

        MessageFactory mf = MessageFactory.newInstance();
        SOAPMessage soapm = mf.createMessage();

        SOAPPart soapp = soapm.getSOAPPart();
        SOAPEnvelope soape = soapp.getEnvelope();
        SOAPBody soapb = soape.getBody();

        soape.getHeader().detachNode();
        QName name = new QName("http://court.kyjovsm.mff.cuni.cz/", "archiveClient", "court");
        SOAPElement soapel = soapb.addBodyElement(name);

        soapel.addChildElement(
                new QName("http://court.kyjovsm.mff.cuni.cz/", "arg0")).addTextNode("666");
        String endpoint = "http://127.0.0.1:8000/Court";
        SOAPMessage response = soapc.call(soapm, endpoint);
        SOAPBody responseBody = response.getSOAPBody();

        if (responseBody.hasFault()) {
            System.out.println(responseBody.getFault().getFaultString());
        } else {

            QName result = new QName("http://court.kyjovsm.mff.cuni.cz/", "result");

            SOAPBodyElement finalResponse = (SOAPBodyElement)
                    responseBody.getChildElements(result).next();

            System.out.println(finalResponse.getValue());
        }
        soapc.close();
    }
}
