import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class ConnectionPool {

    private static ConnectionPool instance;

    private static Integer poolSize = null;
    private final List<Connection> connections;

    private ConnectionPool(Integer poolSize) {
        ConnectionPool.poolSize = poolSize;
        this.connections = Collections.synchronizedList(new ArrayList<>());
        IntStream.range(0, poolSize)
                .boxed()
                .forEach(index-> this.connections.add(new Connection()));
    }

    public static synchronized ConnectionPool getInstance(Integer poolSize) {
        if (instance == null) {
            instance = new ConnectionPool(poolSize);
        }
        return instance;
    }

    public synchronized Connection getConnection() {

        if(this.connections.isEmpty()){
            try {
                connections.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return this.connections.remove(connections.size() - 1);

    }

    public void releaseConnection(Connection connection) {

        synchronized (this.connections){
            this.connections.add(connection);
            this.connections.notifyAll();
        }
    }
}
