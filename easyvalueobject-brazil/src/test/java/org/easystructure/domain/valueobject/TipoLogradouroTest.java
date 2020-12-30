package org.easystructure.domain.valueobject;

import org.easystructure.domain.valueobject.address.TipoLogradouro;
import static org.allsetconfigtest.TestingLevels.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TipoLogradouroTest {

    @Test(priority = 1, groups = UNIT)
    public void checkToStringShowTipoLogradouroTest() {
        final TipoLogradouro tl1 = TipoLogradouro.RUA;
        final TipoLogradouro tl2 = TipoLogradouro.RUA;
        final TipoLogradouro tl3 = TipoLogradouro.CONDOMINIO;

        Assert.assertEquals(tl1.toString(), "TipoLogradouro[id=35,value=Rua,abb=R.]");

        Assert.assertEquals("Rua", tl1.toShow());

        Assert.assertEquals(35, tl2.getId());

        Assert.assertEquals("RUA", tl1.getValue());

        Assert.assertEquals(tl1, tl2);

        Assert.assertNotEquals(tl1, tl3);
        
        Assert.assertEquals(tl3.getAbb(), "Cond.");

        Assert.assertFalse(TipoLogradouro.getTipoLogradouros().isEmpty());
    }
}
