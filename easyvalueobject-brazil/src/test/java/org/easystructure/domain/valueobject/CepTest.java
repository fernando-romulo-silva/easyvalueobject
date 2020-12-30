package org.easystructure.domain.valueobject;

import static org.allsetconfigtest.TestingLevels.UNIT;

import org.easystructure.domain.valueobject.address.Cep;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class CepTest {

    @DataProvider(name = "createCepInvalidData")
    public Object[][] createCepInvalidData() {
        return new Object[][]{ //   
                { "" }, { "   " }, { null }, //  
                { "1956023610" }, { "AAAABBBC" }, };
    }

    @Test(priority = 1, dataProvider = "createCepInvalidData", groups = UNIT, expectedExceptions = { IllegalArgumentException.class })
    public void createCepInvalidTest(final String value) {
        new Cep(value);
    }

    @Test(priority = 2, groups = UNIT)
    public void checkToStringShowCepTest() {
        final Cep cep1 = new Cep("19560000");
        final Cep cep2 = new Cep("19560-000");

        Assert.assertEquals("Cep[value=19560-000]", cep1.toString());

        Assert.assertEquals("19560-000", cep1.toShow());

        Assert.assertEquals("19560000", cep1.getValue());

        Assert.assertTrue(cep1.equals(cep2));
    }
}
