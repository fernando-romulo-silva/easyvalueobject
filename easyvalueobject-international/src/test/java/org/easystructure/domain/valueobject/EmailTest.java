package org.easystructure.domain.valueobject;

import org.easystructure.domain.valueobject.communication.Email;
import static org.allsetconfigtest.TestingLevels.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class EmailTest {

    @DataProvider(name = "createEmailInvalidData")
    public Object[][] createEmailInvalidData() {

        return new Object[][]{ // 
                { "" }, { "   " }, { null }, //  
                { "test.com" }, { "myemail@com" },
        };
    }

    @Test(priority = 1, dataProvider = "createEmailInvalidData", groups = UNIT, expectedExceptions = { IllegalArgumentException.class })
    public void createEmailInvalidTest(final String value) {
        new Email(value);
    }

    @Test(priority = 2, groups = UNIT)
    public void checkToStringShowEmailTest() {
        final Email email1 = new Email("someone@gmail.com");
        final Email email2 = new Email("someone@gmail.com");

        Assert.assertEquals("Email[value=someone@gmail.com]", email1.toString());

        Assert.assertEquals("someone@gmail.com", email1.toShow());

        Assert.assertEquals("someone@gmail.com", email1.getValue());

        Assert.assertEquals(email1, email2);
    }
}
