package day_5;

import org.junit.Test;
import utilities.APIRunner;

public class BankAccountWithApiRunner {
//1289

    @Test
    public void test_1_getSingleBankAccount(){
        String path = "/api/myaccount/bankaccount/1289";
        APIRunner.runGET(path);
        String bankAccountName= APIRunner.getCustomResponse().getBank_account_name();
        System.out.println("my bank account name: "+bankAccountName);

    }
}
