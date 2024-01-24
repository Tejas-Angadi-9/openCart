package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

    @Test
    public void verify_account_registration() {
        logger.info("*** STARTING TC_001_AccountRegistrationTest ***");

        logger.debug("Application logs");
        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        logger.info("Clicked on account link");
        hp.clickRegister();
        logger.info("Clicked on registration link");

        logger.info("Enter customer details");
        AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
        regpage.setFirstName(randomeString().toUpperCase());
        regpage.setLastName(randomeString().toUpperCase());
        regpage.setEmail(randomeString() + "@gmail.com");// randomly generated the email
        regpage.setTelephone(randomeNumber());

        String password = randomAlphaNumeric();
        regpage.setPassword(password);
        regpage.setConfirmPassword(password);

        logger.info("Entered all the details");

        regpage.setPrivacyPolicy();
        regpage.clickContinue();
        logger.info("Clicked on continue...");

        logger.info("Validating expected message");
        String confmsg = regpage.getConfirmationMsg();

        if (confmsg.equals("Your Account Has Been Created!")) {
//                Assert.assertEquals(confmsg, "Your Account Has Been Created!");
            logger.info("Test Passed....");
            Assert.assertTrue(true);
        } else {
            logger.error("Test failed");
            Assert.fail();
        }

        logger.info("*** FINISHED TC_001_AccountRegistrationTest ***");
    }
}








