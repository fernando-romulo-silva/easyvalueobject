package org.easystructure.domain.valueobject.personal;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.easystructure.domain.valueobject.AbstractValueObject;

import com.google.common.base.Preconditions;

// http://www.guj.com.br/t/resolvido-algoritmo-inscricao-estadual/139155/5

/**
 * Classe que representa o RG do Brasil.
 * 
 * @author Fernando Romulo da Silva
 */
public class RegistroGeral extends AbstractValueObject<String> {

    /**
     * Construtor padrao.
     * 
     * @param value
     *            O valor do RG
     */
    public RegistroGeral(final String value) {
        super(value);
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#valid(java.lang.Object)
     */
    @Override
    protected void validate(final String value) {

        Preconditions.checkArgument(!StringUtils.isBlank(value), "RG em branco");

        final String valueTemp = value.replaceAll("\\D+", "");

        Preconditions.checkArgument(!StringUtils.isBlank(valueTemp), MessageFormat.format("RG {0} invalido", value));

        final int tamanho = valueTemp.length();

        final int[] vetor = new int[tamanho];

        for (int i = 0; i < tamanho; i++) {
            vetor[i] = Character.getNumericValue(valueTemp.charAt(i)) * (i + 2);
        }

        if (tamanho >= 9) {
            vetor[8] = Character.getNumericValue(valueTemp.charAt(8)) * 100;
        }

        int total = 0;

        for (int i = 0; i < tamanho; i++) {
            total += vetor[i];
        }

        final int resto = total % 11;

        if (resto != 0) {
            throw new IllegalArgumentException(MessageFormat.format("Rg invalido!", value));
        }
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#toShow()
     */
    @Override
    public String toShow() {
        final String valueTemp = value.replaceAll("\\D+", "");

        if (valueTemp.length() == 7) {
            return format(value, "#.###.###");
        }

        if (valueTemp.length() == 8) {
            return format(value, "#.###.###-##");
        }

        if (valueTemp.length() == 9) {
            return format(value, "##.###.###-#");
        } else {
            return format(value, "##.###.###-##");
        }
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
