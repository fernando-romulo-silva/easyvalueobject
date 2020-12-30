package org.easystructure.domain.valueobject;

import org.easystructure.domain.valueobject.address.Uf;
import org.easystructure.domain.valueobject.business.InscricaoEstadual;
import static org.allsetconfigtest.TestingLevels.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class InscricaoEstadualTest {

    @DataProvider(name = "createInscricaoEstadualInvalidData")
    public Object[][] createInscricaoEstadualInvalidData() {

        return new Object[][]{ //
                { Uf.AC, "" }, { Uf.AM, "   " }, { Uf.BA, null }, //
                { null, "963.170.560.718" }, //
                { Uf.AC, "01.565.094/363-97" }, { Uf.AL, "348314270" }, { Uf.AP, "037445228" }, //
                { Uf.AM, "53.764.317-3" }, { Uf.BA, "BA-053982-23" }, { Uf.CE, "89855751-0" }, //
                { Uf.DF, "07721304001-68" }, { Uf.ES, "57901973-1" }, { Uf.GO, "10.992.971-7" }, //
                { Uf.MA, "12561149-0" }, { Uf.MT, "6354309353-0" }, { Uf.MS, "29040257-0" }, //
                { Uf.MG, "336.485.172/2611" }, { Uf.PA, "15-452931-2" }, { Uf.PB, "30678882-4" }, //
                { Uf.PR, "907.91583-98" }, { Uf.PE, "0321418-41" }, { Uf.PE, "18.1.001.0000005-9" }, { Uf.PI, "92472275-1" }, //
                { Uf.RJ, "18.306.15-0" }, { Uf.RN, "20.747.362-7" }, { Uf.RS, "504/6722054" }, //
                { Uf.RO, "2969764608865-4" }, { Uf.RR, "24116823-1" }, { Uf.SP, "963.170.560.719" }, //
                { Uf.SC, "996.004.301" }, { Uf.SE, "97708097-0" }, { Uf.TO, "1403501474-2" }, //
        };
    }

    @Test(priority = 1, dataProvider = "createInscricaoEstadualInvalidData", groups = UNIT, expectedExceptions = { IllegalArgumentException.class })
    public void createInscricaoEstadualInvalidTest(final Uf uf, final String value) {
        new InscricaoEstadual(uf, value);
    }

    @Test(priority = 2, groups = UNIT)
    public void checkToStringShowInscricaoEstadualTest() {

        // 40.328.944-0
        // 2.977.269
        final InscricaoEstadual inscricaoEstadual1 = new InscricaoEstadual(Uf.SP, "963170560718");
        final InscricaoEstadual inscricaoEstadual2 = new InscricaoEstadual(Uf.SP, "963.170.560.718");
        final InscricaoEstadual inscricaoEstadual3 = new InscricaoEstadual(Uf.PR, "9069158398");

        Assert.assertEquals("InscricaoEstadual[uf=SP,value=963.170.560.718]", inscricaoEstadual1.toString());

        Assert.assertEquals("963.170.560.718", inscricaoEstadual1.toShow());

        Assert.assertEquals("906.91583-98", inscricaoEstadual3.toShow());

        Assert.assertEquals("963170560718", inscricaoEstadual2.getValue());

        Assert.assertEquals(inscricaoEstadual1, inscricaoEstadual2);

        Assert.assertNotEquals(inscricaoEstadual1, inscricaoEstadual3);
    }

    @DataProvider(name = "createInscricaoEstadualValidData")
    public Object[][] createInscricaoEstadualValidData() {
        return new Object[][]{ //
                { Uf.AC, "01.565.095/363-97" }, { Uf.AL, "248314270" }, { Uf.AP, "037445227" }, //
                { Uf.AM, "52.764.317-3" }, { Uf.BA, "053982-21" }, { Uf.CE, "99855751-0" }, //
                { Uf.DF, "07721304001-67" }, { Uf.ES, "57901973-0" }, { Uf.GO, "10.992.871-7" }, //
                { Uf.MA, "12561139-0" }, { Uf.MT, "6354309352-0" }, { Uf.MS, "28040257-0" }, //
                { Uf.MG, "336.485.172/2610" }, { Uf.PA, "15-452931-1" }, { Uf.PB, "30668882-4" }, //
                { Uf.PR, "906.91583-98" }, { Uf.PE, "0321418-40" }, { Uf.PE, "18.1.001.0000004-9" }, { Uf.PI, "92472285-1" }, //
                { Uf.RJ, "18.306.14-0" }, { Uf.RN, "20.747.361-7" }, { Uf.RS, "504/6721054" }, //
                { Uf.RO, "2969664608865-4" }, { Uf.RR, "24116723-1" }, { Uf.SP, "963.170.560.718" }, //
                { Uf.SC, "996.004.300" }, { Uf.SE, "97708096-0" }, { Uf.TO, "1403401474-2" }, //
        };
    }

    @Test(priority = 3, dataProvider = "createInscricaoEstadualValidData", groups = UNIT)
    public void createInscricaoEstadualValidTest(final Uf uf, final String value) {
        final InscricaoEstadual ie = new InscricaoEstadual(uf, value);

        final String fullIe = ie.toShow();

        Assert.assertEquals(fullIe, value);
    }
}
