package org.easystructure.domain.valueobject;

import org.easystructure.domain.valueobject.personal.Cpf;
import static org.allsetconfigtest.TestingLevels.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class CpfTest {

    @DataProvider(name = "createCpfInvalidData")
    public Object[][] createCpfInvalidData() {

        return new Object[][]{ // 
                { "" }, { "   " }, { null }, //  
                { "111" }, { "aabbccaabbc" }, { "379.863.952-33" }, // 
                { "11111111111" }, { "22222222222" }, { "33333333333" }, // 
                { "44444444444" }, { "55555555555" }, { "66666666666" }, // 
                { "77777777777" }, { "88888888888" }, { "99999999999" }, { "00000000000" }// 
        };
    }

    @Test(priority = 1, dataProvider = "createCpfInvalidData", groups = UNIT, expectedExceptions = { IllegalArgumentException.class })
    public void createCpfInvalidTest(final String value) {
        new Cpf(value);
    }

    @Test(priority = 2, groups = UNIT)
    public void checkToStringShowCpfTest() {
        final Cpf cpf1 = new Cpf("809612347A50");
        final Cpf cpf2 = new Cpf("809.612.347-50");
        final Cpf cpf3 = new Cpf("289.544.815-90");

        Assert.assertEquals("Cpf[value=809.612.347-50]", cpf1.toString());

        Assert.assertEquals("809.612.347-50", cpf1.toShow());

        Assert.assertEquals("80961234750", cpf2.getValue());

        Assert.assertEquals(cpf1, cpf2);

        Assert.assertNotEquals(cpf1, cpf3);
    }
}
