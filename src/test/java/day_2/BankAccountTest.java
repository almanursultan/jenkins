package day_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.CashwiseAuthorization;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

public class BankAccountTest {
    @Test
    public void test_1_token_generator(){
        String url = "https://backend.cashwise.us/api/myaccount/auth/login";

        String requestBody = "{\n" +
                "    \"email\": \"myapi@gmail.com\", \n" +
                "    \"password\" : \"bonappetit\"\n" +
                "}";
        Response response = RestAssured.given().
                contentType(ContentType.JSON).body(requestBody).post(url);

        System.out.println("status code "+ response.statusCode());
        response.prettyPrint();

        //x.jwt_token
         String token = response.jsonPath().getString("jwt_token");
        System.out.println("My token "+token);


    }
    @Test
    public void test_2_create_new_account(){

        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJteWFwaUBnbWFpbC5jb20iLCJleHAiOjE3MDcxODI3NjAsImlhdCI6MTcwNjU3Nzk2MH0.eXRXpVrJ9bBmMwsmfT8xkSLE4F7QbRlUQ2Z7I5-jM6Yyy_MMa5GI7CDGT70djennO57plmhE9k6ftV-CZF-G5g";
        String url = "https://backend.cashwise.us/api/myaccount/bankaccount";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("type_of_pay", "BANK" );
        requestBody.put("bank_account_name", "Intellij Bank new" );
        requestBody.put("description", "zoom bank new");
        requestBody.put("balance", 10000 );


        Response response = RestAssured.given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(url);
        System.out.println(response.statusCode());
    }


    @Test
    public void test_3_getListOfBankAccounts() {
        String token = CashwiseAuthorization.getToken();
        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount";
//        System.out.println(token);
//        System.out.println(url);

        Response response = RestAssured.given()
                .auth().oauth2(token)
                .get(url);

        System.out.println("My status code " + response.statusCode());
//        response.prettyPrint();

        int sizeOfBankAccounts = response.jsonPath().getList("").size();
        System.out.println(sizeOfBankAccounts);


        for (int i = 0; i < sizeOfBankAccounts; i++) {
            System.out.println("==============================");

            String id = response.jsonPath().getString("["+ i +"].id");
            String bankName = response.jsonPath().getString("["+ i +"].bank_account_name");
            String descr = response.jsonPath().getString("["+ i +"].description");
            String typeOfPay = response.jsonPath().getString("["+ i +"].type_of_pay");
            String balance = response.jsonPath().getString("["+ i +"].balance");
            System.out.println("id " + id + " , bank name is " + bankName + " ,description " +
                    descr + " ,type of pay is " + typeOfPay + " ,balance is " + balance);

            Assert.assertNotNull("id is null", id);
            Assert.assertNotNull( "Bank account is null" , bankName);
            Assert.assertNotNull( "Description is null" , descr);
            Assert.assertNotNull( "Type of pay is null" , typeOfPay);
            Assert.assertNotNull( "Balance is null" , balance);


            System.out.println("==============================");
        }
    }
    @Test
    public void test_4_getSingleAccount(){
        String url = Config.getProperty("baseUrl")+"/api/myaccount/bankaccount/989";
        String token = CashwiseAuthorization.getToken();

        Response response = RestAssured.given()
                .auth().oauth2(  token  )
                .get( url );
        response.prettyPrint();
        //x.bank_account_name
        String bankAccName = response.jsonPath().getString("bank_account_name");
        Assert.assertNotNull(bankAccName);
        System.out.println(bankAccName);
    }
}
