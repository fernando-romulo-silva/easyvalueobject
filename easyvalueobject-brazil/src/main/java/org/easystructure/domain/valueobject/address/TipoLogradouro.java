package org.easystructure.domain.valueobject.address;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.easystructure.domain.valueobject.InterfaceValueObject;

/**
 * Tipos de logradouros no Brasil.
 * 
 * @author Fernando Romulo da Silva
 */
public enum TipoLogradouro implements InterfaceValueObject<String> {

    /**
     * Acesso.
     */
    ACESSO(1, "Acesso", "Ac"),

    /**
     * Aeroporto.
     */
    AEROPORTO(2, "Aeroporto", "Aer."),

    /**
     * Alameda.
     */
    ALAMEDA(3, "Alameda", "Al."),

    /**
     * Àrea.
     */
    AREA(4, "Àrea", "Ar."),

    /**
     * Avenida.
     */
    AVENIDA(5, "Avenida", "Av."),

    /**
     * Campo.
     */
    CAMPO(6, "Campo", "Campo"),

    /**
     * Chacara.
     */
    CHACARA(7, "Chacara", "Chac."),

    /**
     * Colonia.
     */
    COLONIA(8, "Colonia", "Col."),

    /**
     * Comunidade.
     */
    COMUNIDADE(9, "Comunidade", "Com."),

    /**
     * Condomínio.
     */
    CONDOMINIO(10, "Condomínio", "Cond."),

    /**
     * Complexo Viário.
     */
    COMPLEXO_VIARIO(11, "Complexo Viário", "Comp. Via."),

    /**
     * Conjunto.
     */
    CONJUNTO(12, "Conjunto", "Conj."),

    /**
     * Distrito.
     */
    DISTRITO(13, "Distrito", "Dist."),

    /**
     * Explanada.
     */
    ESPLANADA(14, "Explanada", "Exp."),

    /**
     * Estação.
     */
    ESTACAO(15, "Estação", "Est."),

    /**
     * Estrada.
     */
    ESTRADA(16, "Estrada", "Estr."),

    /**
     * Favela.
     */
    FAVELA(17, "Favela", "Fav."),

    /**
     * Fazenda.
     */
    FAZENDA(18, "Fazenda", "Faz."),

    /**
     * Feira.
     */
    FEIRA(19, "Feira", "Fei."),

    /**
     * Jardim.
     */
    JARDIM(20, "Jardim", "Jd."),

    /**
     * Ladeira.
     */
    LADEIRA(21, "Ladeira", "Lad."),

    /**
     * Lago.
     */
    LAGO(22, "Lago", "Lg."),

    /**
     * Lagoa.
     */
    LAGOA(23, "Lagoa", "Lga."),

    /**
     * Largo.
     */
    LARGO(24, "Largo", "Largo"),

    /**
     * Loteamento.
     */
    LOTEAMENTO(24, "Loteamento", "Lot."),

    /**
     * Marginal.
     */
    MARGINAL(24, "Marginal", "Marg."),

    /**
     * Morro.
     */
    MORRO(25, "Morro", "Mor."),

    /**
     * Nucleo.
     */
    NUCLEO(26, "Núcleo", "Nuc."),

    PARQUE(27, "Parque", "Pq."),

    PASSAGEM(33, "Passagem", "Pas."),

    PASSARELA(28, "Passarela", ""),

    PATIO(29, "Pátio", ""),

    PEDAGIO(34, "Pedágio", "Ped."),

    PRACA(30, "Praça", ""),

    PONTE(31, "Ponte", ""),

    PORTO(34, "Porto", ""),

    QUADRA(31, "Quadra", ""),

    RECANTO(32, "Recanto", ""),

    RESIDENCIAL(33, "Residencial", ""),

    RODOVIA(34, "Rodovia", ""),

    RUA(35, "Rua", "R."),

    SETOR(36, "Setor", ""),

    SITIO(37, "Sítio", ""),

    TRAVESSA(38, "Travessa", ""),

    TERMINAL(34, "Terminal", ""),

    TRECHO(39, "Trecho", ""),

    TREVO(40, "Trevo", ""),

    VALE(41, "Vale", ""),

    VEREDA(42, "Vereda", ""),

    VIA(43, "Via", ""),

    VIADUTO(44, "Viaduto", ""),

    VIELA(45, "Viela", ""),

    VILA(46, "Vila", "V.");

    private final int id;

    private final String value;

    private final String abb;

    private TipoLogradouro(final int id, final String value, final String abb) {
        this.id = id;
        this.value = value;
        this.abb = abb;
    }

    public int getId() {
        return id;
    }

    public String getAbb() {
        return abb;
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
     * @see org.easystructure.domain.valueobject.InterfaceValueObject#toShow()
     */
    @Override
    public String toShow() {
        return value;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE) //
                .append("id", getId()) //
                .append("value", toShow()) //
                .append("abb", abb) //
                .toString();
    }

    public static List<String> getTipoLogradouros() {
        final List<String> result = new ArrayList<String>();

        for (final TipoLogradouro dir : TipoLogradouro.values()) {
            result.add(dir.getValue());
        }

        return result;
    }
}
