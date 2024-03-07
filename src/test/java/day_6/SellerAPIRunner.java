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

public class SellerAPIRunner {
    @Test
    public void test_1_getListOfSellers() {
        String path = "/api/myaccount/sellers/all";
        APIRunner.runGET(path);
        CustomResponse[] customResponses = APIRunner.getCustomResponseArray();


        for (int i = 0; i < customResponses.length; i++) {
            System.out.println("Id: " + customResponses[i].getSeller_id());
            Assert.assertNotNull(customResponses[i].getSeller_id());
            System.out.println("bank account name " + customResponses[i].getSeller_name());
            Assert.assertNotNull(customResponses[i].getSeller_name());



        }
    }
    @Test
    public void test_2_createNewBankAccount() throws  JsonProcessingException {
        Faker faker = new Faker();
        // https://backend.cashwise.us   /api/myaccount/bankaccount    // STEP -==> set up your URL
        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount";


        RequestBody requestBody = new RequestBody();
        requestBody.setType_of_pay("CASH");
        requestBody.setBank_account_name(  faker.company().name()+ " bank"  );
        requestBody.setDescription( faker.commerce().department()+ " company" );
        requestBody.setBalance( faker.number().numberBetween(200, 15000)  );

        Response response = RestAssured.given()
                .auth().oauth2(   getToken()  )
                .contentType( ContentType.JSON )
                .body( requestBody )
                .post(url );


        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(  response.asString(), CustomResponse.class  );
        response.prettyPrint();

    }
    @Test
    public void test_3_createSeller(){
        Faker faker = new Faker();
        String path = "/api/myaccount/sellers";

        RequestBody requestBody = new RequestBody();
        requestBody.setSeller_name(faker.name().fullName());
        requestBody.setEmail(faker.internet().emailAddress());
        requestBody.setAddress(faker.address().fullAddress());

        APIRunner.runPOST(path,  requestBody );
        System.out.println("Seller id: "+APIRunner.getCustomResponse().getSeller_id());
        System.out.println("Seller Email: "+APIRunner.getCustomResponse().getEmail());
        System.out.println("Seller Address: "+APIRunner.getCustomResponse().getAddress());
        Assert.assertNotNull(APIRunner.getCustomResponse().getSeller_id());


    }
}

