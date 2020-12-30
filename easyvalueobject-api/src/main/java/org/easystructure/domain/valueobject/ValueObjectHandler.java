package org.easystructure.domain.valueobject;

/**
 * Classe funcional para manipulacao de valores de value objects.
 * 
 * @author Fernando Romulo da Silva
 * @param <T>
 *            O tipo que vai ser manipulado
 */
public interface ValueObjectHandler<T> {

    /**
     * Valida o valor passado por parametro.
     * 
     * @param value
     *            O valor a ser validado
     */
    T handle(final T value);
}
