package org.easystructure.domain.valueobject;

/**
 * Classe funcional para validacao de valores de value objects.
 * 
 * @author Fernando Romulo da Silva
 * @param <T>
 *            O tipo que vai ser validado
 */
public interface ValueObjectValidator<T> {

    /**
     * Valida o valor passado por parametro.
     * 
     * @param value
     *            O valor a ser validado
     */
    void validate(final T value);
}
