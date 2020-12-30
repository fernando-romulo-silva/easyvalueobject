package org.easystructure.domain.valueobject.communication;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.easystructure.domain.valueobject.InterfaceValueObject;
import org.easystructure.domain.valueobject.address.Uf;

/**
 * Codigo de regiao do telefone.
 * 
 * @author Fernando Romulo da Silva
 */
public enum Ddd implements InterfaceValueObject<String> {

    Ddd11("11", Uf.SP, "São Paulo/Região Metropolitana"), //
    Ddd12("12", Uf.SP, "São José dos Campos e Vale do Paraíba"), //
    Ddd13("13", Uf.SP, "Santos/Baixada Santista/Vale do Ribeira"), //
    Ddd14("14", Uf.SP, "Bauru/Marília/Jaú/Botucatu"), //
    Ddd15("15", Uf.SP, "Sorocaba e Itapetininga"), //
    Ddd16("16", Uf.SP, "Ribeirão Preto/Araraquara/São Carlos"), //
    Ddd17("17", Uf.SP, "São José do Rio Preto/Barretos"), //
    Ddd18("18", Uf.SP, "Presidente Prudente/Araçatuba/Assis"), //
    Ddd19("19", Uf.SP, "Campinas e Região Metropolitana/Piracicaba"), //
    Ddd21("21", Uf.RJ, "Rio de Janeiro, Região Metropolitana e Teresópolis"), //
    Ddd22("22", Uf.RJ, "Campos dos Goytacazes/Nova Friburgo/Macaé/Cabo Frio"), //
    Ddd24("24", Uf.RJ, "Petrópolis/Volta Redonda/Angra dos Reis"), //
    Ddd27("27", Uf.ES, "Vitória e Região Metropolitana"), //
    Ddd28("28", Uf.ES, "Cachoeiro de Itapemirim"), //
    Ddd31("31", Uf.MG, "Belo Horizonte, Região Metropolitana e Vale do Aço"), //
    Ddd32("32", Uf.MG, "Juiz de Fora/São João Del Rei"),
    Ddd33("33", Uf.MG, "Governador Valadares/Teófilo Otoni/Caratinga/Manhuaçu"),
    Ddd34("34", Uf.MG, "Uberlândia e Triângulo Mineiro"),
    Ddd35("35", Uf.MG, "Poços de Caldas/Pouso Alegre/Varginha"),
    Ddd37("37", Uf.MG, "Divinópolis/Itaúna"),
    Ddd38("38", Uf.MG, "Montes Claros"),
    Ddd41("41", Uf.PR, "Curitiba e Região Metropolitana"),
    Ddd42("42", Uf.PR, "Ponta Grossa/Guarapuava"),
    Ddd43("43", Uf.PR, "Londrina/Apucarana"),
    Ddd44("44", Uf.PR, "Maringá/Campo Mourão/Umuarama"),
    Ddd45("45", Uf.PR, "Cascavel/Foz do Iguaçu"),
    Ddd46("46", Uf.PR, "Francisco Beltrão/Pato Branco"),
    Ddd47("47", Uf.SC, "Joinville/Blumenau/Itajaí/Balneário Camboriú"),
    Ddd48("48", Uf.SC, "Florianópolis e Região Metropolitana/Criciúma/Tubarão"),
    Ddd49("49", Uf.SC, "Chapecó/Lages/Caçador"),
    Ddd51("51", Uf.RS, "Porto Alegre e Região Metropolitana/Santa Cruz do Sul/Litoral Norte"),
    Ddd53("53", Uf.RS, "Pelotas/Rio Grande"),
    Ddd54("54", Uf.RS, "Caxias do Sul/Passo Fundo"),
    Ddd55("55", Uf.RS, "Santa Maria/Uruguaiana/Santana do Livramento/Santo Ângelo"),
    Ddd61("61", Uf.DF, "Abrangência em todo o Distrito Federal e municípios da Região Integrada de Desenvolvimento do Distrito Federal e Entorno"),
    Ddd62("62", Uf.GO, "Goiânia e Região Metropolitana/Anápolis"),
    Ddd63("63", Uf.TO, "Abrangência em todo o estado"),
    Ddd64("64", Uf.GO, "Rio Verde/Itumbiara"),
    Ddd65("65", Uf.MA, "Cuiabá e Região Metropolitana"),
    Ddd66("66", Uf.MA, "Rondonópolis/Sinop"),
    Ddd67("67", Uf.MT, "Abrangência em todo o estado"),
    Ddd68("68", Uf.AC, "Abrangência em todo o estado"),
    Ddd69("69", Uf.RO, "Abrangência em todo o estado"),
    Ddd71("71", Uf.BA, "Salvador e Região Metropolitana"),
    Ddd73("73", Uf.BA, "Itabuna/Ilhéus"),
    Ddd74("74", Uf.BA, "Juazeiro"),
    Ddd75("75", Uf.BA, "Feira de Santana/Alagoinhas"),
    Ddd77("77", Uf.BA, "Vitória da Conquista/Barreiras"),
    Ddd79("79", Uf.SE, "Abrangência em todo o estado"),
    Ddd81("81", Uf.PE, "Recife e Região Metropolitana/Caruaru"),
    Ddd82("82", Uf.AL, "Abrangência em todo o estado"),
    Ddd83("83", Uf.PB, "Abrangência em todo o estado"),
    Ddd84("84", Uf.RN, "Abrangência em todo o estado"),
    Ddd85("85", Uf.CE, "Fortaleza e Região Metropolitana"),
    Ddd86("86", Uf.PI, "Teresina e Região Metropolitana/Parnaíba"),
    Ddd87("87", Uf.PR, "Petrolina/Garanhuns/Serra Talhada/Salgueiro"),
    Ddd88("88", Uf.CE, "Juazeiro do Norte/Sobral"),
    Ddd89("89", Uf.PI, "Picos/Floriano"),
    Ddd91("91", Uf.PA, "Belém/Região Metropolitana"),
    Ddd92("92", Uf.AM, "Manaus/Região Metropolitana/Parintins"),
    Ddd93("93", Uf.PA, "Santarém/Altamira"),
    Ddd94("94", Uf.PA, "Marabá"),
    Ddd95("95", Uf.RO, "Abrangência em todo o estado"),
    Ddd96("96", Uf.AP, "Abrangência em todo o estado"),
    Ddd97("97", Uf.AM, "Abrangência no interior do estado"),
    Ddd98("98", Uf.MA, "São Luís e Região Metropolitana"),
    Ddd99("99", Uf.MA, "Imperatriz/Caxias");

    private final String value;

    private final Uf uf;

    private final String regiao;

    /**
     * Construtor padrao.
     * 
     * @param value
     *            o valor do DDD
     * @param uf
     *            A Unidade Federativa
     * @param regiao
     *            Regiao do DDD
     */
    private Ddd(final String value, final Uf uf, final String regiao) {
        this.value = value;
        this.uf = uf;
        this.regiao = regiao;
    }

    @Override
    public String getValue() {
        return value;
    }

    public Uf getUf() {
        return uf;
    }

    public String getRegiao() {
        return regiao;
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
                .append("value",toShow()) //
                .toString();
    }

    public static List<String> getDdds() {
        final List<String> result = new ArrayList<String>();

        for (final Ddd dir : Ddd.values()) {
            result.add(dir.value);
        }

        return result;
    }

    public static Ddd getDdd(final String ddd) {
        final List<String> ddds = getDdds();

        final Predicate<String> dddPredicate = EqualPredicate.equalPredicate(ddd);

        final List<String> selectsDdd = ListUtils.select(ddds, dddPredicate);
        
        if (selectsDdd.isEmpty()) {
            return null;
        }

        final String dddString = selectsDdd.get(0);
        
        return Ddd.valueOf("Ddd"+dddString);
    }
}
