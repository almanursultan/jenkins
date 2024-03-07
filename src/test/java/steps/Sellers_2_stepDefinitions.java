package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utilities.APIRunner;

import java.util.HashMap;
import java.util.Map;

public class Sellers_2_stepDefinitions {
    @Given("a path {string}")
    public void a_path(String path) {
        System.out.println(path);
        APIRunner.runGET(path);

    }
    @When("get their names, print and validate they are not Null")
    public void get_their_names_print_and_validate_they_are_not_null() {
        int sizeOfArray = APIRunner.getCustomResponseArray().length ;

        for (int i=0; i < sizeOfArray; i++){
            System.out.println("seller name "+APIRunner.getCustomResponseArray()[i].getSeller_name());
            Assert.assertNotNull(APIRunner.getCustomResponseArray()[i].getSeller_name());
        }

    }
}
