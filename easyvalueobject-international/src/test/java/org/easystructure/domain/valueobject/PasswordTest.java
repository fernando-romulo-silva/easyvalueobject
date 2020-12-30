package org.easystructure.domain.valueobject;

import org.easystructure.domain.valueobject.system.Password;
import static org.allsetconfigtest.TestingLevels.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class PasswordTest {

    @DataProvider(name = "createPasswordInvalidData")
    public Object[][] createPasswordInvalidData() {

        return new Object[][]{ //
                { "" }, { "   " }, { null }, { "123mudar" }, //
                { "weakPass" }, { "12345678" }, //
        };
    }

    @Test(priority = 1, dataProvider = "createPasswordInvalidData", groups = UNIT, expectedExceptions = { IllegalArgumentException.class })
    public void createPasswordInvalidTest(final String value) {
        new Password(value);
    }

    @Test(priority = 2, groups = UNIT)
    public void checkToStringShowPasswordTest() {

        final Password password1 = new Password("$uperTest123$");
        final Password password2 = new Password("$uperTest123$");
        final Password password3 = new Password("MyP@ss4You480");

        Assert.assertEquals(password1, password2);

        Assert.assertNotEquals(password1, password3);
        
        Assert.assertEquals("LAiQy6YdSwz2jJhQkyCE6A==", password1.getValue());
        
        
        // Assert.assertEquals("Password[value=*************]", password1.toString());
        //
        // Assert.assertEquals("**************", password1.toShow());
    }
}
