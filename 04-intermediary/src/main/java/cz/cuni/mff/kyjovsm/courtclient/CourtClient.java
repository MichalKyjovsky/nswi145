package cz.cuni.mff.kyjovsm.courtclient;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.soap.*;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "courtClient", value = "/intermediary")
public class CourtClient extends HttpServlet {
    private String message;

    @Override
    public void init() {
        System.out.println("It hecking works!");
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("It hecking works!");
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

    public void destroy() {
    }
}