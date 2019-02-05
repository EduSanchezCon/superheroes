package heroes.it;

import heroes.dao.PowerDaoDTO;
import heroes.dao.PowerDAO;
import heroes.model.Hero;
import heroes.test.util.HeroMother;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class PowerSelect_IT extends DAO_IT{

    private Hero hero = HeroMother.heroWith1Power();
    private PowerDaoDTO power = new PowerDaoDTO(hero.getId(), hero.getPowers().get(0));

    @Override
    protected void prepareData(Connection connection) throws SQLException {
        insertHero(connection);
        insertPower(connection);
    }

    protected void performOperation() {
        PowerDAO sut = applicationContext.getBean(PowerDAO.class);
        List<PowerDaoDTO> powers = sut.selectPowers();

        assertThat(powers, hasSize(1));
        PowerDaoDTO actualPower = powers.get(0);
        assertEquals(power.getId(), actualPower.getId());
        assertEquals(power.getHeroId(), actualPower.getHeroId());
        assertEquals(power.getName(), actualPower.getName());
        assertEquals(power.getDescription(), actualPower.getDescription());
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

    private void insertPower(Connection connection) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "insert into powers (id, hero_id, name, description) values (?, ?, ?, ?)");

        stmt.setString(1, power.getId());
        stmt.setString(2, hero.getId());
        stmt.setString(3, power.getName());
        stmt.setString(4, power.getDescription());

        stmt.executeUpdate();
    }
}
