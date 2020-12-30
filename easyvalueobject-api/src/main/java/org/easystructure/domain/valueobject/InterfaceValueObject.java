package org.easystructure.domain.valueobject;

/**
 * Interface para abstrair o value object.
 * 
 * @author Fernando Romulo da Silva
 * @param <T>
 *            Tipo do value
 */
public interface InterfaceValueObject<T> {

    /**
     * Mostra o objeto para usuarios finais.
     * 
     * @return Uma string formatada
     */
    String toShow();

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#toString()
     */
    @Override
    String toString();

    /**
     * Retorna o valor do value object.
     * 
     * @return O valor do objeto
     */
    T getValue();
}