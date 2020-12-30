package org.easystructure.domain.valueobject.business;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.easystructure.domain.valueobject.ValueObjectConst;
import org.easystructure.domain.valueobject.ValueObjectValidator;
import org.easystructure.domain.valueobject.address.Uf;

import com.google.common.base.Preconditions;

/**
 * Classe de validacao de Inscricao Estadual.
 * 
 * @author Fernando Romulo da Silva
 */
class InscricaoEstadualValidator implements ValueObjectValidator<String> {

    private final Uf uf;

    public InscricaoEstadualValidator(final Uf uf) {
        super();
        validadeUF(uf);
        this.uf = uf;
    }

    private void validadeUF(final Uf uf) {
        Preconditions.checkArgument(uf != null, "Uf da Inscrição Estadual vazio.");
    }

    /*
     * (non-Javadoc)
     * @see
     * org.easystructure.domain.valueobject.business.ValueValidator#validate(java.lang.Object)
     */
    @Override
    public void validate(final String value) {
        Preconditions.checkArgument(!StringUtils.isBlank(value), "Inscrição Estadual vazio.");

        String strIE = value.replaceAll(ValueObjectConst.REGEX_NUMBER, StringUtils.EMPTY);

        switch (uf) {
        case AC:
            validaIEAcre(strIE);
            break;
        case AL:
            validaIEAlagoas(strIE);
            break;
        case AP:
            validaIEAmapa(strIE);
            break;
        case AM:
            validaIEAmazonas(strIE);
            break;
        case BA:
            validaIEBahia(strIE);
            break;
        case CE:
            validaIECeara(strIE);
            break;
        case ES:
            validaIEEspiritoSanto(strIE);
            break;
        case GO:
            validaIEGoias(strIE);
            break;
        case MA:
            validaIEMaranhao(strIE);
            break;
        case MT:
            validaIEMatoGrosso(strIE);
            break;
        case MS:
            validaIEMatoGrossoSul(strIE);
            break;
        case MG:
            validaIEMinasGerais(strIE);
            break;
        case PA:
            validaIEPara(strIE);
            break;
        case PB:
            validaIEParaiba(strIE);
            break;
        case PR:
            validaIEParana(strIE);
            break;
        case PE:
            validaIEPernambuco(strIE);
            break;
        case PI:
            validaIEPiaui(strIE);
            break;
        case RJ:
            validaIERioJaneiro(strIE);
            break;
        case RN:
            validaIERioGrandeNorte(strIE);
            break;
        case RS:
            validaIERioGrandeSul(strIE);
            break;
        case RO:
            validaIERondonia(strIE);
            break;
        case RR:
            validaIERoraima(strIE);
            break;
        case SC:
            validaIESantaCatarina(strIE);
            break;
        case SP:
            if (value.charAt(0) == 'P') {
                strIE = "P" + strIE;
            }
            validaIESaoPaulo(strIE);
            break;
        case SE:
            validaIESergipe(strIE);
            break;
        case TO:
            validaIETocantins(strIE);
            break;
        case DF:
            validaIEDistritoFederal(strIE);
            break;
        default:
            throw new IllegalArgumentException(MessageFormat.format("Uf não encontrado : {0}", uf));
        }
    }

    /**
     * Valida Inscricao estadual do estado do Acre. AC-01.328.350/704-97
     * 
     * @param ie
     *            Inscricao estadual
     */
    private void validaIEAcre(final String ie) { // inicio do validaIEAcre()
        // valida a quantidade de digitos
        if (ie.length() != 13) {
            throw new IllegalArgumentException("Quantidade de digitos inválida.");
        }

        // valida os dois primeiros digitos - devem ser iguais a 01
        for (int i = 0; i < 2; i++) {
            if (Integer.parseInt(String.valueOf(ie.charAt(i))) != i) {
                throw new IllegalArgumentException("Inscrição Estadual inválida");
            }
        }

        int soma = 0;
        int pesoInicial = 4;
        int pesoFinal = 9;
        int d1 = 0; // primeiro digito verificador
        int d2 = 0; // segundo digito verificador

        // calcula o primeiro digito
        for (int i = 0; i < ie.length() - 2; i++) {
            if (i < 3) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoInicial;
                pesoInicial--;
            } else {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoFinal;
                pesoFinal--;
            }
        }
        d1 = 11 - soma % 11;
        if (d1 == 10 || d1 == 11) {
            d1 = 0;
        }

