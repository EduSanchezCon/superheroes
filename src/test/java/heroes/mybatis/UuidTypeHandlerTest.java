package heroes.mybatis;

import org.apache.ibatis.type.JdbcType;
import org.junit.Test;

import java.sql.*;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UuidTypeHandlerTest {

    private static final UUID uuid = UUID.randomUUID();
    private static final String strUUID = uuid.toString();

    private UuidTypeHandler sut = new UuidTypeHandler();

    @Test
    public void shouldConvertStringIntoUUIDBeforeSettingParam() throws SQLException {
        PreparedStatement statementMock = mock(PreparedStatement.class);

        sut.setParameter(statementMock, 0, strUUID, JdbcType.OTHER);

        verify(statementMock).setObject(0, uuid, Types.OTHER);
    }

    @Test
    public void givenANullParamShouldNotConvertIntoUUIDBeforeSetting() throws SQLException {
        PreparedStatement statementMock = mock(PreparedStatement.class);

        sut.setParameter(statementMock, 0, null, JdbcType.OTHER);

        verify(statementMock).setObject(0, null, Types.OTHER);
    }

    @Test
    public void shouldConvertUUIDIntoStringInResultSet() throws SQLException {
        ResultSet rs = mock(ResultSet.class);
        when(rs.getObject(anyInt())).thenReturn(uuid);

        String result = sut.getResult(rs, 0);

        assertEquals(strUUID, result);
    }

    @Test
    public void shouldConvertUUIDIntoStringInResultSetByColumnName() throws SQLException {
        ResultSet rs = mock(ResultSet.class);
        when(rs.getObject(anyString())).thenReturn(uuid);

        String result = sut.getResult(rs, "column");

        assertEquals(strUUID, result);
    }

    @Test
    public void shouldConvertUUIDIntoStringInCallableStatement() throws SQLException {
        CallableStatement cs = mock(CallableStatement.class);
        when(cs.getObject(anyInt())).thenReturn(uuid);

        String result = sut.getResult(cs, 0);

        assertEquals(strUUID, result);
    }
}