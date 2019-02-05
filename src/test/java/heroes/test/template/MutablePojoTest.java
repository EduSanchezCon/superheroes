package heroes.test.template;

import io.micronaut.core.reflect.ReflectionUtils;
import org.junit.Test;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.Assert.fail;

public abstract class MutablePojoTest<T extends Serializable> extends PojoTest<T> {

    @Test
    public void shouldHaveAllPropertiesAccesiblesToSet() {

        try {
            for (PropertyDescriptor property : Introspector.getBeanInfo( getConcreteClass() ).getPropertyDescriptors()){
                Optional<Field> field = ReflectionUtils.findField( getConcreteClass(), property.getName());
                Method writeMethod = property.getWriteMethod();
                field.ifPresent(f -> {

                });
                if (writeMethod != null && field.isPresent()){
                    Field f = field.get();
                    f.setAccessible( true );
                    writeMethod.invoke( pojo, f.get( otherPojo ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
