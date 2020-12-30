package org.easystructure.domain.valueobject;

import org.easystructure.domain.valueobject.communication.Ddd;
import org.easystructure.domain.valueobject.communication.Telefone;
import static org.allsetconfigtest.TestingLevels.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TelefoneTest {

    @DataProvider(name = "createTelefoneInvalidData")
    public Object[][] createTelefoneInvalidData() {
        return new Object[][]{ //   
                { "" }, { "   " }, { null }, //  
                { "10906897005" }, { "AAAABBBC" }, };
    }

    @Test(priority = 1, dataProvider = "createTelefoneInvalidData", groups = UNIT, expectedExceptions = { IllegalArgumentException.class })
    public void createTelefoneInvalidTest(final String value) {
        new Telefone(value);
    }

    @Test(priority = 2, groups = UNIT)
    public void checkToStringShowTelefoneTest() {
        final Telefone telefone1 = new Telefone("11970584621");
        final Telefone telefone2 = new Telefone("(11)97058-4621");
        final Telefone telefone3 = new Telefone("(11)7058-3622");

        Assert.assertEquals("Telefone[value=(11)97058-4621]", telefone1.toString());

        Assert.assertEquals("(11)97058-4621", telefone1.toShow());

        Assert.assertEquals("11970584621", telefone2.getValue());

        Assert.assertTrue(telefone1.equals(telefone2));

        Assert.assertFalse(telefone1.equals(telefone3));

        Assert.assertEquals("(11)7058-3622", telefone3.toShow());

        Assert.assertTrue(telefone3.getDdd().equals(Ddd.Ddd11));
    }
}
