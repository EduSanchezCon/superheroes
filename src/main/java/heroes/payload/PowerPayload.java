package heroes.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import heroes.model.Power;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class PowerPayload implements Serializable {

    private final String name;
    private final String description;

    public PowerPayload(@JsonProperty("name") String name,
                        @JsonProperty("description") String description) {
        this.name = name;
        this.description = description;
    }

    public PowerPayload(Power model){
        this.name = model.getName();
        this.description = model.getDescription();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof PowerPayload) {

            PowerPayload that = (PowerPayload) o;

            return new EqualsBuilder()
                    .append(name, that.name)
                    .append(description, that.description)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(description)
                .toHashCode();
    }

    public Power toPower() {
        return new Power(name, description);
    }
}
