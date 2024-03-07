package day_7;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import pojo.CustomResponse;
import utilities.CashwiseAuthorization;
import utilities.Config;

public class PaymentPractice {
    @Test
    public void test_1_getSinglePayment() throws JsonProcessingException {
        String url = Config.getProperty("baseUrl") + "/api/myaccount/payments/219";
        Response response = RestAssured.given()
                .auth().oauth2(CashwiseAuthorization.getToken())
                .get(url);

        response.prettyPrint();
        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        System.out.println("total pay "+customResponse.getResponse().getTotal_pay());
        System.out.println("Invoice title "+customResponse.getResponse().getInvoice_title());

        System.out.println("Category id: "+customResponse.getCategory_response()[0].getCategory_id());

        System.out.println("category title "+customResponse.getCategory_response()[0].getCategory_title());
    }



}
