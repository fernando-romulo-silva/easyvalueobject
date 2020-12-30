package org.easystructure.domain.valueobject;

import org.easystructure.domain.valueobject.personal.TituloEleitor;
import static org.allsetconfigtest.TestingLevels.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class TituloEleitorTest {

    @DataProvider(name = "createTituloEleitorInvalidData")
    public Object[][] createTituloEleitorInvalidData() {

        return new Object[][]{ // 
                { "" }, { "   " }, { null }, //  
                { "002823082375" }, { "101634601197" }, };
    }

    @Test(priority = 1, dataProvider = "createTituloEleitorInvalidData", groups = UNIT, expectedExceptions = { IllegalArgumentException.class })
    public void createTituloEleitorInvalidTest(final String value) {
        new TituloEleitor(value);
    }

    @Test(priority = 2, groups = UNIT)
    public void checkToStringShowTituloEleitorTest() {

        //  40.328.944-0
        //  2.977.269          
        final TituloEleitor tituloEleitor1 = new TituloEleitor("002823082305");
        final TituloEleitor tituloEleitor2 = new TituloEleitor("0028.2308.2305");
        final TituloEleitor tituloEleitor3 = new TituloEleitor("101634601597");

        Assert.assertEquals("TituloEleitor[value=0028.2308.2305]", tituloEleitor1.toString());

        Assert.assertEquals("0028.2308.2305", tituloEleitor1.toShow());

        Assert.assertEquals("1016.3460.1597", tituloEleitor3.toShow());

        Assert.assertEquals("002823082305", tituloEleitor1.getValue());

        Assert.assertEquals(tituloEleitor1, tituloEleitor2);

        Assert.assertNotEquals(tituloEleitor1, tituloEleitor3);
    }
}
