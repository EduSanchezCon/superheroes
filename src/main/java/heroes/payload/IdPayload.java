package heroes.payload;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class IdPayload implements Serializable {

    private final String id;

    public IdPayload(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof IdPayload) {

            IdPayload idPayload = (IdPayload) o;

            return new EqualsBuilder()
                    .append(id, idPayload.id)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }
}
