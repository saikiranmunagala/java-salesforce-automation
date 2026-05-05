package tests;

import base.BaseTest;
import org.testng.annotations.Test;

public class loginSalesforce extends BaseTest{



    @Test
    //opening the Salesforce login page;

    public void salesforceLogin() throws InterruptedException {

        driver.get("https://login.salesforce.com/?locale=uk");
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(driver.getTitle());
    }

}
