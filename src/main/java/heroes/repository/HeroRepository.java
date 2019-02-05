package heroes.repository;

import heroes.model.Hero;

import java.util.List;

public interface HeroRepository {

    List<Hero> getAllHeroes();

    String saveHero(Hero hero);
}
