import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Connection {

    Logger LOGGER = LogManager.getLogger(Connection.class);


    public void create() {

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOGGER.debug(Thread.currentThread().getName() + " is creating something.");

    }

    public void read() {

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOGGER.debug(Thread.currentThread().getName() + " is reading something.");
    }

    public void update() {

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOGGER.debug(Thread.currentThread().getName() + " is updating something.");

    }

    public void delete() {

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOGGER.debug(Thread.currentThread().getName() + " is deleting something.");

    }
}
