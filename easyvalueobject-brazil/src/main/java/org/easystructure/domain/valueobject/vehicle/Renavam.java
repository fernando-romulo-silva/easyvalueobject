package org.easystructure.domain.valueobject.vehicle;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.easystructure.domain.valueobject.AbstractValueObject;

import com.google.common.base.Preconditions;

/**
 * Codigo Renavam do Veiculo no Brasil.
 * 
 * @author Fernando Romulo da Silva
 */
public class Renavam extends AbstractValueObject<String> {

    /**
     * Construtor padrao.
     * 
     * @param value
     */
    public Renavam(final String value) {
        super(value);
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#valid(java.lang.Object)
     */
    @Override
    protected void validate(final String value) {

        Preconditions.checkArgument(!StringUtils.isBlank(value), "Renavam vazio.");

        final String valueTemp = value.replaceAll("\\D+", "");

        if (valueTemp.length() != 11) {
            throw new IllegalArgumentException(MessageFormat.format("Renavam {0} invalido.", value));
        }

        int calc = 0;
        final int digito = Integer.parseInt(value.substring(8, 9));

        for (int i = 0; i < value.length() - 1; i++) {
            calc += Integer.parseInt(value.substring(i, i + 1)) * (i + 2);
        }

        if (calc % 11 == digito || calc % 11 == 0) {
            throw new IllegalArgumentException(MessageFormat.format("Renavam {0} invalido.", value));
        }
    }

    @Override
    public String toShow() {
        return value;
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#handle(java.lang.Object)
     */
    @Override
    protected String handle(final String value) {
        return value.replaceAll("\\D+", "");
    }
}
