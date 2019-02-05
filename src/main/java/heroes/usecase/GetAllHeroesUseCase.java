package heroes.usecase;

import heroes.model.Hero;

import java.util.List;

public interface GetAllHeroesUseCase {

    List<Hero> perform();
}
