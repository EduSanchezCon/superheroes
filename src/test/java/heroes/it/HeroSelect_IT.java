package heroes.it;

import heroes.dao.HeroDaoDTO;
import heroes.dao.HeroDAO;
import heroes.model.Hero;
import heroes.test.util.HeroMother;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class HeroSelect_IT extends DAO_IT{

    private Hero hero = HeroMother.heroWith1Power();

    @Override
    protected void prepareData(Connection connection) throws SQLException {
        insertHero(connection);
    }

    protected void performOperation() {
        HeroDAO sut = applicationContext.getBean(HeroDAO.class);
        List<HeroDaoDTO> heroes = sut.selectHeroes();

        assertThat(heroes, hasSize(1));
        HeroDaoDTO actualHero = heroes.get(0);
        assertEquals(hero.getId(), actualHero.getId());
        assertEquals(hero.getName(), actualHero.getName());
        assertEquals(hero.getPrivateName(), actualHero.getPrivateName());
        assertEquals(hero.getWeakness(), actualHero.getWeakness());
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
