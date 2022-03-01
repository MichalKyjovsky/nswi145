package cz.cuni.mff.kyjovsm.ocr;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface OCR {

    /**
     * This function should fetch the file from the associated S3 bucket and pass it to the OCR computation service.
     *
     * @return <code>true</code> if the computation succeeds, else <code>false</code>.
     */
    @WebMethod
    boolean compute(String s3Uri, String accessToken);

}
