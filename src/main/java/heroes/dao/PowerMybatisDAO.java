package heroes.dao;

import heroes.mybatis.PowerMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class PowerMybatisDAO implements PowerDAO {


    private final SqlSessionFactory sqlSessionFactory;

    public PowerMybatisDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<PowerDaoDTO> selectPowers() {
        try( SqlSession session = sqlSessionFactory.openSession(true)){
            return session.getMapper(PowerMapper.class).selectPowers();
        }
    }

    @Override
    public int insertPower(PowerDaoDTO power) {
        try( SqlSession session = sqlSessionFactory.openSession(true)){
            return session.getMapper(PowerMapper.class).insertPower(power);
        }
    }

    @Override
    public int deletePower(PowerDaoDTO power) {
        try( SqlSession session = sqlSessionFactory.openSession(true)){
            return session.getMapper(PowerMapper.class).deletePower(power);
        }
    }
}
