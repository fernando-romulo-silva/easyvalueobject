package org.easystructure.domain.valueobject;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;

import org.easystructure.domain.valueobject.address.RegiaoUf;
import org.easystructure.domain.valueobject.address.Uf;
import static org.allsetconfigtest.TestingLevels.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UFTest {

    @Test(priority = 1, groups = UNIT)
    public void checkToStringShowUFTest() {
        final Uf uf1 = Uf.AC;
        final Uf uf2 = Uf.AC;
        final Uf uf3 = Uf.SP;

        Assert.assertEquals("Uf[idIbge=12,value=AC,nome=Acre]", uf1.toString());

        Assert.assertEquals("AC", uf1.toShow());

        Assert.assertEquals(12, uf1.getIdIbge());

        Assert.assertEquals("AC", uf2.getValue());
        
        Assert.assertEquals(RegiaoUf.NORTE, uf1.getRegiao());

        Assert.assertEquals(uf1, uf2);

        Assert.assertNotEquals(uf1, uf3);

        final ArrayList<SimpleEntry<String, String>> estados1 = new ArrayList<SimpleEntry<String, String>>();

        estados1.add(new SimpleEntry<String, String>("AC", "Acre"));
        estados1.add(new SimpleEntry<String, String>("AL", "Alagoas"));
        estados1.add(new SimpleEntry<String, String>("AM", "Amazonas"));
        estados1.add(new SimpleEntry<String, String>("AP", "Amapá"));
        estados1.add(new SimpleEntry<String, String>("BA", "Bahia"));
        estados1.add(new SimpleEntry<String, String>("CE", "Ceará"));
        estados1.add(new SimpleEntry<String, String>("DF", "Distrito Federal"));
        estados1.add(new SimpleEntry<String, String>("ES", "Espírito Santo"));
        estados1.add(new SimpleEntry<String, String>("GO", "Goiás"));
        estados1.add(new SimpleEntry<String, String>("MA", "Maranhão"));
        estados1.add(new SimpleEntry<String, String>("MG", "Minas Gerais"));
        estados1.add(new SimpleEntry<String, String>("MS", "Mato Grosso do Sul"));
        estados1.add(new SimpleEntry<String, String>("MT", "Mato Grosso"));
        estados1.add(new SimpleEntry<String, String>("PA", "Pará"));
        estados1.add(new SimpleEntry<String, String>("PB", "Paraíba"));
        estados1.add(new SimpleEntry<String, String>("PE", "Pernambuco"));
        estados1.add(new SimpleEntry<String, String>("PI", "Piauí"));
        estados1.add(new SimpleEntry<String, String>("PR", "Paraná"));
        estados1.add(new SimpleEntry<String, String>("RJ", "Rio de Janeiro"));
        estados1.add(new SimpleEntry<String, String>("RN", "Rio Grande do Norte"));
        estados1.add(new SimpleEntry<String, String>("RO", "Rondônia"));
        estados1.add(new SimpleEntry<String, String>("RR", "Roraima"));
        estados1.add(new SimpleEntry<String, String>("RS", "Rio Grande do Sul"));
        estados1.add(new SimpleEntry<String, String>("SC", "Santa Catarina"));
        estados1.add(new SimpleEntry<String, String>("SE", "Sergipe"));
        estados1.add(new SimpleEntry<String, String>("SP", "São Paulo"));
        estados1.add(new SimpleEntry<String, String>("TO", "Tocantins"));

        final ArrayList<String> estados2 = new ArrayList<>(Uf.getUfs());

        Uf ufTemp = null;

        for (final SimpleEntry<String, String> entry : estados1) {
            ufTemp = Uf.valueOf(entry.getKey());

            Assert.assertEquals(ufTemp.getValue(), entry.getKey());
            Assert.assertEquals(ufTemp.getNome(), entry.getValue());

            Assert.assertTrue(estados2.contains(ufTemp.getValue()));
        }
    }
}
