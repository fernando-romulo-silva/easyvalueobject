package org.easystructure.domain.valueobject.address;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.easystructure.domain.valueobject.InterfaceValueObject;

/**
 * Regioes das Unidades Federativas do Brasil.
 * 
 * @author Fernando Romulo da Silva
 */
public enum RegiaoUf implements InterfaceValueObject<String> {

    /**
     * Norte.
     */
    NORTE(1, "Norte"),

    /**
     * Nordeste.
     */
    NORDESTE(2, "Nordeste"),

    /**
     * Centro Oeste.
     */
    CENTRO_OESTE(3, "Centro Oeste"),

    /**
     * Sul.
     */
    SUL(4, "Sul"),

    /**
     * Sudeste.
     */
    SUDESTE(5, "Sudeste");

    private final int id;

    private final String nome;

    private RegiaoUf(final int idIbge, final String nome) {
        this.id = idIbge;
        this.nome = nome;
    }

    @Override
    public String toShow() {
        return nome;
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.InterfaceValueObject#getValue()
     */
    @Override
    public String getValue() {
        return this.name();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE) //
                .append("id", id) //
                .append("value", toShow()) //
                .append("nome", nome) //
                .toString();
    }
}
