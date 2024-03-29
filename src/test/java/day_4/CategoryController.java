package day_4;

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
public class CategoryController {
    Faker faker = new Faker();
    static  String categoryId= "";
    @Test
    public void test_1_createNewCategory(){
        String url = Config.getProperty("baseUrl")+"/api/myaccount/categories";
        String category = faker.commerce().department();
        String descr = category+ " company";

        RequestBody requestBody = new RequestBody();
        requestBody.setCategory_title(category);
        requestBody.setCategory_description(descr);
        requestBody.setFlag(false);
        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(url);

        response.prettyPrint();
        categoryId = response.jsonPath().getString("category_id");



    }

    @Test
    public void test_2_getSingleCategory() throws JsonProcessingException {
        String url = Config.getProperty("baseUrl")+ "/api/myaccount/categories/"+categoryId;

        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .get(url);

        System.out.println("=======get data by json path ========");
        System.out.println("status code "+response.statusCode());
        System.out.println(response.jsonPath().getString("category_id"));
        System.out.println(response.jsonPath().getString("category_title"));
        System.out.println(response.jsonPath().getString("category_description"));


        System.out.println("====================================");

        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        System.out.println(   customResponse.getCategory_id()     );
        System.out.println(   customResponse.getCategory_title()  );
        System.out.println(   customResponse.getCategory_description()   );


        System.out.println("test started");
        Assert.assertNotNull(customResponse.getCategory_id()  );
        Assert.assertNotNull(customResponse.getCategory_title()   );
        Assert.assertNotNull( customResponse.getCategory_description()  );
        System.out.println("test passed");

    }
}
