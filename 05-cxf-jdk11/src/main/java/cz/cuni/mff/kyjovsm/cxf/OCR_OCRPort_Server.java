
package cz.cuni.mff.kyjovsm.cxf;

import cz.cuni.mff.kyjovsm.ocr.OCRImpl;
import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 3.5.1
 * 2022-03-23T00:04:56.054+01:00
 * Generated source version: 3.5.1
 *
 */

public class OCR_OCRPort_Server{

    protected OCR_OCRPort_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new OCRImpl();
        String address = "http://127.0.0.1:8000/OCR";
        Endpoint.publish(address, implementor);
    }

    public static void main(String args[]) throws java.lang.Exception {
        new OCR_OCRPort_Server();
        System.out.println("Server ready...");

        Thread.sleep(5 * 60 * 1000);
        System.out.println("Server exiting");
        System.exit(0);
    }
}
