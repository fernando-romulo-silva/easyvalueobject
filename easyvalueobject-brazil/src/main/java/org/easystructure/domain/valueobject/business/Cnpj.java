package org.easystructure.domain.valueobject.business;

import java.text.MessageFormat;
import java.util.InputMismatchException;

import org.apache.commons.lang3.StringUtils;
import org.easystructure.domain.valueobject.AbstractValueObject;

import com.google.common.base.Preconditions;

/**
 * Classe que representa um Cnpj. Apenas os valores sem as mascaras de Cnpj.
 * 
 * @author Fernando Romulo da Silva
 */
public class Cnpj extends AbstractValueObject<String> {

    private static final String CNPJ_INVALID_MESSAGE = "CNPJ {0} invalido.";

    /**
     * Construtor padrao.
     * 
     * @param value
     */
    public Cnpj(final String value) {
        super(value);
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#valid(java.lang.Object)
     */
    @Override
    protected void validate(final String value) {
        Preconditions.checkArgument(!StringUtils.isBlank(value), "Cnpj vazio.");

        final String valueTemp = value.replaceAll("\\D+", "");

        if (valueTemp.equals("00000000000000") || valueTemp.equals("11111111111111") || valueTemp.equals("22222222222222") || //
                valueTemp.equals("33333333333333") || valueTemp.equals("44444444444444") || valueTemp.equals("55555555555555") || //
                valueTemp.equals("66666666666666") || valueTemp.equals("77777777777777") || valueTemp.equals("88888888888888") || // 
                valueTemp.equals("99999999999999") || valueTemp.length() != 14) {
            throw new IllegalArgumentException(MessageFormat.format(CNPJ_INVALID_MESSAGE, value));
        }

        try {
            int soma = 0, dig;

            String cnpjCalc = valueTemp.substring(0, 12);

            final char[] chrCnpj = valueTemp.toCharArray();
            /* Primeira parte */
            for (int i = 0; i < 4; i++) {
                if (chrCnpj[i] - 48 >= 0 && chrCnpj[i] - 48 <= 9) {
                    soma += (chrCnpj[i] - 48) * (6 - (i + 1));
                }
            }
            for (int i = 0; i < 8; i++) {
                if (chrCnpj[i + 4] - 48 >= 0 && chrCnpj[i + 4] - 48 <= 9) {
                    soma += (chrCnpj[i + 4] - 48) * (10 - (i + 1));
                }
            }
            dig = 11 - soma % 11;
            cnpjCalc += dig == 10 || dig == 11 ? "0" : Integer.toString(dig);
            /* Segunda parte */
            soma = 0;
            for (int i = 0; i < 5; i++) {
                if (chrCnpj[i] - 48 >= 0 && chrCnpj[i] - 48 <= 9) {
                    soma += (chrCnpj[i] - 48) * (7 - (i + 1));
                }
            }
            for (int i = 0; i < 8; i++) {
                if (chrCnpj[i + 5] - 48 >= 0 && chrCnpj[i + 5] - 48 <= 9) {
                    soma += (chrCnpj[i + 5] - 48) * (10 - (i + 1));
                }
            }
            dig = 11 - soma % 11;
            cnpjCalc += dig == 10 || dig == 11 ? "0" : Integer.toString(dig);

            if (!cnpjCalc.equals(valueTemp)) {
                throw new IllegalArgumentException(MessageFormat.format(CNPJ_INVALID_MESSAGE, value));
            }
        } catch (final InputMismatchException ex) {
            throw new IllegalArgumentException(MessageFormat.format(CNPJ_INVALID_MESSAGE, value));
        }
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#toShow()
     */
    @Override
    public String toShow() {
        return format(value, "##.###.###/####-##");
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
