package org.easystructure.domain.valueobject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * Classe abstrata de objetos value objeto. O atributo value nao pode ser null, nao faz sentido um Value Object vazio.
 * 
 * @author Fernando Romulo da Silva
 * @param <T>
 *            O tipo do valor de objeto
 */
public abstract class AbstractValueObject<T> implements InterfaceValueObject<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractValueObject.class);

    /**
     * O valor do objeto.
     */
    protected final T value;

    /**
     * Construtor padrao. Vai excutar o metodo {@link #validate(T)} e o metodo {@link #handle(T)}.
     * 
     * @param value
     *            O valor do objeto
     */
    protected AbstractValueObject(final T value) {
        super();
        checkValueInstance(value);

        validate(value);
        this.value = handle(value);
    }

    /**
     * Construtor para executar validacao numa classe funcional. Vai executar o metodo {@link org.easystructure.domain.valueobject.ValueObjectValidator#validate(T)} e o {@link #handle(T)}.
     * 
     * @param validator
     *            Objeto funcional de validacao
     * @param value
     *            o valor do objeto
     */
    protected AbstractValueObject(final ValueObjectValidator<T> validator, final T value) {
        super();
        checkValueInstance(value);

        Preconditions.checkArgument(validator != null, "Validador instance cannot be null.");

        validator.validate(value);
        this.value = handle(value);
    }

    /**
     * Construtor para executar validacao numa classe funcional. Vai executar o metodo {@link #validate(T)} e o {@link org.easystructure.domain.valueobject.ValueObjectHandler#handle(T)}.
     * 
     * @param handler
     *            Objeto funcional de manipulacao
     * @param value
     *            o valor do objeto
     */
    protected AbstractValueObject(final ValueObjectHandler<T> handler, final T value) {
        super();
        checkValueInstance(value);
        Preconditions.checkArgument(handler != null, "Handler instance cannot be null.");

        validate(value);
        this.value = handler.handle(value);
    }

    /**
     * Construtor para executar validacao numa classe funcional. Vai executar o metodo {@link org.easystructure.domain.valueobject.ValueObjectValidator#validate(T)} e o {@link org.easystructure.domain.valueobject.ValueObjectHandler#handle(T)}.
     * 
     * @param handler
     *            Objeto funcional de manipulacao
     * @param validator
     *            Objeto funcional de validacao
     * @param value
     *            o valor do objeto
     */
    protected AbstractValueObject(final ValueObjectHandler<T> handler, final ValueObjectValidator<T> validator, final T value) {
        super();
        checkValueInstance(value);

        Preconditions.checkArgument(handler != null, "Handler instance cannot be null.");
        Preconditions.checkArgument(validator != null, "Validator instance cannot be null.");

        validator.validate(value);
        this.value = handler.handle(value);
    }

    /**
     * Construtor sem executar a validacao e tratamento de valor, em casos de a origem do dados for de confianca. Para melhor desempenho.
     * 
     * @param value
     *            O valor do objeto
     * @param source
     *            A classe de origem para log, se for necessario
     */
    protected AbstractValueObject(final T value, final Class<?> source) {
        super();
        this.value = value;
        LOGGER.info("Class source {}, Value Object Class {}.", source, this.getClass());
    }
    
    private void checkValueInstance(T value) {
        Preconditions.checkArgument(value != null, "Value cannot be null.");
    }

    @Override
    public final T getValue() {
        return value;
    }

    /**
     * Metodo que trata o valor do objeto.
     * 
     * @param newValue
     *            O valor novo
     * @return O valor tratado
     */
    protected T handle(final T newValue) {
        return newValue;
    }

    /**
     * Formatar o valor do objeto.
     * 
     * @param valueString
     *            O valor do objeto em string
     * @param mask
     *            A mascara para formatar o valor
     * @return Uma string com o valor formatado
     */
    protected String format(final String valueString, final String mask) {

        if (StringUtils.isBlank(mask)) {
            return valueString;
        }

        final StringBuilder data = new StringBuilder("");

        char charTest = ' ';

        for (int i = 0; i < valueString.length(); i++) {
            charTest = valueString.charAt(i);
            if (Character.isDigit(charTest)) {
                data.append(charTest);
            }
        }

        int indexMask = mask.length();
        int indexField = data.length();

        while (indexField >= 0 && indexMask > 0) {
            if (mask.charAt(--indexMask) == '#') {
                indexField--;
            }
        }

        final StringBuilder out = new StringBuilder("");

        while (indexMask < mask.length()) {
            out.append(mask.charAt(indexMask) == '#' ? data.charAt(indexField++) : mask.charAt(indexMask));
            indexMask++;
        }

        return out.toString();
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.InterfaceValueObject#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE) //
            .append("value", toShow()) //
            .toString();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }

        @SuppressWarnings("rawtypes")
        final AbstractValueObject other = (AbstractValueObject) obj;

        return new EqualsBuilder() //
            .append(this.value, other.getValue()) //
            .isEquals();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder() //
            .append(this.value) //
            .hashCode();
    }

    /**
     * Valida o valor do objeto, se nao for valido sera lancado uma <code>IllegalArgumentException</code>.
     * 
     * @param newValue
     *            O valor que vai ser validado
     */
    protected void validate(final T newValue) {
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.InterfaceValueObject#toShow()
     */
    @Override
    public abstract String toShow();
}
