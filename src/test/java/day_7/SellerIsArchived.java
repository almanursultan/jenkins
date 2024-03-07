package day_7;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.CustomResponse;
import utilities.APIRunner;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

import static utilities.CashwiseAuthorization.getToken;

public class SellerIsArchived {
    @Test
    public void test_1_getAllIsArchived() throws JsonProcessingException {
        String url = Config.getProperty("baseUrl") + "/api/myaccount/sellers";
        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 10);

        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .contentType(ContentType.JSON)
                .params(params)
                .get(url);

//        response.prettyPrint();
        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponses = mapper.readValue(response.asString(), CustomResponse.class);
        int sizeOfArray = customResponses.getResponses().length;
        for (int i = 0; i < sizeOfArray; i++) {

            System.out.println("Seller id " + customResponses.getResponses()[i].getSeller_id());
            Assert.assertNotNull(customResponses.getResponses()[i].getSeller_id());
            System.out.println("Seller name " + customResponses.getResponses()[i].getSeller_name());
            Assert.assertNotNull(customResponses.getResponses()[i].getSeller_name());
        }

    }

    @Test
    public void test_1_getAllIsArchived_APIRunner() {
        String path = "/api/myaccount/sellers";
        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 10);

        APIRunner.runGET(path, params);

//        int sizeOfArray = APIRunner.getCustomResponse().getResponses().length;

        CustomResponse[] customResponseArrayOfSellers = APIRunner.getCustomResponse().getResponses();
        int sizeOfArray = customResponseArrayOfSellers.length;

        for (int i = 0; i < sizeOfArray; i++) {

            System.out.println("Seller id " + customResponseArrayOfSellers[i].getSeller_id());
            Assert.assertNotNull(customResponseArrayOfSellers[i].getSeller_id());
            System.out.println("Seller name " + customResponseArrayOfSellers[i].getSeller_name());
            Assert.assertNotNull(customResponseArrayOfSellers[i].getSeller_name());
        }

    }

    @Test
    public void test_3_getAllSellersIsArchived() {
        String path = "/api/myaccount/sellers";
        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", true);
        params.put("page", 1);
        params.put("size", 5);

        APIRunner.runGET(path, params);

//        int sizeOfArray = APIRunner.getCustomResponse().getResponses().length;

        CustomResponse[] customResponseArrayOfSellers = APIRunner.getCustomResponse().getResponses();
        int sizeOfArray = customResponseArrayOfSellers.length;

        for (int i = 0; i < sizeOfArray; i++) {

            System.out.println("Seller id " + customResponseArrayOfSellers[i].getSeller_id());
            Assert.assertNotNull(customResponseArrayOfSellers[i].getSeller_id());
            System.out.println("Seller name " + customResponseArrayOfSellers[i].getSeller_name());
            Assert.assertNotNull(customResponseArrayOfSellers[i].getSeller_name());
        }

    }

}
