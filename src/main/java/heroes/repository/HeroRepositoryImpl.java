package heroes.repository;

import heroes.dao.HeroDAO;
import heroes.dao.HeroDaoDTO;
import heroes.dao.PowerDAO;
import heroes.dao.PowerDaoDTO;
import heroes.model.Hero;
import heroes.model.Power;

import javax.inject.Singleton;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;

@Singleton
public class HeroRepositoryImpl implements HeroRepository {

    private HeroDAO heroDAO;
    private PowerDAO powerDAO;

    public HeroRepositoryImpl(HeroDAO heroDAO, PowerDAO powerDAO) {
        this.heroDAO = heroDAO;
        this.powerDAO = powerDAO;
    }

    @Override
    public List<Hero> getAllHeroes() {
        List<HeroDaoDTO> heroes = heroDAO.selectHeroes();
        List<PowerDaoDTO> powers = powerDAO.selectPowers();

        Map<String, List<Power>> powersMap = powers.stream()
                .collect(
                        Collectors.groupingBy(PowerDaoDTO::getHeroId,
                            Collectors.mapping( p -> new Power(p.getId(), p.getName(), p.getDescription()), Collectors.toList())));

        return heroes.stream().map(h -> new Hero(h.getId(), h.getName(), h.getPrivateName(), h.getWeakness(), powersMap.get(h.getId())))
                .collect(Collectors.toList());
    }


    @Override
    public String saveHero(Hero hero) {

        Deque<Runnable> compensations = new LinkedBlockingDeque<>();
        try {
            insertHero(new HeroDaoDTO(hero), compensations);
            hero.getPowers().stream()
                    .map(power -> new PowerDaoDTO(hero.getId(), power))
                    .forEach(p -> insertPower(p, compensations));
        }catch ( Throwable t){
            compensations.forEach(Runnable::run);
            throw t;
        }
        return hero.getId();
    }

    private void insertPower(PowerDaoDTO power, Deque<Runnable> compensations) {
        powerDAO.insertPower(power);
        compensations.push(() -> powerDAO.deletePower(power));
    }

    private void insertHero(HeroDaoDTO hero, Deque<Runnable> compensations) {
        heroDAO.insertHero(hero);
        compensations.push(() -> heroDAO.deleteHero(hero));
    }
}
