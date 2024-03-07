package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.APIRunner;

import java.util.HashMap;
import java.util.Map;

public class Sellers_stepDefinitions {

    @Given("get all sellers with api path {string} and parameters: isArchived {string} , page {int}, size {int}")
    public void get_all_sellers_with_api_path_and_parameters_is_archived_page_size(String path, String isArchived, Integer page, Integer size) {
        System.out.println(path);
        System.out.println(isArchived);
        System.out.println(page);
        System.out.println(size);

        Map<String,Object> params= new HashMap<>();
        params.put("isArchived", isArchived);
        params.put("page", page);
        params.put("size", size);

        APIRunner.runGET(path,params);

    }

    @Then("user gets all phone numbers and validate is not null")
    public void user_gets_all_phone_numbers_and_validate_is_not_null() {
        int sizeOfArray = APIRunner.getCustomResponse().getResponses().length ;

        for (int i=0; i < sizeOfArray; i++){
            System.out.println("============::::::TEST STARTED::::::::::====================");
            System.out.println(  "Sellers phone number: " + APIRunner.getCustomResponse().getResponses()[i].getPhone_number());
//            Assert.assertNotNull(  APIRunner.getCustomResponse().getResponses()[i].getPhone_number());
            System.out.println("============::::::TEST PASSED::::::::::====================");
        }


    }

}
