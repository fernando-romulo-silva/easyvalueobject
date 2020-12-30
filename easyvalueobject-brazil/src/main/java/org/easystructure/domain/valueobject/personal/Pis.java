package org.easystructure.domain.valueobject.personal;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.easystructure.domain.valueobject.AbstractValueObject;

import com.google.common.base.Preconditions;

/**
 * Programa Integracao Social do Brasil.
 * 
 * @author Fernando Romulo da Silva
 */
public class Pis extends AbstractValueObject<String> {

    /**
     * Construtor padrao.
     * 
     * @param value
     *            valor do PIS
     */
    public Pis(final String value) {
        super(value);
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#valid(java.lang.Object)
     */
    @Override
    protected void validate(final String value) {

        Preconditions.checkArgument(!StringUtils.isBlank(value), "Pis Vazio");

        final String valueTemp = value.replaceAll("\\D+", "");

        if (valueTemp.length() != 11) {
            throw new IllegalArgumentException(MessageFormat.format("Pis {0} invalido.", value));
        }

        StringBuffer lsAux = null;

        final StringBuffer lsMultiplicador = new StringBuffer("3298765432");

        int liTotalizador = 0;
        int liResto = 0;
        int liMultiplicando = 0;
        int liMultiplicador = 0;

        int liDigito = 99;
        lsAux = new StringBuffer().append(valueTemp);

        for (int i = 0; i < 10; i++) {
            liMultiplicando = Integer.parseInt(lsAux.substring(i, i + 1));
            liMultiplicador = Integer.parseInt(lsMultiplicador.substring(i, i + 1));
            liTotalizador += liMultiplicando * liMultiplicador;
        }

        liResto = 11 - liTotalizador % 11;
        liResto = liResto == 10 || liResto == 11 ? 0 : liResto;
        liDigito = Integer.parseInt("" + lsAux.charAt(10));

        if (liResto != liDigito) {
            throw new IllegalArgumentException(MessageFormat.format("Pis {0} invalido.", value));
        }
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#toShow()
     */
    @Override
    public String toShow() {
        return format(value, "###.####.###-#");
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
