package heroes.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.UUID;

public class Power implements Serializable {

    private final String id;
    private final String name;
    private final String description;

    public Power(String name, String description) {
        this(UUID.randomUUID().toString(), name, description);
    }

    public Power(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Power) {

            Power power = (Power) o;

            return new EqualsBuilder()
                    .append(id, power.id)
                    .append(name, power.name)
                    .append(description, power.description)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(description)
                .toHashCode();
    }
}
