package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Excel;

public class t1_Login extends BaseTest{
    @Description("The test attempts login with each given username and password and verifies the expected error.")
    @Test(dataProvider = "getDataFromExcel")
    public void tc01_userLogin(String username, String password, String expectedError){
        LoginPage.login(username, password);
        if (expectedError!="") {
            Assert.assertEquals(LoginPage.returnErrorMessage(), expectedError);
        } else {
            if (expectedError==""){
                ProductsPage.logout();
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
