package tests;

import base.BaseTest;
import org.testng.annotations.Test;

public class loginSalesforce extends BaseTest{



    @Test
    //opening the Salesforce login page;

    public void salesforceLogin() {

        driver.get("https://login.salesforce.com/?locale=uk");
        driver.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println(driver.getTitle());
    }

}
