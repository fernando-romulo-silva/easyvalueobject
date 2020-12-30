package org.easystructure.domain.valueobject;

import org.easystructure.domain.valueobject.business.Cnpj;
import static org.allsetconfigtest.TestingLevels.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CnpjTest {

    @DataProvider(name = "createCnpjInvalidData")
    public Object[][] createCnpjInvalidData() {

        return new Object[][]{ // 
                { "" }, { "   " }, { null }, //  
                { "111" }, { "aabbccaabbc" }, { "99.652.236/0001-72" }, // 
                { "11111111111111" }, { "22222222222222" }, { "33333333333333" }, // 
                { "44444444444444" }, { "55555555555555" }, { "66666666666666" }, // 
                { "77777777777777" }, { "88888888888888" }, { "99999999999999" }, { "00000000000000" }// 
        };
    }

    @Test(priority = 1, dataProvider = "createCnpjInvalidData", groups = UNIT, expectedExceptions = { IllegalArgumentException.class })
    public void createCnpjInvalidTest(final String value) {
        new Cnpj(value);
    }

    @Test(priority = 2, groups = UNIT)
    public void checkToStringShowCnpjTest() {
        final Cnpj cnpj1 = new Cnpj("99652236000170");
        final Cnpj cnpj2 = new Cnpj("99.652.236/0001-70");
        final Cnpj cnpj3 = new Cnpj("20.133.236/0001-03");

        Assert.assertEquals("Cnpj[value=99.652.236/0001-70]", cnpj1.toString());

        Assert.assertEquals("99.652.236/0001-70", cnpj1.toShow());

        Assert.assertEquals("99652236000170", cnpj2.getValue());

        Assert.assertEquals(cnpj1, cnpj2);

        Assert.assertNotEquals(cnpj1, cnpj3);
    }
}
