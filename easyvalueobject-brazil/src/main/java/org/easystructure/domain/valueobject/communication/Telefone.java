package org.easystructure.domain.valueobject.communication;

import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.easystructure.domain.valueobject.AbstractValueObject;

import com.google.common.base.Preconditions;

/**
 * Classe que representa o valor de um Telefone. Esse telefone deve possuir o DDD.
 * <p>
 * Sempre sera (XX)YYYY-YYYY. Onde XX o DDD e Y+ os numeros.
 * </p>
 * A Qtde de numeros e' livre, pois ha' regioes com mais digitos q outras.
 * 
 * @author Fernando Romulo da Silva
 */
public class Telefone extends AbstractValueObject<String> {

    /**
     * Construtor padrao.
     * 
     * @param value
     *            valor do Telefone
     */
    public Telefone(final String value) {
        super(value);
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#valid(java.lang.Object)
     */
    @Override
    protected void validate(final String value) {

        Preconditions.checkArgument(!StringUtils.isBlank(value), "Telefone vazio.");

        final String valueTemp = value.replaceAll("\\D+", "");

        Preconditions.checkArgument(!StringUtils.isBlank(valueTemp), MessageFormat.format("Telefone {0} sem numeros.", value));

        final String valueDdd = valueTemp.substring(0, 2);

        final List<String> dds = Ddd.getDdds();

        Preconditions.checkArgument(dds.contains(valueDdd), MessageFormat.format("Telefone {0} com DDD invalido.", value));
    }

    public Ddd getDdd() {
        final String dddString = value.substring(0, 2);
        return Ddd.getDdd(dddString);
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#toShow()
     */
    @Override
    public String toShow() {

        if (value.length() == 10) {
            return format(value, "(##)####-####");
        }

        if (value.length() == 11) {
            return format(value, "(##)#####-####");
        }

        if (value.length() == 12) {
            return format(value, "(##)#####-#####");
        }

        return format(value, "(##)####-#####");
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
