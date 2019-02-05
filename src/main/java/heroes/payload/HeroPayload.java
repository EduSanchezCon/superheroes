package heroes.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import heroes.model.Hero;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class HeroPayload implements Serializable {

    private final String name;
    private final String privateName;
    private final String weakness;
    private final List<PowerPayload> powers;

    public HeroPayload(@JsonProperty("name") String name,
                       @JsonProperty("privateName") String privateName,
                       @JsonProperty("weakness") String weakness,
                       @JsonProperty("powers") List<PowerPayload> powers) {
        this.name = name;
        this.privateName = privateName;
        this.weakness = weakness;
        this.powers = powers;
    }

    public HeroPayload(Hero model){
        this.name = model.getName();
        this.privateName = model.getPrivateName();
        this.weakness = model.getWeakness();
        this.powers = model.getPowers().stream().map(PowerPayload::new).collect(Collectors.toList());
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

    public List<PowerPayload> getPowers() {
        return powers;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof HeroPayload) {

            HeroPayload that = (HeroPayload) o;

            return new EqualsBuilder()
                    .append(name, that.name)
                    .append(privateName, that.privateName)
                    .append(weakness, that.weakness)
                    .append(powers, that.powers)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(privateName)
                .append(weakness)
                .append(powers)
                .toHashCode();
    }

    public Hero toHero() {
        return new Hero(name, privateName, weakness,
                powers.stream().map(PowerPayload::toPower).collect(Collectors.toList()));
    }
}

