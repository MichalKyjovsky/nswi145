package cz.cuni.mff.kyjovsm.lawsuite;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;
import java.util.*;

public class Report implements Serializable {

    private static final Random rand = new Random();

    public enum ReportStatus {
        TODO,
        DATA_MISSING,
        QUALIFIED;

        private static final List<ReportStatus> VALUES =
                Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public ReportStatus randomReport()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }

    private String reportId;

    private Client client;

    private ReportStatus status;

    private List<String> issuedClaims;

    public Report() {
        // NOP
    }

    public Report(Client client) {
        this.reportId = RandomStringUtils.random(14, true, true);
        this.client = Objects.requireNonNull(client, "Client instance is required!");
        this.status = ReportStatus.TODO;

        issuedClaims = loadUserFileLinks(this.client.getName());
    }



    List<String> loadUserFileLinks(String userDir) {
        List<String> links = new ArrayList<>();

        for (int i = 0; i < rand.nextInt(10); i++) {
            links.add(String.format("s3://%s/%s", userDir, RandomStringUtils.random(10, true, true)));
        }
        return links;
    }


    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public List<String> getIssuedClaims() {
        return issuedClaims;
    }

    public void setIssuedClaims(List<String> issuedClaims) {
        this.issuedClaims = issuedClaims;
    }

}
