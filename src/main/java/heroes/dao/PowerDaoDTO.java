package heroes.dao;

import heroes.model.Power;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class PowerDaoDTO implements Serializable {

    private final String id;
    private final String heroId;
    private final String name;
    private final String description;

    public PowerDaoDTO(String id, String heroId, String name, String description) {
        this.id = id;
        this.heroId = heroId;
        this.name = name;
        this.description = description;
    }

    public PowerDaoDTO(String heroId, Power power) {
        this(power.getId(), heroId, power.getName(), power.getDescription());
    }

    public String getId() {
        return id;
    }

    public String getHeroId() {
        return heroId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof PowerDaoDTO) {

            PowerDaoDTO powerDaoDTO = (PowerDaoDTO) o;

            return new EqualsBuilder()
                    .append(id, powerDaoDTO.id)
                    .append(heroId, powerDaoDTO.heroId)
                    .append(name, powerDaoDTO.name)
                    .append(description, powerDaoDTO.description)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(heroId)
                .append(name)
                .append(description)
                .toHashCode();
    }
}
