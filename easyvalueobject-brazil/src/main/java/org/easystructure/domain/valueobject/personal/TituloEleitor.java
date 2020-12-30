package org.easystructure.domain.valueobject.personal;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.easystructure.domain.valueobject.AbstractValueObject;
import org.easystructure.domain.valueobject.ValueObjectConst;

import com.google.common.base.Preconditions;

/**
 * Classe que representa o titulo eleitor de uma pessoa.
 * 
 * @author Fernando Romulo da Silva
 */
public class TituloEleitor extends AbstractValueObject<String> {

    private static final String TITULO_INVALID_MESSAGE = "Titulo Eleitor {0} invalido.";

    /**
     * Construtor padrao.
     * 
     * @param value
     *            O valor do titulo eleitor
     */
    public TituloEleitor(final String value) {
        super(value);
    }

    private String mid(final String texto, final int inicio, final int tamanho) {
        return texto.substring(inicio - 1, inicio + tamanho - 1);
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#valid(java.lang.Object)
     */
    @Override
    protected void validate(final String value) {

        Preconditions.checkArgument(!StringUtils.isBlank(value), "Titulo invalido.");

        String valueTemp = value.replaceAll("\\D+", "");

        if (valueTemp.length() < 12) { // Completar 12 dígitos
            valueTemp = "000000000000" + valueTemp;
            valueTemp = valueTemp.substring(valueTemp.length() - 12);
        } else if (valueTemp.length() > 12) {
            throw new IllegalArgumentException(MessageFormat.format(TITULO_INVALID_MESSAGE, value));
        }

        final int qDig = valueTemp.length(); // Total de caracteres

        // Gravar posição dos caracteres
        final int dig1 = Integer.parseInt(mid(valueTemp, qDig - 11, 1));
        final int dig2 = Integer.parseInt(mid(valueTemp, qDig - 10, 1));
        final int dig3 = Integer.parseInt(mid(valueTemp, qDig - 9, 1));
        final int dig4 = Integer.parseInt(mid(valueTemp, qDig - 8, 1));
        final int dig5 = Integer.parseInt(mid(valueTemp, qDig - 7, 1));
        final int dig6 = Integer.parseInt(mid(valueTemp, qDig - 6, 1));
        final int dig7 = Integer.parseInt(mid(valueTemp, qDig - 5, 1));
        final int dig8 = Integer.parseInt(mid(valueTemp, qDig - 4, 1));
        final int dig9 = Integer.parseInt(mid(valueTemp, qDig - 3, 1));
        final int dig10 = Integer.parseInt(mid(valueTemp, qDig - 2, 1));
        final int dig11 = Integer.parseInt(mid(valueTemp, qDig - 1, 1));
        final int dig12 = Integer.parseInt(mid(valueTemp, qDig, 1));

        // Cálculo para o primeiro dígito validador
        int dv1 = dig1 * 2 + dig2 * 3 + dig3 * 4 + dig4 * 5 + dig5 * 6 + dig6 * 7 + dig7 * 8 + dig8 * 9;
        dv1 = dv1 % 11;

        if (dv1 == 10) {
            dv1 = 0; // Se o resto for igual a 10, dv1 igual a zero
        }

        // Cálculo para o segundo dígito validador
        int dv2 = dig9 * 7 + dig10 * 8 + dv1 * 9;
        dv2 = dv2 % 11;

        if (dv2 == 10) {
            dv2 = 0; // Se o resto for igual a 10, dv1 igual a zero
        }

        // Validação dos dígitos validadores, após o cálculo realizado
        if (!(dig11 == dv1 && dig12 == dv2)) {
            throw new IllegalArgumentException(MessageFormat.format(TITULO_INVALID_MESSAGE, value));
        }
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#toShow()
     */
    @Override
    public String toShow() {
        return format(value, "####.####.####");
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#handle(java.lang.Object)
     */
    @Override
    protected String handle(final String value) {
        return value.replaceAll(ValueObjectConst.REGEX_NUMBER, StringUtils.EMPTY);
    }
}
