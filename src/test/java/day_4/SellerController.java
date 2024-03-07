package day_4;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.CashwiseAuthorization;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

import static utilities.CashwiseAuthorization.getToken;

public class SellerController {
    Faker faker = new Faker();
    static String sellerId = "";

    @Test
    public void test_1_createNewSeller() {
        String url = Config.getProperty("baseUrl") + "/api/myaccount/sellers";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("company_name", faker.company().name());
        requestBody.put("seller_name", faker.name().fullName());
        requestBody.put("email", faker.internet().emailAddress());
        requestBody.put("phone_number", faker.phoneNumber().cellPhone());
        requestBody.put("address", faker.address().fullAddress());

        System.out.println("My token:" + getToken());

        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(url);

        Assert.assertEquals("status code is not correct", 201, response.statusCode());
        response.prettyPrint();

        sellerId = response.jsonPath().getString("seller_id");
        System.out.println("Seller id is " + sellerId);
    }

    @Test
    public void test_2_getArchivedSellers() {
        String url = Config.getProperty("baseUrl") + "/api/myaccount/seller";

        Map<String, Object> param = new HashMap<>();
        param.put("isArchived", false);
        param.put("page", 1);
        param.put("size", 10);

        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .body(param)
                .post(url);
        response.prettyPrint();
    }
        @Test
        public void test_tempor(){

            String token = CashwiseAuthorization.getToken();

            System.out.println( "My token: "+token);

        }
    }

