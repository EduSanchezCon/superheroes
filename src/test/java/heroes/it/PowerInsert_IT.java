package heroes.it;

import heroes.dao.PowerDaoDTO;
import heroes.dao.PowerDAO;
import heroes.model.Hero;
import heroes.test.util.HeroMother;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class PowerInsert_IT extends DAO_IT{

    private Hero hero = HeroMother.heroWith1Power();
    private PowerDaoDTO power = new PowerDaoDTO(hero.getId(), hero.getPowers().get(0));

    @Override
    protected void prepareData(Connection connection) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "insert into heroes (id, name, private_name, weakness) values (?, ?, ?, ?)");

        stmt.setString(1, hero.getId());
        stmt.setString(2, hero.getName());
        stmt.setString(3, hero.getPrivateName());
        stmt.setString(4, hero.getWeakness());

        System.out.println(stmt.toString());
        stmt.executeUpdate();
    }

    @Override
    protected void performOperation() {
        PowerDAO sut = applicationContext.getBean(PowerDAO.class);
        int newRows = sut.insertPower(power);

        assertThat(newRows, is(1));
    }

    @Override
    protected void makeAsserts(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select 1 from powers where id = ?");
        statement.setString(1, power.getId());
        ResultSet rs = statement.executeQuery();

        assertTrue("must exist one row in table", rs.next());
        assertFalse("must exist only one row in table", rs.next());
    }
}
