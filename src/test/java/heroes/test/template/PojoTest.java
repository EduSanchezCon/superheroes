package heroes.test.template;

import io.micronaut.core.reflect.ReflectionUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public abstract class PojoTest<T extends  Serializable> {

    protected T pojo;
    protected T equalsPojo;
    protected T otherPojo;


    public abstract void beforeTests();

    @Test
    public void shouldBeEquals() {
        assertThat(pojo, is(equalsPojo));
    }

    @Test
    public void shouldBeDistinct() {
        assertThat(pojo, is(not(otherPojo)));
    }

    @Test
    public void shouldProduceSameHashCode() {
        assertThat(pojo.hashCode(), is(equalsPojo.hashCode()));
    }

    @Test
    public void shouldProduceDistinctHashCode() {
        assertThat(pojo.hashCode(), is(not(otherPojo.hashCode())));
    }

    @Test
    public void equalsMethodCheckTheType() {
        assertFalse(pojo.equals("a string"));
    }

    @Test
    public void equalsMethodWithNullObject() {
        assertFalse(pojo == null);
    }

    @Test
    public void equalsMethodWithSameObject() {
        final T samePojo = pojo;
        assertTrue(pojo.equals(samePojo));
    }

    @Test
    public void shouldHaveMethodAccessors() throws IntrospectionException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        for (PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(pojo.getClass())
                .getPropertyDescriptors()) {
            assertNotNull(propertyDescriptor.getReadMethod().invoke(pojo));
        }
    }

    @Test
    public void shouldHaveAllPropertiesAccesibles() {

        try {
            for (PropertyDescriptor property : Introspector.getBeanInfo(pojo.getClass()).getPropertyDescriptors()) {
                if ("class".equals(property.getName())) continue;
                Optional<Field> field = ReflectionUtils.findField(pojo.getClass(), property.getName());
                if (field.isPresent()) {
                    Field f = field.get();
                    f.setAccessible(true);
                    assertThat(property.getReadMethod().invoke(pojo), Matchers.is(f.get(pojo)));
                }
            }
        } catch (Exception e) {
            fail();
        }
    }

    protected Class<?> getConcreteClass() {
        return (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected void changeFinalField(T object, String fieldName, Object newValue) throws NoSuchFieldException, IllegalAccessException {
        Field distinctField = getConcreteClass().getDeclaredField(fieldName);
        distinctField.setAccessible(true);
        distinctField.set(object, newValue);
    }

    protected T clonePojo(T original) {
        return SerializationUtils.clone(original);
        //Cloner cloner = new BasicCloner();
        //return cloner.clone(original);
    }

    protected void init() {
        // Overridable method to customize test initialization
    }
}
