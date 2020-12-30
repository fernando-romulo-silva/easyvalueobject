package org.easystructure.domain.valueobject;

import org.easystructure.domain.valueobject.system.Login;
import static org.allsetconfigtest.TestingLevels.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class LoginTest {

    @DataProvider(name = "createLoginInvalidData")
    public Object[][] createLoginInvalidData() {

        return new Object[][]{ //
                { "" }, { "   " }, { null }, { "123AA" }, //
                { "12065648758" }, { "#test@invalid34" }, //
                { "FUlaNa.test" }
        };
    }

    @Test(priority = 1, dataProvider = "createLoginInvalidData", groups = UNIT, expectedExceptions = { IllegalArgumentException.class })
    public void createLoginInvalidTest(final String value) {
        new Login(value);
    }

    @Test(priority = 2, groups = UNIT)
    public void checkToStringShowLoginTest() {

        final Login login1 = new Login("fernando.romulo.silva");
        final Login login2 = new Login("ana-romulo-silva");
        final Login login3 = new Login("fernando.romulo.silva");

        Assert.assertEquals("Login[value=fernando.romulo.silva]", login1.toString());

        Assert.assertEquals("fernando.romulo.silva", login1.toShow());

        Assert.assertEquals("fernando.romulo.silva", login1.getValue());

        Assert.assertEquals(login1, login3);

        Assert.assertNotEquals(login1, login2);
    }
}
