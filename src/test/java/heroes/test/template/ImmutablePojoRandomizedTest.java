package heroes.test.template;

import heroes.test.util.RandomGenerator;
import org.junit.Before;

import java.io.Serializable;


public abstract class ImmutablePojoRandomizedTest<T extends Serializable> extends PojoTest<T> {

    @Before
    public void beforeTests() {

        Class<?> clazz = getConcreteClass();
        pojo = (T) RandomGenerator.nextImmutableObject(clazz);
        equalsPojo = (T) clonePojo(pojo);
        otherPojo = (T) RandomGenerator.nextImmutableObject(clazz);
        init();
    }
}
