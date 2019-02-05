package heroes.controller;

import heroes.model.Hero;
import heroes.payload.HeroPayload;
import heroes.payload.IdPayload;
import heroes.usecase.CreateHeroUseCase;
import heroes.usecase.GetAllHeroesUseCase;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import java.util.List;
import java.util.stream.Collectors;

@Controller("/heroes")
public class HeroesController {

    private GetAllHeroesUseCase getAllHeroesUseCase;
    private CreateHeroUseCase createHeroUseCase;

    public HeroesController(GetAllHeroesUseCase getAllHeroesUseCase, CreateHeroUseCase createHeroUseCase) {

        this.getAllHeroesUseCase = getAllHeroesUseCase;
        this.createHeroUseCase = createHeroUseCase;
    }

    @Get
    public List<HeroPayload> getHeroes(){

        List<Hero> heroes = getAllHeroesUseCase.perform();
        return heroes.stream().map(HeroPayload::new).collect(Collectors.toList());
    }

    @Post
    public IdPayload createHero(@Body HeroPayload heroPayload) {

        String newId = createHeroUseCase.perform(heroPayload.toHero());
        return new IdPayload(newId);
    }
}
