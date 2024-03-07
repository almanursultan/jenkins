package day_6;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pojo.CustomResponse;
import utilities.APIRunner;
import utilities.Config;

import static utilities.CashwiseAuthorization.getToken;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GetSingleData {
    /**
     * Get single bank account
     * Create Object mapper
     * Create CustomResponse
     * GET bankID in class level
     * https://backend.cashwise.us   /api/myaccount/bankaccount/  1202
     */

    String bankID = "";


    @Test
    public void test_1_getSingleBankAccount() throws JsonProcessingException {
        bankID = "982";

        //Step - 1
        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount/" + bankID;

        //Step - 2 Hit GET request
        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .get(url);

        response.prettyPrint();

//            Step - 3 create ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        //Step - 4 create CustomResponse class and Handle exception
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);

        System.out.println("My ID: " + customResponse.getId());



    }

    @Test
    public void test_2_getSingleBankAccount(){
        String path = "/api/myaccount/bankaccount/982";
        APIRunner.runGET(path);
        String bankId = APIRunner.getCustomResponse().getId();
        String bankAccountName = APIRunner.getCustomResponse().getBank_account_name();
        double balance = APIRunner.getCustomResponse().getBalance();
        System.out.println(bankId +  " its bank id");
        System.out.println(bankAccountName+" bank account name");
        System.out.println(balance+ " balance ");

    }
    @Test
    public void test_3_getSingleSellerId(){
        String path = "/api/myaccount/sellers/3469";
        APIRunner.runGET(path);
        double sellerId = APIRunner.getCustomResponse().getSeller_id();
        String sellerName = APIRunner.getCustomResponse().getSeller_name();
        System.out.println("seller ID "+ sellerId+", seller name is "+sellerName);
    }
    @Test
    public void test_4_deleteSingleSeller(){
        String url = Config.getProperty("baseUrl") + "/api/myaccount/sellers/3469";
        Response response = RestAssured.given().auth().oauth2(getToken()).delete(url);
        System.out.println("my status code:" + response.statusCode());
        Assert.assertEquals(200, response.statusCode());
    }

    @Test
    public void test_5_getSingleProduct(){
        String path = "/api/myaccount/products/496";
        APIRunner.runGET(path);
        System.out.println("product ID "+APIRunner.getCustomResponse().getProduct_id());
        System.out.println("product title "+ APIRunner.getCustomResponse().getProduct_title());
        System.out.println("product price "+ APIRunner.getCustomResponse().getProduct_price());

        Assert.assertNotNull(APIRunner.getCustomResponse().getProduct_id());
        Assert.assertNotNull(APIRunner.getCustomResponse().getProduct_title());
        Assert.assertNotNull(APIRunner.getCustomResponse().getProduct_price());

    }



}


