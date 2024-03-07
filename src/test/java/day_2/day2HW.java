package day_2;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.CashwiseAuthorization;

public class day2HW {
    static String id = "";
    @Test
    public void test_1_postNewDog(){
        String url = "https://petstore.swagger.io/v2/pet";
        Faker faker = new Faker();
        String dogName = faker.dog().name();
        String requestBody = "<Pet>\n" +
                "    <id>0</id>\n" +
                "    <Category>\n" +
                "        <id>0</id>\n" +
                "        <name>Dog</name>\n" +
                "    </Category>\n" +
                "    <name>"+dogName+"</name>\n" +
                "    <photoUrls>\n" +
                "        <photoUrl>string</photoUrl>\n" +
                "    </photoUrls>\n" +
                "    <tags>\n" +
                "        <Tag>\n" +
                "            <id>0</id>\n" +
                "            <name>string</name>\n" +
                "        </Tag>\n" +
                "    </tags>\n" +
                "    <status>available</status>\n" +
                "</Pet>";

        Response response = RestAssured.given()
                .contentType(ContentType.XML)
                .body(requestBody).post(url);
        response.prettyPrint();


        id = response.jsonPath().getString("id");
        System.out.println("my id " + id);
        response.prettyPrint();


    }

    @Test
    public void test_2_getDogById(){
        String url = "https://petstore.swagger.io/v2/pet/"+ id;
        Response response = RestAssured.get(url);
        response.prettyPrint();


    }
    @Test
    public void test_3_deleteId(){
        String url = "https://petstore.swagger.io/v2/pet/"+ id;
        Response response = RestAssured.delete(url);
        response.prettyPrint();
    }
}
