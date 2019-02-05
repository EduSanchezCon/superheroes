package heroes.dao;

import heroes.mybatis.HeroMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class HeroMybatisDAO implements HeroDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public HeroMybatisDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<HeroDaoDTO> selectHeroes() {
        try( SqlSession session = sqlSessionFactory.openSession(true)){
            return session.getMapper(HeroMapper.class).selectHeroes();
        }
    }

    @Override
    public int insertHero(HeroDaoDTO hero) {
        try( SqlSession session = sqlSessionFactory.openSession(true)){
            return session.getMapper(HeroMapper.class).insertHero(hero);
        }
    }

    @Override
    public int deleteHero(HeroDaoDTO hero) {
        try( SqlSession session = sqlSessionFactory.openSession(true)){
            return session.getMapper(HeroMapper.class).deleteHero(hero);
        }
    }
}
