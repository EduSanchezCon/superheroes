package heroes.usecase;

import heroes.model.Hero;
import heroes.repository.HeroRepository;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class GetAllHeroesUseCaseImpl implements GetAllHeroesUseCase {

    private HeroRepository heroRepository;

    public GetAllHeroesUseCaseImpl(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @Override
    public List<Hero> perform() {

        return heroRepository.getAllHeroes();
    }
}
