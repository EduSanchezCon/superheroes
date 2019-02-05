package heroes.controller;

import heroes.model.Hero;
import heroes.payload.HeroPayload;
import heroes.payload.IdPayload;
import heroes.test.util.RandomGenerator;
import heroes.usecase.CreateHeroUseCase;
import heroes.usecase.GetAllHeroesUseCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class HeroesControllerTest {

    private final GetAllHeroesUseCase getAllHeroesUseCase = mock(GetAllHeroesUseCase.class);
    private final CreateHeroUseCase createHeroUseCase = mock(CreateHeroUseCase.class);

    HeroesController sut = new HeroesController(getAllHeroesUseCase, createHeroUseCase);

    @Test
    public void shouldRetrieveAllHeroes(){

        when(getAllHeroesUseCase.perform()).thenReturn(
                Arrays.asList(
                        RandomGenerator.nextImmutableObject(Hero.class),
                        RandomGenerator.nextImmutableObject(Hero.class)));

        List<HeroPayload> heroes = sut.getHeroes();

        assertThat(heroes, hasSize(2));
    }

    @Test
    public void shouldRegisterNewHero(){

        String expectedId = "I-Expect-this";
        when(createHeroUseCase.perform(any(Hero.class))).thenReturn(expectedId);

        HeroPayload hero = RandomGenerator.nextImmutableObject(HeroPayload.class);

        IdPayload newId = sut.createHero(hero);

        assertEquals(expectedId, newId.getId());
    }

}
