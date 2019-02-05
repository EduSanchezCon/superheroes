package heroes.dao;

import java.util.List;

public interface PowerDAO {

    List<PowerDaoDTO> selectPowers();

    int insertPower(PowerDaoDTO power);

    int deletePower(PowerDaoDTO hero);
}
