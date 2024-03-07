package day_5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pojo.CustomResponse;
import pojo.RequestBody;
import utilities.Config;

import static utilities.CashwiseAuthorization.getToken;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BankAccountPractice {
    Faker faker = new Faker();
    static String bankId = "";

    @Test
    public void test_1_createNewBankAccount() throws JsonProcessingException {
//        getToken();
        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount";
        RequestBody requestBody = new RequestBody();
        requestBody.setType_of_pay("CASH");
        requestBody.setBank_account_name(faker.company().name() + " bank");
        requestBody.setDescription(faker.commerce().department() + " company");
        requestBody.setBalance(faker.number().numberBetween(200, 10000));


        // hit api with RestAssured(post)
        Response response = RestAssured.given().auth().oauth2(getToken())
                .contentType(ContentType.JSON).body(requestBody).post(url);
        //print out status code and make sure i have right status code
        System.out.println("my status code " + response.statusCode());
        // assert status code
        Assert.assertEquals(201, response.statusCode());
        //print response body and make sure created new bank
        System.out.println("=====response body=====");
        response.prettyPrint();

        System.out.println("=======Use Object Mapper and Get id=========");

        // use objectMapper to read data from response body
        ObjectMapper mapper = new ObjectMapper();
        // go inside custom response class and specify your variables you want to read(fetch data)
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        //
        bankId = customResponse.getId();
        System.out.println("my id : " + bankId);


    }

    @Test
    public void test_2_getSingleBankAccount() throws JsonProcessingException {
        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount/" + bankId;
        Response response = RestAssured.given().auth().oauth2(getToken()).get(url);
        System.out.println("my status code:" + response.statusCode());

        Assert.assertEquals(200, response.statusCode());
        System.out.println("==================");

        ObjectMapper mapper = new ObjectMapper();

        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        System.out.println("======test started=======");
        System.out.println(customResponse.getId());
        System.out.println(customResponse.getBank_account_name());
        System.out.println(customResponse.getBalance());


        Assert.assertNotNull(customResponse.getId());
        Assert.assertNotNull(customResponse.getBank_account_name());
        Assert.assertNotNull(customResponse.getBalance());

    }

    @Test
    public void test_3_getListOfBanks() throws JsonProcessingException {
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
    public void test_4_deleteBankAccount() throws JsonProcessingException {
        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount/" + bankId;
        Response response = RestAssured.given().auth().oauth2(getToken()).delete(url);
        System.out.println("my status code:" + response.statusCode());

        Assert.assertEquals(200, response.statusCode());
        System.out.println("==================");

    }
}