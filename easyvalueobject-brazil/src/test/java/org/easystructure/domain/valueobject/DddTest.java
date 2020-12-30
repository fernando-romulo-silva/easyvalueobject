package org.easystructure.domain.valueobject;

import java.util.List;

import org.easystructure.domain.valueobject.address.Uf;
import org.easystructure.domain.valueobject.communication.Ddd;
import static org.allsetconfigtest.TestingLevels.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DddTest {

    @Test(priority = 1, groups = UNIT)
    public void checkToStringShowDddTest() {

        final Ddd ddd1 = Ddd.Ddd11;
        final Ddd ddd2 = Ddd.Ddd11;
        final Ddd ddd3 = Ddd.Ddd18;

        Assert.assertEquals("Ddd[value=11]", ddd1.toString());

        Assert.assertEquals("11", ddd1.toShow());

        Assert.assertEquals("18", ddd3.getValue());

        Assert.assertEquals(Uf.SP, ddd1.getUf());

        Assert.assertEquals("São Paulo/Região Metropolitana", ddd2.getRegiao());

        Assert.assertEquals(ddd1, ddd2);

        Assert.assertNotEquals(ddd1, ddd3);

        final List<String> ddds = Ddd.getDdds();

        Assert.assertTrue(ddds.contains(ddd1.getValue()));
        
        Assert.assertEquals(Ddd.getDdd("11").getValue(), "11");
    }
}
