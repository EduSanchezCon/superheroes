package heroes.it;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.server.EmbeddedServer;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAO_IT {
    protected static ApplicationContext applicationContext;
    protected static SqlSessionFactory sessionFactory;
    private static EmbeddedServer server;

    @BeforeClass
    public static void init(){

        server = ApplicationContext.run(EmbeddedServer.class, "test");
        applicationContext = server.getApplicationContext();
        sessionFactory = applicationContext.getBean(SqlSessionFactory.class);
    }

    @AfterClass
    public static void shutdown(){
        if (server != null){
            server.close();
        }
    }

    @Test
    public void shouldPerformOperation() throws SQLException {

        prepareData();
        performOperation();
        makeAsserts();
    }

    private void prepareData() throws SQLException {
        SqlSession sqlSession = sessionFactory.openSession(true);
        try  (Connection connection = sqlSession.getConnection()){
            prepareData(connection);
        }
    }

    private void makeAsserts() throws SQLException {
        SqlSession sqlSession = sessionFactory.openSession(true);
        try  (Connection connection = sqlSession.getConnection()){
            makeAsserts(connection);
        }
    }


    protected void prepareData(Connection connection) throws SQLException{};
    protected abstract void performOperation();
    protected void makeAsserts(Connection connection) throws SQLException{};
}
