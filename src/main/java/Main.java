import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

import static javafx.scene.input.KeyCode.K;
import static javafx.scene.input.KeyCode.V;

public class Main {

    public static void main(String[] args) throws IOException {

        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        List<Thread> listOfthreads = new ArrayList<>();
        IntStream.range(0, 100)
                .boxed()
                .forEach(index -> {
                    Thread thread = new Thread(() -> {
                        Connection connection = connectionPool.getConnection();
                        if (connection != null) {
                            connection.read();
                            connectionPool.releaseConnection(connection);
                        }
                    });
                    listOfthreads.add(thread);
                    thread.start();
                });

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        IntStream.range(0, 100)
                .boxed()
                .forEach(index -> {
                    CompletableFuture<Connection> completableFuture = CompletableFuture.supplyAsync(() ->
                            new Connection(), executorService);

                    completableFuture.thenAccept(connection -> connection.update());
                });

        /*FileSing.getInstance().addContent("One");
        FileSing.getInstance().addContent("Two");
        FileSing.getInstance().addContent("Three");

        FileSing.getInstance().displayContent();

        File inputFile = new File("src/main/resources/inputFile.txt");
        File outputFile = new File("src/main/resources/outputFile.txt");
        FileUtils.touch(inputFile);
        FileUtils.touch(outputFile);

        String contents = FileUtils.readFileToString(inputFile, StandardCharsets.UTF_8.name());

        if (inputFile.exists() && !StringUtils.isEmpty(contents)) {
            LOGGER.debug("The file exists. Here is it's contents.");
            LOGGER.debug(contents);
            Integer count;
            String[] wordsArray = StringUtils.split(contents);
            Map<String, Integer> map = new HashMap<>();
            for (String word : wordsArray) {
                if (map.containsKey(word)) {
                    count = map.get(word);
                    map.put(word, count + 1);
                } else {
                    map.put(word, 1);
                }
            }

            Map<String, Integer> sortedMap = sortMap(map);
            for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
                LOGGER.debug(entry.getKey() + " : " + entry.getValue());
                FileUtils.writeStringToFile(outputFile, String.valueOf(sortedMap),  StandardCharsets.UTF_8.name());
            }
        }
    }

    private static Map<String, Integer> sortMap(Map<String, Integer> map) {

        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){
            public int compare(Map.Entry<String, Integer> v1, Map.Entry<String, Integer> v2) {
                return (v1.getValue()).compareTo(v2.getValue());
            }
        });
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();

        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;*/
    }
}







