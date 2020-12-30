package org.easystructure.domain.valueobject.personal;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.easystructure.domain.valueobject.AbstractValueObject;

import com.google.common.base.Preconditions;

/**
 * Classe que representa a Carterira Nacional de Habilitacao, valida tb a antiga PGU.
 * 
 * @author Fernando Romulo da Silva
 */
public class Cnh extends AbstractValueObject<String> {

    private static final String CNH_INVALIDA = "Cnh {0} invalida.";

    /**
     * Construtor padrao da classe.
     * 
     * @param value
     */
    public Cnh(final String value) {
        super(value);
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#handle(java.lang.Object)
     */
    @Override
    protected String handle(final String value) {
        return value.replaceAll("\\D+", "");
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#toShow()
     */
    @Override
    public String toShow() {
        return value;
        //        if (value.length() == 9) {
        //            return format(value, "####-#####");
        //        } else {
        //            return format(value, "#####-######");
        //        }
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#valid(java.lang.Object)
     */
    @Override
    protected void validate(final String value) {

        Preconditions.checkArgument(!StringUtils.isBlank(value), "CNH vazia.");

        final String valueNumber = value.replaceAll("\\D+", "");

        if (valueNumber.length() != 11) {
            throw new IllegalArgumentException(MessageFormat.format(CNH_INVALIDA, value));
        }

        if (value.equals("11111111111") || value.equals("22222222222") || value.equals("33333333333") || value.equals("44444444444") || // 
                value.equals("55555555555") || value.equals("66666666666") || value.equals("77777777777") || value.equals("88888888888") || // 
                value.equals("99999999999") || value.equals("00000000000")) {
            throw new IllegalArgumentException(MessageFormat.format(CNH_INVALIDA, value));
        }

        final String valueTemp = valueNumber.substring(0, 9); //Copy(nCNH,1,9); 

        final String digForn = valueNumber.substring(9, 11);

        int incrDig2 = 0;

        int dig1 = 0;

        // calcula o digito 1 (10º caractere) 
        int soma = 0;

        for (int j = 8; j >= 0; j--) {
            soma = soma + Integer.parseInt(valueTemp.substring(j, j + 1)) * (10 - (j + 1));
            dig1 = soma % 11;

            if (dig1 >= 10) {
                incrDig2 = -2; // Aqui está o segredo
                dig1 = 0;
            }
        }

        // calcula o digito 2 (11º caractere) 
        soma = 0;

        for (int j = 8; j >= 0; j--) {
            soma = soma + Integer.parseInt(valueTemp.substring(j, j + 1)) * (j + 1); // Observem que aqui os multiplicadores estão invertidos 
        }

        int dig2 = soma % 11;

        if (dig2 >= 10) {
            dig2 = 0;
        }

        dig2 = Math.abs(dig2 + incrDig2);

        final String digEnc = Integer.toString(dig1) + Integer.toString(dig2);

        if (!digForn.equals(digEnc)) {
            throw new IllegalArgumentException(MessageFormat.format(CNH_INVALIDA, value));
        }
    }
}
