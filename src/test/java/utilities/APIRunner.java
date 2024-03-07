package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Getter;
import pojo.CustomResponse;
import pojo.RequestBody;

import java.util.Map;

import static utilities.CashwiseAuthorization.getToken;

public class APIRunner {

    @Getter
    private static CustomResponse customResponse;
    @Getter
    private static CustomResponse[] customResponseArray;


    @Getter
    private static int statusCode;


    /** Day_5 APIRunner (Description about this class)
     * APIRunner class contains custom methods for CRUD commands
     * With help of this class we can focus on test logic, instead of automation
     * script
     */

    public static CustomResponse runGET(String path, Map<String, Object> params){
        // step - 1
        String  url =Config.getProperty("baseUrl") + path;
        // step - 2
        Response response = RestAssured.given()
                .auth().oauth2(   getToken()    )
                .contentType(ContentType.JSON)
                .params(params)
                .get( url );
        statusCode = response.statusCode();

        // step - 3
        ObjectMapper mapper = new ObjectMapper();

        // step -4
        try {
            customResponse = mapper.readValue(response.asString(), CustomResponse.class ) ;

        } catch (JsonProcessingException e) {
            // It's nested try-catch; Because we have to handle Array of ==> customResponseArray
            System.out.println( " This is a list response ");
            try {
                customResponseArray = mapper.readValue( response.asString(), CustomResponse[].class );
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }

        }
        System.out.println();
        return customResponse;
    }

    public static CustomResponse runGET( String path  ){
        // step - 1
        String  url =Config.getProperty("baseUrl") + path;
        // step - 2
        Response response = RestAssured.given()
                .auth().oauth2(   getToken()    )
                .get( url );
        statusCode = response.statusCode();

        // step - 3
        ObjectMapper mapper = new ObjectMapper();

        // step -4
        try {
            customResponse = mapper.readValue(response.asString(), CustomResponse.class ) ;

        } catch (JsonProcessingException e) {
            // It's nested try-catch; Because we have to handle Array of ==> customResponseArray
            System.out.println( " This is a list response ");
            try {
                customResponseArray = mapper.readValue( response.asString(), CustomResponse[].class );
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }

        }
        System.out.println();
        return customResponse;
    }
    public static CustomResponse runPOST(String path , RequestBody requestBody){
        // step - 1
        String  url =Config.getProperty("baseUrl") + path;
        // step - 2
        Response response = RestAssured.given()
                .auth().oauth2(   getToken()    )
                .contentType(ContentType.JSON)
                .body( requestBody )
                .post( url );
        statusCode = response.statusCode();


        // step - 3
        ObjectMapper mapper = new ObjectMapper();
        // step -4
        try {
            customResponse = mapper.readValue(response.asString(), CustomResponse.class ) ;
        } catch (JsonProcessingException e) {
            // It's nested try-catch; Because we have to handle Array of ==> customResponseArray
            System.out.println( " This is a list response ");
            try {
                customResponseArray = mapper.readValue( response.asString(), CustomResponse[].class );
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }

        }
        return customResponse;
    }
    public static CustomResponse runDELETE(String path){
        // step - 1
        String  url =Config.getProperty("baseUrl") + path;
        // step - 2
        Response response = RestAssured.given()
                .auth().oauth2(   getToken()    )
                .delete( url );
        statusCode = response.statusCode();


        // step - 3
        ObjectMapper mapper = new ObjectMapper();
        // step -4
        try {
            customResponse = mapper.readValue(response.asString(), CustomResponse.class ) ;
        } catch (JsonProcessingException e) {
            // It's nested try-catch; Because we have to handle Array of ==> customResponseArray
            System.out.println( " This is a list response ");
            try {
                customResponseArray = mapper.readValue( response.asString(), CustomResponse[].class );
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }

        }
        return customResponse;
    }





    // I can read value of my private variable with help of getter method
//    public static CustomResponse getCustomResponse(){
//        return customResponse;
//    }
//
//    public static CustomResponse[] getCustomResponseArray(){
//        return customResponseArray;
//    }




}