package step_defs;

import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domains.Transaction;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

public class TransactionsSteps extends BaseDigitalBankSteps {

    ObjectMapper mapper = new ObjectMapper();

    @When("^I send the following new transaction payload with the checking account id '(\\d+)'$")
    public void i_send_the_following_new_transaction_payload_with_the_checking_account_id(int checkingAccountId, List<Transaction> transactionsList) throws Throwable {
        String json = mapper.writeValueAsString(transactionsList.get(0));
        System.out.println(json);


        String url = "http://3.129.60.236:8080/bank/api/v1/account/" + checkingAccountId + "/transaction";
        response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + the_admin_user_is_authenticated())
                .body(json)
                .baseUri(url)
                .when()
                .post();

    }

    @Then("^the following should be the transaction response payload$")
    public void the_following_should_be_the_transaction_response_payload(DataTable arg1) throws Throwable {
        System.out.println(response.getBody().asPrettyString());

    }

    @Then("^the following should be the transaction type response payload$")
    public void the_following_should_be_the_transaction_type_response_payload(DataTable arg1) throws Throwable {

    }

    @Then("^the following should be the transaction state response payload$")
    public void the_following_should_be_the_transaction_state_response_payload(DataTable arg1) throws Throwable {

    }

    @Then("^the following should be the transaction category response payload$")
    public void the_following_should_be_the_transaction_category_response_payload(DataTable arg1) throws Throwable {

    }
}
