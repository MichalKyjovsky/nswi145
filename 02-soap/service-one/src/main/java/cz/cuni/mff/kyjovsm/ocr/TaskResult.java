package cz.cuni.mff.kyjovsm.ocr;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class TaskResult implements Serializable {

    private static final Random rand = new Random(15);

    private String taskId;

    private HashMap<String, String> metadata;

    private HashMap<String, String> resultSet;

    private TaskResult(String taskId) {
        this.taskId = taskId;
        this.metadata = new HashMap<>();
        this.resultSet = new HashMap<>();
    }

    private TaskResult(String taskId, HashMap<String, String> metadata, HashMap<String, String> resultSet) {
        this.taskId = taskId;
        this.metadata = metadata;
        this.resultSet = resultSet;
    }


    public static TaskResult fetchResultFromOCR(String taskId) {
        try {
            Thread.sleep(2000);

            HashMap<String, String> metadata = new HashMap<>();
            HashMap<String, String> resultSet = new HashMap<>();

            generateStrings(10).forEach(key -> metadata.put(key, RandomStringUtils.random(rand.nextInt(), true, true)));
            generateStrings(12).forEach(key -> resultSet.put(key, RandomStringUtils.random(rand.nextInt(), true, true)));

            return new TaskResult(taskId, metadata, resultSet);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new TaskResult(taskId);
    }

    private static List<String> generateStrings(int size) {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;

        List<String> result = new ArrayList<>();

        for (int i = 0; i < size; i++)
            result.add(RandomStringUtils.random(length, useLetters, useNumbers));

        return result;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public HashMap<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(HashMap<String, String> metadata) {
        this.metadata = metadata;
    }

    public HashMap<String, String> getResultSet() {
        return resultSet;
    }

    public void setResultSet(HashMap<String, String> resultSet) {
        this.resultSet = resultSet;
    }
}
