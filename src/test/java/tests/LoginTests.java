package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.LoginPage;
import pageobjects.ProductsPage;
import utils.Excel;

public class LoginTests extends BaseTest{

    @Test(dataProvider = "getDataFromExcel")
    public void tc01_standardUser(String username, String password, String expectedError){
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        if (expectedError!="") {
            Assert.assertEquals(lp.returnErrorMessage(), expectedError);
        } else {
            if (expectedError==""){
                ProductsPage psp = new ProductsPage(driver);
                psp.logout();
            }
        }
    }

    @DataProvider
    public Object[][] getDataFromExcel(){
        String execelPath = "./src/test/resources/data/usernamesandpasswords.xlsx";
        Object[][] doubleDtable = Excel.getTableArray(execelPath, "Array");
        Object[][] temp = new Object[doubleDtable.length][doubleDtable[0].length];
        for (int i = 0; i < doubleDtable.length; i++) {
            for (int j = 0; j < doubleDtable[0].length; j++) {
                temp[i][j] = doubleDtable[i][j];
            }
        }
        return temp;
    }
}
