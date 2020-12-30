package org.easystructure.domain.valueobject;

import org.easystructure.domain.valueobject.personal.RegistroGeral;
import static org.allsetconfigtest.TestingLevels.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class RegistroGeralTest {

    @DataProvider(name = "createRegistroGeralInvalidData")
    public Object[][] createRegistroGeralInvalidData() {

        return new Object[][]{ // 
                { "" }, { "   " }, { null }, //  
                { "40.328.944-1" }, { "2.977.268" }, };
    }

    @Test(priority = 1, dataProvider = "createRegistroGeralInvalidData", groups = UNIT, expectedExceptions = { IllegalArgumentException.class })
    public void createRegistroGeralInvalidTest(final String value) {
        new RegistroGeral(value);
    }

    @Test(priority = 2, groups = UNIT)
    public void checkToStringShowRegistroGeralTest() {

        //  40.328.944-0
        //  2.977.269          
        final RegistroGeral registroGeral1 = new RegistroGeral("403289440");
        final RegistroGeral registroGeral2 = new RegistroGeral("40.328.944-0");
        final RegistroGeral registroGeral3 = new RegistroGeral("2.977.269");

        Assert.assertEquals("RegistroGeral[value=40.328.944-0]", registroGeral1.toString());

        Assert.assertEquals("40.328.944-0", registroGeral1.toShow());

        Assert.assertEquals("2.977.269", registroGeral3.toShow());

        Assert.assertEquals("403289440", registroGeral1.getValue());

        Assert.assertEquals(registroGeral1, registroGeral2);

        Assert.assertNotEquals(registroGeral1, registroGeral3);
    }
}
