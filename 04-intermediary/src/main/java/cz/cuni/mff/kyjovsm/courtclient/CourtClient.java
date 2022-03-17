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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "courtClient", value = "/intermediary")
public class CourtClient extends HttpServlet {

    private final List<String> KNOWN_AUTH_METHODS = List.of("kerberos", "openapi", "oauth2");

    private static final String DEFAULT_AUTH_POLICY = "kerberos";

    private String message;

    @Override
    public void init() {
        message = "NSWI145 - Homework No.4 - SAAJ Client";
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
        try {
            SOAPConnectionFactory soapcf = SOAPConnectionFactory.newInstance();
            SOAPConnection soapc = soapcf.createConnection();

            MessageFactory mf = MessageFactory.newInstance();
            SOAPMessage soapm = mf.createMessage(new MimeHeaders(), request.getInputStream());

            SOAPHeader header = soapm.getSOAPHeader();

            QName auth_policy = new QName("http://court.kyjovsm.mff.cuni.cz/", "policy");

            String policyType;


            // Check whether the header is present
            if (header.hasChildNodes()) {
                Node n = header.getChildElements(auth_policy).next();

                // If so, then check the security policy attribute
                policyType = n.getAttributes().getNamedItem("policyType").getNodeValue();

                if (policyType != null && this.KNOWN_AUTH_METHODS.contains(policyType)) {
                    System.out.printf("Policy type set to: %s%n", policyType);
                } else {
                    System.out.println("Unsupported or null security policy used.");
                    System.out.printf("Using default security policy... %s", DEFAULT_AUTH_POLICY);
                }

                header.removeChild(n);
            } else {
                System.out.println("No soap header provided");
            }

            String endpoint = "http://127.0.0.1:8000/Court";

            // Record the timestamp of processing
            LocalDateTime archivingDate = LocalDateTime.now();

            // Call the service
            SOAPMessage soapResponse = soapc.call(soapm, endpoint);
            soapc.close();

            SOAPBody responseBody = soapResponse.getSOAPBody();

            if (responseBody.hasFault()) {
                System.out.println(responseBody.getFault().getFaultString());
            } else {

                // Get the response from the service
                QName result = new QName("http://court.kyjovsm.mff.cuni.cz/", "archiveClientResponse", "ns2");


                SOAPBodyElement finalResponse = (SOAPBodyElement)
                        responseBody.getChildElements(result).next();

                SOAPBodyElement finalResponseValue = (SOAPBodyElement)
                        finalResponse.getChildElements().next();

                // Attach the timestamp information
                if (!finalResponseValue.getValue().isEmpty() && finalResponseValue.getValue().equals("true")) {

                    System.out.println("Client was archived");

                    QName archivingTimeStamp = new QName("http://court.kyjovsm.mff.cuni.cz/", "timestamp");

                    if (soapResponse.getSOAPHeader() != null) {
                        soapResponse.getSOAPHeader().addHeaderElement(archivingTimeStamp).addTextNode("Date and time of client archiving: " + archivingDate.format(DateTimeFormatter.ISO_DATE_TIME));
                    } else {
                        SOAPHeader newHeader = soapResponse.getSOAPPart().getEnvelope().addHeader();
                        newHeader.addHeaderElement(archivingTimeStamp).addTextNode("Date and time of client archiving: " + archivingDate.format(DateTimeFormatter.ISO_DATE_TIME));
                    }
                } else {
                    QName errorMessage = new QName("http://court.kyjovsm.mff.cuni.cz/", "error");

                    if (soapResponse.getSOAPHeader() != null) {
                        soapResponse.getSOAPHeader().addHeaderElement(errorMessage).addTextNode("Archiving of the client failed: " + archivingDate.format(DateTimeFormatter.ISO_DATE_TIME));
                    } else {
                        SOAPHeader newHeader = soapResponse.getSOAPPart().getEnvelope().addHeader();
                        newHeader.addHeaderElement(errorMessage).addTextNode("Archiving of the client failed: " + archivingDate.format(DateTimeFormatter.ISO_DATE_TIME));
                    }
                }
                // Send back to the user
                soapResponse.writeTo(response.getOutputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }
}