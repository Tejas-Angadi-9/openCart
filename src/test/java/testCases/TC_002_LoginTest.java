package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {

    @Test
    public void verify_login() throws InterruptedException {
        try {

            logger.info("*** Starting TC_002_LoginTest ***");
            logger.debug("Capturing application debug logs");

            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on myaccount link");
            hp.clickLogin();
            logger.info("Clicked on login link");

            LoginPage lp = new LoginPage(driver);
            logger.info("Entering valid email and password");
            lp.setEmail(prop.getProperty("email"));
            lp.setPassword(prop.getProperty("password"));
            lp.clickLogin();
            logger.info("Clicked on login button");
            Thread.sleep(3000);

            // My Account Page
            MyAccountPage macc = new MyAccountPage(driver);
            boolean targetPage = macc.isMyAccountPageExists();

            if (targetPage) {
                logger.info("Login test passed...");
                Assert.assertTrue(true);
            } else {
                logger.error("Login Failed....");
                Assert.fail();
            }
        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("*** Finished TC_002_LoginTest ***");
    }
}
