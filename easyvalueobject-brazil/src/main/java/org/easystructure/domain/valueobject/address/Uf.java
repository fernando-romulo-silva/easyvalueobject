package org.easystructure.domain.valueobject.address;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.easystructure.domain.valueobject.InterfaceValueObject;

/**
 * Unidades Federativas do Brasil. Estados do Brasil.
 * 
 * @author Fernando Romulo da Silva
 */
public enum Uf implements InterfaceValueObject<String> {

    /**
     * Acre.
     */
    AC(12, "Acre", RegiaoUf.NORTE),

    /**
     * Alagoas.
     */
    AL(27, "Alagoas", RegiaoUf.NORDESTE),

    /**
     * Amapá.
     */
    AP(16, "Amapá", RegiaoUf.NORTE),

    /**
     * Amazonas.
     */
    AM(13, "Amazonas", RegiaoUf.NORTE),

    /**
     * Bahia.
     */
    BA(29, "Bahia", RegiaoUf.NORDESTE),

    /**
     * Ceará.
     */
    CE(23, "Ceará", RegiaoUf.NORDESTE),

    /**
     * Distrito Federal.
     */
    DF(53, "Distrito Federal", RegiaoUf.CENTRO_OESTE),

    /**
     * Espírito Santo.
     */
    ES(32, "Espírito Santo", RegiaoUf.SUDESTE),

    /**
     * Goiás.
     */
    GO(52, "Goiás", RegiaoUf.CENTRO_OESTE),

    /**
     * Maranhão.
     */
    MA(21, "Maranhão", RegiaoUf.NORDESTE),

    /**
     * Mato Grosso.
     */
    MT(51, "Mato Grosso", RegiaoUf.CENTRO_OESTE),

    /**
     * Mato Grosso do Sul.
     */
    MS(50, "Mato Grosso do Sul", RegiaoUf.CENTRO_OESTE),

    /**
     * Minas Gerais.
     */
    MG(31, "Minas Gerais", RegiaoUf.SUDESTE),

    /**
     * Pará.
     */
    PA(15, "Pará", RegiaoUf.NORTE),

    /**
     * Paraíba.
     */
    PB(25, "Paraíba", RegiaoUf.NORDESTE),

    /**
     * Paraná.
     */
    PR(41, "Paraná", RegiaoUf.SUL),

    /**
     * Pernambuco.
     */
    PE(26, "Pernambuco", RegiaoUf.NORDESTE),

    /**
     * Piauí.
     */
    PI(22, "Piauí", RegiaoUf.NORDESTE),

    /**
     * Rio de Janeiro.
     */
    RJ(33, "Rio de Janeiro", RegiaoUf.SUDESTE),

    /**
     * Rio Grande do Norte.
     */
    RN(24, "Rio Grande do Norte", RegiaoUf.NORDESTE),

    /**
     * Rio Grande do Sul.
     */
    RS(43, "Rio Grande do Sul", RegiaoUf.SUL),

    /**
     * Rondônia.
     */
    RO(11, "Rondônia", RegiaoUf.NORTE),

    /**
     * Roraima.
     */
    RR(14, "Roraima", RegiaoUf.NORTE),

    /**
     * Santa Catarina.
     */
    SC(42, "Santa Catarina", RegiaoUf.SUL),

    /**
     * São Paulo.
     */
    SP(35, "São Paulo", RegiaoUf.SUDESTE),

    /**
     * Sergipe.
     */
    SE(28, "Sergipe", RegiaoUf.NORDESTE),

    /**
     * Tocantins.
     */
    TO(17, "Tocantins", RegiaoUf.NORTE);

    private final int idIbge;

    private final String nome;

    private final RegiaoUf regiao;

    private Uf(final int idIbge, final String nome, final RegiaoUf regiao) {
        this.idIbge = idIbge;
        this.nome = nome;
        this.regiao = regiao;
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.InterfaceValueObject#getValue()
     */
    @Override
    public String getValue() {
        return this.name();
    }

    @Override
    public String toShow() {
        return this.name();
    }

    public int getIdIbge() {
        return idIbge;
    }

    public RegiaoUf getRegiao() {
        return regiao;
    }

    public String getNome() {
        return nome;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE) //
                .append("idIbge", idIbge) //
                .append("value", this.name()) //
                .append("nome", nome) //
                .toString();
    }

    /**
     * Retorna todas Ufs permitidos.
     * 
     * @return Uma lista com todas Ufs permitidos.
     */
    public static List<String> getUfs() {
        final List<String> result = new ArrayList<String>();

        for (final Uf dir : Uf.values()) {
            result.add(dir.getValue());
        }

        return result;
    }
}
