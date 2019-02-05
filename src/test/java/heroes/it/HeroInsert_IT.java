package heroes.it;

import heroes.dao.HeroDaoDTO;
import heroes.dao.HeroDAO;
import heroes.test.util.HeroMother;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class HeroInsert_IT extends DAO_IT{

    private HeroDaoDTO hero = new HeroDaoDTO(HeroMother.heroWith1Power());

    @Override
    protected void performOperation() {
        HeroDAO sut = applicationContext.getBean(HeroDAO.class);
        int newRows = sut.insertHero(hero);

        assertThat(newRows, is(1));
    }

    @Override
    protected void makeAsserts(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select 1 from heroes where id = ?");
        statement.setString(1, hero.getId());
        ResultSet rs = statement.executeQuery();

        assertTrue("must exist one row in table", rs.next());
        assertFalse("must exist only one row in table", rs.next());
    }
}
