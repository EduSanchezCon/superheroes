package heroes.mybatis;

import heroes.dao.HeroDaoDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface HeroMapper {

    @Select("select id, name, private_name, weakness from heroes")
    @ConstructorArgs({
            @Arg(column = "id", typeHandler = UuidTypeHandler.class, javaType = String.class),
            @Arg(column = "name", javaType = String.class),
            @Arg(column = "private_name", javaType = String.class),
            @Arg(column = "weakness", javaType = String.class)
    })
    List<HeroDaoDTO> selectHeroes();

    @Insert("insert into heroes (id, name, private_name, weakness) values " +
            "(#{id, jdbcType=OTHER, typeHandler=heroes.mybatis.UuidTypeHandler}," +
            "#{name}, #{privateName}, #{weakness})")
    int insertHero(HeroDaoDTO hero);

    @Delete("delete from heroes where id = #{id, jdbcType=OTHER, typeHandler=heroes.mybatis.UuidTypeHandler}")
    int deleteHero(HeroDaoDTO hero);
}
