package heroes.payload;

import heroes.model.Power;
import heroes.test.template.ImmutablePojoRandomizedTest;
import heroes.test.util.RandomGenerator;
import org.junit.Test;

import static org.junit.Assert.*;

public class PowerPayloadTest extends ImmutablePojoRandomizedTest<PowerPayload> {

    @Test
    public void shouldGenerateModelInstance(){

        PowerPayload powerPayload = RandomGenerator.nextImmutableObject(PowerPayload.class);

        Power power = powerPayload.toPower();

        assertEquals(powerPayload.getName(), power.getName());
        assertEquals(powerPayload.getDescription(), power.getDescription());

    }

    @Test
    public void shouldHaveAConstructorWithModelParameter(){

        Power power = RandomGenerator.nextImmutableObject(Power.class);
        PowerPayload powerPayload = new PowerPayload(power);

        assertEquals(powerPayload.getName(), power.getName());
        assertEquals(powerPayload.getDescription(), power.getDescription());

    }
}