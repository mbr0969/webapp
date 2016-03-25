package loc.linux.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by papa on 24.03.2016.
 */
public interface SqlExecutor <T> {
    T execute(PreparedStatement ps) throws SQLException;
}
