package heroes.test.template;

import heroes.test.util.RandomGenerator;
import io.micronaut.core.reflect.ReflectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.Assert.fail;


public abstract class MutablePojoRandomizedTest<T extends Serializable>  extends MutablePojoTest<T> {
	
	@SuppressWarnings("unchecked")
	@Override
	@Before
	public void beforeTests() {

		Class<?> clazz = getConcreteClass();
		pojo =  (T) RandomGenerator.nextObject(clazz);
		equalsPojo = (T) clonePojo( pojo );
		otherPojo =  (T) RandomGenerator.nextObject( clazz );
		init();
	}

}
