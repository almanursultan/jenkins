package pojo;

import lombok.Data;

@Data //automatically will generate getter and setter method

public class RequestBody {
    //cashwise authorization
    private String email;
    private String password;
    /**
     * Category controller
     *
     * @return
     */


    private String category_title;
    private String category_description;
    private boolean flag;


    //bank account request body variables
    private String type_of_pay;
    private String bank_account_name;
    private String description;
    private double balance;


    //seller controller request body variables
    private String company_name;
    private String seller_name;
    //    private String email;
    private String phone_number;
    private String address;

    //product controller
    private String product_title;
    private double product_price;
    private int service_type_id;
    private int category_id;
    private String product_description;



}

    /*
    public String getCategory_title() {
        return category_title;
    }

    public void setCategory_title(String category_title) {
        this.category_title = category_title;
    }

    public String getCategory_description() {
        return category_description;
    }

    public void setCategory_description(String category_description) {
        this.category_description = category_description;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

     */


