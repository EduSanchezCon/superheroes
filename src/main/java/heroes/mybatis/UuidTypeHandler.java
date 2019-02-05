package heroes.mybatis;

import io.micronaut.core.util.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;
import java.util.UUID;

public class UuidTypeHandler implements TypeHandler<String> {

    @Override
    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
            ps.setObject(i, toUUID(parameter), Types.OTHER);
    }

    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getObject(columnName).toString();
    }

    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getObject(columnIndex).toString();
    }

    @Override
    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getObject(columnIndex).toString();
    }

    private static UUID toUUID(String val) throws SQLException {
        if (StringUtils.isEmpty(val)) {
            return null;
        }
        return UUID.fromString(val);
    }

}
