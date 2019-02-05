package heroes.dao;

import java.util.List;

public interface HeroDAO {

    List<HeroDaoDTO> selectHeroes();

    int insertHero(HeroDaoDTO hero);

    int deleteHero(HeroDaoDTO hero);
}
