package heroes.usecase;

import heroes.model.Hero;
import heroes.repository.HeroRepository;

import javax.inject.Singleton;
import javax.validation.ValidationException;

@Singleton
public class CreateHeroUseCaseImpl implements CreateHeroUseCase {

    private HeroRepository repository;

    public CreateHeroUseCaseImpl(HeroRepository repository) {
        this.repository = repository;
    }

    @Override
    public String perform(Hero hero) {

        validate(hero);

        return repository.saveHero(hero);
    }

    private void validate(Hero hero) {
        if (hero.getPowers().size() < 2){
            throw new ValidationException("we don't want, shitty heroes");
        }
    }
}
