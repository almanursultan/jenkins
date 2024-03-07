package steps;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pojo.RequestBody;
import utilities.APIRunner;


public class Seller_3_HW {
    Faker faker = new Faker();
    static String path = "sellers";
    static int seller_id;
    static String seller_name;
    static String phone_number;
    static String address;

    @Given("All necessary variables are initialized {string}")
    public void allNecessaryVariablesAreInitialized(String path) {

        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name( faker.company().name() );
        requestBody.setSeller_name( faker.name().fullName() );
        requestBody.setEmail( faker.internet().emailAddress() );
        requestBody.setPhone_number( faker.phoneNumber().cellPhone() );
        requestBody.setAddress( faker.address().fullAddress() );

        APIRunner.runPOST(path,requestBody);


    }




    @Then("A new seller is created with name, email, address, and phone number")
    public void a_new_seller_is_created_with_name_email_address_and_phone_number() {



    }
    @Then("Get the seller ID, and validate it")
    public void get_the_seller_id_and_validate_it() {

    }



}
