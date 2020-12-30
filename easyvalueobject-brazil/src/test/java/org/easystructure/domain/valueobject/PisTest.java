package org.easystructure.domain.valueobject;

import org.easystructure.domain.valueobject.personal.Pis;
import static org.allsetconfigtest.TestingLevels.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class PisTest {

    @DataProvider(name = "createPisInvalidData")
    public Object[][] createPisInvalidData() {

        return new Object[][]{ // 
                { "" }, { "   " }, { null }, { "123AA" }, //  
                { "120.6564.875-8" }, { "120.3011.717-2" }, };
    }

    @Test(priority = 1, dataProvider = "createPisInvalidData", groups = UNIT, expectedExceptions = { IllegalArgumentException.class })
    public void createPisInvalidTest(final String value) {
        new Pis(value);
    }

    @Test(priority = 2, groups = UNIT)
    public void checkToStringShowPisTest() {

        final Pis pis1 = new Pis("120.3011.717-1");
        final Pis pis2 = new Pis("12030117171");
        final Pis pis3 = new Pis("120.6564.885-8");

        Assert.assertEquals("Pis[value=120.3011.717-1]", pis1.toString());

        Assert.assertEquals("120.3011.717-1", pis1.toShow());

        Assert.assertEquals("12030117171", pis1.getValue());

        Assert.assertEquals(pis1, pis2);

        Assert.assertNotEquals(pis1, pis3);
    }
}
