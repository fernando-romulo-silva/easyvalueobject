package org.easystructure.domain.valueobject.personal;

import java.text.MessageFormat;
import java.util.InputMismatchException;

import org.apache.commons.lang3.StringUtils;
import org.easystructure.domain.valueobject.AbstractValueObject;

import com.google.common.base.Preconditions;

/**
 * Classe que representa um CPF. Apenas os valores sem as mascaras de CPF.
 * 
 * @author Fernando Romulo da Silva
 */
public final class Cpf extends AbstractValueObject<String> {

    private static final String CPF_INVALID_MESSAGE = "CPF {0} invalido.";

    /**
     * Construtor padrao.
     * 
     * @param value
     *            O valor do CPF
     */
    public Cpf(final String value) {
        super(value);
    }

    /**
     * Construtor sem validacao.
     * 
     * @param value
     *            O valor do CPF
     * @param source
     *            A origem do valor do cpf
     */
    Cpf(final String value, final Class<?> source) {
        super(value, source);
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#valid(java.lang.Object)
     */
    @Override
    protected void validate(final String value) {
        Preconditions.checkArgument(!StringUtils.isBlank(value), "Cpf vazio.");

        final String valueTemp = value.replaceAll("\\D+", "");

        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (valueTemp.equals("00000000000") || valueTemp.equals("11111111111") || valueTemp.equals("22222222222") || valueTemp.equals("33333333333") || //
                valueTemp.equals("44444444444") || valueTemp.equals("55555555555") || valueTemp.equals("66666666666") || valueTemp.equals("77777777777") || //
                valueTemp.equals("88888888888") || valueTemp.equals("99999999999") || valueTemp.length() != 11) { //

            throw new IllegalArgumentException(MessageFormat.format(CPF_INVALID_MESSAGE, value));
        }

        char dig10, dig11;

        int sm, i, r, num, peso;

        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;

            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = valueTemp.charAt(i) - 48;
                sm = sm + num * peso;
                peso = peso - 1;
            }

            r = 11 - sm % 11;
            if (r == 10 || r == 11) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico
            }

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;

            for (i = 0; i < 10; i++) {
                num = valueTemp.charAt(i) - 48;
                sm = sm + num * peso;
                peso = peso - 1;
            }

            r = 11 - sm % 11;

            if (r == 10 || r == 11) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if (dig10 == valueTemp.charAt(9) && dig11 == valueTemp.charAt(10)) {
                return;
            } else {
                throw new IllegalArgumentException(MessageFormat.format(CPF_INVALID_MESSAGE, value));
            }
        } catch (final InputMismatchException ex) {
            throw new IllegalArgumentException(MessageFormat.format(CPF_INVALID_MESSAGE, value));
        }
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#toShow()
     */
    @Override
    public String toShow() {
        return format(value, "###.###.###-##");
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
