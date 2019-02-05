package heroes.usecase;

import heroes.model.Hero;
import heroes.repository.HeroRepository;
import heroes.test.util.HeroMother;
import org.junit.Test;

import javax.validation.ValidationException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CreateHeroUseCaseImplTest {

    private HeroRepository repository = mock(HeroRepository.class);

    CreateHeroUseCaseImpl sut = new CreateHeroUseCaseImpl(repository);

    @Test
    public void whenHeroHasMoreThanOnePowerShouldSaveIt() {

        Hero hero = HeroMother.heroWith2Powers();
        String expectedResponse = "I-expect-this";

        when(repository.saveHero(hero)).thenReturn(expectedResponse);

        String newId = sut.perform(hero);

        assertEquals(expectedResponse, newId);
    }

    @Test(expected = ValidationException.class)
    public void whenHeroHasLessThanTwoPowersShouldRaiseAnException() {

        Hero hero = HeroMother.heroWith1Power();

        try {
            String newId = sut.perform(hero);
        }catch (Exception e){
            verifyZeroInteractions(repository);
            throw e;
        }

    }
}