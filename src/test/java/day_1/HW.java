package day_1;

import io.cucumber.java.bs.A;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.CashwiseAuthorization;
import utilities.Config;

public class HW {
    @Test
    public void test_1_getListOfSellers() {
        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount";
        String token = CashwiseAuthorization.getToken();
//        System.out.println( url+ " "+ token);

        Response response = RestAssured.given().auth().oauth2(token).get(url);

//        response.prettyPrint();

        String jsonResponse = response.getBody().asString();
        int arraySize = response.jsonPath().getList("yourArrayPath").size();
        System.out.println("array size " + arraySize);
        for (int i = 0; i < arraySize; i++) {
            System.out.println("=============");

            String id = response.jsonPath().getString("[" + i + "].id");
            String bankName = response.jsonPath().getString("[" + i + "].bank_account_name");
            String description = response.jsonPath().getString("[" + i + "].description");
            String typeOfPay = response.jsonPath().getString("[" + i + "].type_of_pay");
            String balance = response.jsonPath().getString("[" + i + "].balance");
            System.out.println("id " + id + " , bank name is " + bankName + " ,description " +
                    description + " ,type of pay is " + typeOfPay + " ,balance is " + balance);


            Assert.assertNotNull("id is null", id);
            Assert.assertNotNull("Bank account is null", bankName);
            Assert.assertNotNull("Description is null", description);
            Assert.assertNotNull("Type of pay is null", typeOfPay);
            Assert.assertNotNull("Balance is null", balance);

        }
    }

}
