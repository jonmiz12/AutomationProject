package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.CartPage;
import pageobjects.LoginPage;
import pageobjects.ProductsPage;
import utils.Excel;

public class AddProducts extends BaseTest {

    @Test
    public void tc01_AddItemsFromProductsPage() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.assertEquals(psp.returnCartCount(), 0);
        psp.assertEquals(psp.actionClickAddOrRemoveByArray(itemsNames[0], true, 0), true);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.assertEquals(cp.isInCartByArray(itemsNames[0]), true);
    }

    @Test
    public void tc02_AddItemsFromProductPage(){
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.assertEquals(psp.returnCartCount(), 0);
        psp.assertEquals(psp.addRemoveToCartByArrayBackToProducts(itemsNames[0], true, 0),true);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.assertEquals(cp.isInCartByArray(itemsNames[0]), true);
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
