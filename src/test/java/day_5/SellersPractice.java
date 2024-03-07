package day_5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Data;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pojo.CustomResponse;
import pojo.RequestBody;
import utilities.Config;

import static utilities.CashwiseAuthorization.getToken;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SellersPractice {
    Faker faker = new Faker();
    static int seller_id;

    @Test
    public void test_1_createNewSeller() throws JsonProcessingException {

        String url = Config.getProperty("baseUrl") + "/api/myaccount/sellers/";
        RequestBody requestBody = new RequestBody();
        requestBody.setSeller_name(faker.name().fullName());
        requestBody.setEmail(faker.internet().emailAddress());
        requestBody.setAddress(faker.address().fullAddress());
        Response response = RestAssured.given().auth().oauth2(getToken())
                .contentType(ContentType.JSON).body(requestBody).post(url);
        System.out.println("my status code " + response.statusCode());
        Assert.assertEquals(201, response.statusCode());
        System.out.println("=====response body=====");
        response.prettyPrint();
        System.out.println("=======Use Object Mapper and Get id=========");
        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);

        seller_id = customResponse.getSeller_id();
        System.out.println("my id: " + seller_id);

    }

    @Test
    public void test_2_getSingleSeller() throws JsonProcessingException {
        String url = Config.getProperty("baseUrl") + "/api/myaccount/sellers/" + seller_id;

        Response response = RestAssured.given().auth().oauth2(getToken())
                .get(url);

        Assert.assertEquals(200, response.statusCode());
        System.out.println("==================");

        ObjectMapper mapper = new ObjectMapper();

        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        System.out.println("======test started=======");
        System.out.println(customResponse.getSeller_id());
        System.out.println(customResponse.getSeller_name());
        System.out.println(customResponse.getEmail());
        System.out.println(customResponse.getAddress());


        Assert.assertNotNull(customResponse.getSeller_id());
        Assert.assertNotNull(customResponse.getSeller_name());
        Assert.assertNotNull(customResponse.getEmail());
        Assert.assertNotNull(customResponse.getAddress());

    }

    @Test
    public void test_3_getListOfBanks() throws JsonProcessingException {

        String url = Config.getProperty("baseUrl") + "/api/myaccount/sellers/all";

        Response response = RestAssured.given().auth().oauth2(getToken()).get(url);
        System.out.println("my status code:" + response.statusCode());

        Assert.assertEquals(200, response.statusCode());
        System.out.println("========deserialization==========");

        ObjectMapper mapper = new ObjectMapper();

        CustomResponse[] customResponses = mapper.readValue(response.asString(), CustomResponse[].class);

        int sizeOfSellers = customResponses.length;


        for (int i = 0; i < sizeOfSellers; i++) {
            System.out.println("Sellers id: " + customResponses[i].getSeller_id());
            System.out.println("sellers name: " + customResponses[i].getSeller_name());
            Assert.assertNotNull(customResponses[i].getSeller_name());
            Assert.assertNotNull(customResponses[i].getSeller_id());

        }


    }

    @Test
    public void test_4_deleteSellers() throws JsonProcessingException {
        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount/" + seller_id;
        Response response = RestAssured.given().auth().oauth2(getToken()).delete(url);
        System.out.println("my status code:" + response.statusCode());

        Assert.assertEquals(200, response.statusCode());
        System.out.println("==================");

    }
}
