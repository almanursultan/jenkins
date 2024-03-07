package day_3;

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

public class ProductController {
    String token = CashwiseAuthorization.getToken();

    @Test
    public void test_1_createNewProduct() {
        Faker faker = new Faker();
        String productTitle = faker.commerce().productName();
        double productPrice = faker.number().numberBetween(100, 10000);
        int serviceTypeId = 2;
        int categoryId = 803;
        String productDescription = faker.commerce().productName() + "company";


        String url = Config.getProperty("baseUrl") + "/api/myaccount/products";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("product_title", productTitle);
        requestBody.put("product_price", productPrice);
        requestBody.put("service_type_id", serviceTypeId);
        requestBody.put("category_id", categoryId);
        requestBody.put("product_description", productDescription);
        Response response = RestAssured.given()
                .auth().oauth2(token).contentType(ContentType.JSON).
                body(requestBody).post(url);

        response.prettyPrint();

        String actualProductID = response.jsonPath().getString("product_id");
        System.out.println("=============================================");
//        String actualProdTitle = response.jsonPath().getString("product_title");
//        String actualProdPrice = response.jsonPath().getString("product_price");
//        String actualDescr = response.jsonPath().getString("product_description");
//
//        Assert.assertEquals("Product Title is not correct", productTitle,actualProdTitle);
//        Assert.assertEquals( "product disc is not correct, productPrice,actualProdPrice);
//        Assert.assertEquals("Product Description is not correct", productDescription,actualDescr);


        url = Config.getProperty("baseUrl") + "/api/myaccount/products/" + actualProductID;
        response = RestAssured.given()
                .auth().oauth2(token).contentType(ContentType.JSON).
                body(requestBody).get(url);


        String actualProdTitle = response.jsonPath().getString("product_title");
        String actualProdPrice = response.jsonPath().getString("product_price");
        String actualDescr = response.jsonPath().getString("product_description");

        Assert.assertEquals("Product Title is not correct", productTitle,actualProdTitle);
        Assert.assertEquals("Product price is not correct",productPrice+"",actualProdPrice);
        Assert.assertEquals("Product descr is not correct", productDescription, actualDescr);




    }


}

