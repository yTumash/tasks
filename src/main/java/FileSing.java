import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileSing {

    private static FileSing aFile;
    private static String content = "\n ****** \n";
    Logger LOGGER = LogManager.getLogger(FileSing.class);


    private FileSing() {
    }

    public static FileSing getInstance() {

        if (aFile == null) {
            aFile = new FileSing();
        }
        return aFile;
    }

    public void addContent(String data) {
        content += data + "\n";
    }

    public void displayContent() {
        LOGGER.debug(content);
    }
}

