package org.easystructure.domain.valueobject;

import org.easystructure.domain.valueobject.personal.Cnh;
import static org.allsetconfigtest.TestingLevels.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CnhTest {

    @DataProvider(name = "createCnhInvalidData")
    public Object[][] createCnhInvalidData() {

        return new Object[][]{ // 
                { "" }, { "   " }, { null }, //
                { "111" }, { "43413545762" }, // 
                { "1341905701" }, { "66755722682" }, // 
        };
    }

    @Test(priority = 1, dataProvider = "createCnhInvalidData", groups = UNIT, expectedExceptions = { IllegalArgumentException.class })
    public void createCnhInvalidTest(final String value) {
        new Cnh(value);
    }

    @Test(priority = 2, groups = UNIT)
    public void checkToStringShowCnhTest() {
        final Cnh cnh1 = new Cnh("13419054702");
        final Cnh cnh2 = new Cnh("13419054702");
        final Cnh cnh3 = new Cnh("66755722681");

        Assert.assertEquals("Cnh[value=13419054702]", cnh1.toString());

        Assert.assertEquals("13419054702", cnh1.toShow());

        Assert.assertEquals("13419054702", cnh2.getValue());

        Assert.assertEquals(cnh1, cnh2);

        Assert.assertNotEquals(cnh1, cnh3);
    }
}
