package org.easystructure.domain.valueobject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;

/**
 * Classe util para auxiliar as values objects.
 * 
 * @author Fernando Romulo da Silva
 */
public class ValueObjectUtil {

    /**
     * Cria um objeto Value a partir de um construtor.
     * 
     * @param <Value>
     *            O tipo do valor do value object.
     * @param <ValueObject>
     *            O tipo do value object.
     * @param clazzValueObject
     *            A classe value object
     * @param value
     *            O valor do objeto Value Object
     * @return Um objeto Value Object
     */
    public static <ValueObject extends AbstractValueObject<Value>, Value> ValueObject createValueObject(final Class<ValueObject> clazzValueObject, final Value value) {

        try {

            final Constructor<ValueObject> constructor = clazzValueObject.getDeclaredConstructor(value.getClass(), Class.class);
            constructor.setAccessible(true);

            return constructor.newInstance(value, ValueObjectUtil.class);

        } catch (final InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new IllegalStateException(MessageFormat.format("Not access to reflection on {0} class.", clazzValueObject), ex);
        } catch (final NoSuchMethodException | SecurityException ex) {
            throw new IllegalStateException(MessageFormat.format("Constructor {0}{{0}, Bollean}.", clazzValueObject, value.getClass(), value), ex);
        }
    }
}
