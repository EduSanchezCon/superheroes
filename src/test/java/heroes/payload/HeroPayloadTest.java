package heroes.payload;

import heroes.model.Hero;
import heroes.test.template.ImmutablePojoRandomizedTest;
import heroes.test.util.RandomGenerator;
import org.junit.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class HeroPayloadTest extends ImmutablePojoRandomizedTest<HeroPayload> {


    @Test
    public void shouldGenerateModelInstance(){

        HeroPayload heroPayload = RandomGenerator.nextImmutableObject(HeroPayload.class);

        Hero hero = heroPayload.toHero();

        assertEquals(heroPayload.getName(), hero.getName());
        assertEquals(heroPayload.getPrivateName(), hero.getPrivateName());
        assertEquals(heroPayload.getWeakness(), hero.getWeakness());
        assertThat(hero.getPowers(), hasSize(heroPayload.getPowers().size()));
    }

    @Test
    public void shouldHaveAConstructorWithModelParameter(){

        Hero hero = RandomGenerator.nextImmutableObject(Hero.class);
        HeroPayload heroPayload = new HeroPayload(hero);

        assertEquals(heroPayload.getName(), hero.getName());
        assertEquals(heroPayload.getPrivateName(), hero.getPrivateName());
        assertEquals(heroPayload.getWeakness(), hero.getWeakness());
        assertThat(hero.getPowers(), hasSize(heroPayload.getPowers().size()));
    }
}