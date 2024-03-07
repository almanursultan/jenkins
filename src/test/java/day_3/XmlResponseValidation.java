package day_3;

import com.github.javafaker.Faker;
import io.cucumber.java.bs.A;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.logging.XMLFormatter;

public class XmlResponseValidation {
    static String id = "";

    @Test
    public void test_1_createTraveler() {
        String url = "http://restapi.adequateshop.com/api/Traveler";
        Faker faker = new Faker();
        String name = faker.name().firstName();
        String email = faker.internet().emailAddress();
        String address = faker.address().fullAddress();
        String requestBody = "<?xml version=\"1.0\"?>\n" +
                "<Travelerinformation>\n" +
                "    <name>" + name + "</name>\n" +
                "    <email>" + email + "</email>\n" +
                "    <adderes>" + address + "</adderes>\n" +
                "</Travelerinformation>";

        Response response = RestAssured.given()
                .contentType(ContentType.XML)
                .body(requestBody).post(url);
        response.prettyPrint();

        id = response.xmlPath().getString("Travelerinformation.id");
        System.out.println("my id " + id);

    }

    @Test
    public void test_2_getTraveler() {
        String url = "http://restapi.adequateshop.com/api/Traveler/" + id;
        Response response = RestAssured.get(url);
        response.prettyPrint();
        String actName = response.xmlPath().getString("Travelerinformation.name");
        String acEmail = response.xmlPath().getString("Travelerinformation.email");
        Assert.assertNotNull(actName);
        Assert.assertNotNull(acEmail);
    }

    @Test
    public void test_3_getListOfTravelers() {
        String url = "http://restapi.adequateshop.com/api/Traveler/";
        Response response = RestAssured.get(url);
        response.prettyPrint();
//        String id = response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation.id");
//        String name = response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation.name");
//        String email = response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation.email");
//        System.out.println(id+name+email);

        int sizeOfList = response.xmlPath().getList("TravelerinformationResponse.travelers.Travelerinformation").size();


        for (int i = 0; i < sizeOfList; i++) {
            System.out.println("=========TEST STARTED====================");
            System.out.println(response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation[" + i + "].id"));
            Assert.assertNotNull(response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation[" + i + "].id"));

            System.out.println(response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation[" + i + "].name"));
            Assert.assertNotNull(response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation[" + i + "].name"));

            System.out.println(response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation[" + i + "].email"));
            Assert.assertNotNull(response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation[" + i + "].email"));

            System.out.println("=========:::::::TEST PASSED:::::====================");

        }

    }


}

