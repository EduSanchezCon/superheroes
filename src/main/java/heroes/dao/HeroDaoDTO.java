package heroes.dao;

import heroes.model.Hero;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class HeroDaoDTO implements Serializable {

    private final String id;
    private final String name;
    private final String privateName;
    private final String weakness;

    public HeroDaoDTO(String id, String name, String privateName, String weakness) {
        this.id = id;
        this.name = name;
        this.privateName = privateName;
        this.weakness = weakness;
    }

    public HeroDaoDTO(Hero hero) {
        this(hero.getId(), hero.getName(), hero.getPrivateName(), hero.getWeakness());
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrivateName() {
        return privateName;
    }

    public String getWeakness() {
        return weakness;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof HeroDaoDTO) {

            HeroDaoDTO heroDaoDTO = (HeroDaoDTO) o;

            return new EqualsBuilder()
                    .append(id, heroDaoDTO.id)
                    .append(name, heroDaoDTO.name)
                    .append(privateName, heroDaoDTO.privateName)
                    .append(weakness, heroDaoDTO.weakness)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(privateName)
                .append(weakness)
                .toHashCode();
    }
}
