package cz.cuni.mff.kyjovsm;

import java.io.IOException;
import javax.xml.namespace.QName;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.soap.*;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/intermediary")
public class CourtClient extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            SOAPConnectionFactory soapcf = SOAPConnectionFactory.newInstance();
            SOAPConnection soapc = soapcf.createConnection();

            MessageFactory mf = MessageFactory.newInstance();
            SOAPMessage soapm = mf.createMessage(new MimeHeaders(), request.getInputStream());

            SOAPPart soapp = soapm.getSOAPPart();
            SOAPEnvelope soape = soapp.getEnvelope();
            SOAPBody soapb = soape.getBody();

            SOAPHeader header = soapm.getSOAPHeader();

//            Header removal
//            header.detachNode();


            QName name = new QName("http://court.kyjovsm.mff.cuni.cz/", "archiveClient", "court");
            SOAPElement soapel = soapb.addBodyElement(name);

            soapel.addChildElement(
                    new QName("", "arg0")).addTextNode("666");
            String endpoint = "http://127.0.0.1:8000/Court";
            SOAPMessage soapResponse = soapc.call(soapm, endpoint);
            SOAPBody responseBody = soapResponse.getSOAPBody();

            if (responseBody.hasFault()) {
                System.out.println(responseBody.getFault().getFaultString());
            } else {

                QName result = new QName("http://court.kyjovsm.mff.cuni.cz/", "result");

                SOAPBodyElement finalResponse = (SOAPBodyElement)
                        responseBody.getChildElements(result).next();

                System.out.println(finalResponse.getValue());
            }
            soapc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
