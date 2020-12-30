package org.easystructure.domain.valueobject;

import org.easystructure.domain.valueobject.vehicle.Renavam;
import static org.allsetconfigtest.TestingLevels.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RenavamTest {

    @DataProvider(name = "createRenavamInvalidData")
    public Object[][] createRenavamInvalidData() {

        return new Object[][]{ // 
                { "" }, { "   " }, { null }, //  
                { "111" }, { "aabbccaabbc" }, // 
                { "38649436341" }, { "3864943634" }, // 
        };
    }

    @Test(priority = 1, dataProvider = "createRenavamInvalidData", groups = UNIT, expectedExceptions = { IllegalArgumentException.class })
    public void createRenavamInvalidTest(final String value) {
        new Renavam(value);
    }

    @Test(priority = 2, groups = UNIT)
    public void checkToStringShowRenavamTest() {
        final Renavam renavam1 = new Renavam("75453936689");
        final Renavam renavam2 = new Renavam("75453936689");
        final Renavam renavam3 = new Renavam("23690315391");

        Assert.assertEquals("Renavam[value=75453936689]", renavam1.toString());

        Assert.assertEquals("75453936689", renavam1.toShow());

        Assert.assertEquals("75453936689", renavam2.getValue());

        Assert.assertEquals(renavam1, renavam2);

        Assert.assertNotEquals(renavam1, renavam3);
    }

}
