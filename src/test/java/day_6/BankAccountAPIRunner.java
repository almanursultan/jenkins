package day_6;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.CustomResponse;
import pojo.RequestBody;
import utilities.APIRunner;
import utilities.Config;

import static utilities.CashwiseAuthorization.getToken;

public class BankAccountAPIRunner {
    String bankId = "";

    @Test
    public void test_1_getListOfBanks() throws JsonProcessingException {
        //https://backend.cashwise.us/api/myaccount/bankaccount
        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount/";
        // hit get request with restAssured
        Response response = RestAssured.given().auth().oauth2(getToken()).get(url);
        System.out.println("my status code:" + response.statusCode());

        Assert.assertEquals(200, response.statusCode());
        System.out.println("========deserialization==========");
//        response.prettyPrint();
        // create objectMapper class
        ObjectMapper mapper = new ObjectMapper();
        //since we are getting list of banks we have to declare array of banks
        CustomResponse[] customResponses = mapper.readValue(response.asString(), CustomResponse[].class);
        // get size of array
        int sizeOfBankAccounts = customResponses.length;

        //print out all banks account and assert them
        for (int i = 0; i < sizeOfBankAccounts; i++) {
            System.out.println("Bank id: " + customResponses[i].getId());
            Assert.assertNotNull(customResponses[i].getId());
            Assert.assertNotNull(customResponses[i].getBank_account_name());


        }


    }

    @Test
    public void test_2_getListOfBankAccount_apiRunner() {
        String path = "/api/myaccount/bankaccount/";
        APIRunner.runGET(path);
        CustomResponse[] customResponses = APIRunner.getCustomResponseArray();

        int sizeOfArray = customResponses.length;

        for (int i = 0; i < sizeOfArray; i++) {
            System.out.println("Id: " + customResponses[i].getId());
            Assert.assertNotNull(customResponses[i].getId());
            System.out.println("bank account name " + customResponses[i].getBank_account_name());
            Assert.assertNotNull(customResponses[i].getBank_account_name());
        }
    }

    @Test
    public void test_3_createNewBankAccount() throws JsonProcessingException {
        Faker faker = new Faker();
        // https://backend.cashwise.us   /api/myaccount/bankaccount    // STEP -==> set up your URL
        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount";

        RequestBody requestBody = new RequestBody();
        requestBody.setType_of_pay("CASH");
        requestBody.setBank_account_name(faker.company().name() + " bank");
        requestBody.setDescription(faker.commerce().department() + " company");
        requestBody.setBalance(faker.number().numberBetween(200, 15000));

        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(url);

        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);

        String bankId = customResponse.getId();
        response.prettyPrint();

    }

    @Test
    public void test_4_createNewBankAccount_apiRunner() {
        Faker faker = new Faker();
        String path = "/api/myaccount/bankaccount";
        RequestBody requestBody = new RequestBody();
        requestBody.setType_of_pay("CASH");
        requestBody.setBank_account_name(faker.company().name() + " bank");
        requestBody.setDescription(faker.commerce().department() + " company");
        requestBody.setBalance(faker.number().numberBetween(200, 15000));

        APIRunner.runPOST(path, requestBody);

        System.out.println("Bank ID: " + APIRunner.getCustomResponse().getId());
        System.out.println("Bank account name: " + APIRunner.getCustomResponse().getBank_account_name());


    }

    @Test
    public void test_5_deleteBankAccount() {
        // bankId
        // https://backend.cashwise.us   /api/myaccount/bankaccount/   + bankID

        String path = "/api/myaccount/bankaccount/" + bankId;
        System.out.println(path);

        APIRunner.runDELETE(path);


    }
}
