package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // it will ignore all unknown key(variable)
public class CustomResponse { // ==  ResponseBody

    /**
     * CustomResponse==> we use this class to read value of our Response
     */

    // Category-controller
    private CustomResponse[] category_response;

    private int category_id;
    private String category_title;
    private String category_description;

    // Bank account RESPONSE body variables
    private String id;
    private String bank_account_name;
    private double balance;


    // Sellers RESPONSE body variables
    private CustomResponse[] responses;
    private int  seller_id;
    private String seller_name;
    private String email;
    private String address;
    private String phone_number;


    /**
     * "product_id": 719,
     *     "product_title": "Apple magic mouse",
     *     "product_price": 350.0,
     */
    // Product
    private int product_id;
    private String product_title;
    private double product_price;
    private String product_description;

    // Payment controller
    private CustomResponse response;
    private String invoice_title;
    private double total_pay;



    /**
     * {
     *     "seller_id": 3688,
     *     "company_name": "Apple1 inc",
     *     "seller_name": "Steve",
     *     "seller_surname": null,
     *     "email": "jayszonqd46@gmail.com",
     *     "phone_number": "1233454567",
     *     "address": "Monaco",
     *     "created": "2024-02-02",
     *     "income": false,
     *     "number_of_invoices": 0
     * }
     */













}