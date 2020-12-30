package org.easystructure.domain.valueobject.business;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.easystructure.domain.valueobject.AbstractValueObject;
import org.easystructure.domain.valueobject.ValueObjectConst;
import org.easystructure.domain.valueobject.address.Uf;

/**
 * Registro do contribuinte no cadastro do ICMS mantido pela Receita Estadual. E' necessario adicionar a Uf da inscricao estadual.
 * 
 * @author Fernando Romulo da Silva
 */
public class InscricaoEstadual extends AbstractValueObject<String> {

    /**
     * Estado da inscricao estadual.
     */
    protected final Uf uf;

    /**
     * Construtor padrao.
     * 
     * @param uf
     *            O estado da Inscricao estadual
     * @param value
     *            O valor da Inscricao estadual
     */
    public InscricaoEstadual(final Uf uf, final String value) {
        super(new InscricaoEstadualValidator(uf), value);
        this.uf = uf;
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#handle(java.lang.Object)
     */
    @Override
    protected String handle(final String value) {
        return value.replaceAll(ValueObjectConst.REGEX_NUMBER, StringUtils.EMPTY);
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.InterfaceValueObject#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE) //
            .append("uf", uf.getValue()) //
            .append("value", toShow()) //
            .toString();
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#toShow()
     */
    @Override
    public String toShow() {

        switch (uf) {
        case AC:
            return format(value, "##.###.###/###-##");
        case AL:
            return format(value, "#########");
        case AP:
            return format(value, "#########");
        case AM:
            return format(value, "##.###.###-#");
        case BA:
            return format(value, "######-##");
        case CE:
            return format(value, "########-#");
        case DF:
            return format(value, "###########-##");
        case ES:
            return format(value, "########-#");
        case GO:
            return format(value, "##.###.###-#");
        case MA:
            return format(value, "########-#");
        case MT:
            return format(value, "##########-#");
        case MS:
            return format(value, "########-#");
        case MG:
            return format(value, "###.###.###/####");
        case PA:
            return format(value, "##-######-#");
        case PB:
            return format(value, "########-#");
        case PR:
            return format(value, "###.#####-##");
        case PE:
            if (value.length() == 9) {
                return format(value, "#######-##");
            } else {
                return format(value, "##.#.###.#######-#");
            }
        case PI:
            return format(value, "########-#");

        case RJ:
            return format(value, "##.###.##-#");

        case RN:
            return format(value, "##.###.###-#");

        case RS:
            return format(value, "###/#######");

        case RO:
            return format(value, "#############-#");

        case RR:
            return format(value, "########-#");

        case SP:
            return format(value, "###.###.###.###");

        case SC:
            return format(value, "###.###.###");
        case SE:
            return format(value, "########-#");
        case TO:
            return format(value, "##########-#");

        default:
            throw new IllegalArgumentException("");
        }
    }

    public Uf getUf() {
        return uf;
    }
}