package heroes.usecase;

import heroes.model.Hero;
import heroes.repository.HeroRepository;
import heroes.test.util.RandomGenerator;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class GetAllHeroesUseCaseImplTest {

    HeroRepository heroRepository = mock(HeroRepository.class);

    private GetAllHeroesUseCaseImpl sut = new GetAllHeroesUseCaseImpl(heroRepository);

    @Test
    public void shouldReturnAllHeroes(){

        List<Hero> expectedHeroes = Arrays.asList(
                RandomGenerator.nextImmutableObject(Hero.class),
                RandomGenerator.nextImmutableObject(Hero.class));

        when(heroRepository.getAllHeroes()).thenReturn(expectedHeroes);

        List<Hero> heroes = sut.perform();

        assertEquals(expectedHeroes, heroes);

    }

}