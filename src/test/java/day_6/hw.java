package day_6;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojo.CustomResponse;
import pojo.RequestBody;
import pojo.RequestBody;
import utilities.APIRunner;
import utilities.Config;

import static utilities.CashwiseAuthorization.getToken;
public class hw {
    //https://backend.cashwise.us/api/myaccount/products

@Test
    public void test_1_create_new_product() throws JsonProcessingException {
    Faker faker = new Faker();
    String path = "/api/myaccount/products";

    RequestBody requestBody = new RequestBody();
    requestBody.setProduct_title(faker.commerce().productName());
    requestBody.setProduct_price(faker.number().numberBetween(200, 15000));
    requestBody.setProduct_description(faker.commerce().productName()+" is product name");
    APIRunner.runPOST(path,  requestBody );

    System.out.println("product title is "+ APIRunner.getCustomResponse().getProduct_title());
    System.out.println("product price is "+ APIRunner.getCustomResponse().getProduct_price() );
    System.out.println(" product descr "+ APIRunner.getCustomResponse().getProduct_description());






}
}
