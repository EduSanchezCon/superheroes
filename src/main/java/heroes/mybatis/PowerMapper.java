package heroes.mybatis;

import heroes.dao.PowerDaoDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PowerMapper {

    @Select("select id, hero_id, name, description from powers")
    @ConstructorArgs({
            @Arg(column = "id", typeHandler = UuidTypeHandler.class, javaType = String.class),
            @Arg(column = "hero_id", typeHandler = UuidTypeHandler.class, javaType = String.class),
            @Arg(column = "name", javaType = String.class),
            @Arg(column = "description", javaType = String.class)
    })
    List<PowerDaoDTO> selectPowers();

    @Insert("insert into powers (id, hero_id, name, description) values " +
            "(#{id, jdbcType=OTHER, typeHandler=heroes.mybatis.UuidTypeHandler}," +
            "#{heroId, jdbcType=OTHER, typeHandler=heroes.mybatis.UuidTypeHandler}, #{name}, #{description})")
    int insertPower(PowerDaoDTO power);

    @Delete("delete from powers where id = #{id, jdbcType=OTHER, typeHandler=heroes.mybatis.UuidTypeHandler}")
    int deletePower(PowerDaoDTO power);
}