        // calcula o segundo digito
        soma = d1 * 2;
        pesoInicial = 5;
        pesoFinal = 9;
        for (int i = 0; i < ie.length() - 2; i++) {
            if (i < 4) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoInicial;
                pesoInicial--;
            } else {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoFinal;
                pesoFinal--;
            }
        }

        d2 = 11 - soma % 11;
        if (d2 == 10 || d2 == 11) {
            d2 = 0;
        }

        // valida os digitos verificadores
        final String dv = d1 + "" + d2;
        if (!dv.equals(ie.substring(ie.length() - 2, ie.length()))) {
            throw new IllegalArgumentException("Digito verificador inválido.");
        }
    } // fim do validaIEAcre()

    /**
     * Valida Inscricao estadual do estado do Alagoas
     * 
     * @param ie
     *            Inscricao estadual
     */
    private void validaIEAlagoas(final String ie) {
        // valida quantidade de digitos
        if (ie.length() != 9) {
            throw new IllegalArgumentException("Quantidade de digitos invilida.");
        }

        // valida os dois primeiros digitos - deve ser iguais a 24
        if (!ie.substring(0, 2).equals("24")) {
            throw new IllegalArgumentException("Inscrição estadual inválida.");
        }

        // valida o terceiro digito - deve ser 0,3,5,7,8
        final int[] digits = { 0, 3, 5, 7, 8 };
        boolean check = false;
        for (int i = 0; i < digits.length; i++) {
            if (Integer.parseInt(String.valueOf(ie.charAt(2))) == digits[i]) {
                check = true;
                break;
            }
        }
        if (!check) {
            throw new IllegalArgumentException("Inscrição estadual inválida.");
        }

        // calcula o digito verificador
        int soma = 0;
        int peso = 9;
        int d = 0; // digito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }
        d = soma * 10 % 11;
        if (d == 10) {
            d = 0;
        }

        // valida o digito verificador
        final String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new IllegalArgumentException("Digito verificador inválido.");
        }
    }

    /**
     * Valida Inscricao estadual do estado do Amapi
     * 
     * @param ie
     *            Inscricao estadual
     */
    private void validaIEAmapa(final String ie) {
        // valida quantida de digitos
        if (ie.length() != 9) {
            throw new IllegalArgumentException("Quantidade de digitos inválidas.");
        }

        // verifica os dois primeiros digitos - deve ser igual 03
        if (!ie.substring(0, 2).equals("03")) {
            throw new IllegalArgumentException("Inscrição estadual inválida.");
        }

        // calcula o digito verificador
        int d1 = -1;
        int soma = -1;
        int peso = 9;

        // configura o valor do digito verificador e da soma de acordo com faixa das inscriiies
        final long x = Long.parseLong(ie.substring(0, ie.length() - 1)); // x = Inscricao estadual sem o digito verificador
        if (x >= 3017001L && x <= 3019022L) {
            d1 = 1;
            soma = 9;
        } else if (x >= 3000001L && x <= 3017000L) {
            d1 = 0;
            soma = 5;
        } else if (x >= 3019023L) {
            d1 = 0;
            soma = 0;
        }

        for (int i = 0; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        int d = 11 - soma % 11; // d = armazena o digito verificador apis cilculo
        if (d == 10) {
            d = 0;
        } else if (d == 11) {
            d = d1;
        }

        // valida o digito verificador
        final String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new IllegalArgumentException("Digito verificador inválido.");
        }
    }

    /**
     * Valida Inscricao estadual do estado do Amazonas
     * 
     * @param ie
     *            Inscricao estadual
     */
    private void validaIEAmazonas(final String ie) {
        // valida quantida de digitos
        if (ie.length() != 9) {
            throw new IllegalArgumentException("Quantidade de digitos inválidas.");
        }

        int soma = 0;
        int peso = 9;
        int d = -1; // digito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        if (soma < 11) {
            d = 11 - soma;
        } else if (soma % 11 <= 1) {
            d = 0;
        } else {
            d = 11 - soma % 11;
        }

        // valida o digito verificador
        final String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new IllegalArgumentException("Digito verificador inválido.");
        }
    }

    /**
     * Valida Inscricao estadual do estado do Bahia.
     * 
     * @param ie
     *            Inscricao estadual
     */
    private void validaIEBahia(final String ie) {
        // valida quantida de digitos
        if (ie.length() != 8 && ie.length() != 9) {
            throw new IllegalArgumentException("Quantidade de digitos inválidas." + ie);
        }

        // Cilculo do midulo de acordo com o primeiro digito da Inscricao Estadual
        int modulo = 10;
        final int firstDigit = Integer.parseInt(String.valueOf(ie.charAt(ie.length() == 8 ? 0 : 1)));
        if (firstDigit == 6 || firstDigit == 7 || firstDigit == 9)
            modulo = 11;

        // Cilculo do segundo digito
        int d2 = -1; // segundo digito verificador
        int soma = 0;
        int peso = ie.length() == 8 ? 7 : 8;
        for (int i = 0; i < ie.length() - 2; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        int resto = soma % modulo;

        if (resto == 0 || modulo == 11 && resto == 1) {
            d2 = 0;
        } else {
            d2 = modulo - resto;
        }

        // Cilculo do primeiro digito
        int d1 = -1; // primeiro digito verificador
        soma = d2 * 2;
        peso = ie.length() == 8 ? 8 : 9;
        for (int i = 0; i < ie.length() - 2; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        resto = soma % modulo;

        if (resto == 0 || modulo == 11 && resto == 1) {
            d1 = 0;
        } else {
            d1 = modulo - resto;
        }

        // valida os digitos verificadores
        final String dv = d1 + "" + d2;
        if (!dv.equals(ie.substring(ie.length() - 2, ie.length()))) {
            throw new IllegalArgumentException("Digito verificador inválido." + ie);
        }
    }

    /**
     * Valida Inscricao estadual do estado do Ceari
     * 
     * @param ie
     *            Inscricao estadual
     */
    private void validaIECeara(final String ie) {
        // valida quantida de digitos
        if (ie.length() != 9) {
            throw new IllegalArgumentException("Quantidade de digitos inválidas.");
        }

        // Cilculo do digito verificador
        int soma = 0;
        int peso = 9;
        int d = -1; // digito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        d = 11 - soma % 11;
        if (d == 10 || d == 11) {
            d = 0;
        }
        // valida o digito verificador
        final String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new IllegalArgumentException("Digito verificador inválido.");
        }
    }

    /**
     * Valida Inscricao estadual do estado do Espirito Santo
     * 
     * @param ie
     *            Inscricao estadual
     */
    private void validaIEEspiritoSanto(final String ie) {
        // valida quantida de digitos
        if (ie.length() != 9) {
            throw new IllegalArgumentException("Quantidade de digitos inválidas.");
        }

        // Cilculo do digito verificador
        int soma = 0;
        int peso = 9;

        // digito verificador
        int d = -1;

        for (int i = 0; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        final int resto = soma % 11;

        if (resto < 2) {
            d = 0;
            // if (resto > 1)    
        } else {
            d = 11 - resto;
        }

        // valida o digito verificador
        final String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new IllegalArgumentException("Digito verificador inválido.");
        }
    }

    /**
     * Valida Inscricao estadual do estado do Goiis
     * 
     * @param ie
     *            Inscricao estadual
     */
    private void validaIEGoias(final String ie) {
        // valida quantida de digitos
        if (ie.length() != 9) {
            throw new IllegalArgumentException("Quantidade de digitos inválidas.");
        }

        // vilida os dois primeiros digito
        if (!"10".equals(ie.substring(0, 2)) && !"11".equals(ie.substring(0, 2)) && !"15".equals(ie.substring(0, 2))) {
            throw new IllegalArgumentException("Inscrição estadual inválida");
        }

        if (ie.substring(0, ie.length() - 1).equals("11094402")) {
            if (!ie.substring(ie.length() - 1, ie.length()).equals("0") && !ie.substring(ie.length() - 1, ie.length()).equals("1")) {
                throw new IllegalArgumentException("Inscrição estadual inválida.");
            }
        } else {

            // Cilculo do digito verificador
            int soma = 0;
            int peso = 9;
            int digGoias = -1; // digito verificador

            for (int i = 0; i < ie.length() - 1; i++) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
                peso--;
            }

            final int resto = soma % 11;
            final long faixaInicio = 10103105;
            final long faixaFim = 10119997;
            final long insc = Long.parseLong(ie.substring(0, ie.length() - 1));

            if (resto == 0) {
                digGoias = 0;
            } else if (resto == 1) {
                if (insc >= faixaInicio && insc <= faixaFim) {
                    digGoias = 1;
                } else {
                    digGoias = 0;
                }
            } else {
                //if (resto != 0 && resto != 1) {
                digGoias = 11 - resto;
            }

            // valida o digito verificador
            final String dv = digGoias + "";

            if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
                throw new IllegalArgumentException("Digito verificador inválido.");
            }
        }
    }

    /**
     * Valida Inscricao estadual do estado do Maranhio
     * 
     * @param ie
     *            Inscricao estadual
     */
    private void validaIEMaranhao(final String ie) {
        // valida quantida de digitos
        if (ie.length() != 9) {
            throw new IllegalArgumentException("Quantidade de digitos inválidas.");
        }

        // valida os dois primeiros digitos
        if (!ie.substring(0, 2).equals("12")) {
            throw new IllegalArgumentException("Inscrição estadual inválida.");
        }

        // Calcula o digito verificador
        int soma = 0;
        int peso = 9;
        int d = -1; // digito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        d = 11 - soma % 11;
        if (soma % 11 == 0 || soma % 11 == 1) {
            d = 0;
        }

        // valida o digito verificador
        final String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new IllegalArgumentException("Digito verificador inválido.");
        }
    }

    /**
     * Valida Inscricao estadual do estado do Mato Grosso
     * 
     * @param ie
     *            Inscricao estadual
     */
    private void validaIEMatoGrosso(final String ie) {
        // valida quantida de digitos
        if (ie.length() != 11) {
            throw new IllegalArgumentException("Quantidade de digitos inválidas.");
        }

        // Calcula o digito verificador
        int soma = 0;
        int pesoInicial = 3;
        int pesoFinal = 9;
        int d = -1;

        for (int i = 0; i < ie.length() - 1; i++) {
            if (i < 2) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoInicial;
                pesoInicial--;
            } else {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoFinal;
                pesoFinal--;
            }
        }

        d = 11 - soma % 11;
        if (soma % 11 == 0 || soma % 11 == 1) {
            d = 0;
        }

        // valida o digito verificador
        final String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new IllegalArgumentException("Digito verificador inválido.");
        }
    }

    /**
     * Valida Inscricao estadual do estado do Mato Grosso do Sul
     * 
     * @param ie
     *            Inscricao estadual
     */
    private void validaIEMatoGrossoSul(final String ie) {
        // valida quantida de digitos
        if (ie.length() != 9) {
            throw new IllegalArgumentException("Quantidade de digitos inválidas.");
        }

        // valida os dois primeiros digitos
        if (!ie.substring(0, 2).equals("28")) {
            throw new IllegalArgumentException("Inscrição estadual inválida.");
        }

        // Calcula o digito verificador
        int soma = 0;
        int peso = 9;
        int d = -1; // digito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        final int resto = soma % 11;
        final int result = 11 - resto;
        if (resto == 0) {
            d = 0;
        } else if (resto > 0) {
            if (result > 9) {
                d = 0;
            } else {
                // if (result < 10) {
                d = result;
            }
        }

        // valida o digito verificador
        final String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new IllegalArgumentException("Digito verificador inválido.");
        }
    }

    /**
     * Valida Inscricao estadual do estado do Minas Gerais
     * 
     * @param ie
     *            Inscricao estadual
     */
    private void validaIEMinasGerais(final String ie) {
        /*
         * FORMATO GERAL: A1A2A3B1B2B3B4B5B6C1C2D1D2 Onde: A= Cidigo do Municipio B= Nimero da
         * Inscricao C= Nimero de ordem do estabelecimento D= Digitos de controle
         */

        // valida quantida de digitos
        if (ie.length() != 13) {
            throw new NumberFormatException("Quantidade de digitos inválidas.");
        }

        // iguala a casas para o cilculo
        // em inserir o algarismo zero "0" imediatamente apis o nimero de cidigo do municipio,
        // desprezando-se os digitos de controle.
        final StringBuilder str = new StringBuilder("");

        for (int i = 0; i < ie.length() - 2; i++) {
            if (Character.isDigit(ie.charAt(i))) {
                if (i == 3) {
                    str.append("0");
                    str.append(ie.charAt(i));
                } else {
                    str.append(ie.charAt(i));
                }
            }
        }

        // Cilculo do primeiro digito verificador
        int soma = 0;
        int pesoInicio = 1;
        int pesoFim = 2;
        int d1 = -1; // primeiro digito verificador
        for (int i = 0; i < str.length(); i++) {
            if (i % 2 == 0) {
                final int x = Integer.parseInt(String.valueOf(str.charAt(i))) * pesoInicio;
                final String strX = Integer.toString(x);
                for (int j = 0; j < strX.length(); j++) {
                    soma += Integer.parseInt(String.valueOf(strX.charAt(j)));
                }
            } else {
                final int y = Integer.parseInt(String.valueOf(str.charAt(i))) * pesoFim;
                final String strY = Integer.toString(y);
                for (int j = 0; j < strY.length(); j++) {
                    soma += Integer.parseInt(String.valueOf(strY.charAt(j)));
                }
            }
        }

        int dezenaExata = soma;
        while (dezenaExata % 10 != 0) {
            dezenaExata++;
        }
        d1 = dezenaExata - soma; // resultado - primeiro digito verificador

        // Cilculo do segundo digito verificador
        soma = d1 * 2;
        pesoInicio = 3;
        pesoFim = 11;
        int d2 = -1;
        for (int i = 0; i < ie.length() - 2; i++) {
            if (i < 2) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoInicio;
                pesoInicio--;
            } else {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoFim;
                pesoFim--;
            }
        }

        d2 = 11 - soma % 11; // resultado - segundo digito verificador
        if (soma % 11 == 0 || soma % 11 == 1) {
            d2 = 0;
        }

        // valida os digitos verificadores
        final String dv = d1 + "" + d2;
        if (!dv.equals(ie.substring(ie.length() - 2, ie.length()))) {
            throw new IllegalArgumentException("Digito verificador inválido.");
        }
    }

    /**
     * Valida Inscricao estadual do estado do Pari
     * 
     * @param ie
     *            Inscricao estadual
     */
    private void validaIEPara(final String ie) {
        // valida quantida de digitos
        if (ie.length() != 9) {
            throw new IllegalArgumentException("Quantidade de digitos inválidas.");
        }

        // valida os dois primeiros digitos
        if (!ie.substring(0, 2).equals("15")) {
            throw new IllegalArgumentException("Inscrição estadual inválida.");
        }

        // Calcula o digito verificador
        int soma = 0;
        int peso = 9;
        int d = -1; // digito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        d = 11 - soma % 11;
        if (soma % 11 == 0 || soma % 11 == 1) {
            d = 0;
        }

        // valida o digito verificador
        final String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new IllegalArgumentException("Digito verificador inválido.");
        }
    }

    /**
     * Valida Inscricao estadual do estado do Paraiba
     * 
     * @param ie
     *            Inscricao estadual
     */
    private void validaIEParaiba(final String ie) {
        // valida quantida de digitos
        if (ie.length() != 9) {
            throw new IllegalArgumentException("Quantidade de digitos inválidas.");
        }

        // Calcula o digito verificador
        int soma = 0;
        int peso = 9;
        int d = -1; // digito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        d = 11 - soma % 11;
        if (d == 10 || d == 11) {
            d = 0;
        }

        // valida o digito verificador
        final String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new IllegalArgumentException("Digito verificador inválido.");
        }
    }

    /**
     * Valida Inscricao estadual do estado do Parani
     * 
     * @param ie
     *            Inscricao estadual
     */
    private void validaIEParana(final String ie) {
        // valida quantida de digitos
        if (ie.length() != 10) {
            throw new IllegalArgumentException("Quantidade de digitos inválidas.");
        }

        // Cilculo do primeiro digito
        int soma = 0;
        int pesoInicio = 3;
        int pesoFim = 7;
        int d1 = -1; // digito verificador
        for (int i = 0; i < ie.length() - 2; i++) {
            if (i < 2) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoInicio;
                pesoInicio--;
            } else {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoFim;
                pesoFim--;
            }
        }

        d1 = 11 - soma % 11;
        if (soma % 11 == 0 || soma % 11 == 1) {
            d1 = 0;
        }

        // cilculo do segundo digito
        soma = d1 * 2;
        pesoInicio = 4;
        pesoFim = 7;
        int d2 = -1; // segundo digito
        for (int i = 0; i < ie.length() - 2; i++) {
            if (i < 3) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoInicio;
                pesoInicio--;
            } else {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoFim;
                pesoFim--;
            }
        }

        d2 = 11 - soma % 11;
        if (soma % 11 == 0 || soma % 11 == 1) {
            d2 = 0;
        }

        // valida os digitos verificadores
        final String dv = d1 + "" + d2;
        if (!dv.equals(ie.substring(ie.length() - 2, ie.length()))) {
            throw new IllegalArgumentException("Digito verificador inválido.");
        }
    }

    /**
     * Valida Inscricao estadual do estado do Pernambuco
     * 
     * @param ie
     *            Inscricao estadual
     */
    private void validaIEPernambuco(final String ie) {
        // valida quantida de digitos
        if (ie.length() == 14) {
            // Cilculo do digito verificador
            int soma = 0;
            int pesoInicio = 5;
            int pesoFim = 9;
            int d = -1; // digito verificador

            for (int i = 0; i < ie.length() - 1; i++) {
                if (i < 5) {
                    soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoInicio;
                    pesoInicio--;
                } else {
                    soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoFim;
                    pesoFim--;
                }
            }

            d = 11 - soma % 11;
            if (d > 9) {
                d -= 10;
            }

            // valida o digito verificador
            final String dv = d + "";
            if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
                throw new IllegalArgumentException("Digito verificador inválido.");
            }

        } else if (ie.length() == 9) {

            // Cilculo do digito verficador
            int soma = 0;
            int peso = 9;
            int d = -1; // digito verificador
            for (int i = 0; i < ie.length() - 1; i++) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
                peso--;
            }

            d = 11 - soma % 11;
            if (d == 11 || d == 10) {
                d = 0;
            }

            // valida o digito verificador
            final String dv = d + "";
            if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
                throw new IllegalArgumentException("Digito verificador inválido.");
            }

        } else {
            throw new IllegalArgumentException("Quantidade de digitos inválidas.");
        }

    }

    /**
     * Valida Inscricao estadual do estado do Piaui
     * 
     * @param ie
     *            Inscricao estadual
     */
    private void validaIEPiaui(final String ie) {
        // valida quantida de digitos
        if (ie.length() != 9) {
            throw new IllegalArgumentException("Quantidade de digitos inválidas.");
        }

        // Cilculo do digito verficador
        int soma = 0;
        int peso = 9;
        int d = -1; // digito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        d = 11 - soma % 11;
        if (d == 11 || d == 10) {
            d = 0;
        }

        // valida o digito verificador
        final String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new IllegalArgumentException("Digito verificador inválido.");
        }
    }

    /**
     * Valida Inscricao estadual do estado do Rio de Janeiro
     * 
     * @param ie
     *            Inscricao estadual
     */
    private void validaIERioJaneiro(final String ie) {
        // valida quantida de digitos
        if (ie.length() != 8) {
            throw new IllegalArgumentException("Quantidade de digitos inválidas.");
        }

        // Cilculo do digito verficador
        int soma = 0;
        int peso = 7;
        int d = -1; // digito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            if (i == 0) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * 2;
            } else {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
                peso--;
            }
        }

        d = 11 - soma % 11;
        if (soma % 11 <= 1) {
            d = 0;
        }

        // valida o digito verificador
        final String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new IllegalArgumentException("Digito verificador inválido.");
        }
    }

    /**
     * Valida Inscricao estadual do estado do Rio Grande do Norte
     * 
     * @param ie
     *            Inscricao estadual
     */
    private void validaIERioGrandeNorte(final String ie) {
        // valida quantida de digitos
        if (ie.length() != 10 && ie.length() != 9) {
            throw new IllegalArgumentException("Quantidade de digitos inválidas.");
        }

        // valida os dois primeiros digitos
        if (!ie.substring(0, 2).equals("20")) {
            throw new IllegalArgumentException("Inscrição estadual inválida.");
        }

        // calcula o digito para Inscricao de 9 digitos
        if (ie.length() == 9) {
            int soma = 0;
            int peso = 9;
            int d = -1; // digito verificador
            for (int i = 0; i < ie.length() - 1; i++) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
                peso--;
            }

            d = soma * 10 % 11;
            if (d == 10) {
                d = 0;
            }

            // valida o digito verificador
            final String dv = d + "";
            if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
                throw new IllegalArgumentException("Digito verificador inválido.");
            }
        } else {
            int soma = 0;
            int peso = 10;
            int d = -1; // digito verificador
            for (int i = 0; i < ie.length() - 1; i++) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
                peso--;
            }
            d = soma * 10 % 11;
            if (d == 10) {
                d = 0;
            }

            // valida o digito verificador
            final String dv = d + "";
            if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
                throw new IllegalArgumentException("Digito verificador inválido.");
            }
        }

    }

    /**
     * Valida Inscricao estadual do estado do Rio Grande do Sul
     * 
     * @param ie
     *            Inscricao estadual
     */
    private void validaIERioGrandeSul(final String ie) {
        // valida quantida de digitos
        if (ie.length() != 10) {
            throw new IllegalArgumentException("Quantidade de digitos inválidas.");
        }

        // Cilculo do difito verificador
        int soma = Integer.parseInt(String.valueOf(ie.charAt(0))) * 2;
        int peso = 9;
        int d = -1; // digito verificador
        for (int i = 1; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        d = 11 - soma % 11;
        if (d == 10 || d == 11) {
            d = 0;
        }

        // valida o digito verificador
        final String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new IllegalArgumentException("Digito verificador inválido.");
        }
    }

    /**
     * Valida Inscricao estadual do estado do Rondinia
     * 
     * @param ie
     *            Inscricao estadual
     */
    private void validaIERondonia(final String ie) {
        // valida quantida de digitos
        if (ie.length() != 14) {
            throw new IllegalArgumentException("Quantidade de digitos inválidas.");
        }

        // Cilculo do digito verificador
        int soma = 0;
        int pesoInicio = 6;
        int pesoFim = 9;
        int d = -1; // digito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            if (i < 5) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoInicio;
                pesoInicio--;
            } else {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoFim;
                pesoFim--;
            }
        }

        d = 11 - soma % 11;
        if (d == 11 || d == 10) {
            d -= 10;
        }

        // valida o digito verificador
        final String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new IllegalArgumentException("Digito verificador inválido.");
        }
    }

    /**
     * Valida Inscricao estadual do estado do Roraima
     * 
     * @param ie
     *            Inscricao estadual
     */
    private void validaIERoraima(final String ie) {
        // valida quantida de digitos
        if (ie.length() != 9) {
            throw new IllegalArgumentException("Quantidade de digitos inválidas.");
        }

        // valida os dois primeiros digitos
        if (!ie.substring(0, 2).equals("24")) {
            throw new IllegalArgumentException("Inscrição estadual inválida.");
        }

        int soma = 0;
        int peso = 1;
        int d = -1; // digito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso++;
        }

        d = soma % 9;

        // valida o digito verificador
        final String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new IllegalArgumentException("Digito verificador inválido.");
        }
    }

    /**
     * Valida Inscricao estadual do estado do Santa Catarina
     * 
     * @param ie
     *            Inscricao estadual
     */
    private void validaIESantaCatarina(final String ie) {
        // valida quantida de digitos
        if (ie.length() != 9) {
            throw new IllegalArgumentException("Quantidade de digitos inválidas.");
        }

        // Cilculo do difito verificador
        int soma = 0;
        int peso = 9;
        int d = -1; // digito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        d = 11 - soma % 11;
        if (soma % 11 == 0 || soma % 11 == 1) {
            d = 0;
        }

        // valida o digito verificador
        final String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new IllegalArgumentException("Digito verificador inválido.");
        }
    }

    /**
     * Valida Inscricao estadual do estado do Sio Paulo
     * 
     * @param ie
     *            Inscricao estadual
     */
    private void validaIESaoPaulo(final String ie) {
        // valida quantida de digitos
        if (ie.length() != 12 && ie.length() != 13) {
            throw new IllegalArgumentException("Quantidade de digitos inválidas.");
        }

        if (ie.length() == 12) {
            int soma = 0;
            int peso = 1;
            int d1 = -1; // primeiro digito verificador
            // cilculo do primeiro digito verificador (nona posiiio)
            for (int i = 0; i < ie.length() - 4; i++) {
                if (i == 1 || i == 7) {
                    soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * ++peso;
                    peso++;
                } else {
                    soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
                    peso++;
                }
            }

            d1 = soma % 11;
            final String strD1 = Integer.toString(d1); // O digito i igual ao algarismo mais a direita do resultado de (soma % 11)
            d1 = Integer.parseInt(String.valueOf(strD1.charAt(strD1.length() - 1)));

            // cilculo do segunfo digito
            soma = 0;
            int pesoInicio = 3;
            int pesoFim = 10;
            int d2 = -1; // segundo digito verificador
            for (int i = 0; i < ie.length() - 1; i++) {
                if (i < 2) {
                    soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoInicio;
                    pesoInicio--;
                } else {
                    soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoFim;
                    pesoFim--;
                }
            }

            d2 = soma % 11;
            final String strD2 = Integer.toString(d2); // O digito i igual ao algarismo mais a direita do resultado de (soma % 11)
            d2 = Integer.parseInt(String.valueOf(strD2.charAt(strD2.length() - 1)));

            // valida os digitos verificadores
            if (!ie.substring(8, 9).equals(d1 + "")) {
                throw new IllegalArgumentException("Inscrição estadual inválida.");
            }
            if (!ie.substring(11, 12).equals(d2 + "")) {
                throw new IllegalArgumentException("Inscrição estadual inválida.");
            }

        } else {
            // valida o primeiro caracter
            if (ie.charAt(0) != 'P') {
                throw new IllegalArgumentException("Inscrição estadual inválida.");
            }

            final String strIE = ie.substring(1, 10); // Obtim somente os digitos utilizados no cilculo do digito verificador
            int soma = 0;
            int peso = 1;
            int d1 = -1; // primeiro digito verificador
            // cilculo do primeiro digito verificador (nona posiiio)
            for (int i = 0; i < strIE.length() - 1; i++) {
                if (i == 1 || i == 7) {
                    soma += Integer.parseInt(String.valueOf(strIE.charAt(i))) * ++peso;
                    peso++;
                } else {
                    soma += Integer.parseInt(String.valueOf(strIE.charAt(i))) * peso;
                    peso++;
                }
            }

            d1 = soma % 11;
            final String strD1 = Integer.toString(d1); // O digito i igual ao algarismo mais a direita do resultado de (soma % 11)
            d1 = Integer.parseInt(String.valueOf(strD1.charAt(strD1.length() - 1)));

            // valida o digito verificador
            if (!ie.substring(9, 10).equals(d1 + "")) {
                throw new IllegalArgumentException("Inscrição estadual inválida.");
            }
        }
    }

    /**
     * Valida Inscricao estadual do estado do Sergipe
     * 
     * @param ie
     *            Inscricao estadual
     */
    private void validaIESergipe(final String ie) {
        // valida quantida de digitos
        if (ie.length() != 9) {
            throw new IllegalArgumentException("Quantidade de digitos inválidas.");
        }

        // cilculo do digito verificador
        int soma = 0;
        int peso = 9;
        // digito verificador
        int digIeSergipe = -1;

        for (int i = 0; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        digIeSergipe = 11 - soma % 11;
        if (digIeSergipe == 11 || digIeSergipe == 10) {
            digIeSergipe = 0;
        }

        // valida o digito verificador
        final String dv = digIeSergipe + "";

        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new IllegalArgumentException("Digito verificador inválido.");
        }
    }

    /**
     * Valida Inscricao estadual do estado do Tocantins
     * 
     * @param ie
     *            Inscricao estadual
     */
    private void validaIETocantins(String ie) {
        // valida quantida de digitos
        if (ie.length() != 9 && ie.length() != 11) {
            throw new IllegalArgumentException("Quantidade de digitos invilida.");
        } else if (ie.length() == 9) {
            ie = ie.substring(0, 2) + "02" + ie.substring(2);
        }

        int soma = 0;
        int peso = 9;
        int d = -1; // digito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            if (i != 2 && i != 3) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
                peso--;
            }
        }
        d = 11 - soma % 11;
        if (soma % 11 < 2) {
            d = 0;
        }

        // valida o digito verificador
        final String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new IllegalArgumentException("Digito verificador inválido.");
        }
    }

    /**
     * Valida inscricao estadual do estado do Distrito Federal
     * 
     * @param ie
     *            Inscricao estadual
     */
    private void validaIEDistritoFederal(final String ie) {
        // valida quantida de digitos
        if (ie.length() != 13) {
            throw new IllegalArgumentException("Quantidade de digitos inválidas.");
        }

        // cilculo do primeiro digito verificador
        int soma = 0;
        int pesoInicio = 4;
        int pesoFim = 9;
        int d1 = -1; // primeiro digito verificador
        for (int i = 0; i < ie.length() - 2; i++) {
            if (i < 3) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoInicio;
                pesoInicio--;
            } else {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoFim;
                pesoFim--;
            }
        }

        d1 = 11 - soma % 11;
        if (d1 == 11 || d1 == 10) {
            d1 = 0;
        }

        // cilculo do segundo digito verificador
        soma = d1 * 2;
        pesoInicio = 5;
        pesoFim = 9;
        int d2 = -1; // segundo digito verificador
        for (int i = 0; i < ie.length() - 2; i++) {
            if (i < 4) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoInicio;
                pesoInicio--;
            } else {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoFim;
                pesoFim--;
            }
        }

        d2 = 11 - soma % 11;
        if (d2 == 11 || d2 == 10) {
            d2 = 0;
        }

        // valida os digitos verificadores
        final String dv = d1 + "" + d2;
        if (!dv.equals(ie.substring(ie.length() - 2, ie.length()))) {
            throw new IllegalArgumentException("Digito verificador inválido.");
        }
    }
}
