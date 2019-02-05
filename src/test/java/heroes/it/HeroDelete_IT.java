package heroes.it;

import heroes.dao.HeroDaoDTO;
import heroes.dao.HeroDAO;
import heroes.test.util.HeroMother;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class HeroDelete_IT extends DAO_IT{

    private HeroDaoDTO hero = new HeroDaoDTO(HeroMother.heroWith1Power());

    @Override
    protected void prepareData(Connection connection) throws SQLException {
        insertHero(connection);
    }

    @Override
    protected void performOperation() {
        HeroDAO sut = applicationContext.getBean(HeroDAO.class);
        int deletedRows = sut.deleteHero(hero);

        assertThat(deletedRows, is(1));
    }

    @Override
    protected void makeAsserts(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select 1 from heroes where id = ?");
        statement.setString(1, hero.getId());
        ResultSet rs = statement.executeQuery();

        assertFalse("should not exist any row in table", rs.next());
    }

    private void insertHero(Connection connection) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "insert into heroes (id, name, private_name, weakness) values (?, ?, ?, ?)");

        stmt.setString(1, hero.getId());
        stmt.setString(2, hero.getName());
        stmt.setString(3, hero.getPrivateName());
        stmt.setString(4, hero.getWeakness());

        stmt.executeUpdate();
    }
}
