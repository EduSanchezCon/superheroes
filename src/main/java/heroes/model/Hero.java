package heroes.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Hero implements Serializable {

    private final String id;
    private final String name;
    private final String privateName;
    private final String weakness;
    private List<Power> powers;

    public Hero(String name, String privateName, String weakness, List<Power> powers) {
        this(UUID.randomUUID().toString(), name, privateName, weakness, powers);
    }

    public Hero(String id, String name, String privateName, String weakness, List<Power> powers) {
        this.id = id;
        this.name = name;
        this.privateName = privateName;
        this.weakness = weakness;
        this.powers = powers;
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

    public List<Power> getPowers() {
        return powers;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Hero) {

            Hero hero = (Hero) o;

            return new EqualsBuilder()
                    .append(id, hero.id)
                    .append(name, hero.name)
                    .append(privateName, hero.privateName)
                    .append(weakness, hero.weakness)
                    .append(powers, hero.powers)
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
                .append(powers)
                .toHashCode();
    }
}
