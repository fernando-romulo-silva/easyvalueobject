package org.easystructure.domain.valueobject.address;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.easystructure.domain.valueobject.AbstractValueObject;

import com.google.common.base.Preconditions;

/**
 * Codigo postal do Brasil.
 * 
 * @author Fernando Romulo da Silva
 */
public class Cep extends AbstractValueObject<String> {

    /**
     * Construtor padrao.
     * 
     * @param value
     *            O valor do cep
     */
    public Cep(final String value) {
        super(value);
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#valid(java.lang.Object)
     */
    @Override
    protected void validate(final String value) {
        Preconditions.checkArgument(!StringUtils.isBlank(value), "Cep vazio.");

        final String valueTemp = StringUtils.deleteWhitespace(value).replaceAll("\\D+", "");

        if (valueTemp.length() != 8) {
            throw new IllegalArgumentException(MessageFormat.format("Cep {0} invalido.", value));
        }
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#toShow()
     */
    @Override
    public String toShow() {
        return format(value, "#####-###");
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#handle(java.lang.Object)
     */
    @Override
    protected String handle(final String value) {
        return StringUtils.deleteWhitespace(value).replaceAll("\\D+", "");
    }
}
