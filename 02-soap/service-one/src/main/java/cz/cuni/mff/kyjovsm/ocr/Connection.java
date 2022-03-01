package cz.cuni.mff.kyjovsm.ocr;

import java.io.Serializable;
import java.util.Objects;

public class Connection implements Serializable {

    private final String uri;

    public Connection(String uri) {
        this.uri = Objects.requireNonNull(uri, "URI to the S3 storage must be non null");
    }

    public String getUri() {
        return uri;
    }

    public boolean authenticate(String accessToken) {
        // This is fairly simple
        return accessToken.isEmpty();
    }
}
