package cz.cuni.mff.kyjovsm.ocr;

import javax.jws.WebService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Random;

@WebService(endpointInterface = "cz.cuni.mff.kyjovsm.ocr.OCR")
public class OCRImpl implements OCR {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final Random OCR_COMPUTATION = new Random();

    public boolean compute(String s3Uri, String accessToken) {
        try {

            Connection conn = createConnection(s3Uri);
            if (conn != null && conn.authenticate(accessToken)) {
                LOGGER.info("User has been successfully authenticated to S3");
            }
            LOGGER.info("Passing user's document for OCR Processing");

            if (process(conn)) {
                LOGGER.info("User document has been processed successfully.");
                return true;
            } else {
                LOGGER.info("An error occurred during the processing of user's document.");
                return false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public TaskResult getTaskResult(String taskId) {
        return TaskResult.fetchResultFromOCR(taskId);
    }


    private Connection createConnection(String uri) {
        LOGGER.info("Creating connection to the user storage: {}", uri);
        return !uri.isEmpty() ? new Connection(uri) : null;
    }

    private boolean process(Connection connection) throws InterruptedException {
        Objects.requireNonNull(connection, "Connection to S3 is required.");
        Thread.sleep(3000);
        return OCR_COMPUTATION.nextBoolean();
    }

}
