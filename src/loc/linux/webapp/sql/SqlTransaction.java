package loc.linux.webapp.sql;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by papa on 24.03.2016.
 */
public interface SqlTransaction<T> {
    T execute(Connection conn) throws SQLException;
}
