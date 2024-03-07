package steps;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pojo.RequestBody;
import utilities.APIRunner;

public class ProductController_stepDefinitions {
    RequestBody requestBody = new RequestBody();
    Faker faker = new Faker();

    // declare your variable in class level
    static int product_id;
    static String product_title = "";
    static double product_price;
    static String product_description = "";

    static String apiPath = "";


    /**
     * {
     * "product_title": "Toyota1",
     * "product_price": 100000,
     * "service_type_id": 2,
     * "category_id": 805,
     * "product_description": "Car"
     * }
     */

    @Given("use RequestBody and create new product {string}")
    public void useRequestBodyAndCreateNewProduct(String path) {
        // Expected data
        product_title = faker.commerce().productName();
        product_price = faker.number().randomDouble(2, 2, 51);
        product_description = faker.commerce().department();


        requestBody.setProduct_title(product_title);
        requestBody.setProduct_price(product_price);
        requestBody.setService_type_id(2);
        requestBody.setCategory_id(805);
        requestBody.setProduct_description(product_description);

        APIRunner.runPOST(path, requestBody);

        product_id = APIRunner.getCustomResponse().getProduct_id();

        System.out.println(product_id);

        apiPath = path + "/" + product_id;

        System.out.println("=======New Product Created====================");
        System.out.println("=======TEST PASSED!====================");


    }

    @When("get same product by id and validate data")
    public void getSameProductByIdAndValidateData() {
        APIRunner.runGET(apiPath);
        String actualProductTitle = APIRunner.getCustomResponse().getProduct_title();
        double actualProductPrice = APIRunner.getCustomResponse().getProduct_price();
        String actualProductDescription = APIRunner.getCustomResponse().getProduct_description();


        System.out.println("=============TEST STARTED===================");
        Assert.assertEquals("Product Title is NOT correct", product_title, actualProductTitle);
        Assert.assertEquals("Product Price is NOT correct", product_price + "", actualProductPrice + "");
        Assert.assertEquals("Product Description is NOT correct", product_description, actualProductDescription);

        System.out.println("=============TEST PASSED===================");


    }


    @Then("delete same product by id")
    public void delete_same_product_by_id() {
        APIRunner.runDELETE(apiPath);
        System.out.println("============delete========");


    }


}


