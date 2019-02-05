package heroes.test.util;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.apache.commons.lang3.reflect.TypeUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomGenerator {

	private static final int MIN_COLLECTION_SIZE = 2;
	private static final int MAX_COLLECTION_SIZE = 5;

	private volatile static EnhancedRandom defaultEnhancedRandom;

	private static EnhancedRandom getDefaultRandomizer() {

		if (defaultEnhancedRandom == null) {
			synchronized (RandomGenerator.class) {
				if (defaultEnhancedRandom == null) {
					defaultEnhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
							.collectionSizeRange(1, 5)
							.stringLengthRange(3, 30)
							.scanClasspathForConcreteTypes(true).build();
				}
			}
		}
		return defaultEnhancedRandom;

	}

	public static <T> T nextObject(Class<T> clazz, String... excludedFields) {
		return getDefaultRandomizer().nextObject(clazz, excludedFields);
	}

	@SuppressWarnings("unchecked")
	public static <T> T nextImmutableObject(Class<T> clazz, int... excludedPositions) {
		Constructor<?> constructor = Arrays.stream(clazz.getConstructors())
				.max(Comparator.comparing(Constructor::getParameterCount)).get();
		Type[] genericParameterTypes = constructor.getGenericParameterTypes();
		Object[] generatedParameters = Arrays.stream(genericParameterTypes).map(RandomGenerator::getRandomObject)
				.toArray();

		Arrays.stream(excludedPositions).forEach(i -> generatedParameters[i] = null);

		T generatedInstance = null;

		try {
			generatedInstance = (T) constructor.newInstance(generatedParameters);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return generatedInstance;
	}

	private static Object getRandomObject(Type type) {
		if (type instanceof ParameterizedType) {

			ParameterizedType pType = (ParameterizedType) type;
			Type rawType = pType.getRawType();
			Type parameterType = ((ParameterizedType) type).getActualTypeArguments()[0];

			if (TypeUtils.isAssignable(rawType, List.class)) {

				int size = new Random().nextInt((MAX_COLLECTION_SIZE - MIN_COLLECTION_SIZE) + 1) + MIN_COLLECTION_SIZE;
				return getDefaultRandomizer().objects((Class<?>) parameterType, size).collect(Collectors.toList());

			} else {
				return getDefaultRandomizer().nextObject((Class<?>) rawType);
			}
		}

		return getDefaultRandomizer().nextObject((Class<?>) type);
	}

	public static EnhancedRandomBuilder getBasicRandomBuilder() {
		return EnhancedRandomBuilder.aNewEnhancedRandomBuilder();
	}

}
