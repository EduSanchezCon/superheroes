package heroes.repository;

import heroes.dao.HeroDAO;
import heroes.dao.HeroDaoDTO;
import heroes.dao.PowerDAO;
import heroes.dao.PowerDaoDTO;
import heroes.model.Hero;
import heroes.test.util.HeroMother;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class HeroRepositoryImplTest {

    private HeroRepositoryImpl sut;

    private HeroDAO heroDAO = mock(HeroDAO.class);
    private PowerDAO powerDAO = mock(PowerDAO.class);

    @Before
    public void init(){
        sut = new HeroRepositoryImpl(heroDAO, powerDAO);
    }

    @Test
    public void shouldRetrieveAllHeroesWithTheirPowers(){
        Hero h2p = HeroMother.heroWith2Powers();
        Hero h1p = HeroMother.heroWith1Power();

        List<Hero> expectedHeroList = Arrays.asList(h2p, h1p);

        when(heroDAO.selectHeroes()).thenReturn(
                expectedHeroList.stream()
                    .map(HeroDaoDTO::new)
                    .collect(Collectors.toList()));

        when(powerDAO.selectPowers()).thenReturn(
                expectedHeroList.stream()
                        .flatMap(h -> h.getPowers().stream().map(p -> new PowerDaoDTO(h.getId(), p)))
                        .collect(Collectors.toList()));

        List<Hero> actualHeroList = sut.getAllHeroes();

        assertEquals(expectedHeroList, actualHeroList);

    }

    @Test
    public void shouldSaveNewHeroAndPowers(){

        Hero hero = HeroMother.heroWith2Powers();

        sut.saveHero(hero);

        verify(heroDAO, times(1)).insertHero(any(HeroDaoDTO.class));
        verify(powerDAO, times(2)).insertPower(any(PowerDaoDTO.class));
    }

    @Test(expected = RuntimeException.class)
    public void whenSomeInsertionFailsShouldCompensatePreviousOnes(){

        Hero hero = HeroMother.heroWith2Powers();
        PowerDaoDTO failingPower = new PowerDaoDTO(hero.getId(), hero.getPowers().get(1));

        when(powerDAO.insertPower(failingPower)).thenThrow(new RuntimeException("Unexpected error"));

        try {
            sut.saveHero(hero);
        }catch (Exception e){
            InOrder inOrder = inOrder(heroDAO, powerDAO);
            inOrder.verify(powerDAO, times(1)).deletePower(any(PowerDaoDTO.class));
            inOrder.verify( heroDAO).deleteHero( new HeroDaoDTO(hero));
            throw e;
        }
    }

}